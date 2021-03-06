var express = require('express');
var router = express.Router();

var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());

var jwt = require('jsonwebtoken');
var bcrypt = require('bcryptjs');
var config = require('../config');

var VerifyToken = require('./VerifyToken');

var mongoose = require('mongoose'),
  User = mongoose.model('Users');


/**
 * @api {post} /register Register a new user
 * @apiName RegisterUser
 * @apiGroup auth
 * @apiParam {String} email Required new user unique email.
 * @apiParam {String} passwd Required new user password.
 * @apiParam {String} [name] Optional new user name.
 * @apiParamExample {json} Request-Example:
 *     {
 *       "email": "xyz@mail.com",
 *       "passwd": "[A PASSWORD]",
         "name":"X de Y Z"
 *     }
 * @apiPermission none
 */
router.post('/register', function(req, res , next) {
  
  var hashedPassword = bcrypt.hashSync(req.body.passwd, 8);
  var new_user = new User({
    "name" : req.body.name,
    "email" : req.body.email,
    "phone" : req.body.phone,
    "passwd" : hashedPassword
  });
  new_user.save(function (err, user) {
    //if (err) return res.status(500).send("There was a problem registering the user.");
    //if (err) return res.status(500).send(err);
    if (err) return next(err);
    // create a token
    var token = jwt.sign({ id: user._id }, config.secret, {
      //expiresIn: 86400 // expires in 24 hours
      //expiresIn: 432000 // expires in 5 days. TODO : refresh tokens
    });

    res.status(200).send({ auth: true, token: token });
  }); 
});

/**
 * @api {get} /me Identifies a User.
 * @apiName IdentifyUser
 * @apiGroup auth
 * @apiPermission authenticated user
 */
router.get('/me', VerifyToken, function(req, res, next) {
  User.findById(req.userId, { passwd: 0 }, function (err, user,next) {
    //if (err) return res.status(500).send("There was a problem finding the user.");
    if (err) return next(err);
    if (!user) return res.status(404).send("No user found.");
    
    res.status(200).send(user);
  });
});

/**
 * @api {post} /login Login a user
 * @apiName LoginUser
 * @apiGroup auth
 * @apiParamExample {json} Request-Example:
 *     {
 *       "email": "xyz@mail.com",
 *       "passwd": "[A PASSWORD]",
 *     }
 * @apiPermission authenticated user
 */
router.post('/login',VerifyToken, function(req, res,next) {
  // TODO : trigger /sessions
  User.findOne({ email: req.body.email }, function (err, user,next) {
    //if (err) return res.status(500).send('Error on the server.');
    if (err) return next(err);
    if (!user) return res.status(404).send('No user found.');
    var passwordIsValid = bcrypt.compareSync(req.body.passwd,user.passwd);
    if (!passwordIsValid) return res.status(401).send({ auth: false, token: null });
    var token = jwt.sign({ id: user._id }, config.secret, {
      expiresIn: 86400 // expires in 24 hours
    });

    res.status(200).send({ auth: true, token: token });
  });
});

/**
 * @api {post} /logout Logout a user
 * @apiName LogoutUser
 * @apiGroup auth
 * @apiPermission authenticated user
 */
router.get('/logout',VerifyToken, function(req, res,next) {

  // TODO : trigger /sessions
  res.status(200).send({ auth: false, token: null });
});



// add this to the bottom of AuthController.js
module.exports = router;
