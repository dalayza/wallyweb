'use strict';
module.exports = function(app) {
  var events_controller = require('../controllers/EventsController');

  app.route('/events')
    .get(events_controller.list_all_events)
    .post(events_controller.create_a_event);


  app.route('/events/:eventId')
    .get(events_controller.read_a_event)
    .put(events_controller.update_a_event)
    .delete(events_controller.delete_a_event);
};
