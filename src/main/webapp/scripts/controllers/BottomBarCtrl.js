'use strict';

// var siakun = siakun || {};
siakun.app.controller('BottomBarCtrl', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.menus = ['Menu 1', 'Menu 2'];

    $scope.requestExist = function () {
        return $rootScope.pendingRequests > 0;
    };
}]);
