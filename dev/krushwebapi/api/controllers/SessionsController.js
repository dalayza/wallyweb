'use strict';

var mongoose = require('mongoose'),
  Session = mongoose.model('Sessions'),
  User = mongoose.model('Users');

exports.list_all_sessions = function(req, res) {
  Session.find({}, function(err, session) {
    if (err)
      res.send(err);
    res.status(200).json({data:session});
  });
};

exports.login = function(req, res) {

  User.findOne({email:req.body.email}, function(err, user) {
    if (err)
      res.send(err);

    if (user.passwd.toString() == req.body.passwd) {

      var new_session = new Session({"email":user.email});
    
      new_session.save(function(err, session) {
        if (err)
          res.send(err);
        res.status(200).json({data:session});
      });
    } else
      res.status(200).json({ message: 'Please check email and password...' });
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
      res.status(200).json({ message: 'Session successfully logout' });
    });
  });
};

