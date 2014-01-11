'use strict';

var siakun = siakun || {};
siakun.app = angular.module('siakunApp');

siakun.app.controller('MainCtrl', ['$scope', function (scope) {
    scope.repeats = 6;
}]);