siakun.func.initStates = function ($stateProvider, $urlRouterProvider, Config) {
    $urlRouterProvider
        // The `when` method says if the url is ever the 1st param, then redirect to the 2nd param
        // Here we are just setting up some convenience urls.
//        .when('/c?id', '/contacts/:id')
//        .when('/user/:id', '/contacts/:id')

        // If the url is ever invalid, e.g. '/asdf', then redirect to '/' aka the home state
        .otherwise('/home');

    // main

    $stateProvider
        .state("root", {
            url: '',
            abstract: true,
            views: {
                "topBar": {
                    controller: 'TopBarCtrl',
                    templateUrl: Config.root + '/views/top-bar.html'
                },
                "content": {
                    controller: 'HomeCtrl',
                    templateUrl: Config.root + '/views/home.html'
                },
                "bottomBar": {
                    controller: 'BottomBarCtrl',
                    templateUrl: Config.root + '/views/bottom-bar.html'
                }
            }
        })
        .state("root.home", {
            url: "/home",
            views: {
                "content@": {
                    controller: 'HomeCtrl',
                    templateUrl: Config.root + '/views/home.html'
                }
            }
        })
        .state("root.journal", {
            url: "/journal",
            views: {
                "content@": {
                    controller: 'JournalCtrl',
                    templateUrl: Config.root + '/views/journal.html'
                }
            }
        })
        .state("root.reporting", {
            url: "/reporting",
            views: {
                "content@": {
                    controller: 'ReportingCtrl',
                    templateUrl: Config.root + '/views/reporting.html'
                }
            }
        })

        // master data
        .state("root.account", {
            url: "/account",
            views: {
                "content@": {
                    controller: 'AccountCtrl',
                    templateUrl: Config.root + '/views/account/list.html'
                }
            }
        })
        .state("root.country", {
            url: "/country",
            views: {
                "content@": {
                    controller: 'CountryCtrl',
                    templateUrl: Config.root + '/views/country/list.html'
                }
            },
            data: {}

        })
        .state("root.country.entry", {
            url: "/:id",
            views: {
                "content@": {
                    controller: 'CountryFormCtrl',
                    templateUrl: Config.root + '/views/country/form.html'
                }
            }
        })

        // fiscal period
        .state("root.fiscalPeriod", {
            url: "/fiscal-period",
            views: {
                "content@": {
                    controller: 'FiscalPeriodCtrl',
                    templateUrl: Config.root + '/views/fiscal-period/list.html'
                }
            }
        })
        // fiscal period
        .state("root.currency", {
            url: "/currency",
            views: {
                "content@": {
                    controller: 'CurrencyCtrl',
                    templateUrl: Config.root + '/views/currency/list.html'
                }
            }
        })
}
;



    