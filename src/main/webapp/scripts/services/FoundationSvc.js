var FoundationSvc = function ($timeout) {
    this.$timeout = $timeout;
}

FoundationSvc.prototype.init = function () {
    this.$timeout(function () {
        $(document).foundation();
    }, 5);
};


siakun.app.service('FoundationSvc', FoundationSvc);