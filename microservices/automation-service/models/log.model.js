class Log {
    constructor(zoneId, temperature, humidity, action, timestamp) {
        this.zoneId = zoneId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.action = action;
        this.timestamp = timestamp;
    }
}

module.exports = Log;