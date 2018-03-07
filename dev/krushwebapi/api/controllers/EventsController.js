'use strict';

var mongoose = require('mongoose'),
  Event = mongoose.model('Events');

exports.list_all_events = function(req, res) {

  if (req.param('ownerUserId') !== undefined) {
    Event.find({owner_user_id:req.param('ownerUserId')},function(err, aevent) {
      if (err)
        res.send(err);
      res.json(aevent);
    });
  } else if (req.param('dealId') !== undefined) {
    Event.find({deal_id:req.param('dealId')},function(err, aevent) {
      if (err)
        res.send(err);
      res.json(aevent);
    });
  } else if (req.param('clientId') !== undefined) {
    Event.find({client_id:req.param('clientId')},function(err, deals) {
      if (err)
        res.send(err);
      
      res.json(deals);
    });
  } else if (req.param('startDate') !== undefined) {
    Event.find({start_date:{$gt:new Date(req.param('startDate')).getTime(),$lt:new Date(req.param('endDate')).getTime()}},function(err, deals) {
      if (err)
        res.send(err);
      
      res.json(deals);
    });
  } else if (req.param('eventType') !== undefined) {
    Event.find({event_type:req.param('eventType')},function(err, aevent) {
      if (err)
        res.send(err);
      res.json(aevent);
    });
  } else {
    Event.find({}, function(err, aevent) {
      if (err)
        res.send(err);
      res.json(aevent);
    });
  }
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

  var new_event = new Event({"title":"FIRST CALL",
                             "event_type":"call",
                             "owner_user_id":deal.owner_user_id,
                             "client_id":deal.client_id,
                             "deal_id":deal.deal_id});

  new_event.save(function(err, aevent) {
    if (err)
      console.log("error on creating a first call event..." + err.toString());
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
