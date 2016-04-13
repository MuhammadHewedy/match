'use strict';


angular.module('myApp')

.factory('AlertService', ['$rootScope', function($rootScope) {
    return {
        success: function(msg) {
            var alert = {
                message: msg,
                type: 'success'
            }

            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
        },
        danger: function(msg) {
            var alert = {
                message: msg,
                type: 'danger'
            }
            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
        }
    }
}]);