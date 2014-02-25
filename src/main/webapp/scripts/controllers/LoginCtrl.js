'use strict';

siakun.app.controller('LoginCtrl', ['$resource', '$location', '$timeout', '$scope', 'Util', 'Config',
    function ($resource, $location, $timeout, $scope, Util, Config) {

        // resource
        $scope.Login = $resource(Config.servletPath + '/login', {},
            {
                login: {
                    method: 'POST',
                    url: Config.servletPath + '/login/validate'
                }
            });

        $scope.login = function () {
            $scope.Login.login($scope.data.form, function (res) {
                console.log("ok");
                console.log($location.url());
                $location.path("/home");
            }, function (res) {
                $scope.populateErrors(res.data.errors);
            });
        }


        $scope.populateErrors = function (errors) {
            $scope.data.errors = [];
            $scope.data.globalErrors = [];

            errors.forEach(function (err) {
                if (angular.isDefined(err.field)) {
                    Util.writeProperty($scope.data.errors, err.field, err);
                } else {
                    $scope.data.globalErrors.push(err);
                }
            });
        }


        // initialize
        $scope.data = {
            form: {
                userId: '', password: ''
            },
            errors: {},
            globalErrors: []
        };

        $timeout(function () {
            $scope.$broadcast("startEdit");
        }, 0);

    }]
);