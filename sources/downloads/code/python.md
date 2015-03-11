###WEBAPI
```
#!/usr/bin/python                                                               
#coding:utf-8                                                                   
import requests                                                                 

url = "https://sendcloud.sohu.com/webapi/mail.send.xml"                         

params = {                                                                      
    "api_user": "***", # 使用api_user和api_key进行验证                       
    "api_key" : "***",                                             
    "to" : "to1@domain.com;to2@domain.com", # 收件人地址，用正确邮件地址替代，多个地址用';'分隔                                
    "from" : "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代                                        
    "fromname" : "SendCloud",                                                    
    "subject" : "SendCloud python webapi example",                              
    "html": "欢迎使用SendCloud"
}                                                                               

r = requests.post(url, files="", data=params)                                                                                                                                            
print r.text
```
    
- - - 
    
###SMTP
```
#!/usr/local/bin/python                                                         
# -*- coding:utf-8 -*-                                                          
import smtplib                                                                  
import json                                                                     
from email import Encoders                                                      
from email.mime.base import MIMEBase                                            
from email.mime.multipart import MIMEMultipart                                  
from email.mime.text import MIMEText                                            
from email.header import Header                                                                                                                                                          

def sc_send():                                                                  
    # 发信人，用正确邮件地址替代                                                
    from_email = 'sendcloud@sendcloud.org'                                      

    # 收件人地址，用正确邮件地址替代                             
    to_email=['to1@sendcloud.com']                                

    msg = MIMEMultipart('alternative')                                          
    msg['Subject'] = "SendCloud Python Smtp Example"                            
    msg['From'] = from_email                                                    
    msg['To'] = ', '.join(to_email)                                             

    # 邮件正文，html格式                                                        
    html = """                                                                  
            <html><head></head><body>                                           
            <p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>                               
            </body></html>                                                      
            """                                                                 

    part = MIMEText(html, 'html')                                                  
    msg.attach(part)                                                               

    # 使用api_user和api_key进行验证                                                
    api_user = '***'                             
    api_key = '***'                                                   
    s = smtplib.SMTP('smtpcloud.sohu.com:25')                                      
    s.login(api_user, api_key)                                                     
    s.sendmail(from_email, to_email, msg.as_string())                              
    s.quit()                                                                    

if __name__ == '__main__':                                                      
    sc_send()
```
    
