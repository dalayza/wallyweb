'use strict';
module.exports = function(app) {
  var controller = require('../controllers/LeadPushController');

  app.route('/leads')
    .get(controller.list_all_leads)
    .post(controller.create_a_lead);


  app.route('/leads/:leadId')
    .get(controller.read_a_lead)
    .put(controller.update_a_lead)
    .delete(controller.delete_a_lead);
};
