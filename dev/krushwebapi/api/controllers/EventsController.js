'use strict';

var mongoose = require('mongoose'),
  Event = mongoose.model('Events');

exports.list_all_events = function(req, res) {
  Event.find({}, function(err, aevent) {
    if (err)
      res.send(err);
    res.json(aevent);
  });
};

exports.create_a_event = function(req, res) {
  var new_event = new Event(req.body);
  new_event.save(function(err, aevent) {
    if (err)
      res.send(err);
    res.json(aevent);
  });
};

exports.create_first_call_event = function(deal) {

  var new_event = new Event({'title':'FIRST CALL',
                             'event_type':'call',
                             'owner':deal.owner,'deal_id':deal.deal_id});

  new_event.save(function(err, aevent) {
    if (err)
      console.out("error on creating a first call event...");
  });
};

exports.read_a_event = function(req, res) {
  Event.findById(req.params.eventId, function(err, aevent) {
    if (err)
      res.send(err);
    res.json(aevent);
  });
};

exports.update_a_event = function(req, res) {
  Event.findOneAndUpdate({_id: req.params.eventId}, req.body, {new: true}, function(err, aevent) {
    if (err)
      res.send(err);
    res.json(aevent);
  });
};

exports.delete_a_event = function(req, res) {

  Event.remove({
    _id: req.params.eventId
  }, function(err, aevent) {
    if (err)
      res.send(err);
    res.json({ message: 'Event successfully deleted' });
  });
};
