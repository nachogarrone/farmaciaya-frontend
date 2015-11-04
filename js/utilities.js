function cerrarSesion() {
    Cookies.remove('token');
    Cookies.remove('name');
    Cookies.remove('last');
    $(location).attr('href', 'login.html');
}
var carrito;
