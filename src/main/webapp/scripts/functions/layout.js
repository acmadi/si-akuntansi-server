"use strict";

siakun.func.layout = function (options) {
    var defaults = {
        topBar: ".top-bar",
        container: ".container",
        spacing: 0
    }
    var options = $.extend({}, defaults, options);
    var hTopBar = $(options.topBar).height();
    $(options.container).css("margin-top", hTopBar + options.spacing);
};