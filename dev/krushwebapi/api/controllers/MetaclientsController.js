'use strict';

var mongoose = require('mongoose'),
  Metaclient = mongoose.model('Metaclients');

exports.list_all_metaclients = function(req, res) {
  Metaclient.find({}, function(err, metaclient) {
    if (err)
      res.send(err);
    res.json(metaclient);
  });
};

exports.create_a_metaclient = function(req, res) {
  var new_metaclient = new Metaclient(req.body);
  new_metaclient.save(function(err, metaclient) {
    if (err)
      res.send(err);
    res.json(metaclient);
  });
};

exports.read_a_metaclient = function(req, res) {
  Metaclient.findById(req.params.metaclientId, function(err, metaclient) {
    if (err)
      res.send(err);
    res.json(metaclient);
  });
};

exports.update_a_metaclient = function(req, res) {
  Metaclient.findOneAndUpdate({_id: req.params.metaclientId}, req.body, {new: true}, function(err, metaclient) {
    if (err)
      res.send(err);
    res.json(metaclient);
  });
};

exports.delete_a_metaclient = function(req, res) {

  Metaclient.remove({
    _id: req.params.metaclientId
  }, function(err, metaclient) {
    if (err)
      res.send(err);
    res.json({ message: 'Metaclient successfully deleted' });
  });
};
