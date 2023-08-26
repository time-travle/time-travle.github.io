/**
 * common validate tools
 */

function checkIdCard(obj) {
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test(obj) === false) {
        return false;
    } else {
        return true;
    }
}

function checkName(obj) {
    var name = /^[a-zA-Z\u4e00-\u9fa5]+$/;
    if (name.test(obj)) {
        return true;
    } else {
        return false;
    }
}

function checkEmail(obj) {
    var eamil = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if (eamil.test(obj)) {
        return true;
    } else {
        return false;
    }
}

function checkMobile(obj) {
    var mobile = /^1[3|4|5|7|8|][0-9]\d{8}$/;
    if (mobile.test(obj)) {
        return true;
    } else {
        return false;
    }
}

function checkLoginName(obj) {
    var regStr = /^[A-Za-z0-9]*$/;
    if (regStr.test(obj)) {
        return true;
    } else {
        return false;
    }
}

function checkNullObj1(obj) {
    for (var i in obj) { // 如果不为空，则会执行到这一步，返回true
        return true
    }
    return false // 如果为空,返回false
}

function checkNullObj2(obj) {
    if (JSON.stringify(data) === '{}') {
        return false // 如果为空,返回false
    }
    return true // 如果不为空，则会执行到这一步，返回true
}
