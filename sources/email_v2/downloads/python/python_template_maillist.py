#coding:utf-8                                                                   

import requests                                                                 

url = "http://api.sendcloud.net/apiv2/mail/sendtemplate"                         

API_USER = '...'
API_KEY = '...'

params = {                                                                      
    "apiUser": API_USER, # 使用api_user和api_key进行验证                       
    "apiKey" : API_KEY,                                             
    "to" : "test@maillist.sendcloud.org", # 使用地址列表的别称地址
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代
    "fromName" : "SendCloud",                                                    
    "subject" : "SendCloud python template address_list",                              
    "templateInvokeName" : "test12346",
    "useAddressList" : "true",
    "respEmailId": "true",
}                                                                               

filename = "./test.txt"
display_filename = "filename"

files = { "attachments" : (urllib.quote(display_filename), open(filename,"rb"))}

r = requests.post(url, files=files, data=params)

print r.text
