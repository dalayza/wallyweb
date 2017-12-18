'use strict';

var mongoose = require('mongoose'),
  Lead = mongoose.model('Leads');

exports.list_all_leads = function(req, res) {
  Lead.find().sort("-_id").exec(function(err, leads) {
    if (err)
      res.send(err);
    res.json(leads);
  });
};

exports.create_a_lead = function(req, res) {
  var new_lead = new Lead(req.body);
  new_lead.save(function(err, lead) {
    if (err)
      res.send(err);
    res.json(lead);
  });
};

exports.read_a_lead = function(req, res) {
  Lead.findById(req.params.leadId, function(err, lead) {
    if (err)
      res.send(err);
    res.json(lead);
  });
};

exports.update_a_lead = function(req, res) {
  Lead.findOneAndUpdate({_id: req.params.leadId}, req.body, {new: true}, function(err, lead) {
    if (err)
      res.send(err);
    res.json(lead);
  });
};

exports.delete_a_lead = function(req, res) {


  Lead.remove({
    _id: req.params.leadId
  }, function(err, lead) {
    if (err)
      res.send(err);
    res.json({ message: 'Lead successfully deleted' });
  });
};

