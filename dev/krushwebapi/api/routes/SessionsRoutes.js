'use strict';
module.exports = function(app) {
  var session_controller = require('../controllers/SessionsController');

/**
 * @api {get} /sessions Request All Sessions
 * @apiName ListAllSessions
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Sessions.
 */

/**
 * @api {post} /sessions Creates a Session
 * @apiName CreateSession
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with Session created.
 */

/**
 * @api {delete} /sessions/:id Delete a Session
 * @apiName DeleteSession
 * @apiGroup api
 *
 * @apiParam {Number} id Session unique ID.
 *
 * @apiSuccess {String} JSON string with Session unique ID confirmation.
 */
  app.route('/sessions')
    .get(session_controller.list_all_sessions)
    .post(session_controller.login)
    .delete(session_controller.logout);
};
