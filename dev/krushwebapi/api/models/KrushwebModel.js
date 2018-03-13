var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var EventSchema = new Schema({

  title: {
    type: String,
    required: 'event title is required'
  },
  event_type: {
    type: String,
    enum : ['call','meeting','note','e-mail','wally_call'],
    required: 'event etype is required'
  },
  start_date: {
    type: Date,
    default: Date.now,
    required: 'event start_date is required'
  },
  status: {
    type: String,
    enum: ['done','open'],
    required:true,
    default:'open'
  },
  owner_user_id: {type : Schema.ObjectId, ref : 'User'},
  description: {
    type: String
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
  address: {
    type: String
  },
  duration: {
    type: Number
  },
  duration_scale: {
    type: String,
    enum: ['day','hour','minute']
  },
  deal_id: {type : Schema.ObjectId, ref : 'Deal'},
  client_id: {type : Schema.ObjectId, ref : 'Client'}
});

/*
 * for both Metaclient/Organization there are no unicity requirements..
 * we basically just link the deal with them.
 */
var MetaclientOrganizationSchema = new Schema({

  name: {
    type: String,
    required: 'organization name is required',
    default:'SEM NOME'
  },
  regid: {
    type: String
  },
  address: {
    type: String
  },
  email: {
    type: String
  },
  phone: {
    type: String
  },
  parent_id: {type : Schema.ObjectId, ref : 'MetaclientOrganization'}
});

var MetaclientSchema = new Schema({

  name: {
    type: String,
    required:true,
    default:'SEM NOME'
  },
  email: {
    type: String
  },
  phone: {
    type: String
  },
  organization_name: {
    type: String
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
  source: {
    type: String,
    enum : ['facebook bot','google','networking','linkedin','desk','phone','metaclient','inbound marketing'],
    required: 'deal source is required',
    default: 'metaclient'
  },
  status: {
    type: String,
    enum: ['lost','lead','contacted','current','won'],
    required: 'deal status is required',
    default: 'lead'
  },
  currency: {
    type: String,
    required: 'deal currency is required',
    default: 'REAL'
  },
  client_id: {
    type : Schema.ObjectId, ref : 'Client',
    required: 'deal client is required'
  },
  product: {
    type: [String],
    required:true,
    default:["não especificado"]
  },
  owner_user_id: {type : Schema.ObjectId, ref : 'User'},
  tags: [String],
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
  followers: [{
    type : Schema.ObjectId, 
    ref : 'User'
  }]
});

var ClientSchema = new Schema({

  name: {
    type: String,
    required: 'client name is required',
    unique: true
  },
  owner_user_id: {type : Schema.ObjectId, ref : 'User',required : true},
  address: {
    type: String
  },
  phone: {
    type: String
  },
  mobile: {
    type: String
  },
  extension: {
    type: String
  },
  parent_id: {type : Schema.ObjectId, ref : 'Client'}
});

var UserSchema = new Schema({

  name: {
    type: String
  },
  email: {
    type: String,
    required: 'user email is required',
    unique: true
  },
  phone: {
    type: String
  },
  mobile: {
    type: String
  },
  extension: {
    type: String
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
  client_id: [{type : Schema.ObjectId, ref : 'Client'}]
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

module.exports = mongoose.model('Users', UserSchema);
module.exports = mongoose.model('Sessions', SessionsSchema);
module.exports = mongoose.model('Clients', ClientSchema);
module.exports = mongoose.model('Metaclients', MetaclientSchema);
module.exports = mongoose.model('MetaclientOrganizations', MetaclientOrganizationSchema);
module.exports = mongoose.model('Deals', DealSchema);
module.exports = mongoose.model('Events', EventSchema);
