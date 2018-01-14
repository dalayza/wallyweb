var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EventSchema = new Schema({
  title: {
    type: String,
    required: 'event title is required'
  },
  etype: {
    type: String,
    enum : ['call','meeting','note'],
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
  deal_id: {type : Schema.ObjectId, ref : 'Deal'}
});

var MetaclientSchema = new Schema({

  // TODO : 11. user_id ???
  //pipedrive_user_id: {
  //  type: Number
  //},
  //chatfuel_user_id: {
  //  type: Number
  //},

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
    required: 'metaclient email is required'
  },
  phone: {
    type: String,
    required: 'metaclient phone is required'
  },
  product: {
    type: String,
    required: 'metaclient product is required'
  },
  branch: {
    type: String
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  
  // var params for a person/organization
  metadata: Schema.Types.Mixed
});

var DealSchema = new Schema({

    // TODO : 11. user_id
    //pipedrive_user_id: {
    // type: Number
    // },

  title: {
    type: String,
    required: 'deal title is required',
    unique: true
  },
  source: {
    type: String,
    enum : ['origem','landing-page','facebook bot','site'],
    required: 'deal source is required'
  },
  campaign: {
    type: String,
    required: 'deal campaign is required'
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
    required: 'deal status is required',
    default: "LEAD"
  },
  currency: {
    type: String,
    required: 'deal currency is required'
  },
  price: {
    type: Number,
    required: 'deal currency is required'
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  metaclient_id: {type : Schema.ObjectId, ref : 'Metaclient'},
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
    required: 'client name is required'
  },
  phone: {
    type: String,
    required: 'client phone is required',
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
module.exports = mongoose.model('Metaclients', MetaclientSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Events', EventSchema);
