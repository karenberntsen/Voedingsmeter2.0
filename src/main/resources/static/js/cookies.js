/**
 * 
 */

function createCookie(cname, cvalue) {

        //var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";"+ "path=/";
        //alert(document.cookie + "cookie")
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return ca;
}

function checkCookie() {
    var user=getCookie("username");
    if (user != "") {
        return user;
    } else {return ""}
}

function removeCookies() {
    alert(document.cookie);
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}
