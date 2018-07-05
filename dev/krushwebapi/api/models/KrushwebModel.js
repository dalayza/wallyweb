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
    required:'event status is required',
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
      required:'event service is required'},
    user_id: {
      type: Number,
      required: 'event service user_id is required'},
    token_id: {
      type: String,
      required: 'event service token_id is required'}
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
    required:'Metaclient name is required',
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
    required:'deal product is required',
    default:["nao especificado"]
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
  owner_user_id: {type : Schema.ObjectId, ref : 'User',required:'Client owner is required'},
  status: {
    type: String,
    enum:['active','inactive'],
    required: 'client status is required',
    default: 'active'
  },
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
    type: String,
    required: 'user name is required',
    default:'nao especificado'
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
  phone: {
    type: String
  },
  mobile: {
    type: String
  },
  extension: {
    type: String
  },
  role: {
    type: String,
    enum:['admin','manager','cs'],
    required : 'user role is required',
    default : 'cs'
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
    required: 'session user email identification is required'
    //unique: true TODO : unique sessions...
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

var ClientconfigSchema = new Schema ({

  client_id: {
        type: Schema.ObjectId, ref: 'Client',
        required: 'client config settings client id is required'
  },

  phone_config: {
    status: {
      type: String,
      enum: ['active', 'inactive'],
      default: 'inactive'
      },
    queue_dial_mode: {
      type: String,
      enum: ['interval', 'continuous', 'continuos interval'],
      default: 'interval'
      },
    dial_interval: {
      type: Number,
      default: 5
    },
    metaclient_calls_limit: [{
      max: {
        type: Number,
        default: 3
      },
      repeat_call_interval: {
        type: Number,
        default: 30
      }
    }],
    user_calls_limit: [{
      max: {
          type: Number,
          default: 3
      },
      suspend_time: {
        type: Number,
        default: 60
      }
    }],
    cs_users_ids: [{
        type: Schema.ObjectId, ref: 'Users'
    }],
    deals_sorting: {
      type: String,
      enum: ['no', 'random all users', 'random online users', 'random active users']
    },
    auto_send_sms: {
      type : String,
      enum: ['active', 'inactive'],
      default: 'inactive'
    },
    data_console: {
      type: String,
      enum: ['slack', 'tts', 'wally push'],
      default: 'tts'
    },
    call_to_phone: {
      type: String,
      enum: ['phone', 'mobile', 'extension'],
      default: 'extension'
    },
    work_schedule: {
    // starttime and endtime uses format HH:MM
        sunday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        monday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        tuesday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        wednesday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        thursday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        friday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }],
        saturday: [{
            starttime: {
                type: String
            },
            endtime: {
                type: String
            }
        }]
    }
  }
});

var UserconfigSchema = new Schema ({

    client_id: {
      type: Schema.ObjectId, ref: 'Client',
      required: 'user config settings client id is required'
    },
    status: {
      type: String,
      enum: ['active', 'inactive'],
      default: 'active'
    },
    pushmessage_leads: {
      type: String,
      enum: ['active', 'inactive'],
      default: 'active'
    },
    work_schedule: {
      sunday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      monday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      tuesday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      wednesday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      thursday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      friday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      saturday: [{
          starttime: {
              type: String
          },
          endtime: {
              type: String
          }
      }],
      exception: [{
        // this values are to disable dialer in holidays and/or similars
        start_date: {
          type: Date,
          required: 'exception worktime start_date is required'
        },
        end_date: {
          type: Date,
          required: 'exception worktime end date is required'
        }
      }]
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
module.exports = mongoose.model('Clientconfig', ClientconfigSchema);
module.exports = mongoose.model('Userconfig', UserconfigSchema);
