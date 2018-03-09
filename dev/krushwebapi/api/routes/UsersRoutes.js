'use strict';
module.exports = function(app,auth) {

  var users_controller = require('../controllers/UsersController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /users Request All Users
 * @apiName ListAllUsers
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Users.
 */

/**
 * @api {post} /users Creates a User
 * @apiName CreateUser
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with User created.
 */
  app.get('/users',VerifyToken,auth.can('list deals'),users_controller.list_all_users);
  app.post('/users',VerifyToken,users_controller.create_a_user);

/**
 * @api {get} /users/:id Request a User
 * @apiName ReadUser 
 * @apiGroup api
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User.
 */

/**
 * @api {put} /users/:id Update a User
 * @apiName UpdateUser
 * @apiGroup api
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User unique ID confirmation.
 */

/**
 * @api {delete} /users/:id Delete a User
 * @apiName DeleteUser
 * @apiGroup api
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User unique ID confirmation.
 */
  app.get('/users/:userId',VerifyToken,users_controller.read_a_user);
  app.put('/users/:userId',VerifyToken,users_controller.update_a_user);
  app.delete('/users/:userId',VerifyToken,users_controller.delete_a_user);
};
