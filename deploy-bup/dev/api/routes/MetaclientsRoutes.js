'use strict';
module.exports = function(app,auth) {
  var metaclients_controller = require('../controllers/MetaclientsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /metaclients Request All Metaclients
 * @apiName ListAllMetaclients
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Metaclients.
 */

/**
 * @api {post} /metaclients Creates a Metaclient
 * @apiName CreateMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with Metaclient created.
 */
  app.get('/metaclients',VerifyToken,metaclients_controller.list_all_metaclients);
  app.post('/metaclients',VerifyToken,metaclients_controller.create_a_metaclient);

/**
 * @api {get} /metaclients/:id Request a Metaclient
 * @apiName ReadMetaclient 
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient.
 */

/**
 * @api {put} /metaclients/:id Update a Metaclient
 * @apiName UpdateMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */

/**
 * @api {delete} /metaclients/:id Delete a Metaclient
 * @apiName DeleteMetaclient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */
  app.get('/metaclients/:metaclientId',VerifyToken,metaclients_controller.read_a_metaclient);
  app.put('/metaclients/:metaclientId',VerifyToken,metaclients_controller.update_a_metaclient);
  app.delete('/metaclients/:metaclientId',VerifyToken,metaclients_controller.delete_a_metaclient);
};
