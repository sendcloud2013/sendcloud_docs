#coding:utf-8                                                                   

import requests                                                                 

url = "http://sendcloud.sohu.com/webapi/mail.send_template.json"                         

API_USER = '...'
API_KEY = '...'

params = {                                                                      
    "api_user": API_USER, # 使用api_user和api_key进行验证                       
    "api_key" : API_KEY,                                             
    "to" : "test@maillist.sendcloud.org", # 使用地址列表的别称地址
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代
    "fromname" : "SendCloud",                                                    
    "subject" : "SendCloud python template address_list",                              
    "template_invoke_name" : "sendcloud_template",
    "use_maillist" : "true",
    "resp_email_id": "true",
}                                                                               

filename = "..."
display_filename = "..."

files = { "file1" : (display_filename, open(filename,"rb"))}

r = requests.post(url, files=files, data=params)

print r.text


