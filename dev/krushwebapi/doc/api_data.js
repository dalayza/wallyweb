define({ "api": [
  {
    "type": "post",
    "url": "/clients",
    "title": "Creates a Client",
    "name": "CreateClient",
    "group": "api",
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
    "url": "/metaclients",
    "title": "Creates a Metaclient",
    "name": "CreateMetaclient",
    "group": "api",
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
    "url": "/metaclients/:id",
    "title": "Delete a Metaclient",
    "name": "DeleteMetaclient",
    "group": "api",
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
    "url": "/metaclients",
    "title": "Request All Metaclients",
    "name": "ListAllMetaclients",
    "group": "api",
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
    "url": "/metaclients/:id",
    "title": "Request a Metaclient",
    "name": "ReadMetaclient",
    "group": "api",
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
    "url": "/metaclients/:id",
    "title": "Update a Metaclient",
    "name": "UpdateMetaclient",
    "group": "api",
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
  }
] });
