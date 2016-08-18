'use strict';

angular.module('myApp')

.controller('FilterApplicantCtrl', ['$scope', 'Applicant', '$location', '$route', 'LookupService', function($scope, Applicant, $location, $route, LookupService) {
    $scope.lookups = LookupService;

    $scope.cancel = function() {
        $route.reload();
    }

}]);