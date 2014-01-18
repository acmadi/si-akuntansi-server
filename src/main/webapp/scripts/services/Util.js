var Util = function ($timeout) {
    this.$timeout = $timeout;
}

Util.prototype.initFoundation = function () {
    this.$timeout(function () {
        $(document).foundation();
    }, 5);
}

Util.prototype.randomStr = function (len, charSet) {
    charSet = charSet || 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var randomString = '';
    for (var i = 0; i < len; i++) {
        var randomPoz = Math.floor(Math.random() * charSet.length);
        randomString += charSet.substring(randomPoz, randomPoz + 1);
    }
    return randomString;
};


siakun.app.service('Util', Util);