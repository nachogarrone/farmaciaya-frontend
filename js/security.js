(function() {
    var token = Cookies.get('token');
    if (typeof token == 'undefined') {
        window.location = "login.html";
    }
})();
