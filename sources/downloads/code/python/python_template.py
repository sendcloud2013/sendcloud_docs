#coding:utf-8                                                                   

import requests                                                                 

url = "http://sendcloud.sohu.com/webapi/mail.send_template.json"                         

API_USER = '...'
API_KEY = '...'

params = {
    "api_user": API_USER, # 使用api_user和api_key进行验证                       
    "api_key" : API_KEY,                                             
    "template_invoke_name" : "test_template",
    "substitution_vars" : '{"to": ["to1@domain.com", "to2@domain.com"], "sub": {"%name%": ["user1", "user2"], "%money%": ["1000", "2000"]}}',
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代
    "fromname" : "SendCloud",
    "subject" : "SendCloud python template",
    "resp_email_id": "true",
}

filename = "..."
display_filename = "..."

files = { "file1" : (display_filename, open(filename,"rb"))}

r = requests.post(url, files=files, data=params)

print r.text

