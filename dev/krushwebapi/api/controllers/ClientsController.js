'use strict';


var mongoose = require('mongoose'),
  Client = mongoose.model('Clients');

exports.list_all_clients = function(req, res,next) {

  // A full list of ALL WITH NO RESTRICTION... MUST BE ADMIN role...
  if (req.user.role === 'admin') {
    Client.find({}, function(err, client) {
      if (err)
        return next(err);
      res.status(200).json({data:client});
    });
  } else {
    Client.find({'owner_user_id':req.user.id}, function(err, client) {
      if (err)
        return next(err);
      res.status(200).json({data:client});
    });
  }
};

exports.create_a_client = function(req, res,next) {

  var new_client = new Client(req.body);
  
  new_client.save(function(err, client) {
     if (err)
      return next(err);
   
     res.status(200).json({data:client});
  });
};

exports.read_a_client = function(req, res,next) {
  Client.findById(req.params.clientId, function(err, client) {
    if (err)
      return next(err);
    res.status(200).json({data:client});
  });
};

exports.update_a_client = function(req, res,next) {
  Client.findOneAndUpdate({_id: req.params.clientId}, req.body, {new: true}, function(err, client) {
    if (err)
      return next(err);
    res.status(200).json({data:client});
  });
};

exports.delete_a_client = function(req, res,next) {

  Client.remove({
    _id: req.params.clientId
  }, function(err, client) {
    if (err)
      return next(err);
    res.status(200).json({ message: 'Client successfully deleted' });
  });
};
