from flask import Flask, jsonify
import requests
from apscheduler.schedulers.background import BackgroundScheduler
import py_eureka_client.eureka_client as eureka_client
import atexit

app = Flask(__name__)

# 🟢 1. EUREKA REGISTER
eureka_client.init(
    eureka_server="http://localhost:8761/eureka",
    app_name="SENSOR-SERVICE",
    instance_port=8082,
    instance_host="localhost",
    instance_ip="127.0.0.1"
)

# 🟢 CONFIG
BASE_URL = "http://104.211.95.241:8080/api"
USERNAME = "testuser"
PASSWORD = "123456"

access_token = None
latest_telemetry = []

#  AUTHENTICATION
def login():
    global access_token

    try:
        response = requests.post(
            f"{BASE_URL}/auth/login",
            json={"username": USERNAME, "password": PASSWORD}
        )

        if response.status_code == 200:
            access_token = response.json().get("accessToken")
            print("✅ Logged in to External IoT API")
        else:
            print("❌ Login failed")

    except Exception as e:
        print("❌ Login error:", str(e))


#  FETCH + PUSH DATA
def fetch_and_send_data():
    global access_token, latest_telemetry

    try:
        # login if no token
        if not access_token:
            login()
            if not access_token:
                return

        headers = {"Authorization": f"Bearer {access_token}"}

        #  GET DEVICES
        devices_res = requests.get(f"{BASE_URL}/devices", headers=headers)

        #  token expired
        if devices_res.status_code == 401:
            print(" Token expired, re-login...")
            login()
            headers = {"Authorization": f"Bearer {access_token}"}
            devices_res = requests.get(f"{BASE_URL}/devices", headers=headers)

        devices = devices_res.json()

        if not devices:
            print("⚠️ No devices found")
            return

        latest_telemetry = []

        #  LOOP ALL DEVICES (IMPORTANT)
        for device in devices:
            device_id = device.get("deviceId")

            telemetry_res = requests.get(
                f"{BASE_URL}/devices/telemetry/{device_id}",
                headers=headers
            )

            if telemetry_res.status_code == 200:
                data = telemetry_res.json()

                temp = data.get("value", {}).get("temperature")
                humidity = data.get("value", {}).get("humidity")

                print(f"Device: {device_id} | Temp: {temp}°C | Humidity: {humidity}%")

                latest_telemetry.append(data)

                #  SEND TO AUTOMATION SERVICE
                requests.post(
                    "http://localhost:8083/api/automation/process",
                    json=data
                )

            else:
                print(f"❌ Failed to fetch data for device {device_id}")

    except Exception as e:
        print("❌ Error:", str(e))


#  SCHEDULER (EVERY 10 SEC)
scheduler = BackgroundScheduler()
scheduler.add_job(fetch_and_send_data, trigger="interval", seconds=10)
scheduler.start()

# shutdown properly
atexit.register(lambda: scheduler.shutdown())


#  TEST ENDPOINT
@app.route("/api/sensors/latest", methods=["GET"])
def get_latest():
    if not latest_telemetry:
        return jsonify({"message": "No data yet"}), 404
    return jsonify(latest_telemetry), 200


#  RUN APP
if __name__ == "__main__":
    print(" Sensor Service Running on port 8082")
    app.run(host="0.0.0.0", port=8082)