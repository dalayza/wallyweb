define({ "api": [
  {
    "type": "post",
    "url": "/clients",
    "title": "Creates a Client",
    "name": "CreateClient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>Client name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>Client status (ex:['active','inactive')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "owner_user_id",
            "description": "<p>Client owner User ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Client created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/ClientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/deals",
    "title": "Creates a Deal",
    "name": "CreateDeal",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "product",
            "description": "<p>Deal product.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "client_id",
            "description": "<p>Deal Client ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_name",
            "description": "<p>Deal Metaclient name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_phone",
            "description": "<p>Deal Metaclient phone.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_email",
            "description": "<p>Deal Metaclient email.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_org_name",
            "description": "<p>Deal Metaclient Organization name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_org_regid",
            "description": "<p>Deal Metaclient Organization registration ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_org_address",
            "description": "<p>Deal Metaclient Organization address.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_org_phone",
            "description": "<p>Deal Metaclient Organization phone.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclient_org_email",
            "description": "<p>Deal Metaclient Organization email.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "owner_user_id",
            "description": "<p>Deal client owner User ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Deal created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/DealsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/events",
    "title": "Creates a Event",
    "name": "CreateEvent",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>Event title.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "event_type",
            "description": "<p>Event type (ex:'call','meeting','note','e-mail','wally_call')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>Event status (ex:'done','open')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "description",
            "description": "<p>Event description.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Event created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/EventsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/metaclientorgs",
    "title": "Creates a Metaclient",
    "name": "CreateMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientOrgsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/metaclients",
    "title": "Creates a Metaclient",
    "name": "CreateMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/sessions",
    "title": "Creates a Session",
    "name": "CreateSession",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Session created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/SessionsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/users",
    "title": "Creates a User",
    "name": "CreateUser",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with User created.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/UsersRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/clients/:id",
    "title": "Delete a Client",
    "name": "DeleteClient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Client unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Client unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/ClientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/deals/:id",
    "title": "Delete a Deal",
    "name": "DeleteDeal",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Deal unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Deal unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/DealsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/events/:id",
    "title": "Delete a Event",
    "name": "DeleteEvent",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Event unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Event unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/EventsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/metaclientorgs/:id",
    "title": "Delete a Metaclient",
    "name": "DeleteMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientOrgsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/metaclients/:id",
    "title": "Delete a Metaclient",
    "name": "DeleteMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/sessions/:id",
    "title": "Delete a Session",
    "name": "DeleteSession",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Session unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Session unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/SessionsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "delete",
    "url": "/users/:id",
    "title": "Delete a User",
    "name": "DeleteUser",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>User unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with User unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/UsersRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/clients",
    "title": "Request All Clients",
    "name": "ListAllClients",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Clients.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/ClientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/deals",
    "title": "Request All Deals",
    "name": "ListAllDeals",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "status",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclientId",
            "description": "<p>Metaclient filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "metaclientorgId",
            "description": "<p>Metaclient Organization filter sorted by date.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Deals.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/DealsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/events",
    "title": "Request All Events",
    "name": "ListAllEvents",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "ownerUserId",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "dealId",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "clientId",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "eventType",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "startDate",
            "description": "<p>Deal status filter sorted by date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "status",
            "description": "<p>Deal status filter sorted by date.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Events.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/EventsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/metaclientorgs",
    "title": "Request All Metaclients",
    "name": "ListAllMetaclients",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Metaclients.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientOrgsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/metaclients",
    "title": "Request All Metaclients",
    "name": "ListAllMetaclients",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Metaclients.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/sessions",
    "title": "Request All Sessions",
    "name": "ListAllSessions",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Sessions.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/SessionsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/users",
    "title": "Request All Users",
    "name": "ListAllUsers",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Users.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/UsersRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/clients/:id",
    "title": "Request a Client",
    "name": "ReadClient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Client unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Client.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/ClientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/deals/:id",
    "title": "Request a Deal",
    "name": "ReadDeal",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Deal unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Deal.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/DealsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/events/:id",
    "title": "Request a Event",
    "name": "ReadEvent",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Event unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Event.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/EventsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/metaclientorgs/:id",
    "title": "Request a Metaclient",
    "name": "ReadMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientOrgsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/metaclients/:id",
    "title": "Request a Metaclient",
    "name": "ReadMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/users/:id",
    "title": "Request a User",
    "name": "ReadUser",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>User unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with User.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/UsersRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/clients/:id",
    "title": "Update a Client",
    "name": "UpdateClient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Client unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Client unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/ClientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/deals/:id",
    "title": "Update a Deal",
    "name": "UpdateDeal",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Deal unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Deal unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/DealsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/events/:id",
    "title": "Update a Event",
    "name": "UpdateEvent",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Event unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Event unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/EventsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/metaclientorgs/:id",
    "title": "Update a Metaclient",
    "name": "UpdateMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientOrgsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/metaclients/:id",
    "title": "Update a Metaclient",
    "name": "UpdateMetaclient",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Metaclient unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with Metaclient unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/MetaclientsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/users/:id",
    "title": "Update a User",
    "name": "UpdateUser",
    "group": "api",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>User unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with User unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/UsersRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "post",
    "url": "/login",
    "title": "Login a user",
    "name": "LoginUser",
    "group": "auth",
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n  \"email\": \"xyz@mail.com\",\n  \"passwd\": \"[A PASSWORD]\",\n}",
          "type": "json"
        }
      ]
    },
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "version": "0.0.0",
    "filename": "auth/AuthController.js",
    "groupTitle": "auth"
  },
  {
    "type": "post",
    "url": "/logout",
    "title": "Logout a user",
    "name": "LogoutUser",
    "group": "auth",
    "permission": [
      {
        "name": "authenticated user"
      }
    ],
    "version": "0.0.0",
    "filename": "auth/AuthController.js",
    "groupTitle": "auth"
  },
  {
    "type": "post",
    "url": "/register",
    "title": "Register a new user",
    "name": "RegisterUser",
    "group": "auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>Required new user unique email.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "passwd",
            "description": "<p>Required new user password.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>Optional new user name.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n  \"email\": \"xyz@mail.com\",\n  \"passwd\": \"[A PASSWORD]\",\n     \"name\":\"X de Y Z\"\n}",
          "type": "json"
        }
      ]
    },
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "0.0.0",
    "filename": "auth/AuthController.js",
    "groupTitle": "auth"
  }
] });
