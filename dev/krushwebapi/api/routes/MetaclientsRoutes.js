'use strict';
module.exports = function(app) {
  var metaclients_controller = require('../controllers/MetaclientsController');

/**
 * @api {get} /metaclients Request All Metaclients
 * @apiName ListAllMetaclients
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Metaclients.
 */

/**
 * @api {post} /metaclients Creates a Metaclient
 * @apiName CreateMetaclient
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with Metaclient created.
 */
  app.route('/metaclients')
    .get(metaclients_controller.list_all_metaclients)
    .post(metaclients_controller.create_a_metaclient);


/**
 * @api {get} /metaclients/:id Request a Metaclient
 * @apiName ReadMetaclient 
 * @apiGroup api
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient.
 */

/**
 * @api {put} /metaclients/:id Update a Metaclient
 * @apiName UpdateMetaclient
 * @apiGroup api
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */

/**
 * @api {delete} /metaclients/:id Delete a Metaclient
 * @apiName DeleteMetaclient
 * @apiGroup api
 *
 * @apiParam {Number} id Metaclient unique ID.
 *
 * @apiSuccess {String} JSON string with Metaclient unique ID confirmation.
 */
  app.route('/metaclients/:metaclientId')
    .get(metaclients_controller.read_a_metaclient)
    .put(metaclients_controller.update_a_metaclient)
    .delete(metaclients_controller.delete_a_metaclient);
};
