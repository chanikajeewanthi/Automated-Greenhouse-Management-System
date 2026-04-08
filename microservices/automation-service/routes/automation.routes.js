const express = require("express");
const router = express.Router();

const controller = require("../controllers/automation.controller");

// process sensor data
router.post("/process", controller.processData);

// get logs
router.get("/logs", controller.getLogs);

module.exports = router;