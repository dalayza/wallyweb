define({ "api": [
  {
    "type": "delete",
    "url": "/leads/:id",
    "title": "Delete a Lead",
    "name": "DeleteLead",
    "group": "api",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Lead unique ID.</p>"
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
            "description": "<p>string with Lead unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/LeadsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/leads",
    "title": "Request All Leads",
    "name": "ListAllLeads",
    "group": "api",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "JSON",
            "description": "<p>string with all Leads.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/LeadsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "get",
    "url": "/leads/:id",
    "title": "Request a Lead",
    "name": "ReadLead",
    "group": "api",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Lead unique ID.</p>"
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
            "description": "<p>string with Lead.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/LeadsRoutes.js",
    "groupTitle": "api"
  },
  {
    "type": "put",
    "url": "/leads/:id",
    "title": "Update a Lead",
    "name": "UpdateLead",
    "group": "api",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Lead unique ID.</p>"
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
            "description": "<p>string with Lead unique ID confirmation.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/routes/LeadsRoutes.js",
    "groupTitle": "api"
  }
] });