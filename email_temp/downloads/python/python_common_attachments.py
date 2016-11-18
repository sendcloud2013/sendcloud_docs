#coding:utf-8                                                                   

import requests                                                                 

url = "http://sendcloud.sohu.com/webapi/mail.send.json"                         

API_USER = '...'
API_KEY = '...'

params = {                                                                      
    "api_user": API_USER, # 使用api_user和api_key进行验证                       
    "api_key" : API_KEY,                                             
    "to" : "to1@domain.com;to2@domain.com", # 收件人地址, 用正确邮件地址替代, 多个地址用';'分隔  
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代     
    "fromname" : "SendCloud",                                                    
    "subject" : "SendCloud python common",                              
    "html": "欢迎使用SendCloud",
    "resp_email_id": "true",
}                                                                               

filename1 = "1.txt"
display_filename_1 = "aaa"

filename2 = "2.txt"
display_filename_2 = "bbb"

files = [
    ("file", (display_filename_1, open(filename1, 'rb'),'application/octet-stream')),
    ("file", (display_filename_2, open(filename2, 'rb'),'application/octet-stream'))
]

r = requests.post(url, files=files, data=params)

print r.text

