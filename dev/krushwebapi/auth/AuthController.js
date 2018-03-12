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


router.post('/register', function(req, res) {
  
  var hashedPassword = bcrypt.hashSync(req.body.passwd, 8);
  var new_user = new User({
    "name" : req.body.name,
    "email" : req.body.email,
    "phone" : req.body.phone,
    "role" : req.body.role,
    "passwd" : hashedPassword
  });
  new_user.save(function (err, user) {
    //if (err) return res.status(500).send("There was a problem registering the user.");
    if (err) return res.status(500).send(err);
    // create a token
    var token = jwt.sign({ id: user._id }, config.secret, {
      //expiresIn: 86400 // expires in 24 hours
      expiresIn: 432000 // expires in 5 days. TODO : refresh tokens
    });
    res.status(200).send({ auth: true, token: token });
  }); 
});

router.get('/me', VerifyToken, function(req, res, next) {
  User.findById(req.userId, { passwd: 0 }, function (err, user) {
    if (err) return res.status(500).send("There was a problem finding the user.");
    if (!user) return res.status(404).send("No user found.");
    
    res.status(200).send(user);
  });
});

router.post('/login', function(req, res) {
  User.findOne({ email: req.body.email }, function (err, user) {
    if (err) return res.status(500).send('Error on the server.');
    if (!user) return res.status(404).send('No user found.');
    var passwordIsValid = bcrypt.compareSync(req.body.password, user.password);
    if (!passwordIsValid) return res.status(401).send({ auth: false, token: null });
    var token = jwt.sign({ id: user._id }, config.secret, {
      expiresIn: 86400 // expires in 24 hours
    });
    res.status(200).send({ auth: true, token: token });
  });
});

// AuthController.js
router.get('/logout', function(req, res) {
  res.status(200).send({ auth: false, token: null });
});



// add this to the bottom of AuthController.js
module.exports = router;
