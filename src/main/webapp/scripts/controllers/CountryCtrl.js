'use strict';


siakun.app.controller('CountryCtrl', ['$resource', '$timeout', '$state', '$scope', 'localStorageService', 'Dialog', 'Util', 'Config',

    function ($resource, $timeout, $state, $scope, localStorageService, Dialog, Util, Config) {
        $scope.Country = $resource(Config.servletPath + '/country/:id', {},
            {
                query: {
                    url: Config.servletPath + '/country/browse',
                    method: 'POST'
                },
                delete: {
                    url: Config.servletPath + '/country/:id',
                    method: 'DELETE',
                    params: {
                        id: '@id'
                    }
                }
            });


        $scope.addRecord = function () {
            $scope.data.recordInForm = {};
            $state.$current.data = $scope.data;
            $state.go(".form", null, null);
        };

        $scope.editRecord = function (record) {
            $scope.data.recordInForm = record;
            $state.$current.data = $scope.data;
            $state.go(".form", {id: record.id}, null);
        };


        $scope.removeRecord = function (record, idx) {
            var prompt = "Yakin akan menghapus record '" + record.name + "' ?";
            if (Dialog.confirm(prompt)) {
                $scope.Country.delete(record, function () {
                    $scope.data.dataset.data.splice(idx, 1);
                    if ($scope.data.dataset.data.length === 0) {
                        $scope.refresh();
                    }
                }, function (response) {
                    if (response.status === 422) {
                        Dialog.alert("Data tidak dapat dihapus");
                    }
                });
            }
        };

        $scope.columnClick = function ($event, column) {
            if (column.isData) {
                var orders = $scope.data.queryParams.orders;
                if (!$event.ctrlKey) {
                    if (orders.length === 0) {
                        var order = {
                            property: column.remoteProperty,
                            ordering: column.ordering
                        };
                        orders.push(order);
                    } else {
                        var old = orders[0];
                        if (old.property === column.remoteProperty) {
                            column.ordering = Util.toggleOrdering(column.ordering);
                        }

                        var order = {
                            property: column.remoteProperty,
                            ordering: column.ordering
                        };

                        orders.length = 0; // clean array
                        orders.push(order);
                    }
                } else {
                    // check jika column sudah ada dalam ordering
                    var inOrdering = false;
                    for (var i = 0; i < orders.length; i++) {
                        if (column.remoteProperty === orders[i].property) {
                            column.ordering = Util.toggleOrdering(column.ordering);
                            orders[i].ordering = column.ordering;
                            inOrdering = true;
                        }
                    }

                    if (!inOrdering) {
                        var order = {
                            property: column.remoteProperty,
                            ordering: column.ordering
                        };
                        orders.push(order);
                    }
                }
                $scope.refresh();
            }
        };

        $scope.updatePaging = function () {
            var curr = $scope.data.paging.current;
            if (curr < 1) curr = 1;
            var shownPages = Config.pageCount;
            var dataPerPage = $scope.data.queryParams.count;
            var totalData = $scope.data.dataset.count;

            var paging = Util.generatePages(curr, shownPages, dataPerPage, totalData);
            $scope.data.paging.count = paging.count;
            $scope.data.paging.pages = paging.pages;

            if ($scope.data.paging.current > $scope.data.paging.count && $scope.data.paging.count > 0) {
                $scope.data.paging.current = $scope.data.paging.count;
            } else if ($scope.data.paging.current < 1) {
                $scope.data.paging.current = 1;
            }
        };

        $scope.refresh = function () {
            $scope.data.queryParams.start = ($scope.data.paging.current - 1) * $scope.data.queryParams.count;
            $scope.Country.query({}, $scope.data.queryParams, function (data) {
                $scope.data.dataset = data;
                $scope.updatePaging();
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


        $scope.mouseOver = function (event, index) {
            $scope.hoverRow = index;
            console.log($scope.hoverRow);
        }

        $scope.mouseLeave = function (event) {
            $scope.hoverRow = -1;
        }

        $scope.isHover = function ($index) {
            return $scope.hoverRow === $index;
        }

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

        if (Util.isUndefinedOrNull($state.$current.data.dataset)) {
            $scope.data = {
                dataset: [],
                notification: null,
                recordInForm: null,
                paging: {
                    current: 1, total: 0, pages: []
                },
                queryParams: {
                    start: 0, count: 2, keyword: null, orders: []
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
                    $scope.updatePaging();
                }
            }
            $scope.needRefresh = false;
        }

        $scope.data.columns = [
            {title: '', isData: false, className: 'indicator'},
            {title: 'No', isData: false},
            {title: 'ISO 2', isData: true, remoteProperty: 'iso2', ordering: "ASC"},
            {title: 'ISO 3', isData: true, remoteProperty: 'iso3', ordering: "ASC"},
            {title: 'Nama', isData: true, remoteProperty: 'name', ordering: "ASC"}
        ];


        $scope.hoverRow = -1;

        if ($scope.needRefresh)
            $scope.refresh();
    }
])
;


