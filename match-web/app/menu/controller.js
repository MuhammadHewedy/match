'use strict';

angular.module('myApp')

.controller('MenuCtrl', ['$rootScope', '$location', '$scope', 'LoginService', function($rootScope, $location, $scope, LoginService) {

    $rootScope.menu = [{
        title: 'manage_admin',
        role: ['SUPER_ADMIN_ROLE'],
        link: '/admin'
    }, {
        title: 'manage_applicant',
        role: ['SUPER_ADMIN_ROLE', 'ADMIN_ROLE'],
        link: '/applicant'
    }];

    $scope.logout = function() {
        LoginService.logout().then(function() {
            $location.path('/login');
            $rootScope.user = null;
        })
    }
}]);