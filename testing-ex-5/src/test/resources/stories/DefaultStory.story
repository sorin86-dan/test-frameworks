Scenario: 1 When request to endpoint is made, an appropriate response is received

Given the message endpoint
And has ?service=TestServiceX
When the request is sent to the endpoint
Then successful message is received

Scenario: 2 When request to endpoint is made, an appropriate response is received

Given the mssage endpoint
And has ?service=TestServiceX
When the request is sent to the endpoint
Then error code 404 is received

Scenario: 3 When request to endpoint is made, an appropriate response is received

Given the message endpoint
And has ?serviceX=TestServiceX
When the request is sent to the endpoint
Then error code 400 is received

Scenario: 4 When request to endpoint is made, an appropriate response is received

Given the exception endpoint
When the request is sent to the endpoint
Then exception message is received