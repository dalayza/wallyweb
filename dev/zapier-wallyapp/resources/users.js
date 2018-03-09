const _sharedBaseUrl = 'http://api.agenciamacro.com';

const getUser = (z, bundle) => {
  return z.request({
      url: `${_sharedBaseUrl}/users/${bundle.inputData.id}`,
    })
    .then((response) => JSON.parse(response.content));
};

const listUsers = (z, bundle) => {
  return z.request({
      url: _sharedBaseUrl + '/users',
      params: {
      }
    })
    .then((response) => JSON.parse(response.content));
};

const createUser = (z, bundle) => {
  const requestOptions = {
    url: _sharedBaseUrl + '/users',
    method: 'POST',
    body: JSON.stringify({
      name: bundle.inputData.name,
      email: bundle.inputData.email,
      phone: bundle.inputData.phone,
      passwd: bundle.inputData.passwd
    }),
    headers: {
      'content-type': 'application/json'
    }
  };

  return z.request(requestOptions)
    .then((response) => JSON.parse(response.content));
};

const searchUser = (z, bundle) => {
  return z.request({
      url: _sharedBaseUrl + '/users',
      params: {
        nameSearch: bundle.inputData.name
      }
    })
    .then((response) => {
      const matchingUsers = JSON.parse(response.content);

      // Only return the first matching user
      if (matchingUsers && matchingUsers.length) {
        return [matchingUsers[0]];
      }

      return [];
    });
};

// This file exports a User resource. The definition below contains all of the keys available,
// and implements the list and create methods.
module.exports = {
  key: 'user',
  noun: 'User',
  // The get method is used by Zapier to fetch a complete representation of a record. This is helpful when the HTTP
  // response from a create call only return an ID, or a search that only returns a minimuml representation of the
  // record. Zapier will follow these up with the get() to retrieve the entire object.
  get: {
    display: {
      label: 'Get User',
      description: 'Gets a user.',
    },
    operation: {
      inputFields: [
        {key: 'id', required: true},
      ],
      perform: getUser,
    },
  },
  // The list method on this resource becomes a Trigger on the app. Zapier will use polling to watch for new records
  list: {
    display: {
      label: 'New User',
      description: 'Trigger when a new user is added.',
    },
    operation: {
      inputFields: [
        {key: 'name', type: 'string', helpText: 'The name of the user.'},
      ],
      perform: listUsers,
    },
  },
  // If your app supports webhooks, you can define a hook method instead of a list method.
  // Zapier will turn this into a webhook Trigger on the app.
  // hook: {
  //
  // },

  // The create method on this resource becomes a Write on this app
  create: {
    display: {
      label: 'Create User',
      description: 'Creates a new user.',
    },
    operation: {
      inputFields: [
        {key: 'name', required: true, type: 'string'},
        {key: 'email', required: true, type: 'string'},
        {key: 'phone', required: true, type: 'string'},
        {key: 'passwd', required: false, type: 'string'}
      ],
      perform: createUser,
    },
  },
  // The search method on this resource becomes a Search on this app
  search: {
    display: {
      label: 'Find User',
      description: 'Finds an existing user by name.',
    },
    operation: {
      inputFields: [
        {key: 'name', required: true, type: 'string'},
      ],
      perform: searchUser,
    },
  },

  // In cases where Zapier needs to show an example record to the user, but we are unable to get a live example
  // from the API, Zapier will fallback to this hard-coded sample. It should reflect the data structure of
  // returned records, and have obviously dummy values that we can show to any user.
  sample: {
    id: 1,
    name: 'xyz',
    email: 'xyz@mail.com',
    phone: '1234-1234',
    passwd: 'xyz1234',
  },

  // If the resource can have fields that are custom on a per-user basis, define a function to fetch the custom
  // field definitions. The result will be used to augment the sample.
  //outputFields: () => { return []; }
  // Alternatively, a static field definition should be provided, to specify labels for the fields
  //outputFields: [
  //  {key: 'id', label: 'ID'},
  //  {key: 'createdAt', label: 'Created At'},
  //  {key: 'name', label: 'Name'},
  //  {key: 'directions', label: 'Directions'},
  //  {key: 'authorId', label: 'Author ID'},
  //  {key: 'style', label: 'Style'},
 // ]
};
