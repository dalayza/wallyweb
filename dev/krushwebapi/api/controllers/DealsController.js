'use strict';

var mongoose = require('mongoose'),
  Metaclient = mongoose.model('Metaclients'),
  Client = mongoose.model('Clients'),
  Deal = mongoose.model('Deals');

exports.list_all_deals = function(req, res) {
  Deal.find().sort("-_id").exec(function(err, deals) {
    if (err)
      res.send(err);
    res.json(deals);
  });
};

exports.create_a_deal = function(req, res) {
  
  var new_deal = new Deal(req.body);

  var client_name = req.body.client_name;

  var client =  Client.findOne({name:client_name}, function(err, client) {
    if (err)
      res.send(err);

    var metaclient_name = req.body.metaclient_name;
    var metaclient_phone = req.body.metaclient_phone; 
    var metaclient_email = req.body.metaclient_email; 

    var new_metaclient = new Metaclient({"name" : metaclient_name,
                                       "phone": metaclient_phone,
                                       "email": metaclient_email,
                                       "client_id": client._id});
    // TODO : check if it does not exists...
    new_metaclient.save(function(err, metaclient) {
      if (err)
        res.send(err);

      new_deal.metaclient_id = metaclient._id;
  
      new_deal.save(function(err, deal) {
        if (err)
          res.send(err);
        res.json(deal);
      });
    });
  });
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

