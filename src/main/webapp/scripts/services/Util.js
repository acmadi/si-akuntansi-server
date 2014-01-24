'use strict';

var Util = function ($timeout) {
    this.$timeout = $timeout;
}

Util.prototype.initFoundation = function () {
    this.$timeout(function () {
        $(document).foundation();
    }, 5);
}

Util.prototype.isUndefinedOrNull = function (obj) {
    return !angular.isDefined(obj) || obj === null;
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

Util.prototype.toggleOrdering = function (ordering) {
    return ordering == "ASC" ? "DESC" : "ASC";
}

Util.prototype.generatePages = function (curr, numPage, numShownData, numData) {
    var paging = {
        count: 0,
        pages: []
    };

    paging.count = Math.ceil(numData / numShownData);
    var start = curr - Math.floor(numPage / 2);
    if (start < 1) start = 1;
    for (var i = start; i < start + numPage; i++) {
        if (i > paging.count)
            break;
        else
            paging.pages.push(i);
    }
    return paging;
}

Util.prototype.optionsDataCount = function () {
    return [1, 2, 3, 5, 10, 15, 20, 25];
}

siakun.app.service('Util', Util);