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
mongoose.connect('mongodb://admin:admin123@ds253918.mlab.com:53918/wallytmpdb');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// the user authentication
var AuthController = require('./auth/AuthController');
app.use('/auth', AuthController);

// the user authorization
var auth = require('authorized');
auth.role('admin', function(req, done) {
  done(null, req.user.role === "admin");
});
auth.action('list deals', ['admin']);
var middleware = auth.can('list deals');
app.use(function(err, req, res, next) {
  if (err instanceof auth.UnauthorizedError) {
    res.send(401, 'Unauthorized');
  } else {
    next(err);
  }
});

var users_routes = require('./api/routes/UsersRoutes');
var metaclients_routes = require('./api/routes/MetaclientsRoutes');
var events_routes = require('./api/routes/EventsRoutes');
var deals_routes = require('./api/routes/DealsRoutes');
var clients_routes = require('./api/routes/ClientsRoutes');
var sessions_routes = require('./api/routes/SessionsRoutes');
deals_routes(app);
clients_routes(app); 
users_routes(app,auth); 
events_routes(app); 
metaclients_routes(app); 
sessions_routes(app); 

app.listen(port);

console.log('Krushweb RESTful API server started on: ' + port);

app.use(function(req, res) {
  res.status(404).send({url: req.originalUrl + ' not found'})
});
