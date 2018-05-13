'use strict';

var mongoose = require('mongoose'),
  Event = mongoose.model('Events');

exports.list_all_events = function(req, res,next) {

  var now = new Date();
  var perPage = 10
  var page = req.params.page || 1
 
  if (req.param('dealId') !== undefined) {

    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({deal_id:req.param('dealId'),start_date:{$lte:now}},null, {sort: '-start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
    else 
      Event.find({deal_id:req.param('dealId'),start_date:{$lte:now}},null, {sort: 'start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
  } else if (req.param('clientId') !== undefined) {

    var cronos = req.param('cronoOrder');
    var astatus = req.param('status');

    if (cronos !== undefined && cronos === '-1')

     if (astatus !== undefined)
      Event.find({client_id:req.param('clientId'),'status':astatus,start_date:{$lte:now}},null, {sort: '-start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
     else {
      Event.find({client_id:req.param('clientId'),start_date:{$lte:now}},null, {sort: '-start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
     }
    else 
     if (astatus !== undefined)
      Event.find({client_id:req.param('clientId'),'status':astatus,start_date:{$lte:now}},null, {sort: 'start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
     else
      Event.find({client_id:req.param('clientId'),start_date:{$lte:now}},null, {sort: 'start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
  } else if (req.param('eventType') !== undefined) {
    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({event_type:req.param('eventType'),start_date:{$lte:now}},null, {sort: '-start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
    else 
      Event.find({event_type:req.param('eventType'),start_date:{$lte:now}},null, {sort: 'start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
  } else if (req.param('startDate') !== undefined) {
    Event.find({start_date:{$gt:new Date(req.param('startDate')).getTime(),$lt:new Date(req.param('endDate')).getTime()}}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, events) {
      if (err)
          return next(err);
      
      var max = req.param('max');
      if (max !== undefined) {
        res.status(200).json({data:events.slice(0,max)});
      } else
        res.status(200).json({data:events});
    });
  } else if (req.param('status') !== undefined) {

    var cronos = req.param('cronoOrder');

    if (cronos !== undefined && cronos === '-1')
      Event.find({'status':req.param('status'),start_date:{$lte:now}},null, {sort: '-start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
    else 
      Event.find({'status':req.param('status'),start_date:{$lte:now}},null, {sort: 'start_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
        if (err)
          return next(err);

        var max = req.param('max');
        if (max !== undefined) {
          res.status(200).json({data:sorted.slice(0,max)});
        } else
          res.status(200).json({data:sorted});
      });
  } else {


    // A full list of ALL WITH NO RESTRICTION... MUST BE ADMIN role...
    if (req.user.role === 'admin') {
      Event.find({}, function(err, events) {
        if (err)
            return next(err);

        res.status(200).json({data:events});
      });
    } else {

        var cronos = req.param('cronoOrder');

        if (cronos !== undefined && cronos === '-1')
          Event.find({'owner_user_id':req.userId,start_date:{$lte:now}},null,{sort:'-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, events) {
            if (err)
                return next(err);
            var max = req.param('max');
            if (max !== undefined) {
              res.json(events.slice(0,max));
            } else
              res.json(events);
          });
        else
          Event.find({'owner_user_id':req.userId,start_date:{$lte:now}},null,{sort:'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, events) {
            if (err)
                return next(err);
            var max = req.param('max');
            if (max !== undefined) {
              res.json(events.slice(0,max));
            } else
              res.json(events);
          });
    }
  }
};

exports.create_a_event = function(req, res,next) {
  var new_event = new Event(req.body);
  new_event.save(function(err, aevent) {
    if (err)
          return next(err);
    res.status(200).json({data:aevent});
  });
};

exports.create_first_call_event = function(deal) {

  // TODO : make this code acceptable...

  if (deal.owner_user_id !== undefined) {

    var new_event = new Event({"title":"FIRST CALL",
                               "event_type":"call",
                               "owner_user_id":deal.owner_user_id,
                               "client_id":deal.client_id,
                               "deal_id":deal._id});

    new_event.save(function(err, aevent) {
      if (err)
        console.log("error on creating a first call event..." + err.toString());
    });
  } else {

    var new_event = new Event({"title":"FIRST CALL",
                               "event_type":"call",
                               "client_id":deal.client_id,
                               "deal_id":deal._id});

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
    
    res.status(200).json({data:aevent});
  });
};

exports.update_a_event = function(req, res,next) {
  Event.findOneAndUpdate({_id: req.params.eventId}, req.body, {new: true}, function(err, aevent) {
    if (err)
          return next(err);
    res.status(200).json({data:aevent});
  });
};

exports.delete_a_event = function(req, res,next) {

  Event.remove({
    _id: req.params.eventId
  }, function(err, aevent) {
    if (err)
          return next(err);
    res.status(200).json({ message: 'Event successfully deleted' });
  });
};
