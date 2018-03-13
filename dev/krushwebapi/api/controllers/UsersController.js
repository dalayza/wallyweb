'use strict';

var mongoose = require('mongoose'),
  User = mongoose.model('Users');

var bcrypt = require('bcryptjs');


exports.list_all_users = function(req, res,next) {
  User.find({}, function(err, user) {
    if (err)
      return next(err);
    res.json(user);
  });
};

exports.create_a_user = function(req, res,next) {
  var new_user = new User(req.body);

  // basic encryption...
  var hashedPassword = bcrypt.hashSync(req.body.passwd, 8);
  new_user.passwd = hashedPassword;

  new_user.save(function(err, user) {
    if (err)
      return next(err);
    res.json(user);
  });
};

exports.read_a_user = function(req, res,next) {
  User.findById(req.params.userId, function(err, user) {
    if (err)
      return next(err);
    res.json(user);
  });
};

exports.update_a_user = function(req, res,next) {
  User.findOneAndUpdate({_id: req.params.userId}, req.body, {new: true}, function(err, user) {
    if (err)
      return next(err);
    res.json(user);
  });
};

exports.delete_a_user = function(req, res,next) {

  User.remove({
    _id: req.params.userId
  }, function(err, user) {
    if (err)
      return next(err);
    res.json({ message: 'User successfully deleted' });
  });
};
