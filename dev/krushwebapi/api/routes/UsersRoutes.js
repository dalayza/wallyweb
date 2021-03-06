'use strict';
module.exports = function(app,auth) {

  var users_controller = require('../controllers/UsersController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /users Request All Users
 * @apiName ListAllUsers
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with all Users.
 */

/**
 * @api {post} /users Creates a User
 * @apiName CreateUser
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiSuccess {String} JSON string with User created.
 */
  app.get('/users',VerifyToken,auth.can('list all users'),users_controller.list_all_users);
  app.post('/users',VerifyToken,auth.can('create a user'),users_controller.create_a_user);

/**
 * @api {get} /users/:id Request a User
 * @apiName ReadUser 
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User.
 */

/**
 * @api {put} /users/:id Update a User
 * @apiName UpdateUser
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User unique ID confirmation.
 */

/**
 * @api {delete} /users/:id Delete a User
 * @apiName DeleteUser
 * @apiGroup api
 * @apiPermission authenticated user
 *
 * @apiParam {Number} id User unique ID.
 *
 * @apiSuccess {String} JSON string with User unique ID confirmation.
 */
  app.get('/users/:userId',auth.can('read a user'),VerifyToken,users_controller.read_a_user);
  app.put('/users/:userId',VerifyToken,auth.can('update a user'),users_controller.update_a_user);
  app.delete('/users/:userId',VerifyToken,auth.can('delete a user'),users_controller.delete_a_user);
};
