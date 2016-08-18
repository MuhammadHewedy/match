'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admins', {
        templateUrl: 'admin/list/view.html',
        controller: 'ListAdminCtrl'
    });
}])

.controller('ListAdminCtrl', ['$scope', 'Admin', '$location', '$routeParams', function($scope, Admin, $location, $routeParams) {

    $scope.item = {}
    $scope.currentPage = 1

    $scope.getData = function() {
        console.log($scope.item);

        Admin.query({
            page: $scope.currentPage - 1
        }, function(list) {
            $scope.list = list;
            console.log($scope.list);
        });
    };

    $scope.getData();
}]);