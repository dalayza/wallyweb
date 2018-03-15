'use strict';

var Zap = {
    get_session_info: function(bundle) {
        var api_key,
            api_key_request_payload,
            api_key_response;

        // Assemble the meta data for our key swap request
        api_key_request_payload = {
            method: 'POST',
            url: 'http://api.agenciamacro.com/auth/register',
            //data: JSON.stringify(bundle.auth_fields),
            data: '{"email":"wallyclient-zap3@agenciamacro.com","passwd":"admin123" , "role":"admin"}',
            headers: {
              'Content-Type': 'application/json',  // Could be anything.
              Accept: 'application/json'
            }
        };

        // Fire off the key exchange request.
        api_key_response = z.request(api_key_request_payload);

        // Or, alternatively, extract the `api_key` from returned JSON.
        api_key = JSON.parse(api_key_response.content).token;

        // This structure is an example. You may need to add
        // a differeny key name, or multiple keys, depending
        // on your API's requirements.
        return {'api_key': api_key};
    }
};
