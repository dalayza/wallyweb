'use strict';

var mongoose = require('mongoose'),
  Metaclient = mongoose.model('MetaclientOrganizations');

exports.list_all_metaclientorgs = function(req, res,next) {
  Metaclient.find({}, function(err, metaclientorg) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclientorg});
  });
};

exports.create_a_metaclientorg = function(req, res,next) {
  var new_metaclientorg = new Metaclient(req.body);
  new_metaclientorg.save(function(err, metaclientorg) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclientorg});
  });
};

exports.read_a_metaclientorg = function(req, res,next) {
  Metaclient.findById(req.params.metaclientorgId, function(err, metaclientorg) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclientorg});
  });
};

exports.update_a_metaclientorg = function(req, res,next) {
  Metaclient.findOneAndUpdate({_id: req.params.metaclientorgId}, req.body, {new: true}, function(err, metaclientorg) {
    if (err)
      return next(err);
    res.status(200).json({data:metaclientorg});
  });
};

exports.delete_a_metaclientorg = function(req, res,next) {

  Metaclient.remove({
    _id: req.params.metaclientorgId
  }, function(err, metaclientorg) {
    if (err)
      return next(err);
    res.status(200).json({ message: 'Metaclient successfully deleted' });
  });
};
