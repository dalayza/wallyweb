'use strict';
module.exports = function(app) {
  var leads_controller = require('../controllers/LeadsController');

  app.route('/leads')
    .get(leads_controller.list_all_leads)
    .post(leads_controller.create_a_lead);


  app.route('/leads/:leadId')
    .get(leads_controller.read_a_lead)
    .put(leads_controller.update_a_lead)
    .delete(leads_controller.delete_a_lead);

};
