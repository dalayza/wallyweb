'use strict';

var mongoose = require('mongoose'),
  Client = mongoose.model('Clients');

exports.list_all_clients = function(req, res) {
  Client.find({}, function(err, client) {
    if (err)
      res.send(err);
    res.json(client);
  });
};

/*
 * if client has branches , the pattern shall be :
 *  
 * name - branch
 *
 */
exports.create_a_client = function(req, res) {

  // loops over the branches so that it should be a new Client...
  var branches = req.body.branches;
  if (branches != null) {
  
    branches.forEach(function(value) {

      // branch new clients will hold only a name for now...
      var new_client_branch = new Client({"name":req.body.name + "-" + value.name});
 
      new_client_branch.save(function(err, client) {
       if (err)
         res.send(err);
      });
    }); 
  } 

  // the main branch...
  var new_client = new Client(req.body);
  
  new_client.save(function(err, client) {
     if (err)
       res.send(err);
   
     res.json(client);
  });
};

exports.read_a_client = function(req, res) {
  Client.findById(req.params.clientId, function(err, client) {
    if (err)
      res.send(err);
    res.json(client);
  });
};

exports.update_a_client = function(req, res) {
  Client.findOneAndUpdate({_id: req.params.clientId}, req.body, {new: true}, function(err, client) {
    if (err)
      res.send(err);
    res.json(client);
  });
};

exports.delete_a_client = function(req, res) {

  Client.remove({
    _id: req.params.clientId
  }, function(err, client) {
    if (err)
      res.send(err);
    res.json({ message: 'Client successfully deleted' });
  });
};
