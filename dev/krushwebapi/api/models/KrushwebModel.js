 strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EventSchema = new Schema({
  title: {
    type: String,
    required: 'event title is required',
    unique: true
  },
  etype:”call, meeting, note”
    type: String,
    enum : ['call','meeting','note']
    required: 'event etype is required'
  },
  description: {
    type: String,
    required: 'event description is required'
  },
  due_date: {
    type: Date,
    default: Date.now,
    required: 'event due_date is required'
  },
  start_time: {
    type: String,
    required: 'event start_time is required'
  },
  due_time: {
    type: String,
    required: 'event due_time is required'
  },
  duration: {
    type: Number,
    required: 'event duration timestamp is required'
  },
  metaclient_id: {type : Schema.ObjectId, ref : 'MetaClient'},
  deal_id: {type : Schema.ObjectId, ref : 'Deal'}
});

var MetaClientSchema = new Schema({

  // TODO : 11. user_id ???

  firstname: {
    type: String,
    required: 'metaclient first name is required',
    unique: true
  },
  lastname: {
    type: String,
    required: 'metaclient last name is required',
    unique: true
  },
  email: {
    type: String,
    required: 'lead email is required'
  },
  phone: {
    type: String,
    required: 'lead phone is required'
  },
  product: {
    type: String,
    required: 'lead email is required'
  },
  branch: {
    type: String,
    required: 'lead phone is required'
  },
  pipedrive_user_id: {
    type: Number
  },
  chatfuel_user_id: {
    type: Number
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  metadata: Schema.Types.Mixed
});

var DealSchema = new Schema({

    // TODO : 11. user_id 

  title: {
    type: String,
    required: 'event title is required',
    unique: true
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
  targets: {
    type: "array",
    items: {
      type: "string"
    },
    "minItems": 1,
    "uniqueItems": true
  },
  status: {
    type: String,
    required: 'deal status is required'
  },
  currency: {
    type: String,
    required: 'deal currency is required'
  },
  price: {
    type: Number,
    required: 'deal currency is required'
  },
  pipedrive_user_id: {
    type: Number
  },
  created_date: {
    type: Date,
    default: Date.now
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

var ClientSchema = new Schema({
  address: {
    type: String,
    required: 'client address is required'
  },
  name: {
    type: String,
    required: 'client name is required',
    unique: true
  },
  phone: {
    type: String,
    unique: true
  }
});

var UserSchema = new Schema({
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

module.exports = mongoose.model('Users', UserSchema);
module.exports = mongoose.model('Sessions', SessionsSchema);
module.exports = mongoose.model('Clients', ClientSchema);
module.exports = mongoose.model('MetaClients', MetaClientSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Events', EventSchema);
