define({ "api": [
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
    "url": "/deals",
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
    "url": "/deals/:id",
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
    "url": "/deals",
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
    "url": "/deals/:id",
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
    "url": "/deals/:id",
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
  }
] });
