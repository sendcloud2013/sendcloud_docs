
### WEBAPI
```
# 普通发送
curl 'http://sendcloud.sohu.com/webapi/mail.send.json?api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true'

# 普通发送 ( 同时使用to, cc, bcc 和 x_smtpapi )
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true&x_smtpapi={"to":["mary@ifaxin.com", "karl@ifaxin.com"]}' http://sendcloud.sohu.com/webapi/mail.send.json

# 普通发送 ( 使用地址列表 users@maillist.sendcloud.org, 用户可以根据返回的 `task_id` 在 [WebHook](../guide/advance.md#webhook) 中使用 )
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com;users@maillist.sendcloud.org&replyto=reply@test.com&resp_email_id=true&use_maillist=true' http://sendcloud.sohu.com/webapi/mail.send.json

# 普通发送 ( 发送附件 )
# 带附件发送需要使用 form 提交
curl -F api_user='***' -F api_key='***' -F from='test@test.com' -F fromname='来自测试发送' -F subject='测试' -F html='这是一封测试邮件' -F to='ben@ifaxin.com' -F replyto='reply@test.com' -F resp_email_id='true' -F files=@/path/attach.pdf http://sendcloud.sohu.com/webapi/mail.send.json
```
更多示例, 请见[`邮件API - 邮件发送`](../../email/send_email.md)

- - - 
    
### SMTP
```
curl smtp://smtpcloud.sohu.com:25 -v \
--mail-from "sendcloud@sendcloud.org" \ 
--mail-rcpt "to@sendcloud.org" \
-u api_user:api_key \
-T mail_content.eml
```

