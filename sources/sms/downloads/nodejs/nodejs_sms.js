var http = require("http")
var crypto = require('crypto')

function md5(data) {
    var Buffer = require("buffer").Buffer;
    var buf = new Buffer(data);
    var str = buf.toString("binary");
    var crypto = require("crypto");
    return crypto.createHash("md5").update(str).digest("hex");
} 

function sortDict(dict){
    var dict2={},
        keys = Object.keys(dict).sort();
    for (var i = 0, n = keys.length, key; i < n; ++i) {
        key = keys[i];
        console.log(key);
        dict2[key] = dict[key];
    }
    return dict2;
}

var smsKey = '***';

var param = {
    'msgType':0,
    'smsUser':'***',
    'templateId' : 1,  
    'phone' : 13112345678, 
    'vars' : '{%code%:123456}'
}

sorted_param = sortDict(param);

var param_str = "";
for(var key in sorted_param)
    param_str += (key + '=' + sorted_param[key] + '&')
var param_str = smsKey + '&' + param_str + smsKey;
var sign = md5(param_str);
param['signature'] = sign.toUpperCase();


data = require('querystring').stringify(param); 
var options = {
    host:"www.sendcloud.net",
    port:80,
    path:"/smsapi/send",
    method:"POST"
}
options.path = options.path + '?' + data;
console.log(options.path);

var req = http.request(options, function(res){
    var responseStr = '';
    res.on('data', function (chunk) {
        responseStr += chunk;
    });
    res.on('end', function() {
        console.log(responseStr);
    });
});
req.end();