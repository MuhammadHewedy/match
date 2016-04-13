'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute'
]).
config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

    $routeProvider.otherwise({
        redirectTo: '/home'
    });

}]).run(['$rootScope', '$location', 'LoginService', function($rootScope, $location, LoginService) {

    $rootScope.user = null;

    $rootScope.$on('$routeChangeStart', function(ev, next, curr) {
        if (next.$$route) {
            if (!curr) { //on page reload
                authenticateOnServer(LoginService, $rootScope, $location);
            } else {
                if ($rootScope.user) {
                    if (next.$$route.originalPath == '/login') {
                        $location.path('/')
                    }
                } else {
                    $location.path('/login');
                }
            }
        }
    });
}]);

function authenticateOnServer(LoginService, $rootScope, $location) {
    LoginService.login().then(
        function(user) {
            if (user) {
                $rootScope.user = user;
                $location.path('/')
                console.log('authenticated: ', user);
            } else {
                $rootScope.user = null
                $location.path('/login')
            }
        },
        function() {
            $rootScope.user = null
            $location.path('/login')
        });
}