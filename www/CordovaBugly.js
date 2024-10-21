var exec = require('cordova/exec');

var CordovaBugly = function () { };

// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'CordovaBugly', 'coolMethod', [arg0]);
// };

// CordovaBugly.prototype.sayHello = function (arg0, success, error) {
//     console.log('sayHello from plugin.js :', arg0);
//     exec(success, error, 'CordovaBugly', 'sayHello', [arg0]);
// };

CordovaBugly.prototype.sayHello = function (arg0, success, error) {
    
    exec(function(result) {
        console.log('sayHello exec success:', result);
        success(result);
    }, function(err) {
        console.log('sayHello exec error:', err);
        error(err);
    }, 'CordovaBugly', 'sayHello', [arg0]);
    
    console.log('sayHello from plugin.js - end');
};

CordovaBugly.prototype.testCrash = function () {
    exec(null, null, 'CordovaBugly', 'testCrash', []);
};


CordovaBugly.install = function () {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.CordovaBugly = new CordovaBugly();
    return window.plugins.CordovaBugly;
};


cordova.addConstructor(CordovaBugly.install);
