'use strict';

var events = require('./EventsController');
var mongoose = require('mongoose'),
  Metaclient = mongoose.model('Metaclients'),
  MetaclientOrganization = mongoose.model('MetaclientOrganizations'),
  Client = mongoose.model('Clients'),
  Deal = mongoose.model('Deals');

exports.list_all_deals = function(req, res) {
  Deal.find().sort("-_id").exec(function(err, deals) {
    if (err)
      res.send(err);
    res.json(deals);
  });
};

/*
 * basically consider a deal from either a Metaclient OR a MetaclientOrganization...
 */
exports.create_a_deal = function(req, res) {
  
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
            res.send(err);

          new_deal.metaclient_org_id = metaclient_org._id;
          new_metaclient.save(function(err, metaclient) {
            if (err)
              res.send(err);

            new_deal.metaclient_id = metaclient._id;
   
            // applies only for an null title...
            if (new_deal.title === undefined) {
              new_deal.title = metaclient.name + " - " + new_deal.product[0];
            }
            new_deal.client_id = client_id;
    
            // finally saves the deal...
            new_deal.save(function(err, deal) {
               if (err)
                 res.send(err);

               events.create_first_call_event(deal);

               res.json(deal);
            });
          });
        });
      } else {

      new_metaclient.save(function(err, metaclient) {
          if (err)
            res.send(err);

          new_deal.metaclient_id = metaclient._id;
   
          // applies only for an null title...
          if (new_deal.title === undefined) {
            new_deal.title = metaclient.name + " - " + new_deal.product[0];
          }
          new_deal.client_id = client_id;
    
          // finally saves the deal...
          new_deal.save(function(err, deal) {
             if (err)
               res.send(err);

             events.create_first_call_event(deal);

             res.json(deal);
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
            res.send(err);

          new_deal.metaclient_org_id = metaclient_org._id;

          // applies only for an null title...
          if (new_deal.title === undefined) {
            new_deal.title = metaclient_org.name + " - " + new_deal.product[0];
          }

          new_deal.client_id = client_id;

          // finally saves the deal...
          new_deal.save(function(err, deal) {
             if (err)
               res.send(err);

             events.create_first_call_event(deal);

             res.json(deal);
          });
      });
  }
};

exports.read_a_deal = function(req, res) {
  Deal.findById(req.params.dealId, function(err, deal) {
    if (err)
      res.send(err);
    res.json(deal);
  });
};

exports.update_a_deal = function(req, res) {
  Deal.findOneAndUpdate({_id: req.params.dealId}, req.body, {new: true}, function(err, deal) {
    if (err)
      res.send(err);
    res.json(deal);
  });
};

exports.delete_a_deal = function(req, res) {

  Deal.remove({
    _id: req.params.dealId
  }, function(err, deal) {
    if (err)
      res.send(err);
    res.json({ message: 'Deal successfully deleted' });
  });
};

