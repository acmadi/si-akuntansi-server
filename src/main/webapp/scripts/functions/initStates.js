siakun.func.initStates = function ($stateProvider, $urlRouterProvider) {
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
                    templateUrl: 'views/top-bar.html'
                },
                "content": {
                    controller: 'HomeCtrl',
                    templateUrl: 'views/home.html'
                },
                "bottomBar": {
                    controller: 'BottomBarCtrl',
                    templateUrl: 'views/bottom-bar.html'
                }
            }
        })
        .state("root.home", {
            url: "/home",
            views: {
                "content@": {
                    controller: 'HomeCtrl',
                    templateUrl: 'views/home.html'
                }
            }
        })
        .state("root.journal", {
            url: "/journal",
            views: {
                "content@": {
                    controller: 'JournalCtrl',
                    templateUrl: 'views/journal.html'
                }
            }
        })
        .state("root.reporting", {
            url: "/reporting",
            views: {
                "content@": {
                    controller: 'ReportingCtrl',
                    templateUrl: 'views/reporting.html'
                }
            }
        })

        // master data
        .state("root.account", {
            url: "/account",
            views: {
                "content@": {
                    controller: 'AccountCtrl',
                    templateUrl: 'views/account/list.html'
                }
            }
        })

        .state("root.country", {
            url: "/country",
            views: {
                "content@": {
                    controller: 'CountryCtrl',
                    templateUrl: 'views/country/list.html'
                }
            }
        })
        .state("root.country.edit", {
            url: "/country/:id",
            views: {
                "content@": {
                    controller: 'CountryCtrl',
                    templateUrl: 'views/country/form.html'
                }
            }
        })

        // fiscal period
        .state("root.fiscalPeriod", {
            url: "/fiscal-period",
            views: {
                "content@": {
                    controller: 'FiscalPeriodCtrl',
                    templateUrl: 'views/fiscal-period/list.html'
                }
            }
        })
        // fiscal period
        .state("root.currency", {
            url: "/currency",
            views: {
                "content@": {
                    controller: 'CurrencyCtrl',
                    templateUrl: 'views/currency/list.html'
                }
            }
        })
};



    