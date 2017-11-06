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

  Client.findOne({email:req.body.email}, function(err, client) {
    if (err)
      res.send(err);

    if (client.passwd.toString() == req.body.passwd) {

      var new_session = new Session({"email":client.email});
    
      new_session.save(function(err, session) {
        if (err)
          res.send(err);
        res.json(session);
      });
    } else
      res.json({ message: 'Please check email and password...' });
  });
};

exports.logout = function(req, res) {

  Session.findOne({email:req.body.email}, function(err, session) {
    if (err)
      res.send(err);

    Session.remove({
      _id: session._id
    }, function(err, session) {
      if (err)
        res.send(err);
      res.json({ message: 'Session successfully logout' });
    });
  });
};

