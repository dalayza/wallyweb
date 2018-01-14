'use strict';

var mongoose = require('mongoose'),
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
  new_deal.save(function(err, deal) {
    if (err)
      res.send(err);
    res.json(deal);
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

