'use strict';

var mongoose = require('mongoose'),
  Event = mongoose.model('Events');

exports.list_all_events = function(req, res,next) {
 
  if (req.param('ownerUserId') !== undefined) {

    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({owner_user_id:req.param('ownerUserId')},null, {sort: '-start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
    else 
      Event.find({owner_user_id:req.param('ownerUserId')},null, {sort: 'start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
  } else if (req.param('dealId') !== undefined) {
    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({deal_id:req.param('dealId')},null, {sort: '-start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
    else 
      Event.find({deal_id:req.param('dealId')},null, {sort: 'start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
  } else if (req.param('clientId') !== undefined) {
    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({client_id:req.param('clientId')},null, {sort: '-start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
    else 
      Event.find({client_id:req.param('clientId')},null, {sort: 'start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
  } else if (req.param('eventType') !== undefined) {
    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({event_type:req.param('eventType')},null, {sort: '-start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
    else 
      Event.find({event_type:req.param('eventType')},null, {sort: 'start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
  } else if (req.param('startDate') !== undefined) {
    Event.find({start_date:{$gt:new Date(req.param('startDate')).getTime(),$lt:new Date(req.param('endDate')).getTime()}},function(err, deals) {
      if (err)
          return next(err);
      
      var max = req.param('max');
      if (max !== undefined) {
        res.json(deals.slice(0,max));
      } else
        res.json(deals);
    });
  } else if (req.param('status') !== undefined) {

    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({'status':req.param('status')},null, {sort: '-start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
    else 
      Event.find({'status':req.param('status')},null, {sort: 'start_date'},function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.json(sorted.slice(0,max));
        } else
          res.json(sorted);
      });
  } else {

    // A full list of ALL... MUST BE ADMIN role...
    //if (req.user.role === 'admin') {
      Event.find({}, function(err, events) {
        if (err)
            return next(err);
        var max = req.param('max');
        if (max !== undefined) {
          res.json(events.slice(0,max));
        } else
          res.json(events);
      });
//    } else return next(new Error('Unauthorized'));
  }
};

exports.create_a_event = function(req, res,next) {
  var new_event = new Event(req.body);
  new_event.save(function(err, aevent) {
    if (err)
          return next(err);
    res.json(aevent);
  });
};

exports.create_first_call_event = function(deal) {

  // TODO : make this code acceptable...

  if (deal.owner_user_id !== undefined) {

    var new_event = new Event({"title":"FIRST CALL",
                               "event_type":"call",
                               "owner_user_id":deal.owner_user_id,
                               "client_id":deal.client_id,
                               "deal_id":deal.deal_id});

    new_event.save(function(err, aevent) {
      if (err)
        console.log("error on creating a first call event..." + err.toString());
    });
  } else {

    var new_event = new Event({"title":"FIRST CALL",
                               "event_type":"call",
                               "client_id":deal.client_id,
                               "deal_id":deal.deal_id});

    new_event.save(function(err, aevent) {
      if (err)
        console.log("error on creating a first call event..." + err.toString());
    });

  }
};


exports.read_a_event = function(req, res,next) {
  Event.findById(req.params.eventId, function(err, aevent) {
    if (err)
          return next(err);
    
    res.json(aevent);
  });
};

exports.update_a_event = function(req, res,next) {
  Event.findOneAndUpdate({_id: req.params.eventId}, req.body, {new: true}, function(err, aevent) {
    if (err)
          return next(err);
    res.json(aevent);
  });
};

exports.delete_a_event = function(req, res,next) {

  Event.remove({
    _id: req.params.eventId
  }, function(err, aevent) {
    if (err)
          return next(err);
    res.json({ message: 'Event successfully deleted' });
  });
};
