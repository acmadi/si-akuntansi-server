'use strict';

siakun.app.controller('CurrencyFormCtrl', ['$resource', '$timeout', '$state', '$stateParams', '$scope', '$q', 'Util', 'Config',
    function ($resource, $timeout, $state, $stateParams, $scope, $q, Util, Config) {
        $scope.Country = $resource(Config.servletPath + '/lookup/country', {});
        $scope.Currency = $resource(Config.servletPath + '/currency/:id', {},
            {
                getById: {
                    method: 'GET',
                    params: {
                        id: '@id'
                    }
                },
                save: {
                    method: 'POST',
                    url: Config.servletPath + '/currency/form'
                },
                update: {
                    method: 'PUT',
                    url: Config.servletPath + '/currency/form'
                }
            });

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
            $scope.Currency.save($scope.data.record, function (response) {
                $scope.data.parent.recordInForm = response;
                $state.go($state.$current.parent.name, null, null);
            }, function (response) {
                $scope.populateErrors(response.data.errors);
            });
        }

        $scope.doUpdate = function () {
            $scope.Currency.update($scope.data.record, function (response) {
                $scope.data.parent.recordInForm = response;
                $state.go($state.$current.parent.name, null, null);
            }, function (response) {
                $scope.populateErrors(response.data.errors);
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
            parent: {},
            errors: {},
            globalErrors: [],
            lookups: {},
            record: {
                id: null,
                version: null,
                code: null,
                name: null,
                country: null,
                symbol: null
            }
        };

        // save parent data
        if (!Util.isUndefinedOrNull($state.$current.parent.data)) {
            $scope.data.parent = $state.$current.parent.data;
        }

        // populate lookup
        var Lookup = $scope.Country.get({}, function (res) {
            $scope.data.lookups.country = res;
        });

        // get selected record
        if (!Util.isEmpty($stateParams.id)) {
            Lookup.$promise.then(function () {
                $scope.Currency.getById({"id": $stateParams.id}, function (res) {
                    $scope.data.record = res;
                    var lookups = $scope.data.lookups.country.data;
                    for (var i = 0; i < lookups.length; i++) {
                        if (lookups[i].id === $scope.data.record.country.id) {
                            $scope.data.record.country = lookups[i];
                            break;
                        }
                    }
                })
            });
        }

        $timeout(function () {
            $scope.$broadcast("startEdit");
        }, 0);

    }]
);