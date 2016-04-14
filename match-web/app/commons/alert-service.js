'use strict';


angular.module('myApp')

.factory('AlertService', ['$rootScope', function($rootScope) {
    return {
        success: function(msg, link) {
            var alert = {
                message: msg ? msg : 'success',
                type: 'success',
                link: link
            }

            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
        },
        error: function(msg, link) {
            var alert = {
                message: msg ? msg : 'fail',
                type: 'danger',
                link: link
            }
            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
        },
        reset: function() {
            $rootScope.alerts = [];
        }
    }
}]);