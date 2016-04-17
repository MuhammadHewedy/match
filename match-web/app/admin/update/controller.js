'use strict';

angular.module('myApp')

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admins/edit/:id', {
        templateUrl: 'admin/update/view.html',
        controller: 'UpdateAdminCtrl'
    });
}])

.controller('UpdateAdminCtrl', ['$scope', 'Admin', 'AlertService', '$routeParams', '$location', function($scope, Admin, AlertService, $routeParams, $location) {

    $scope.type = 'update'

    // -- get
    Admin.get({
        _id: $routeParams.id
    }, function(item) {
        $scope.item = item;
    })

    // -- update
    $scope.save = function() {
        $scope.item.$update(function() {
            $location.path('/admins');
            AlertService.success();
        }, function(error) {
            AlertService.error(error.data.message);
        })
    };

}]);
