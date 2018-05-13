'use strict';

var mongoose = require('mongoose'),
  Session = mongoose.model('Sessions'),
  User = mongoose.model('Users');

var jwt = require('jsonwebtoken');
var bcrypt = require('bcryptjs');
var config = require('../../config');

exports.list_all_sessions = function(req, res) {
  Session.find({}, function(err, session) {
    if (err)
      res.send(err);
    res.status(200).json({data:session});
  });
};

exports.login = function(req, res) {

  User.findOne({'email': req.body.email},function(err, user) {

    if (user === null)
      res.status(501).send({ auth:false, error_msg : 'no user match for the email...' });

    if (bcrypt.compareSync(req.body.passwd,user.passwd) == true) {

      var new_session = new Session({"email":user.email});
      new_session.save(function(err, session) {

        if (err)
          res.send(err);

        var token = jwt.sign({ id: user._id }, config.secret, {
          //expiresIn: 86400 // expires in 24 hours
          expiresIn: 432000 // expires in 5 days. TODO : refresh tokens
        });
        res.status(200).send({ auth: true, token: token });
      });

    } else  
      res.send('password does not match...');
  });
};

exports.logout = function(req, res) {

  Session.findOne({'email':req.body.email}, function(err, session) {
    if (err) 
      res.send(err);

    Session.update({ _id: session._id }, { $set: { status: 'inactive' }}, function(err, session) {
      if (err)
        res.send(err);
      res.status(200).json({ message: 'Session successfully logout' });
    });
  });
};

