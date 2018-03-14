'use strict';
module.exports = function(app,auth) {

  var events_controller = require('../controllers/EventsController');
  var VerifyToken = require('../../auth/VerifyToken');

/**
 * @api {get} /events Request All Events
 * @apiName ListAllEvents
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with all Events.
 */

/**
 * @api {post} /events Creates a Event
 * @apiName CreateEvent
 * @apiGroup api
 *
 * @apiSuccess {String} JSON string with Event created.
 */
  app.get('/events',VerifyToken,auth.can('list all events'),events_controller.list_all_events);
  app.put('/events',VerifyToken,auth.can('create a event'),events_controller.create_a_event);

/**
 * @api {get} /events/:id Request a Event
 * @apiName ReadEvent 
 * @apiGroup api
 *
 * @apiParam {Number} id Event unique ID.
 *
 * @apiSuccess {String} JSON string with Event.
 */

/**
 * @api {put} /events/:id Update a Event
 * @apiName UpdateEvent
 * @apiGroup api
 *
 * @apiParam {Number} id Event unique ID.
 *
 * @apiSuccess {String} JSON string with Event unique ID confirmation.
 */

/**
 * @api {delete} /events/:id Delete a Event
 * @apiName DeleteEvent
 * @apiGroup api
 *
 * @apiParam {Number} id Event unique ID.
 *
 * @apiSuccess {String} JSON string with Event unique ID confirmation.
 */
  app.get('/events/:eventId',VerifyToken,auth.can('read a event'),events_controller.read_a_event);
  app.put('/events/:eventId',VerifyToken,auth.can('update a event'),events_controller.update_a_event)
  app.delete('/events/:eventId',VerifyToken,auth.can('delete a event'),events_controller.delete_a_event);

};
