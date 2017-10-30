'use strict';
module.exports = function(app) {
  var session_controller = require('../controllers/SessionsController');

  app.route('/sessions')
    .get(session_controller.list_all_sessions)

  app.route('/session')
    .post(session_controller.login)
    .delete(session_controller.logout);
};
