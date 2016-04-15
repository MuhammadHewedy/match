'use strict';

// http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/

angular.module('myApp').factory('Admin', ['$resource', function($resource) {

    return $resource('/api/admins/:id', {
        id: '@id'
    }, {
        update: {
            method: 'PUT'
        },
        query: {
            isArray: false,
            params: {
                page: '@page',
                size: '@size'
            }
        }
    });

}]);