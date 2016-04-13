'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/login', {
        templateUrl: 'login/view.html',
        controller: 'LoginCtrl'
    });
}])

.controller('LoginCtrl', ['$scope', '$rootScope', 'LoginService', 'AlertService', function($scope, $rootScope, LoginService, AlertService) {

    $scope.credentials = {
        username: '',
        password: ''
    };

    $scope.login = function() {
        LoginService.login($scope.credentials).then(
            function() {
                $rootScope.authenticated = true;
                console.log('authenticated');
            },
            function(error) {
                $rootScope.authenticated = false;
                AlertService.error(error.data.message);
                console.error(error.data.message);
            });
    }
}]);