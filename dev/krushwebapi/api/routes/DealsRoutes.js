'use strict';
module.exports = function(app) {
  var deals_controller = require('../controllers/DealsController');

/**
 * @api {get} /deals Request All Deals
 * @apiName ListAllDeals
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Deals.
 */

/**
 * @api {post} /deals Creates a Deal
 * @apiName CreateDeal
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with Deal created.
 */
  app.route('/deals')
    .get(deals_controller.list_all_deals)
    .post(deals_controller.create_a_deal);

/**
 * @api {get} /deals/:id Request a Deal
 * @apiName ReadDeal 
 * @apiGroup api
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal.
 */

/**
 * @api {put} /deals/:id Update a Deal
 * @apiName UpdateDeal
 * @apiGroup api
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal unique ID confirmation.
 */

/**
 * @api {delete} /deals/:id Delete a Deal
 * @apiName DeleteDeal
 * @apiGroup api
 *
 * @apiParam {Number} id Deal unique ID.
 *
 * @apiSuccess {String} JSON string with Deal unique ID confirmation.
 */
  app.route('/deals/:dealId')
    .get(deals_controller.read_a_deal)
    .put(deals_controller.update_a_deal)
    .delete(deals_controller.delete_a_deal);

};
