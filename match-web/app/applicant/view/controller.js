'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/applicants/view/:id', {
        templateUrl: 'applicant/view/view.html',
        controller: 'ViewApplicantCtrl'
    });
}])

.controller('ViewApplicantCtrl', ['$scope', 'Applicant', 'AlertService', '$routeParams', function($scope, Applicant, AlertService, $routeParams) {

    // -- get
    Applicant.get({
        _id: $routeParams.id
    }, function(item) {
        $scope.item = item;
    })
}]);
