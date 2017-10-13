'use strict';
module.exports = function(app) {
  var clients_controller = require('../controllers/ClientsController');

  app.route('/clients')
    .get(clients_controller.list_all_clients)
    .post(clients_controller.create_a_client);


  app.route('/clients/:clientId')
    .get(clients_controller.read_a_client)
    .put(clients_controller.update_a_clientlead)
    .delete(clients_controller.delete_a_clientlead);
};
