"use strict";

siakun.func.layout = function (options) {
    var defaults = {
        container: ".container",
        top: ".title-bar",
        side: ".sidebar",
        bottom: ".bottom-bar",
        spacing: 0
    }
    var options = $.extend(defaults, options);

    var hTop = $(options.top).height();
    var hBottom = $(options.bottom).height();
//    $(options.container).css({
//        "margin-top": hTop + "px",
//        "padding": "0px 0px 0px 0px"
//    });

    $(options.container).css({
//        "margin-top": hTop,
        "padding": "0px 0px 0px 0px",
        "top": (hTop) + "px",
        "position": "absolute"
    });
//    var hBottom = $(options.bottom).height();
//
//    var hSide = window.innerHeight - hTop - hBottom;
//    $(options.side).height(hSide);
};