'use strict';

var mongoose = require('mongoose'),
  Metaclient = mongoose.model('Metaclients');

exports.list_all_metaclients = function(req, res,next) {
  Metaclient.find({}, function(err, metaclient) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclient});
  });
};

exports.create_a_metaclient = function(req, res,next) {
  var new_metaclient = new Metaclient(req.body);
  new_metaclient.save(function(err, metaclient) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclient});
  });
};

exports.read_a_metaclient = function(req, res,next) {
  Metaclient.findById(req.params.metaclientId, function(err, metaclient) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclient});
  });
};

exports.update_a_metaclient = function(req, res,next) {
  Metaclient.findOneAndUpdate({_id: req.params.metaclientId}, req.body, {new: true}, function(err, metaclient) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclient});
  });
};

exports.delete_a_metaclient = function(req, res,next) {

  Metaclient.remove({
    _id: req.params.metaclientId
  }, function(err, metaclient) {
    if (err)
      return next(err);
    res.status(200).json({ message: 'Metaclient successfully deleted' });
  });
};
