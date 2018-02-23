'use strict';
module.exports = function(app) {
  var users_controller = require('../controllers/UsersController');

  app.route('/users')
    .get(users_controller.list_all_users)
    .post(users_controller.create_a_user);


  app.route('/users/:userId')
    .get(users_controller.read_a_user)
    .put(users_controller.update_a_user)
    .delete(users_controller.delete_a_user);
};
