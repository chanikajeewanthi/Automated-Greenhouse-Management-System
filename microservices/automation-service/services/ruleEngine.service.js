exports.evaluate = (data) => {

    const temp = data?.value?.temperature;
    const humidity = data?.value?.humidity;

    let action = "STABLE";

    // 🔥 RULES
    if (temp > 32) {
        action = "TURN_FAN_ON";
    }

    if (temp < 20) {
        action = "TURN_HEATER_ON";
    }

    if (humidity > 80) {
        action = "TURN_DEHUMIDIFIER_ON";
    }

    return {
        action
    };
};