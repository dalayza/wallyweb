'use strict';
module.exports = function(app,auth) {
  var metaclient_orgs_controller = require('../controllers/MetaclientOrgsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /metaclientorgs Request All Metaclients
 * @apiName ListAllMetaclients
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Metaclients.
 */

/**
 * @api {post} /metaclientorgs Creates a Metaclient
 * @apiName CreateMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with Metaclient created.
 */
  app.get('/metaclientorgs',VerifyToken,metaclient_orgs_controller.list_all_metaclientorgs);
  app.post('/metaclientorgs',VerifyToken,metaclient_orgs_controller.create_a_metaclientorg);

/**
 * @api {get} /metaclientorgs/:id Request a Metaclient
 * @apiName ReadMetaclient 
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient.
 */

/**
 * @api {put} /metaclientorgs/:id Update a Metaclient
 * @apiName UpdateMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */

/**
 * @api {delete} /metaclientorgs/:id Delete a Metaclient
 * @apiName DeleteMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */
  app.get('/metaclientorgs/:metaclientorgId',VerifyToken,metaclient_orgs_controller.read_a_metaclientorg);
  app.put('/metaclientorgs/:metaclientId',VerifyToken,metaclient_orgs_controller.update_a_metaclientorg);
  app.delete('/metaclientorgs/:metaclientId',VerifyToken,metaclient_orgs_controller.delete_a_metaclientorg);
};
