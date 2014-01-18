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
            },
            onEnter: function () {
                console.log("enter home state");
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
};



    