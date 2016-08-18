'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants/edit/:id', {
        templateUrl: 'applicant/update/view.html',
        controller: 'UpdateApplicantCtrl'
    });
}])

.controller('UpdateApplicantCtrl', ['$scope', 'Applicant', 'AlertService', '$routeParams', '$location', 'LookupService', function($scope, Applicant, AlertService, $routeParams, $location, LookupService) {

    $scope.type = 'update'

    // -- get
    Applicant.get({
        _id: $routeParams.id
    }, function(item) {
        $scope.item = item;

        if ($scope.item.hijab == null) {
            $scope.item.hijab = undefined
        }
        if ($scope.item.haveKids == null) {
            $scope.item.haveKids = undefined
        }

        if ($scope.item.likeToHaveKids == null) {
            $scope.item.likeToHaveKids = undefined
        }
    })

    // -- update
    $scope.save = function() {
        $scope.item.$update(function() {
            $location.path('/applicants');
            AlertService.success();
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

    $scope.lookups = LookupService;

}]);
