'use strict';

var events = require('./EventsController');
var mongoose = require('mongoose'),
  Metaclient = mongoose.model('Metaclients'),
  MetaclientOrganization = mongoose.model('MetaclientOrganizations'),
  Client = mongoose.model('Clients'),
  Deal = mongoose.model('Deals');

exports.list_all_deals = function(req, res,next) {

  var perPage = 10
  var page = req.param('page') || 1

  // A full list of ALL WITH NO RESTRICTION... MUST BE ADMIN

  if (req.user.role === 'admin') {
    Deal.find({}, function(err, deals) {
      if (err)
        return next(err);
      var max = req.param('max');
      if (max !== undefined) {
        res.status(200).json({data:deals.slice(0,max)});
      } else
        res.status(200).json({data:deals});
    });

  } else {

    if (req.param('status') !== undefined) {

      var cronos = req.param('cronoOrder');

      if (cronos !== undefined && cronos === '-1')
        Deal.find({'status':req.param('status'),'owner_user_id':req.userId},null, {sort: '-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
      else 
        Deal.find({'status':req.param('status'),'owner_user_id':req.userId},null, {sort: 'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });

    } else if (req.param('metaclientId') !== undefined) {

      var cronos = req.param('cronoOrder');

      if (cronos !== undefined && cronos === '-1')
        Deal.find({'metaclient_id':req.param('metaclientId'),'owner_user_id':req.userId},null, {sort: '-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
      else 
        Deal.find({'metaclient_id':req.param('metaclientId'),'owner_user_id':req.userId},null, {sort: 'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
          res.status(200).json({data:sorted});
        });

    } else if (req.param('metaclientorgId') !== undefined) {

      var cronos = req.param('cronoOrder');

      if (cronos !== undefined && cronos === '-1')
        Deal.find({'metaclient_org_id':req.param('metaclientorgId'),'owner_user_id':req.userId},null, {sort: '-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
      else 
        Deal.find({'metaclient_org_id':req.param('metaclientorgId'),'owner_user_id':req.userId},null, {sort: 'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
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

      if (cronos !== undefined && cronos === '-1')
        Deal.find({'client_id':req.param('clientId'),'owner_user_id':req.userId},null, {sort: '-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
      else 
        Deal.find({'client_id':req.param('clientId'),'owner_user_id':req.userId},null, {sort: 'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
    } else {

      // NO MAJOR PARAMS ... check OWNERSHIP ONLY
      var cronos = req.param('cronoOrder');

      if (cronos !== undefined && cronos === '-1')
        Deal.find({'owner_user_id':req.userId},null, {sort: '-create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
      else
        Deal.find({'owner_user_id':req.userId},null, {sort: 'create_date'}).skip((perPage * page) - perPage).limit(perPage).exec(function(err, sorted) {
          if (err)
            return next(err);

          var max = req.param('max');
          if (max !== undefined) {
            res.status(200).json({data:sorted.slice(0,max)});
          } else
            res.status(200).json({data:sorted});
        });
    }
  } 
};

/*
 * basically consider a deal from either a Metaclient OR a MetaclientOrganization...
 */
exports.create_a_deal = function(req, res,next) {
  
  var new_deal = new Deal(req.body);

  // metaclient
  var metaclient_name = req.body.metaclient_name;
  var metaclient_phone = req.body.metaclient_phone; 
  var metaclient_email = req.body.metaclient_email; 

  // metaclient_org
  var metaclient_org_name = req.body.metaclient_org_name;
  var metaclient_org_regid = req.body.metaclient_org_regid; 
  var metaclient_org_address = req.body.metaclient_org_address; 
  var metaclient_org_phone = req.body.metaclient_org_phone; 
  var metaclient_org_email = req.body.metaclient_org_email; 

  var client_id = req.body.client_id;

  // this means save it as a Metaclient...
  if (metaclient_name != null) {

      var new_metaclient = new Metaclient({"name" : metaclient_name,
                                       "phone": metaclient_phone,
                                       "email": metaclient_email});

      // this will be the metaclient organization...
      if (metaclient_org_name != null) {

        new_metaclient.organization_name=metaclient_org_name;
        var new_metaclient_org = new MetaclientOrganization({"name" : metaclient_org_name});

        new_metaclient_org.save(function(err, metaclient_org) {
          if (err)
            return next(err);

          new_deal.metaclient_org_id = metaclient_org._id;
          new_metaclient.save(function(err, metaclient) {
            if (err)
              return next(err);

            new_deal.metaclient_id = metaclient._id;
   
            // applies only for an null title...
            if (new_deal.title === undefined) {
              new_deal.title = metaclient.name + " - " + new_deal.product[0];
            }
            new_deal.client_id = client_id;
    
            // finally saves the deal...
            new_deal.save(function(err, deal) {
               if (err)
                 return next(err);

               events.create_first_call_event(deal);

               res.status(200).json({data:deal});
            });
          });
        });
      } else {

      new_metaclient.save(function(err, metaclient) {
          if (err)
            return next(err);

          new_deal.metaclient_id = metaclient._id;
   
          // applies only for an null title...
          if (new_deal.title === undefined) {
            new_deal.title = metaclient.name + " - " + new_deal.product[0];
          }
          new_deal.client_id = client_id;
    
          // finally saves the deal...
          new_deal.save(function(err, deal) {
             if (err)
               return next(err);

             events.create_first_call_event(deal);

             res.status(200).json({data:deal});
          });
      });

    }

  } else

    // this means save it as a MetaclientOrganization...
    if (metaclient_org_name != null) {

      var new_metaclient_org = new MetaclientOrganization({"name" : metaclient_org_name,
                                                   "phone": metaclient_org_phone,
                                                   "regid": metaclient_org_regid,
                                                   "address": metaclient_org_address,
                                                   "email": metaclient_org_email,
                                                   "client_id": client_id});

      new_metaclient_org.save(function(err, metaclient_org) {
          if (err)
            return next(err);

          new_deal.metaclient_org_id = metaclient_org._id;

          // applies only for an null title...
          if (new_deal.title === undefined) {
            new_deal.title = metaclient_org.name + " - " + new_deal.product[0];
          }

          new_deal.client_id = client_id;

          // finally saves the deal...
          new_deal.save(function(err, deal) {
             if (err)
               return next(err);

             events.create_first_call_event(deal);

             res.status(200).json({data:deal});
          });
      });
  }
};

exports.read_a_deal = function(req, res,next) {
  Deal.findById(req.params.dealId, function(err, deal) {
    if (err)
      return next(err);
    res.status(200).json({data:deal});
  });
};

exports.update_a_deal = function(req, res,next) {
  Deal.findOneAndUpdate({_id: req.params.dealId}, req.body, {new: true}, function(err, deal) {
    if (err)
      return next(err);
    res.status(200).json({data:deal});
  });
};

exports.delete_a_deal = function(req, res,next) {

  // A full list of ALL WITH NO RESTRICTION... MUST BE ADMIN role...
  if (req.user.role === 'admin') {
    Deal.remove({
      _id: req.params.dealId
    }, function(err, deal) {
      if (err)
        return next(err);
      res.status(200).json({ message: 'Deal successfully deleted' });
    });
  } else {
    Deal.remove({
      _id: req.params.dealId,'owner_user_id':req.user.id
    }, function(err, deal) {
      if (err)
        return next(err);
      res.status(200).json({ message: 'Deal successfully deleted' });
    });
  }
};

