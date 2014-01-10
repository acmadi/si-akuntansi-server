'use strict';

describe('Controller: MainCtrl', function () {

    var scope, ctrl;

    beforeEach(module('siakunApp'));

    beforeEach(inject(function ($controller, $rootScope) {
        console.log($controller);
        scope = $rootScope.$new();
        ctrl = $controller('MainCtrl', {$scope: scope});
    }));
//

    it('test Main Ctrl', function () {
        expect(2).toBe(2);
    });


});
