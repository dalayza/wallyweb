var jwt = require('jsonwebtoken');
var config = require('../config');
var mongoose = require('mongoose'),
  User = mongoose.model('Users');


function verifyToken(req, res, next) {
  var token = req.headers['x-access-token'];
  if (!token)
    return res.status(403).send({ auth: false, message: 'No token provided.' });
  jwt.verify(token, config.secret, function(err, decoded) {
    if (err)
    return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });
    // if everything good, save to request for use in other routes
    req.user = decoded;
    req.userId = decoded.id;

    User.findById(req.userId, { passwd: 0 }, function (err, user) {
       if (err) return res.status(500).send("There was a problem finding the user.");
       if (!user) return res.status(404).send("No user found.");

       req.user.role = user.role;
       req.user.id = user.id;
       next();
     });
  });
}
module.exports = verifyToken;
