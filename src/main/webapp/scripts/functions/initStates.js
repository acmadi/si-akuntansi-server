siakun.func.initStates = function ($stateProvider, $urlRouterProvider, Config) {
    $urlRouterProvider
        // The `when` method says if the url is ever the 1st param, then redirect to the 2nd param
        // Here we are just setting up some convenience urls.
//        .when('/c?id', '/contacts/:id')
//        .when('/user/:id', '/contacts/:id')

        // If the url is ever invalid, e.g. '/asdf', then redirect to '/' aka the home state
        .otherwise('/secure');

    // main

    $stateProvider
        .state("login", {
            url: '/login',
            views: {
                "content": {
                    controller: 'LoginCtrl',
                    templateUrl: Config.root + '/views/login.html'
                }
            }
        })
        .state("secure", {
            url: "/secure",
            views: {
                "content": {
                    templateUrl: Config.root + '/views/secure.html',
                    controller: 'SecureCtrl'
                }
            }
        })
        .state("secure.home", {
            url: "/home",
            views: {
                "content@secure": {
                    controller: 'HomeCtrl',
                    templateUrl: Config.root + '/views/home.html'
                }
            },
            data: {}
        })
        .state("secure.country", {
            url: "/country",
            views: {
                "content@secure": {
                    controller: 'CountryCtrl',
                    templateUrl: Config.root + '/views/country/list.html'
                }
            },
            data: {}
        })
        .state("secure.country.form", {
            url: "/form/:id",
            views: {
                "content@secure": {
                    controller: 'CountryFormCtrl',
                    templateUrl: Config.root + '/views/country/form.html'
                }
            }
        })
    //        // fiscal period
        .state("secure.fiscalPeriod", {
            url: "/fiscal-period",
            views: {
                "content@secure": {
                    controller: 'FiscalPeriodCtrl',
                    templateUrl: Config.root + '/views/fiscal-period/list.html'
                }
            },
            data: {}
        })
        .state("secure.fiscalPeriod.form", {
            url: "/form/:id",
            views: {
                "content@secure": {
                    controller: 'FiscalPeriodFormCtrl',
                    templateUrl: Config.root + '/views/fiscal-period/form.html'
                }
            }
        })
        // currency (mata uang)
        .state("secure.currency", {
            url: "/currency",
            views: {
                "content@secure": {
                    controller: 'CurrencyCtrl',
                    templateUrl: Config.root + '/views/currency/list.html'
                }
            },
            data: {}
        })
        .state("secure.currency.form", {
            url: "/form/:id",
            views: {
                "content@secure": {
                    controller: 'CurrencyFormCtrl',
                    templateUrl: Config.root + '/views/currency/form.html'
                }
            }
        })


//
//        .state("secure.journal", {
//            url: "/secure/journal",
//            views: {
//                "content@": {
//                    controller: 'JournalCtrl',
//                    templateUrl: Config.root + '/views/journal.html'
//                }
//            }
//        })
//        .state("secure.reporting", {
//            url: "/secure/reporting",
//            views: {
//                "content@": {
//                    controller: 'ReportingCtrl',
//                    templateUrl: Config.root + '/views/reporting.html'
//                }
//            }
//        })
//
//        // master data
//        .state("secure.account", {
//            url: "/secure/account",
//            views: {
//                "content@": {
//                    controller: 'AccountCtrl',
//                    templateUrl: Config.root + '/views/account/list.html'
//                }
//            }
//        })
//
//

}
;



    