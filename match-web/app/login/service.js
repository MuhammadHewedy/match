'use strict';

angular.module('myApp')

.factory('LoginService', ['$http', 'Base64Service', function($http, Base64Service) {
    return {
        login: function(credentials) {
            var auth = 'Basic ' + Base64Service.encode(credentials.username + ':' + credentials.password);
            return $http({
                method: 'POST',
                url: '/api/auth/login',
                headers: {
                    'Authorization': auth
                }
            }).then(function(response) {
                return response.data;
            });
        }
    }
}]);