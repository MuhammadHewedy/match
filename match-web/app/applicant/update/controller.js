'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants/edit/:id', {
        templateUrl: 'applicant/update/view.html',
        controller: 'UpdateApplicantCtrl'
    });
}])

.controller('UpdateApplicantCtrl', ['$scope', 'Applicant', 'AlertService', '$routeParams', function($scope, Applicant, AlertService, $routeParams) {

    $scope.type = 'update'

    // -- get
    Applicant.get({
        id: $routeParams.id
    }, function(item) {
        $scope.item = item;
    })

    // -- update
    $scope.update = function() {
        $scope.item.$update(function() {
            AlertService.success();
            $location.path('/applicants');
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

}]);