'use strict';
module.exports = function(app) {
  var events_controller = require('../controllers/EventsController');

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
  app.route('/events')
    .get(events_controller.list_all_events)
    .post(events_controller.create_a_event);

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

  app.route('/events/:eventId')
    .get(events_controller.read_a_event)
    .put(events_controller.update_a_event)
    .delete(events_controller.delete_a_event);

};
