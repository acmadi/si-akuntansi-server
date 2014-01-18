'use strict';

siakun.app = angular.module(siakun.appName, [
        'ngRoute',
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ui.router'
    ])
    .constant('Config', {
        appName: 'SiAkun',
        root: '/accounting_exploded',
        servletPath: '/accounting_exploded/app'
    })
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {
        siakun.func.initStates($stateProvider, $urlRouterProvider);
        $httpProvider.interceptors.push('requestInterceptor');
    }])

    // define app configuration
    .run(['Util', '$rootScope', function (Util, $rootScope) {
        $rootScope.$on('$viewContentLoaded', function () {
            Util.initFoundation();
        });
    }]);
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