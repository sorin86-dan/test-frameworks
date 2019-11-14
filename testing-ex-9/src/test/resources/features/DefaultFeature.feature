Feature: Default story
#Scenario: Start SpringApp
Background:
  * def springClass =
"""
function() {
  var clazs = Java.type('com.testing.MainApp');
  return clazs.main(new Array("", ""));
  }
"""
  * def result = callonce springClass {}

Scenario: 1 When request to endpoint is made, an appropriate response is received
  Given url 'http://localhost:8080/message?service=TestServiceX'
  When method GET
  Then status 200
  And match $ == "Hello, TestServiceX!"

Scenario: 2 When request to endpoint is made, an appropriate response is received
  Given url 'http://localhost:8080/mssage?service=TestServiceX'
  When method GET
  Then status 404

Scenario: 3 When request to endpoint is made, an appropriate response is received
  Given url 'http://localhost:8080/message?serviceX=TestServiceX'
  When method GET
  Then status 400

Scenario: 4 When request to endpoint is made, an appropriate response is received
  Given url 'http://localhost:8080/exception'
  When method GET
  Then status 409
  And match $ == "Custom Exception"
