'use strict';
module.exports = function(app) {
  var leads_controller = require('../controllers/LeadsController');

/**
 * @api {get} /leads Request All Leads
 * @apiName ListAllLeads
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Leads.
 */
  app.route('/leads')
    .get(leads_controller.list_all_leads)
    .post(leads_controller.create_a_lead);

/**
 * @api {get} /leads/:id Request a Lead
 * @apiName ReadLead 
 * @apiGroup api
 *
 * @apiParam {Number} id Lead unique ID.
 *
 * @apiSuccess {String} JSON string with Lead.
 */

/**
 * @api {put} /leads/:id Update a Lead
 * @apiName UpdateLead
 * @apiGroup api
 *
 * @apiParam {Number} id Lead unique ID.
 *
 * @apiSuccess {String} JSON string with Lead unique ID confirmation.
 */

/**
 * @api {delete} /leads/:id Delete a Lead
 * @apiName DeleteLead
 * @apiGroup api
 *
 * @apiParam {Number} id Lead unique ID.
 *
 * @apiSuccess {String} JSON string with Lead unique ID confirmation.
 */
  app.route('/leads/:leadId')
    .get(leads_controller.read_a_lead)
    .put(leads_controller.update_a_lead)
    .delete(leads_controller.delete_a_lead);

};
