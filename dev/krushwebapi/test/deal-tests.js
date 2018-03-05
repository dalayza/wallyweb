var chai = require('chai');
var mongoose = require('mongoose');
var chaiHttp = require('chai-http');

// TODO : create env vars to use same app.js
//var server = require('../test'); // test app

var should = chai.should();

chai.use(chaiHttp);

describe('Krushweb API Tests', function() {
 /* before(function() {
    mongoose.createConnection('mongodb://localhost/bot-test', myOptionsObj);
  });

  beforeEach(function(done) {
    // I do stuff like populating db
  });

  afterEach(function(done) {
    // I do stuff like deleting populated db
  });

  after(function() {
    mongoose.connection.close();
  });
*/
  describe('Deals', function() {

    it.only('should list ALL deals on /deals GET', function(done) {
      chai.request("http://localhost:8080")
        .get('/deals')
        .end(function(err, res){
          res.should.have.status(200);
          done();
        });
    });
  });
});
