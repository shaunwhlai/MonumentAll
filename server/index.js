const express = require('express')
const app = express()
const mongoose = require('mongoose')

const port = 3000
const database_url = 'mongodb://localhost/MonumentAll'

mongoose.connect(database_url, {useNewUrlParser: true, useUnifiedTopology: true})
const db = mongoose.connection
db.on('error', (error) => console.error(error))
db.once('open', () => console.log('connected to database'))

app.use(express.json())
const testRouter = require('./routes/routes')
app.use('/tests', testRouter)

app.listen(port, () => console.log('server started on port 3000'))
