###WEBAPI
```
curl -d "api_user=***&api_key=***&to=to@domain.com&from=sendcloud@sendcloud.org&fromname=SendCloud&subject=SendCloud curl webapi example&html=欢迎使用SendCloud！" https://sendcloud.sohu.com/webapi/mail.send.xml
```
    
- - - 
    
###SMTP
```
curl smtp://smtpcloud.sohu.com:25 -v \
--mail-from "sendcloud@sendcloud.org" \ 
--mail-rcpt "to@sendcloud.org" \
-u api_user:api_key \
-T mail_content.eml
```

