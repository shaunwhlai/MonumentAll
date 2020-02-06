const mongoose = require('mongoose')
const monumentSchema = new mongoose.Schema({
    title : {
        type: String,
        required: true
    },
    latitude: {
        type: Number,
        required: true
    },
    longitude: {
        type: Number,
        required: true
    }
})

module.exports = mongoose.model('Test', monumentSchema)