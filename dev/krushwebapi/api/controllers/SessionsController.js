'use strict';


var mongoose = require('mongoose'),
  Session = mongoose.model('Sessions');

exports.list_all_sessions = function(req, res) {
  Session.find({}, function(err, session) {
    if (err)
      res.send(err);
    res.json(session);
  });
};

exports.login = function(req, res) {
  var new_session = new Session(req.body);
  new_session.save(function(err, session) {
    if (err)
      res.send(err);
    res.json(session);
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

