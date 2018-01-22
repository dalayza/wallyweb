'use strict';
module.exports = function(app) {
  var clients_controller = require('../controllers/ClientsController');

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
  app.route('/clients')
    .get(clients_controller.list_all_clients)
    .post(clients_controller.create_a_client);


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
  app.route('/clients/:clientId')
    .get(clients_controller.read_a_client)
    .put(clients_controller.update_a_client)
    .delete(clients_controller.delete_a_client);
};
