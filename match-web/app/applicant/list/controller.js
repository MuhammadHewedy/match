'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants', {
        templateUrl: 'applicant/list/view.html',
        controller: 'ListApplicantCtrl'
    });
}])

.controller('ListApplicantCtrl', ['$scope', 'Applicant', '$location', '$routeParams', function($scope, Applicant, $location, $routeParams) {

    $scope.isCollapsed = false;

    $scope.getData = function() {
        Applicant.query({
            page: $scope.currentPage - 1
        }, function(list) {
            $scope.list = list;
            console.log($scope.list);
        });
    };

    $scope.getData();
}]);