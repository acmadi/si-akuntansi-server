'use strict';

siakun.app.controller('FiscalPeriodFormCtrl', ['$resource', '$timeout', '$state', '$stateParams', '$scope', 'Util', 'Config',
    function ($resource, $timeout, $state, $stateParams, $scope, Util, Config) {

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
            FiscalPeriod.save($scope.data.record, function (response) {
                $scope.data.parent.recordInForm = response;
                $state.go($state.$current.parent.name, null, null);
            }, function (response) {
                $scope.populateErrors(response.data.errors);
            });
        }

        $scope.doUpdate = function () {
            FiscalPeriod.update($scope.data.record, function (response) {
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

        // resource
        var FiscalPeriod = $resource(Config.servletPath + '/fiscal-period/:id', {},
            {
                getById: {
                    method: 'GET',
                    params: {
                        id: '@id'
                    }
                },
                save: {
                    method: 'POST',
                    url: Config.servletPath + '/fiscal-period/form'
                },
                update: {
                    method: 'PUT',
                    url: Config.servletPath + '/fiscal-period/form'
                }
            });

        // initialize
        $scope.data = {
            parent: {},
            errors: {},
            globalErrors: []
        };
        if (!Util.isUndefinedOrNull($state.$current.parent.data)) {
            $scope.data.parent = $state.$current.parent.data;
        }


        if (Util.isEmpty($stateParams.id)) {
            $scope.data.record = {
                id: null,
                version: null,
                startAt: {
                    month: null,
                    year: null
                },
                endAt: {
                    month: null,
                    year: null
                }
            };
        } else {
            FiscalPeriod.getById($stateParams, function (response) {
                $scope.data.record = response;
            });
        }

        $timeout(function () {
            $scope.$broadcast("startEdit");
        }, 0);

    }]
);