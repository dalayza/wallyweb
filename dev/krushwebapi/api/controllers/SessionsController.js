'use strict';


var mongoose = require('mongoose'),
  Session = mongoose.model('Sessions'),
  Client = mongoose.model('Clients');

exports.list_all_sessions = function(req, res) {
  Session.find({}, function(err, session) {
    if (err)
      res.send(err);
    res.json(session);
  });
};

exports.login = function(req, res) {

  Client.findById({_id:req.body.client_id}, function(err, client) {
    if (err)
      res.send(err);

    if (client.passwd == req.body.passwd) {

      var new_session = new Session({"client_id":req.body.client_id});
    
      new_session.save(function(err, session) {
        if (err)
          res.send(err);
        res.json(session);
      });
    } else
      res.json({ message: 'Please check client_id and password...' });
  });
};

exports.logout = function(req, res) {

  Session.remove({
    _id: req.params.sessionId
  }, function(err, session) {
    if (err)
      res.send(err);
    res.json({ message: 'Session successfully logout' });
  });
};

