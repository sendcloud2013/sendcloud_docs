
## 普通发送

**URL**
```  
http://sendcloud.sohu.com/webapi/mail.send.json
```
   
**HTTP请求方式** 
```bash
post   get
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|API_USER|  
|api_key|string|是|API_KEY|  
|from|string|是|发件人地址. from 和[发信域名](../guide/base.md#_3), 会影响是否[显示代发](../faq/index.md#2)|  
|to|string|是|收件人地址. 多个地址使用';'分隔, 如 `ben@ifaxin.com;joe@ifaxin.com`|  
|subject|string|是|标题. 不能为空|  
|html|string|是|邮件的内容. 不能为空, 可以是文本格式或者 HTML 格式|  
|fromname|string|否|发件人名称. 显示如: `ifaxin客服支持 <support@ifaxin.com>`|  
|bcc|string|否|密送地址. 多个地址使用';'分隔|  
|cc|string|否|抄送地址. 多个地址使用';'分隔|  
|replyto|string|否|默认的回复邮件地址. 如果 replyto 没有或者为空, 则默认的回复邮件地址为 from|  
|label|int|否|本次发送所使用的标签ID. 此标签需要事先创建|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|files|string|否|邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)|  
|x_smtpapi|string|否|SMTP 扩展字段. 详见 [X-SMTPAPI](index.md#x-smtpapi). |  
|resp_email_id|string (true, false)|否|是否返回 [emailId](index.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|use_maillist|string (true, false)|否|参数 to 是否`含有`地址列表. 比如: `to=ben@ifaxin.com;users@maillist.sendcloud.org`| 
|gzip_compress|string (true, false)|否|邮件内容是否使用gzip压缩. 默认不使用 gzip 压缩正文|  

注意:

1. API 参数 to 的收件人是全部显示在邮件中, X-SMTPAPI 中的 to 是独立显示在邮件中
2. 如果 X-SMTPAPI 中指定了 to, 那么API 参数中的 to, cc 和 bcc 都会被忽略
3. X-SMTPAPI 中的字段 to, 以及 API 参数中的 cc, bcc 都不支持地址列表
4. API 参数 to 的收件人个数不能超过100
5. X-SMTPAPI 中的 to 的收件人个数不能超过100
6. 地址列表中的收件人个数不能超过 100000

**请求, 返回值示例**

普通发送 ( get 方式, 使用to, cc, bcc, 返回emailId )
```
curl 'http://sendcloud.sohu.com/webapi/mail.send.json?api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&label=16800&resp_email_id=true'
# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426053463570_15_32087_2059.sc-10_10_127_105-inbound0$ben@ifaxin.com",
        "1426053463570_15_32087_2059.sc-10_10_127_105-inbound1$joe@ifaxin.com",
        "1426053463570_15_32087_2059.sc-10_10_127_105-inbound2$bida@ifaxin.com",
        "1426053463570_15_32087_2059.sc-10_10_127_105-inbound3$lianzimi@ifaxin.com"
    ]
}
```

普通发送 ( post 方式, 同时使用to, cc, bcc 和 x_smtpapi, 返回emailId )
```
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&label=16800&resp_email_id=true&x_smtpapi={"to":["mary@ifaxin.com", "karl@ifaxin.com"]}' http://sendcloud.sohu.com/webapi/mail.send.json
# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426053897008_15_28341_795.sc-10_10_127_22-inbound0$mary@ifaxin.com",
        "1426053897008_15_28341_795.sc-10_10_127_22-inbound1$karl@ifaxin.com",
        "1426053897008_15_28341_795.sc-10_10_127_22-inbound2$bida@ifaxin.com",
        "1426053897008_15_28341_795.sc-10_10_127_22-inbound3$lianzimi@ifaxin.com"
    ]
}
```

普通发送 ( post 方式, 使用地址列表 users@maillist.sendcloud.org, 用户可以根据返回的 `task_id` 在 [WebHook](../email/webhook.md#mail_list_task_id_list) 中使用 )
```
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com;users@maillist.sendcloud.org&replyto=reply@test.com&resp_email_id=true&use_maillist=true' http://sendcloud.sohu.com/webapi/mail.send.json
# 失败返回值 ( 地址列表不存在 )
{
    "message":"error",
    "errors":[
        "Can not queue maillist email, use maillist to send mail fail! msg=Mailing List doesnot exsit, please check your param!"
    ]
}
# 成功返回值
{
    "message":"success",
    "mail_list_task_id_list":[135323]
}
```

普通发送 ( post方式, 发送附件 )
```
# 带附件发送需要使用 form 提交
curl -F api_user='***' -F api_key='***' -F from='test@test.com' -F fromname='来自测试发送' -F subject='测试' --form-string html='<p>这是一封测试邮件<p>' -F to='ben@ifaxin.com' -F replyto='reply@test.com' -F resp_email_id='true' -F files=@/path/attach.pdf http://sendcloud.sohu.com/webapi/mail.send.json
# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426063441767_15_28341_5332.sc-10_10_127_22-inbound0$ben@ifaxin.com"
    ]
}
```

- - -

## 模板发送


**URL**
```  
http://sendcloud.sohu.com/webapi/mail.send_template.json
```
   
**HTTP请求方式** 
```bash
post
```

**参数说明('''New!''')**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|API_USER|  
|api_key|string|是|API_KEY|  
|from|string|是|发件人地址. from 和[发信域名](../guide/base.md#_3), 会影响是否[显示代发](../faq/index.md#2)|  
|substitution_vars|string|*|模板替换变量. 在 `use_maillist=false` 时使用, 如: `{"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}` | 
|to|string|*|收件人的地址列表. 在 `use_maillist=true` 时使用|
|subject|string|否|邮件标题|  
|template_invoke_name|string|是|邮件模板调用名称| 
|fromname|string|否|发件人名称. 显示如: `ifaxin客服支持 <support@ifaxin.com>`|  
|replyto|string|否|默认的回复邮件地址. 如果 replyto 没有或者为空, 则默认的回复邮件地址为 from|  
|label|int|否|本次发送所使用的标签ID. 此标签需要事先创建|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|files|string|否|邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)|  
|resp_email_id|string (true, false)|否|是否返回 [emailId](index.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|use_maillist|string (true, false)|否|参数 to 是否支持地址列表, 默认为 false. 比如: `to=users@maillist.sendcloud.org`| 
|gzip_compress|string (true, false)|否|邮件内容是否使用gzip压缩. 默认不使用 gzip 压缩正文|  

注意:

1. `to` 和 `substitution_vars` 分别对应地址列表使用与否的情况, 两者不能同时使用
2. 只能调用审核通过的模板
3. api_user类型和模板类型必须匹配

**请求, 返回值示例**

普通发送 ( 调用模板 ifaxin_bill )
```
# 模板内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.
#---------------------------------------------------
# 调用模板发送, `%`需要 urlencode
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&template_invoke_name=ifaxin_bill&replyto=reply@test.com&label=16800&resp_email_id=true' --data-urlencode 'substitution_vars={"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}' http://sendcloud.sohu.com/webapi/mail.send_template.json

# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426129060356_15_28341_7776.sc-10_10_127_22-inbound0$ben@ifaxin.com",
        "1426129060356_15_28341_7776.sc-10_10_127_22-inbound1$joe@ifaxin.com"
    ]
}

# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.

#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.
```
普通发送 ( 调用模板 ifaxin_bill, 调用地址列表 users@maillist.sendcloud.org, 用户可以根据返回的 `task_id` 在 [WebHook](../email/webhook.md#mail_list_task_id_list)  中使用 )
```
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&template_invoke_name=ifaxin_bill&replyto=reply@test.com&label=16800&resp_email_id=true&use_maillist=true&to=users@maillist.sendcloud.org' http://sendcloud.sohu.com/webapi/mail.send_template.json

# 返回值
{
    "message":"success",
    "mail_list_task_id_list":[135677]
}
```
普通发送 ( 调用模板 ifaxin_bill, 发送附件 )
```
# 带附件发送需要使用 form 提交
curl -F api_user='***' -F api_key='***' -F from='test@test.com' -F fromname='来自测试发送' -F subject=' 测试' -F template_invoke_name='ifaxin_bill' -F substitution_vars='{"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}' -F replyto='reply@test.com' -F resp_email_id='true' -F files=@/path/attach.pdf http://sendcloud.sohu.com/webapi/mail.send_template.json

# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426140696534_15_26257_4999.sc-10_10_127_57-inbound0$ben@ifaxin.com",
        "1426140696534_15_26257_4999.sc-10_10_127_57-inbound1$joe@ifaxin.com"
    ]
}
```
普通发送 ( 调用模板 ifaxin_bill, 调用地址列表 users@maillist.sendcloud.org, 发送附件 )
```
# 带附件发送需要使用 form 提交
curl -F api_user='***' -F api_key='***' -F from='test@test.com' -F fromname='来自测试发送' -F subject=' 测试' -F template_invoke_name='ifaxin_bill' -F to='users@maillist.sendcloud.org' -F replyto='reply@test.com' -F resp_email_id='true' -F use_maillist=true -F files=@/path/attach.pdf http://sendcloud.sohu.com/webapi/mail.send_template.json

# 返回值
{
    "message":"success",
    "mail_list_task_id_list":[135677]
}
```



