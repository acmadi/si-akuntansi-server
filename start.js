var express = require('express');
var app = express();

app.configure(function () {
    var path = "src/main/webapp/";
    app.use(
        "/",
        express.static(path)
    );
});

app.listen(3000);