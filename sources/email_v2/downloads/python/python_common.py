#encoding:utf-8
import requests

url = "http://api.sendcloud.net/apiv2/mail/send"                         

API_USER = '...'
API_KEY = '...'

params = {                                                                      
    "apiUser": API_USER, # 使用api_user和api_key进行验证                       
    "apiKey" : API_KEY,                                             
    "to" : "test@ifaxin.com", # 收件人地址, 用正确邮件地址替代, 多个地址用';'分隔  
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代     
    "fromName" : "SendCloud",                                                    
    "subject" : "SendCloud python common",                              
    "html": "欢迎使用SendCloud"
}                                                                               

filename1 = "1.txt"
display_filename_1 = "aaa"

files = {
    "attachments" : (display_filename_1, open(filename1, 'rb'),'application/octet-stream')
}

r = requests.post(url, files=files, data=params)

print r.text
