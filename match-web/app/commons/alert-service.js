'use strict';


angular.module('myApp')

.factory('AlertService', ['$rootScope', function($rootScope) {
    return {
        success: function(msg, link, timeMillis) {
            var alert = {
                message: msg ? msg : 'success',
                type: 'success',
                link: link
            }
            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
            autoHide(timeMillis ? timeMillis : 500)
        },
        error: function(msg, link, timeMillis) {
            var alert = {
                message: msg ? msg : 'fail',
                type: 'danger',
                link: link
            }
            $rootScope.alerts = [];
            $rootScope.alerts.push(alert);
            autoHide(timeMillis ? timeMillis : 5000)
        }
    }
}]);

function autoHide(timeMillis) {
    setTimeout(function() {
        $('#alert').fadeOut('fast');
    }, timeMillis);
}