'use strict';

angular.module('myApp')

.value('MenuService', [{
    title: 'home',
    link: '/home'
}, {
    title: 'manage_admin',
    role: ['ROLE_SUPER_ADMIN'],
    link: '/admins'
}, {
    title: 'manage_applicant',
    role: ['ROLE_SUPER_ADMIN', 'ROLE_ADMIN'],
    link: '/applicants'
}]);