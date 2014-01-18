'use strict';

// var siakun = siakun || {};
siakun.app.controller('TopBarCtrl', function ($scope) {
    $scope.appTitle = 'SiAkun';
    $scope.menus = [
        {
            caption: 'Data Master',
            path:'#/data_master',
            children: [
                {
                    caption:'Akun',
                    path:'#/account'
                }
            ]
        },
        {
            caption: 'Pesan',
            path:'#/message'
        },
        {
            caption: 'Demo',
            path:'#'
        }
    ];

    $scope.$on('$includeContentLoaded', function () {
        $(document).foundation();
    });

});
