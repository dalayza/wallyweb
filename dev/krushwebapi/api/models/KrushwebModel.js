'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var LeadEventTypeSchema = new Schema({
  name: {
    type: String,
    required: 'lead event type name is required'
  },
  integration: {
    type: String,
    enum : ['salesforce', 'google agenda', 'total voice', 'twillio']
  }
});
var ActivitySchema = new Schema({
  subject: {
    type: String,
    required: 'activity subject is required'
  },
  atype: {
    type: String,
    enum : ['notas','ligações','reuniões','comentários','agendamentos']
    required: 'activity atype is required'
  },
  due_timestamp: {
    type: String,
    required: 'activity due timestamp is required'
  },
  duration: {
    type: Number,
    required: 'activity due timestamp is required'
  },
  metaclient_id: {type : Schema.ObjectId, ref : 'MetaClient'}
});

var DealSchema = new Schema({
  status: {
    type: String,
    required: 'deal status is required'
  },
  currency: {
    type: String,
    required: 'deal currency is required'
  },
  metaclient_id: {type : Schema.ObjectId, ref : 'MetaClient'},
  followers: {
    type: "array",
    items: {
      type: "string"
    },
    "minItems": 1,
    "uniqueItems": true
  }
});

var MetaClientSchema = new Schema({
  name: {
    type: String,
    required: 'user name is required',
    unique: true
  },
  metadata: Schema.Types.Mixed
});

var ClientSchema = new Schema({
  address: {
    type: String,
    required: 'client address is required'
  },
  name: {
    type: String,
    required: 'user name is required',
    unique: true
  },
  phone: {
    type: String,
    unique: true
  }
});

var LeadSchema = new Schema({
  metaclient_id: {type : Schema.ObjectId, ref : 'MetaClient'},
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
    enum : ['origem','landing-page','facebook bot','site'],
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
  phone: {
    type: String,
    unique: true
  },
  passwd: {
    type: String,
    required: 'user password is required'
  },
  client_id: {type : Schema.ObjectId, ref : 'Client'}
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
module.exports = mongoose.model('Clients', ClientSchema);
module.exports = mongoose.model('MetaClients', MetaClientSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Activities', ActivitySchema);
module.exports = mongoose.model('LeadEventTypes', LeadEventTypeSchema);
