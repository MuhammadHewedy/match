'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants', {
        redirectTo: '/applicants/1'
    }).when('/applicants/:page', {
        templateUrl: 'applicant/list/view.html',
        controller: 'ListApplicantCtrl'
    });
}])

.controller('ListApplicantCtrl', ['$scope', 'Applicant', 'AlertService', '$routeParams', function($scope, Applicant, AlertService, $routeParams) {

    $scope.currentPage = $routeParams.page;

    $scope.goToPage = function() {
        $location.path('/applicants/page/' + $scope.currentPage);
    }

    $scope.getData = function() {
        Applicant.query({
            page: $routeParams.page - 1
        }, function(list) {
            $scope.list = list.content;
            console.log($scope.list.content);
        });
    }();

}]);