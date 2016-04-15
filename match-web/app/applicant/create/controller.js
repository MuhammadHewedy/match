'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants/create', {
        templateUrl: 'applicant/create/view.html',
        controller: 'CreateApplicantCtrl'
    });
}])

.controller('CreateApplicantCtrl', ['$scope', '$location', 'Applicant', 'AlertService', 'LookupService', function($scope, $location, Applicant, AlertService, LookupService) {

    $scope.type = 'create'

    $scope.item = new Applicant();
    $scope.save = function() {
        $scope.item.$save(function() {
            $location.path('/applicants');
            AlertService.success();
            // $scope.item = new Applicant();
            // $scope.form.$setPristine();
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

    $scope.lookups = LookupService;

}]);