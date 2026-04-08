const ruleEngine = require("../services/ruleEngine.service");

let logs = [];

// 🔥 process incoming sensor data
exports.processData = (req, res) => {
    const data = req.body;

    const result = ruleEngine.evaluate(data);

    const log = {
        zoneId: data.zoneId,
        temperature: data?.value?.temperature,
        humidity: data?.value?.humidity,
        action: result.action,
        timestamp: new Date()
    };

    logs.push(log);

    console.log("🔥 Automation Triggered:", log);

    res.json(log);
};

// 🔥 get logs
exports.getLogs = (req, res) => {
    res.json(logs);
};