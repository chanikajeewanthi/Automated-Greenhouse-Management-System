const express = require("express");
const app = express();

app.use(express.json());

let crops = [];

// CREATE crop
app.post("/api/crops", (req, res) => {
    const crop = {
        id: Date.now(),
        name: req.body.name,
        status: "SEEDLING"
    };

    crops.push(crop);

    res.json(crop);
});

// GET all crops
app.get("/api/crops", (req, res) => {
    res.json(crops);
});

// UPDATE status
app.put("/api/crops/:id/status", (req, res) => {
    const crop = crops.find(c => c.id == req.params.id);

    if (!crop) {
        return res.status(404).send("Crop not found");
    }

    crop.status = req.body.status;

    res.json(crop);
});

app.listen(8084, () => {
    console.log("🌱 Crop Service running on port 8084");
});