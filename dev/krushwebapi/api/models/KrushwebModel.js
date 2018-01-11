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

var UserSchema = new Schema({
  name: {
    type: String,
    required: 'user name is required'
  },
  name: {
    type: String,
    required: 'user name is required',
    unique: true
  },
  email: {
    type: String,
    required: 'user email is required',
    unique: true
  },
  passwd: {
    type: String,
    required: 'user password is required'
  },
  metadata: {
    type: "array",
    items: {
      type: "string"
    },
    "minItems": 1,
    "uniqueItems": true
  },
});

var SessionsSchema = new Schema({
  email: {
    type: String,
    required: 'session user email identification is required',
    unique: true
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  status: {
    type: [{
      type: String,
      enum: ['active', 'inactive']
    }],
    default: ['active']
  }
});

module.exports = mongoose.model('Leads', LeadSchema);
module.exports = mongoose.model('Users', UserSchema);
module.exports = mongoose.model('Sessions', SessionsSchema);
