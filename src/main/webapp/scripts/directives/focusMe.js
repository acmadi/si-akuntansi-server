siakun.app.directive('dvFocusMe', function ($timeout, $parse) {
        return {
            //scope: true,   // optionally create a child scope
            link: function ($scope, element, attrs) {
                $scope.$on(attrs.focusMe, function (value) {
                    $timeout(function () {
                        element[0].focus();
                    }, 0);

                });
            }
        };
    }
);