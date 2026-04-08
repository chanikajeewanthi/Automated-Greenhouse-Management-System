const express = require("express");
const cors = require("cors");

const automationRoutes = require("./routes/automation.routes");

const app = express();

app.use(cors());
app.use(express.json());

// routes
app.use("/api/automation", automationRoutes);

app.listen(8083, () => {
    console.log(" Automation Service running on port 8083");
});