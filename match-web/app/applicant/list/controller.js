'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants', {
        templateUrl: 'applicant/list/view.html',
        controller: 'ListApplicantCtrl'
    });
}])

.controller('ListApplicantCtrl', ['$scope', 'Applicant', '$location', '$routeParams', function($scope, Applicant, $location, $routeParams) {

    $scope.item = {}
    $scope.currentPage = 1

    $scope.getData = function() {
        console.log($scope.item);

        Applicant.query({
            page: $scope.currentPage - 1,
            id: $scope.item.id,
            gender: $scope.item.gender,
            age: $scope.item.age,
            nationality: $scope.item.nationality,
            countryOfPlace: $scope.item.countryOfPlace,
            weight: $scope.item.weight,
            length: $scope.item.length,
            job: $scope.item.job,
            religion: $scope.item.religion,
            maritalStatus: $scope.item.maritalStatus,
            haveKids: $scope.item.haveKids
        }, function(list) {
            $scope.list = list;
            console.log($scope.list);
        });
    };

    $scope.getData();
}]);
