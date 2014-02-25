siakun.app.directive('dvDataCount', ['Config', 'Util', function (Config, Util) {
    return {
        templateUrl: Config.root + '/template/dataCount.html',
        restrict: 'E',
        scope: {
            current: '=',
            onSelect: '='
        },
        controller: function ($scope) {
            $scope.options = Util.optionsDataCount();
        },
        replace: true
    };
}]);