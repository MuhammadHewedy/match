'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants/edit/:id', {
        templateUrl: 'applicant/update/view.html',
        controller: 'UpdateApplicantCtrl'
    });
}])

.controller('UpdateApplicantCtrl', ['$scope', 'Applicant', 'AlertService', '$routeParams', '$location', function($scope, Applicant, AlertService, $routeParams, $location) {

    $scope.type = 'update'

    // -- get
    Applicant.get({
        id: $routeParams.id
    }, function(item) {
        $scope.item = item;
    })

    // -- update
    $scope.save = function() {
        $scope.item.$update(function() {
            $location.path('/applicants/1');
            AlertService.success();
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

}]);