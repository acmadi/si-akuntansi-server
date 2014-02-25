'use strict';

siakun.app = angular.module(siakun.appName, [
        'ngRoute',
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngAnimate',
        'ui.router',
        'ui.keypress',
        'LocalStorageModule'
    ])
    .constant('Config', {
        appName: 'SiAkun',
        root: '/accounting_exploded',
        servletPath: '/accounting_exploded/app',
        pageCount: 10
    })
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', 'Config',
        function ($stateProvider, $urlRouterProvider, $httpProvider, Config) {
            siakun.func.initStates($stateProvider, $urlRouterProvider, Config);
            $httpProvider.interceptors.push('requestInterceptor');
        }]
    )
    // define app configuration
    .run(['$timeout', 'Util', '$rootScope', function ($timeout, Util, $rootScope) {
        $rootScope.$on('$viewContentLoaded', function () {
//            Util.initFoundation();
        });

        $rootScope.optionsDataCount = function () {
            return Util.optionsDataCount();
        };
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
//    var config = {
//        "container": ".container",
//        "top": ".title-bar",
//        "side": ".sidebar",
//        "bottom": ".fixed-bottom",
//        "spacing": 0
//    };

    // layouting
//    siakun.func.layout(config);
//    $(window).resize(function () {
//        siakun.func.layout(config);
//    });
//    $(document).foundation();
});