var Dialog = function ($window, $timeout) {
    this.$timeout = $timeout;
    this.$window = $window;
}

Dialog.prototype.confirm = function (prompt) {
    var result = this.$window.confirm(prompt);
    return result === true;
}

Dialog.prototype.alert = function (prompt) {
    this.$window.alert(prompt);
}


siakun.app.service('Dialog', Dialog);