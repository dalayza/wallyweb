'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var LeadSchema = new Schema({
  client_id: {
    type: String,
    required: 'lead client identification is required'
  },
  email: {
    type: String,
    required: 'lead email is required'
  },
  phone: {
    type: String,
    required: 'lead phone is required'
  },
  source: {
    type: String,
    required: 'lead source is required'
  },
  campaign: {
    type: String,
    required: 'lead campaign is required'
  },
  forms: {
    type: "array",
    items: {
      type: "object"
    }
  },
  targets: {
    type: "array",
    items: {
      type: "string"
    },
    "minItems": 1,
    "uniqueItems": true
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  status: {
    type: [{
      type: String,
      enum: ['pending', 'ongoing', 'completed']
    }],
    default: ['pending']
  }
});

var ClientSchema = new Schema({
  name: {
    type: String,
    required: 'client name is required'
  },
  email: {
    type: String,
    required: 'client email is required'
  }
});

var ClientDataSchema = new Schema({
  client_id: {
    type: String,
    required: 'lead client identification is required'
  },
  name: {
    type: String,
    required: 'client data name is required'
  },
  value: {
    type: String,
    required: 'client data value is required'
  }
});
    

module.exports = mongoose.model('Leads', LeadSchema);
module.exports = mongoose.model('Clients', ClientSchema);
