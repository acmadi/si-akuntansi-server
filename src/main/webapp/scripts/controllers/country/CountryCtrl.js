'use strict';


siakun.app.controller('CountryCtrl', ['$resource', '$timeout', '$state', '$scope', 'localStorageService', 'Dialog', 'Util', 'Config',
    function ($resource, $timeout, $state, $scope, localStorageService, Dialog, Util, Config) {

        $scope.addCountry = function () {
            $scope.data.recordInForm = {};
            $state.$current.data = $scope.data;
            $state.go(".entry", null, null);
        };

        $scope.editCountry = function (record) {
            $scope.data.recordInForm = record;
            $state.$current.data = $scope.data;
            $state.go(".entry", {id: record.id}, null);
        };


        $scope.removeCountry = function (record, idx) {
            var prompt = "Yakin akan menghapus record '" + record.name + "' ?";
            if (Dialog.confirm(prompt)) {
                Country.delete(record, function () {
                    $scope.data.dataset.data.splice(idx, 1);
                }, function (response) {
                    if (response.status === 422) {
                        Dialog.alert("Data tidak dapat dihapus");
                    }
                });
            }
        };

        $scope.columnClick = function (col) {
            if (col.isData) {
                if ($scope.data.queryParams.orderProperty === col.remoteField) {
                    $scope.data.queryParams.orderDir = Util.toggleOrdering($scope.data.queryParams.orderDir);
                } else {
                    $scope.data.queryParams.orderProperty = col.remoteField;
                }
                $scope.refresh();
            }
        };

        $scope.refresh = function () {
            $scope.data.queryParams.start = ($scope.data.paging.current - 1) * $scope.data.queryParams.count;

            Country.get($scope.data.queryParams, function (data) {
                $scope.data.dataset = data;
                var curr = $scope.data.paging.current;
                var shownPages = Config.pageCount;
                var dataPerPage = $scope.data.queryParams.count;
                var totalData = $scope.data.dataset.count;

                var paging = Util.generatePages(curr, shownPages, dataPerPage, totalData);
                $scope.data.paging.count = paging.count;
                $scope.data.paging.pages = paging.pages;

                if ($scope.data.paging.current > $scope.data.paging.count) {
                    $scope.data.paging.current = $scope.data.paging.count;
                    $scope.refresh();
                }
            });
        };


        $scope.toPage = function (page) {
            if (angular.isNumber(page)) {
                $scope.data.paging.current = page;
            } else if (page === "first") {
                $scope.data.paging.current = 1;
            } else if (page === "prev") {
                $scope.data.paging.current--;
                if ($scope.data.paging.current < 1) {
                    $scope.data.paging.current = 1;
                }
            } else if (page === "next") {
                $scope.data.paging.current++;
                if ($scope.data.paging.current > $scope.data.paging.count) {
                    $scope.data.paging.current = $scope.data.paging.count;
                }
            } else if (page === "last") {
                $scope.data.paging.current = $scope.data.paging.count;
            }

            $scope.refresh();
        };


        $scope.inDataset = function (rec) {
            var arr = $scope.data.dataset.data;
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].id === rec.id) {
                    return true;
                }

            }
            return false;
        };

        $scope.updateRecordValue = function (rec) {
            var arr = $scope.data.dataset.data;
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].id === rec.id) {
                    arr[i] = rec;
                    return;
                }
            }
        };

        $scope.setDataCount = function (count) {
            $scope.data.queryParams.count = count;
            $scope.refresh();
        }

        // init controller
        var Country = $resource(Config.servletPath + '/country/:id', {},
            {
                delete: {
                    method: 'DELETE',
                    params: {
                        id: '@id'
                    }
                }
            });


        // init table columns

        if (Util.isUndefinedOrNull($state.$current.data.dataset)) {
            $scope.data = {
                dataset: [],
                notification: null,
                recordInForm: null,
                paging: {
                    current: 1,
                    total: 0,
                    pages: []
                },
                queryParams: {
                    start: 0,
                    count: 1,
                    keyword: null,
                    orderProperty: null,
                    orderDir: null
                }
            };
            $scope.needRefresh = true;
        } else {
            $scope.data = $state.$current.data;
            if ($scope.data.recordInForm != null) {
                if ($scope.inDataset($scope.data.recordInForm)) {
                    $scope.updateRecordValue($scope.data.recordInForm);
                } else {
                    $scope.data.dataset.data.unshift($scope.data.recordInForm);
                    $scope.data.dataset.count++;
                }
            }
            $scope.needRefresh = false;
        }

        $scope.data.columns = [
            {title: '', isData: false},
            {title: 'No', isData: false},
            {title: 'ISO 2', isData: true, remoteField: 'iso2'},
            {title: 'ISO 3', isData: true, remoteField: 'iso3'},
            {title: 'Nama', isData: true, remoteField: 'name'}
        ];

        if ($scope.needRefresh)
            $scope.refresh();
    }
])
;


