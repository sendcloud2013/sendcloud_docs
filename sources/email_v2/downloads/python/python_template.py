#coding:utf-8                                                                   

import requests, json                                                                

url = "http://api.sendcloud.net/apiv2/mail/sendtemplate"                         

API_USER = '...'
API_KEY = '...'

xsmtpapi = {
    'to': ['test1@ifaxin.com', 'test2@ifaxin.com'],
    'sub': {
        '%name%': ['user1', 'user2'],
        '%money%': ['1000', '2000'],
    }
}

params = {
    "apiUser": API_USER, # 使用apiUser和apiKey进行验证                       
    "apiKey" : API_KEY,                                             
    "templateInvokeName" : "test12346",
    "xsmtpapi" : json.dumps(xsmtpapi),
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代
    "fromName" : "SendCloud",
    "subject" : "SendCloud python template",
    "respEmailId": "true",
}

filename = "./test.txt"
display_filename = "filename"

files = { "attachments" : (display_filename, open(filename,"rb"))}

r = requests.post(url, files=files, data=params)

print r.text
