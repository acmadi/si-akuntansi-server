'use strict';

siakun.app = angular.module('siakunApp', [
        'ngRoute',
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngRoute'
    ]).config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/customer', {
                templateUrl: 'views/customer.html',
                controller: 'CustomerCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }).run(function ($rootScope) {
        $rootScope.$on('$viewContentLoaded', function () {
            $(document).foundation();
        });
    });

$(document).ready(function () {

    var config = {
        "topBar": ".top-bar",
        "container": ".container",
        "spacing": 0
    }
    siakun.func.layout(config);

    $(document).foundation();

})