'use strict';

// var siakun = siakun || {};
siakun.app.controller('TopBarCtrl', ['Config', '$timeout', '$scope', 'Util',
    function (Config, $timeout, $scope, Util) {
        $scope.appTitle = Config.appName;
        $scope.menus = [
            {
                caption: 'Data Master',
                path: '#/data_master',
                children: [
                    {
                        caption: 'Akun',
                        path: '#/account'
                    },
                    {
                        caption: 'Pelanggan',
                        path: '#/customer'
                    },
                    {
                        caption: 'Mata Uang',
                        path: '#/currency'
                    },
                    {
                        caption: 'Tahun Fiskal',
                        path: '#/fiscal-period'
                    },
                    {
                        caption: 'Negara',
                        path: '#/country'
                    }
                ]
            }
        ];

        $scope.messageCount = 0;

        $scope.hasChildren = function (menu) {
            return menu.children != null && menu.children != undefined ?
                menu.children.length > 0 :
                false;

        }

        $scope.$on('$viewContentLoaded', function (event) {
            Util.initFoundation();
        });

        // start message check thread
        $scope.updateMessageCount = function () {
            $scope.messageCount = Math.floor(Math.random() * 10);
            $timeout($scope.updateMessageCount, 1000);
        }

        $timeout($scope.updateMessageCount, 1000);
    }]);
