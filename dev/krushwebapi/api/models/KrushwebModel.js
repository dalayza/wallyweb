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
    type: String
  },
  due_time: {
    type: String
  },
  duration: {
    type: Number
  },
  services_credentials: [{
    service: {
      type:String,
      enum:['totalvoice'],
      required:true},
    user_id: {
      type: Number,
      required: true},
    token_id: {
      type: String,
      required: true}
    }],
  deal_id: {type : Schema.ObjectId, ref : 'Deal',required : true}
});

var MetaclientOrganizationSchema = new Schema({

  name: {
    type: String,
    required: 'organization name is required'
  },
  regid: {
    type: String,
    required: 'organization registration id is required'
  },
  address: {
    type: String,
    required: 'organization address is required'
  },
  email: {
    type: String,
    required: 'organization email is required'
  },
  phone: {
    type: String,
    required: 'organization phone is required'
  },
  branch: [{
    name: String
  }]
});

var MetaclientSchema = new Schema({

  name: {
    type: String,
    required: 'metaclient name is required'
  },
  email: {
    type: String,
    required: 'metaclient email is required'
  },
  phone: {
    type: String,
    required: 'metaclient phone is required'
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
  },
  client_id: {
    type : Schema.ObjectId, ref : 'Client',
    required: 'deal client is required'
  }
});

var ClientSchema = new Schema({
  address: {
    type: String
  },
  name: {
    type: String,
    required: 'client name is required',
    unique: true
  },
  phone: {
    type: String
  },
  branch: [{
    name: String
  }]
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
var VoicecallBillingSchema = new Schema({
Custo	ID Totalvoice	URL da gravação	Tempo Cobrado												
  client_name: {
    type: String,
    required: 'client name is required'
  },
  deal_id: {type : Schema.ObjectId, ref : 'Deal',required : true},
  created_date: {
    type: Date,
    default: Date.now
  },
  branch_phone_number: {
    type: String,
    required: 'branch phone number is required'
  },
  deal_phone_number: {
    type: String,
    required: 'deal phone number is required'
  },
  status: {
    type: String,
    required: 'call status is required',
    default: 'sem resposta'
  },
  cost: {
    type: Number
    required: 'call cost is required'
  },
  calling_service_id: {
    type: String,
    required: 'call system id is required'
  },
  calling_url: {
    type: String
  },
  duration: {
    type: Number
    required: 'call duration is required'
  }
});

module.exports = mongoose.model('Users', UserSchema);

module.exports = mongoose.model('Users', UserSchema);
module.exports = mongoose.model('Sessions', SessionsSchema);
module.exports = mongoose.model('Clients', ClientSchema);
module.exports = mongoose.model('Metaclients', MetaclientSchema);
module.exports = mongoose.model('MetaclientOrganizations', MetaclientOrganizationSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Events', EventSchema);
