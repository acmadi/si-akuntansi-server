siakun.app.directive('dvDataPaging', ['Config', function (Config) {
    return {
        templateUrl: Config.root + '/template/dataPaging.html',
        restrict: 'E',
        scope: {
            pagingData: '=',
            clickFunc: '='
        },
        replace: true
    };
}]);