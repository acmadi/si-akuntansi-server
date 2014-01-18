'use strict';

siakun.app.controller('CountryCtrl', ['$resource', '$scope', 'Config', 'Util', function ($resource, $scope, Config, Util) {
    var Country = $resource(Config.servletPath + '/country/:id', {id: '@id'});
    $scope.countries = Country.get();

    $scope.highlightedRow = -1;

    $scope.refresh = function () {
        $scope.countries = Country.get();
    }

    $scope.add = function () {
        var newCountry = {
            "iso2": Util.randomStr(2),
            "iso3": Util.randomStr(3),
            "name": Util.randomStr(10)
        };
        newCountry = Country.save(newCountry);
        $scope.countries.data.push(newCountry);
    }

    $scope.mouseOver = function (index) {
        $scope.highlightedRow = index;
    }

    $scope.mouseLeave = function (index) {
        $scope.highlightedRow = -1;
    }

    $scope.showActionPane = function(index) {
        return $scope.highlightedRow === index;
    }

    $scope.removeCountry = function (country) {
        Country.remove(country);
        var data = $scope.countries.data;
        var idx = -1;
        for (var i = 0; i < data.length; i++) {
            if (data[i].id === country.id) {
                data.splice(i, 1);
                break;
            }
        }

    }
}]);
