'use strict';

siakun.app.controller('CountryFormCtrl', ['$resource', '$timeout', '$state', '$stateParams', '$scope', 'Util', 'Config',
    function ($resource, $timeout, $state, $stateParams, $scope, Util, Config) {

        // resource
        var Country = $resource(Config.servletPath + '/country/:id', {},
            {
                getById: {
                    method: 'GET',
                    params: {
                        id: '@id'
                    }
                },
                update: { method: 'PUT' }
            });

        // init scope
        if (Util.isUndefinedOrNull($state.$current.parent.data)) {
            $scope.data = {
                parent: {},
                errors: {},
                globalErrors: []
            };
        } else {
            $scope.data = {
                parent: $state.$current.parent.data,
                errors: {},
                globalErrors: []
            };
        }


        if ($stateParams.id != "") {
            Country.getById($stateParams, function (response) {
                $scope.data.record = response;
            });
        } else {
            $scope.data.record = {
                id: null,
                version: null,
                iso2: null,
                iso3: null,
                name: null
            }
        }

        $timeout(function () {
            $scope.$broadcast("startEdit");
        }, 0);


        $scope.save = function () {
            if (Util.isUndefinedOrNull($scope.data.record.id)) {
                $scope.doSave();
            } else {
                $scope.doUpdate();
            }
            $state.$current.parent.page++;

        }

        $scope.cancel = function () {
            $scope.data.parent.recordInForm = null;
            $state.go($state.$current.parent.name, null, null);
        }

        $scope.doSave = function () {
            Country.save($scope.data.record, function (response) {
                $scope.data.parent.recordInForm = response;
                $state.go($state.$current.parent.name, null, null);
            }, function (response) {
                $scope.populateErrors(response.data.errors);
            });
        }

        $scope.doUpdate = function () {
            Country.update($scope.data.record, function (response) {
                $scope.data.parent.recordInForm = response;
                $state.go($state.$current.parent.name, null, null);
            }, function (response) {
                $scope.populateErrors(response.data.errors);
            });
        }

        $scope.populateErrors = function (errors) {
            console.log(errors);
            $scope.data.errors = [];
            $scope.data.globalErrors = [];

            errors.forEach(function (err) {
                if (angular.isDefined(err.field)) {
                    $scope.data.errors[err.field] = err;
                } else {
                    $scope.data.globalErrors.push(err);
                }
            });
        }
    }])
;