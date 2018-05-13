'use strict';
module.exports = function(app,auth) {
  var session_controller = require('../controllers/SessionsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /sessions Request All Sessions
 * @apiName ListAllSessions
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Sessions.
 */

/**
 * @api {post} /sessions Creates a Session
 * @apiName CreateSession
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with Session created.
 */

/**
 * @api {delete} /sessions/:id Delete a Session
 * @apiName DeleteSession
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id Session unique ID.
 *
 * @apiSuccess {String} JSON string with Session unique ID confirmation.
 */
  app.get('/sessions',VerifyToken,auth.can('list all sessions'),session_controller.list_all_sessions);
  app.post('/sessions',VerifyToken,auth.can('login'),session_controller.login);
  app.delete('/sessions',VerifyToken,auth.can('logout'),session_controller.logout);
};
