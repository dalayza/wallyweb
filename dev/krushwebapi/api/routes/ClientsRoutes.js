'use strict';
module.exports = function(app,auth) {
  var clients_controller = require('../controllers/ClientsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /clients Request All Clients
 * @apiName ListAllClients
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Clients.
 */

/**
 * @api {post} /clients Creates a Client
 * @apiName CreateClient
 * @apiGroup api
 * @apiPermission authenticated user
 * @apiParam {String} [name]  Client name.
 * @apiParam {String} [status]  Client status (ex:['active','inactive')
 * @apiParam {String} [owner_user_id] Client owner User ID.
 *
 * @apiSuccess {String} JSON string with Client created.
 */
  app.get('/clients',VerifyToken,auth.can('list all clients'),clients_controller.list_all_clients);
  app.post('/clients',VerifyToken,auth.can('create a client'),clients_controller.create_a_client);

/**
 * @api {get} /clients/:id Request a Client
 * @apiName ReadClient 
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client.
 */

/**
 * @api {put} /clients/:id Update a Client
 * @apiName UpdateClient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client unique ID confirmation.
 */

/**
 * @api {delete} /clients/:id Delete a Client
 * @apiName DeleteClient
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client unique ID confirmation.
 */
  app.get('/clients/:clientId',VerifyToken,auth.can('read a client'),clients_controller.read_a_client);
  app.put('/clients/:clientId',VerifyToken,auth.can('update a client'),clients_controller.update_a_client);
  app.delete('/clients/:clientId',VerifyToken,auth.can('delete a client'),clients_controller.delete_a_client);
};
