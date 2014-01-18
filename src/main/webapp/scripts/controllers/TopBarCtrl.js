'use strict';

// var siakun = siakun || {};
siakun.app.controller('MenubarCtrl', function ($scope) {
    $scope.appTitle = "SiAkun";
    $scope.menus = ["Data Master", "Pesan"];

    $scope.$on('$includeContentLoaded', function () {
        $(document).foundation();
        console.log("foundation init");
    });

});
