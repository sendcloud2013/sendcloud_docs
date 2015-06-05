## WEBAPI
    
```
import requests
import json
import urllib
import time
from hashlib import md5

def generate_md5(fp):
    m = md5()    
    m.update(fp)
    return m.hexdigest()

url = 'http://sendcloud.sohu.com/smsapi/send'
SMS_KEY = '***'

param = {
    'smsUser':'***',
    'smsKey' : SMS_KEY,
    'templateId' : 1,
    'phone' : 13412345678,
    'vars' : '{"%code%":"123456"}'
}

param_keys = list(param.keys())
param_keys.sort()

param_str = ""
for key in param_keys:
    param_str += key + '=' + str(param[key]) + '&'
param_str = param_str[:-1]

sign_str = SMS_KEY + '&' + param_str + '&' + SMS_KEY
sign = generate_md5(sign_str)

param['signature'] = sign

res = requests.post(url,data=param)
print res.text

```
