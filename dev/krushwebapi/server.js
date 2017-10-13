var express = require('express'),
  app = express(),
  port = process.env.PORT || 3000,
  mongoose = require('mongoose'),
  Lead = require('./api/models/KrushwebModel'), 
  bodyParser = require('body-parser');
  
// mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/krushwebdb'); 


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


var leads_routes = require('./api/routes/LeadsRoutes'); 
var clients_routes = require('./api/routes/ClientsRoutes'); 
leads_routes(app); 
//clients_routes(app); 


app.listen(port);


console.log('Krushweb RESTful API server started on: ' + port);

app.use(function(req, res) {
  res.status(404).send({url: req.originalUrl + ' not found'})
});