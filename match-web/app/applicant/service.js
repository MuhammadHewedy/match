'use strict';

// http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/

angular.module('myApp').factory('Applicant', ['$resource', function($resource) {

    return $resource('/api/applicants/:id', {
        id: '@id'
    }, {
        update: {
            method: 'PUT'
        },
        query: {
            isArray: false,
            params: {
                page: '@page',
                size: '@size',
                gender: '@gender',
                age: '@age',
                nationality: '@nationality',
                countryOfPlace: '@countryOfPlace',
                weight: '@weight',
                length: '@length',
                job: '@job',
                religion: '@religion',
                maritalStatus: '@maritalStatus',
                maritalStatus: '@maritalStatus'
            }
        }
    });

}]);