var express = require('express'),
  app = express(),
  port = process.env.PORT || 8080,
  mongoose = require('mongoose'),
  Deal = require('./api/models/KrushwebModel'),
  Client = require('./api/models/KrushwebModel'),
  Session = require('./api/models/KrushwebModel'),
  Event = require('./api/models/KrushwebModel'),
  Metaclient = require('./api/models/KrushwebModel'),
  User = require('./api/models/KrushwebModel'),
  bodyParser = require('body-parser');

// the logging
const level = process.env.LOG_LEVEL || 'info';
var expressWinston = require('express-winston');
var winston = require('winston');

//mongoose instance connection url connection
mongoose.Promise = global.Promise;
if (process.argv[2] === 'dev')
  mongoose.connect('mongodb://admin:admin123@ds253918.mlab.com:53918/wallytmpdb');
else
  mongoose.connect('mongodb://admin:admin123@ds259865.mlab.com:59865/krushwebdb');

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
auth.role('cs', function(req, done) {
  done(null, req.user.role === "cs");
});
auth.role('manager', function(req, done) {
  done(null, req.user.role === "manager");
});

auth.action('list all users', ['admin','manager']);
auth.action('create a user', ['admin','manager']);
auth.action('read a user', ['admin','manager','cs']);
auth.action('update a user', ['admin','manager']);
auth.action('delete a user', ['admin','manager']);

auth.action('list all clients', ['admin']);
auth.action('create a client', ['admin']);
auth.action('read a client', ['admin','manager']);
auth.action('update a client', ['admin']);
auth.action('delete a client', ['admin']);

auth.action('list all deals', ['admin','manager','cs']);
auth.action('create a deal', ['admin','manager','cs']);
auth.action('read a deal', ['admin','manager','cs']);
auth.action('update a deal', ['admin','manager','cs']);
auth.action('delete a deal', ['admin','manager']);

auth.action('list all events', ['admin','manager','cs']);
auth.action('create a event', ['admin','manager','cs']);
auth.action('read a event', ['admin','manager','cs']);
auth.action('update a event', ['admin','manager','cs']);
auth.action('delete a event', ['admin','manager','cs']);

auth.action('list all sessions', ['admin']);
auth.action('login', ['admin']);
auth.action('logout', ['admin']);


var middleware = auth.can('list all users');
app.use(function(err, req, res, next) {
  if (err instanceof auth.UnauthorizedError) {
    res.send(401, 'Unauthorized');
  } else {
    next(err);
  }
});

// Place the express-winston logger before the router.
app.use(expressWinston.logger({
  transports: [
    new winston.transports.Console({
      json: true,
      level: level,
      timestamp: function () {
         return (new Date()).toISOString();
      },
      colorize: true
    })
  ]
}));


// the routes
var users_routes = require('./api/routes/UsersRoutes');
var metaclients_routes = require('./api/routes/MetaclientsRoutes');
var metaclient_orgs_routes = require('./api/routes/MetaclientOrgsRoutes');
var events_routes = require('./api/routes/EventsRoutes');
var deals_routes = require('./api/routes/DealsRoutes');
var clients_routes = require('./api/routes/ClientsRoutes');
var sessions_routes = require('./api/routes/SessionsRoutes');
deals_routes(app,auth);
clients_routes(app,auth); 
users_routes(app,auth); 
events_routes(app,auth); 
metaclients_routes(app,auth); 
metaclient_orgs_routes(app,auth); 
sessions_routes(app,auth); 

// the docs
app.use('/doc', express.static('doc'));

// starts the server...
app.listen(port);

console.log('Krushweb RESTful API server started on: ' + port);

app.use(function(req, res) {
  res.status(404).send({url: req.originalUrl + ' not found'})
});

// Place the express-winston errorLogger after the router.
app.use(expressWinston.errorLogger({
  transports: [
    new winston.transports.Console({
      json: true,
      level: level,
      timestamp: function () {
         return (new Date()).toISOString();
      },
      colorize: true
    })
  ]
}));
