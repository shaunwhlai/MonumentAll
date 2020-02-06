const express = require('express')
const router = express.Router()
const Tester = require('../models/test')

// Get all entries
router.get('/', async (req, res) => {
    try {
        const tests = await Tester.find()
        res.json(tests)
    } catch (error) {
        res.status(500).json({message: error.message})
    }
})

// Create one entry
router.post('/', async (req, res) => {
    const entry = new Tester({
        title: req.body.title,
        latitude: req.body.latitude,
        longitude: req.body.longitude
    })

    try {
        const newTester = await entry.save()
        res.status(201).json(newTester)
    } catch (error) {
        res.status(400).json({message: error.message})
    }
})

// Get one entry
router.get('/:id', getEntry, (req, res) => {
    res.json(res.entry)
})

// Update one entry
router.patch('/:id', getEntry, async (req, res) => {
    if (req.body.title != null) {
        res.entry.title = req.body.title
    }

    if (req.body.latitude != null) {
        res.entry.latitude = req.body.latitude
    }

    if (req.body.longitude != null) {
        res.entry.longitude = req.body.longitude
    }

    try {
        const updatedEntry = await res.entry.save()
        res.json(updatedEntry)
    } catch (error) {
        res.status(400).json({message: error.message})
    }
})

// Delete one entry
router.delete('/:id', getEntry, async (req, res) => {
    try {
        await res.entry.remove()
        res.json({message: 'Deleted this entry'})
    } catch (error) {
        res.status(500).json({message: error.message})
    }
})

async function getEntry(req, res, next) {
    try {
        entry = await Tester.findById(req.params.id)
        if (entry == null) {
            return res.status(404).json({message: 'Could not find entry'})
        }
    } catch (error) {
        return res.status(500).json({message: error.message})
    }

    res.entry = entry
    next()
}

module.exports = router