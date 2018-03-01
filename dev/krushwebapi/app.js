var express = require('express'),
  app = express(),
  port = process.env.PORT || 8080,
  mongoose = require('mongoose'),
  morgan = require('morgan'),
  Deal = require('./api/models/KrushwebModel'),
  Client = require('./api/models/KrushwebModel'),
  Session = require('./api/models/KrushwebModel'),
  Event = require('./api/models/KrushwebModel'),
  Metaclient = require('./api/models/KrushwebModel'),
  User = require('./api/models/KrushwebModel'),
  bodyParser = require('body-parser');

//mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://admin:admin123@ds259865.mlab.com:59865/krushwebdb');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var users_routes = require('./api/routes/UsersRoutes');
var metaclients_routes = require('./api/routes/MetaclientsRoutes');
var events_routes = require('./api/routes/EventsRoutes');
var deals_routes = require('./api/routes/DealsRoutes');
var clients_routes = require('./api/routes/ClientsRoutes');
var sessions_routes = require('./api/routes/SessionsRoutes');
deals_routes(app);
clients_routes(app); 
users_routes(app); 
events_routes(app); 
metaclients_routes(app); 
sessions_routes(app); 

app.listen(port);

console.log('Krushweb RESTful API server started on: ' + port);

app.use(function(req, res) {
  res.status(404).send({url: req.originalUrl + ' not found'})
});
