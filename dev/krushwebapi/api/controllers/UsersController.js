'use strict';

var mongoose = require('mongoose'),
  User = mongoose.model('Users');

exports.list_all_users = function(req, res,next) {
  User.find({}, function(err, user) {
    if (err)
      return next(err);
    res.status(200).json({data:user});
  });
};

exports.create_a_user = function(req, res,next) {
  var new_user = new User(req.body);
  new_user.save(function(err, user) {
    if (err)
      return next(err);
    res.status(200).json({data:user});
  });
};

exports.read_a_user = function(req, res,next) {
  User.findById(req.params.userId, function(err, user) {
    if (err)
      return next(err);
    res.status(200).json({data:user});
  });
};

exports.update_a_user = function(req, res,next) {
  User.findOneAndUpdate({_id: req.params.userId}, req.body, {new: true}, function(err, user) {
    if (err)
      return next(err);
    res.status(200).json({data:user});
  });
};

exports.delete_a_user = function(req, res,next) {

  User.remove({
    _id: req.params.userId
  }, function(err, user) {
    if (err)
      return next(err);
    res.status(200).json({ message: 'User successfully deleted' });
  });
};
