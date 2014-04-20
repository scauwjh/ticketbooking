/**
 * get url parameters
 * need to include map.js(user function)
 */
function getParameters() {
    var str = window.location.search;
    var map = new Map();
    params = new Array();
    str = decodeURI(str);
    params = str.split("=");
    /* substring the param then put the key and value into a map */
    var name = null;
    var key = null;
    for (var i = 0; i < params.length; i++){
        if (i == 0) {
            name = params[i].substring(1, params[i].length);
            continue;
        }
        var end = params[i].indexOf("&");
        if(end == -1) {
            end = params[i].length;
        }
        key = params[i].substring(0, end);
        map.put(name, key);
        name = params[i].substring(end + 1, params[i].length);
    }
    return map;
}