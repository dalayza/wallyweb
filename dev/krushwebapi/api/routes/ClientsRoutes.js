'use strict';
module.exports = function(app) {
  var clients_controller = require('../controllers/ClientsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /clients Request All Clients
 * @apiName ListAllClients
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Clients.
 */

/**
 * @api {post} /clients Creates a Client
 * @apiName CreateClient
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with Client created.
 */
  app.get('/clients',VerifyToken,clients_controller.list_all_clients);
  app.post('/clients',VerifyToken,clients_controller.create_a_client);

/**
 * @api {get} /clients/:id Request a Client
 * @apiName ReadClient 
 * @apiGroup api
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client.
 */

/**
 * @api {put} /clients/:id Update a Client
 * @apiName UpdateClient
 * @apiGroup api
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client unique ID confirmation.
 */

/**
 * @api {delete} /clients/:id Delete a Client
 * @apiName DeleteClient
 * @apiGroup api
 *
 * @apiParam {Number} id Client unique ID.
 *
 * @apiSuccess {String} JSON string with Client unique ID confirmation.
 */
  app.get('/clients/:clientId',VerifyToken,clients_controller.read_a_client);
  app.put('/clients/:clientId',VerifyToken,clients_controller.update_a_client);
  app.delete('/clients/:clientId',VerifyToken,clients_controller.delete_a_client);
};
