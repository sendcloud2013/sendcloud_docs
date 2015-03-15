#coding:utf-8                                                                   

import requests                                                                 

url = "http://sendcloud.sohu.com/webapi/mail.send.json"                         
API_USER = '...'
API_KEY = ''

params = {                                                                      
    "api_user": api_user, # 使用api_user和api_key进行验证                       
    "api_key" : api_key,                                             
    "to" : "to1@domain.com;to2@domain.com", # 收件人地址, 用正确邮件地址替代, 多个地址用';'分隔                                
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代                                        
    "fromname" : "SendCloud",                                                    
    "subject" : "SendCloud python webapi example",                              
    "html": "欢迎使用SendCloud",
    "resp_email_id": "true",
}                                                                               

r = requests.post(url, files="", data=params)                                                                                                                                            
print r.text

