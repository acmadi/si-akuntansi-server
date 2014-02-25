'use strict';

siakun.app.controller('SecureCtrl', ['$resource', '$location', '$timeout', '$rootScope', '$scope', 'Util', 'Config',
    function ($resource, $location, $timeout, $rootScope, $scope, Util, Config) {
        $scope.messageCount = 0;

        $scope.hasChildren = function (menu) {
            return menu.children != null && menu.children != undefined ?
                menu.children.length > 0 :
                false;

        }

        $scope.$on('$viewContentLoaded', function (event) {
        });

        // start message check thread
        $scope.updateMessageCount = function () {
            $scope.messageCount = Math.floor(Math.random() * 10);
            $timeout($scope.updateMessageCount, 1000);
        }

        $scope.requestExist = function () {
            return $rootScope.pendingRequests > 0;
        };


        // initialize
        $scope.appTitle = Config.appName;
        $scope.menus = {};
        $scope.menus.top = [
            {
                caption: 'Data Master',
                path: '#/data_master',
                children: [
                    {
                        caption: 'Akun', path: '#/secure/account'
                    },
                    {
                        caption: 'Pelanggan', path: '#/secure/customer'
                    },
                    {
                        caption: 'Mata Uang', path: '#/secure/currency'
                    },
                    {
                        caption: 'Tahun Fiskal', path: '#/secure/fiscal-period'
                    },
                    {
                        caption: 'Negara', path: '#/secure/country'
                    }
                ]
            },
            {
                caption: 'Setting',
                path: '#/secure/setting',
                children: [
                    {
                        caption: 'Period Fiskal',
                        path: '#/setting/fiscal-period'
                    }
                ]
            }
        ];

        // bottom menu
        $scope.menus.bottom = ['Menu 1', 'Menu 2'];

        $timeout($scope.updateMessageCount, 1000);
    }]
);