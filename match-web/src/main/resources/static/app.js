'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute', 'ui.bootstrap', 'pascalprecht.translate', 'ngResource', 'ngMessages', 'frapontillo.bootstrap-switch', 'angular-loading-bar', 'ngAnimate'
]).
config(['$routeProvider', '$httpProvider', '$translateProvider', function($routeProvider, $httpProvider, $translateProvider) {

    $routeProvider.otherwise({
        redirectTo: '/home'
    });

    $translateProvider.useStaticFilesLoader({
        prefix: 'messages/',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage('ar');
    $translateProvider.useSanitizeValueStrategy('escape');

    $httpProvider.defaults.headers.common['Accept-Language'] = 'ar';
    $httpProvider.interceptors.push('authHttpResponseInterceptor');

}]).run(['$rootScope', '$location', 'LoginService', 'AlertService', function($rootScope, $location, LoginService, AlertService) {

    $rootScope.user = null;

    $rootScope.$on('$routeChangeStart', function(ev, next, curr) {

        if (next.$$route) {
            if (!curr) { //on page reload
                authenticateOnServer(LoginService, $rootScope, $location, next);
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
}]).factory('authHttpResponseInterceptor', ['$q', '$location', '$rootScope', function($q, $location, $rootScope) {
    return {
        responseError: function(rejection) {
            if (rejection.status === 401 && $location.$$path != '/login') {
                console.log("Response Error 401", rejection);
                $rootScope.user = null
                $location.path('/login')
            }
            return $q.reject(rejection);
        }
    }
}]);

function authenticateOnServer(LoginService, $rootScope, $location, next) {
    LoginService.login().then(
        function(user) {
            if (user) {
                $rootScope.user = user;
                $location.path(next.$route)
                console.log('authenticated: ', user.username);
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
