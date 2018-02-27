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

var MetaclientOrganizationSchema = new Schema({

  name: {
    type: String,
    required: 'organization name is required',
    unique: true
  },
  regid: {
    type: String,
    required: 'organization registration id is required',
    unique: true
  },
  address: {
    type: String,
    required: 'organization address is required'
  },
  email: {
    type: String,
    required: 'organization email is required',
    unique: true
  },
  phone: {
    type: String,
    required: 'organization phone is required'
  },
  branch: {
    type: String
  },
  client_id: {
    type : Schema.ObjectId, ref : 'Client',
    required: 'organization client is required'
  }
});

var MetaclientSchema = new Schema({

  name: {
    type: String,
    required: 'metaclient name is required',
    unique: true
  },
  email: {
    type: String,
    required: 'metaclient email is required',
    unique: true
  },
  phone: {
    type: String,
    required: 'metaclient phone is required'
  },
  branch: {
    type: String
  },
  client_id: {
    type : Schema.ObjectId, ref : 'Client',
    required: 'metaclient client is required'
  },
  created_date: {
    type: Date,
    default: Date.now
  }
});

var DealSchema = new Schema({

  title: {
    type: String,
    required: 'deal title is required'
  },
  product: {
    type: String,
    required: 'deal product is required'
  },
  product_source_type: {
    type: String,
    enum : ['landing-page','facebook bot','site'],
    required: 'deal source is required',
    default: 'site'
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
    default: 'LEAD'
  },
  currency: {
    type: String,
    required: 'deal currency is required',
    default: 'REAL'
  },
  price: {
    type: Number
  },
  created_date: {
    type: Date,
    default: Date.now
  },
  metaclient_id: {
    type : Schema.ObjectId, 
    ref : 'Metaclient'
  },
  metaclient_org_id: {
    type : Schema.ObjectId, 
    ref : 'MetaclientOrganization'
  },
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
  branch: {
    type: String
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
  services_credentials: [{
    service: {
      type:String,
      enum:['pipedrive','slack'],
      required:true},
    user_id: {
      type: Number,
      required: true},
    token_id: {
      type: String,
      required: true}
    }],
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
module.exports = mongoose.model('MetaclientOrganizations', MetaclientOrganizationSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Events', EventSchema);
