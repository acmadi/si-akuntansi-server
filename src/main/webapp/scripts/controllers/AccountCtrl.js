'use strict';

siakun.app.controller('AccountCtrl', ['$resource', '$scope', 'Config', function ($resource, $scope, Config) {
    var Account = $resource(Config.servletPath + '/account/:id', {id: '@id'});
    $scope.countries = Account.get();

    $scope.refresh = function () {
        $scope.countries = Account.get();
    }
}]);
