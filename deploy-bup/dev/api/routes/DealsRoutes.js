'use strict';
module.exports = function(app,auth) {

  var deals_controller = require('../controllers/DealsController');
  var VerifyToken = require('../../auth/VerifyToken');


/**
 * @api {get} /deals Request All Deals
 * @apiName ListAllDeals
 * @apiGroup api
 * @apiPermission authenticated user
 * @apiParam {String} [status] Deal status filter sorted by date.
 * @apiParam {String} [metaclientId] Metaclient filter sorted by date.
 * @apiParam {String} [metaclientorgId] Metaclient Organization filter sorted by date.
 *
 * @apiSuccess {String} JSON string with all Deals.
 */

/**
 * @api {post} /deals Creates a Deal
 * @apiName CreateDeal
 * @apiGroup api
 * @apiPermission authenticated user
 * @apiParam {String} product  Deal product.
 * @apiParam {String} client_id Deal Client ID.
 * @apiParam {String} metaclient_name Deal Metaclient name.
 * @apiParam {String} metaclient_phone Deal Metaclient phone.
 * @apiParam {String} metaclient_email Deal Metaclient email.
 * @apiParam {String} metaclient_name Deal Metaclient name.
 * @apiParam {String} metaclient_phone Deal Metaclient phone.
 * @apiParam {String} metaclient_email Deal Metaclient email.
 * @apiParam {String} [metaclient_org_name] Deal Metaclient Organization name. 
 * @apiParam {String} [metaclient_org_regid] Deal Metaclient Organization registration ID.
 * @apiParam {String} [metaclient_org_address] Deal Metaclient Organization address.
 * @apiParam {String} [metaclient_org_phone] Deal Metaclient Organization phone.
 * @apiParam {String} [metaclient_org_email] Deal Metaclient Organization email.
 * @apiParam {String} [owner_user_id] Deal client owner User ID.
 *
 * @apiSuccess {String} JSON string with Deal created.
 */
  app.get('/deals:page',VerifyToken,auth.can('list all deals'),deals_controller.list_all_deals);
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
