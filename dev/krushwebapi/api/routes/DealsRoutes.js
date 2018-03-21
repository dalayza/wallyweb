'use strict';
module.exports = function(app,auth) {

  var deals_controller = require('../controllers/DealsController');
  var VerifyToken = require('../../auth/VerifyToken');


/**
 * @api {get} /deals Request All Deals
 * @apiName ListAllDeals
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Deals.
 */

/**
 * @api {post} /deals Creates a Deal
 * @apiName CreateDeal
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with Deal created.
 */
  app.get('/deals',VerifyToken,auth.can('list all deals'),deals_controller.list_all_deals);
  app.post('/deals',VerifyToken,auth.can('create a deal'),deals_controller.create_a_deal);

/**
 * @api {get} /deals/:id Request a Deal
 * @apiName ReadDeal 
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal.
 */

/**
 * @api {put} /deals/:id Update a Deal
 * @apiName UpdateDeal
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal unique ID confirmation.
 */

/**
 * @api {delete} /deals/:id Delete a Deal
 * @apiName DeleteDeal
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal unique ID confirmation.
 */
  app.get('/deals/:dealId',VerifyToken,auth.can('read a deal'),deals_controller.read_a_deal);
  app.put('/deals/:dealId',VerifyToken,auth.can('update a deal'),deals_controller.update_a_deal);
  app.delete('/deals/:dealId',VerifyToken,auth.can('delete a deal'),deals_controller.delete_a_deal);
};
