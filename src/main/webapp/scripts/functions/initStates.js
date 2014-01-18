siakun.func.initStates = function ($stateProvider, $urlRouterProvider) {
//    $urlRouterProvider
//        // The `when` method says if the url is ever the 1st param, then redirect to the 2nd param
//        // Here we are just setting up some convenience urls.
//        .when('/c?id', '/contacts/:id')
//        .when('/user/:id', '/contacts/:id')
//
//        // If the url is ever invalid, e.g. '/asdf', then redirect to '/' aka the home state
//        .otherwise('/');

    // main
    $stateProvider
        .state("home", {
            url: "/",
            views: {
                "sideMenu": {
                },
                "content": {
                    controller: "HomeCtrl",
                    templateUrl: siakun.root + "/views/home.html"
                }
            }
        })
        .state("data", {
            url: '/data',
            views: {
                "content": {
                    controller: "RegistrationCtrl",
                    templateUrl: siakun.root + "/view/registration/index.htm"
                }
            }
        })
};


    