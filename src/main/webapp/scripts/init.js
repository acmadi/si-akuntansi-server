'use strict';

siakun.app = angular.module(siakun.appName, [
//        'ngRoute',
//        'ngCookies',
//        'ngResource',
//        'ngSanitize',
//        'ngRoute',
        'ui.router'
    ])
    .constant('Config', {
        appName: 'SiAkun',
        root: '/accounting_exploded'
    })
    .config(['$stateProvider', '$urlRouterProvider', siakun.func.initStates])

    // define app configuration
    .run(function ($rootScope) {
        $rootScope.$on('$viewContentLoaded', function () {
            $(document).foundation();
        });
    });
//    .config(function ($routeProvider) {
//        $routeProvider
//            .when('/', {
//                templateUrl: 'views/main.html',
//                controller: 'MainCtrl'
//            })
//            .when('/customer', {
//                templateUrl: 'views/customer.html',
//                controller: 'CustomerCtrl'
//            })
//            .otherwise({
//                redirectTo: '/'
//            });
//    })


//}


$(document).ready(function () {
    var config = {
        "container": ".container",
        "top": ".title-bar",
        "side": ".sidebar",
        "bottom": ".fixed-bottom",
        "spacing": 0
    };

    // layouting
    siakun.func.layout(config);
    $(window).resize(function () {
        siakun.func.layout(config);
    });
    $(document).foundation();
});