'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admins/create', {
        templateUrl: 'admin/create/view.html',
        controller: 'CreateAdminCtrl'
    });
}])

.controller('CreateAdminCtrl', ['$scope', '$location', 'Admin', 'AlertService', function($scope, $location, Admin, AlertService) {

    $scope.type = 'create'

    $scope.item = new Admin();
    $scope.item.enabled = true;

    $scope.save = function() {
        $scope.item.$save(function() {
            $location.path('/admins');
            AlertService.success();
            // $scope.item = new Admin();
            // $scope.form.$setPristine();
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

}]);