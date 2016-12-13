import requests
import json
import urllib
import time
from hashlib import md5

def generate_md5(fp):
    m = md5()    
    m.update(fp)
    return m.hexdigest()

def sendx():
    url = 'http://www.sendcloud.net/smsapi/sendx'
    SMS_USER = '***'
    SMS_KEY = '***'

    tos = [{'phone':'13488888888', 'vars':{"%reason%":"r1"}}, {'phone':'13488888889', 'vars':{"%reason%":"r2"}}]

    param = {
        'smsUser': SMS_USER,
        'templateId' : 734,
        'msgType': 0,
        'tos': str(tos)
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

    print param
    res = requests.post(url,data=param)
    print res.text

def send():
    url = 'http://www.sendcloud.net/smsapi/send'
    SMS_USER = '***'
    SMS_KEY = '***'

    param = {
        'smsUser': SMS_USER,
        'templateId' : 381,
        'msgType': 0,
        'phone' : 13488888888,
        'vars' : '{"%content%":"liubidatest"}'
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

if __name__ == '__main__':
    #sendx()
    send()