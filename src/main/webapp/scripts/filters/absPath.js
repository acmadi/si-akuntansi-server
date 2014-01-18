'use strict';

siakun.app.filter('absPath', ['Config', function (Config) {
    return function (path) {
        return Config.root + "/" + path;
    }
}]);