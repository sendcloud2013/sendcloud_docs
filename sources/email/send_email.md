##邮件发送

邮件发送 API 应该是用户使用最频繁的 API.    

- - -

### 普通发送

**URL**
```  
http://sendcloud.sohu.com/webapi/mail.send.json
```
   
**HTTP请求方式** 
```
post | get
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|API_USER|  
|api_key|string|是|API_KEY|  
|from|string|是|发件人地址. from 和[发信域名](../guide/base.md#_3), 会影响是否[显示代发](../faq/index.md#2)|  
|to|string|是|收件人地址. 多个地址使用';'分隔, 如`123@qq.com;456@qq.com`|  
|subject|string|是|标题. 不能为空|  
|html|string|是|邮件的内容. 不能为空, 可以是文本格式或者 HTML 格式|  
|fromname|string|否|发件人名称. 显示如: `ifaxin客服支持 <support@ifaxin.com>`|  
|bcc|string|否|密送地址. 多个地址使用';'分隔|  
|cc|string|否|抄送地址. 多个地址使用';'分隔|  
|replyto|string|否|默认的回复邮件地址. 如果 replyto 没有或者为空, 则默认的回复邮件地址为 from|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|files|string|否|邮件附件. 发送附件时, 必须使用multipart/form-data进行post提交???|  
|x_smtpapi|string|否|SMTP 扩展字段. 详见 [X-SMTPAPI](index.md#x-smtpapi). |  
|resp_email_id|boolean (true, false)|否|是否返回 [emailId](index.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|use_maillist|boolean (true, false)|否|参数 to 是否支持`含有`地址列表. 比如: `to=ben@ifaxin.com;users@maillist.sendcloud.org`| 
|gzip_compress|boolean (true, false)|否|邮件内容是否使用gzip压缩. 默认不使用 gzip 压缩正文|  

注意:

1. API 参数 to 的收件人是全部显示在邮件中, X-SMTPAPI 中的 to 是独立显示在邮件中
2. 如果 X-SMTPAPI 中指定了 to, 那么API 参数中的 to 会被忽略, cc 和 bcc 依然有效
3. X-SMTPAPI 中的字段 to, API中的参数 cc, bcc 都不支持地址列表

**请求, 返回值示例**

普通发送 ( get 方式, 使用to, cc, bcc, 返回emailId )
```
curl 'http://sendcloud.sohu.com/webapi/mail.send.json?api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true'
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
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true&x_smtpapi={"to":["mary@ifaxin.com", "karl@ifaxin.com"]}' 'http://sendcloud.sohu.com/webapi/mail.send.json'
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

普通发送 ( post 方式, 使用地址列表 users@maillist.sendcloud.org, 用户可以根据返回的 `task_id` 在 [WebHook](../guide/advance.md#webhook) 中使用 )
```
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com;users@maillist.sendcloud.org&replyto=reply@test.com&resp_email_id=true&use_maillist=true' 'http://sendcloud.sohu.com/webapi/mail.send.json'
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
curl -F api_user='postmaster@delongbat.sendcloud.org' -F api_key='delong' -F from='test@test.com' -F fromname='来自测试发送' -F subject='测试' -F html='这是一封测试邮件' -F to='ben@ifaxin.com' -F replyto='reply@test.com' -F resp_email_id='true' -F files=@/path/attach.pdf 'http://sendcloud.sohu.com/webapi/mail.send.json'
# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426063441767_15_28341_5332.sc-10_10_127_22-inbound0$ben@ifaxin.com"
    ]
}
```

- - -

### 模板发送

**URL**
```  
http://sendcloud.sohu.com/webapi/mail.send_template.json
```
   
**HTTP请求方式** 
```
post | get
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|API_USER|  
|api_key|string|是|API_KEY|  
|from|string|是|发件人地址. from 和[发信域名](../guide/base.md#_3), 会影响是否[显示代发](../faq/index.md#2)|  
|template_invoke_name|string|是|邮件模板调用名称| 
|substitution_vars|string|是|模板替换变量.  | 
|subject|string|是|标题. 不能为空|  
|use_maillist|boolean (true, false)|否|参数 to 是否支持`含有`地址列表. 比如: `to=ben@ifaxin.com;users@maillist.sendcloud.org`| 
|fromname|string|否|发件人名称. 显示如: `ifaxin客服支持 <support@ifaxin.com>`|  
|replyto|string|否|默认的回复邮件地址. 如果 replyto 没有或者为空, 则默认的回复邮件地址为 from|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|files|string|否|邮件附件. 发送附件时, 必须使用multipart/form-data进行post提交???|  
|x_smtpapi|string|否|SMTP 扩展字段. 详见 [X-SMTPAPI](index.md#x-smtpapi). |  
|resp_email_id|boolean (true, false)|否|是否返回 [emailId](index.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|gzip_compress|boolean (true, false)|否|邮件内容是否使用gzip压缩. 默认不使用 gzip 压缩正文|  

        {"to": ["to1@sendcloud.org", "to2@sendcloud.org"], "sub" : { "%name%" : ["约翰", "林肯"], "%money%" : ["1000", "200"]} }

注意:

1. API 参数 to 的收件人是全部显示在邮件中, X-SMTPAPI 中的 to 是独立显示在邮件中
2. 如果 X-SMTPAPI 中指定了 to, 那么API 参数中的 to 会被忽略

**请求, 返回值示例**

普通发送 ( get 方式, 使用to, cc, bcc, 返回emailId )
```
curl 'http://sendcloud.sohu.com/webapi/mail.send.json?api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true'
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
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com&cc=bida@ifaxin.com&bcc=lianzimi@ifaxin.com&replyto=reply@test.com&resp_email_id=true&x_smtpapi={"to":["mary@ifaxin.com", "karl@ifaxin.com"]}' 'http://sendcloud.sohu.com/webapi/mail.send.json'
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

普通发送 ( post 方式, 使用地址列表 users@maillist.sendcloud.org, 用户可以根据返回的 `task_id` 在 [WebHook](../guide/advance.md#webhook) 中使用 )
```
curl -d 'api_user=***&api_key=***&from=test@test.com&fromname=来自测试发送&subject=测试&html=这是一封测试邮件&to=ben@ifaxin.com;joe@ifaxin.com;users@maillist.sendcloud.org&replyto=reply@test.com&resp_email_id=true&use_maillist=true' 'http://sendcloud.sohu.com/webapi/mail.send.json'
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
curl -F api_user='postmaster@delongbat.sendcloud.org' -F api_key='delong' -F from='test@test.com' -F fromname='来自测试发送' -F subject='测试' -F html='这是一封测试邮件' -F to='ben@ifaxin.com' -F replyto='reply@test.com' -F resp_email_id='true' -F files=@/path/attach.pdf 'http://sendcloud.sohu.com/webapi/mail.send.json'
# 返回值
{
    "message":"success",
    "email_id_list":[
        "1426063441767_15_28341_5332.sc-10_10_127_22-inbound0$ben@ifaxin.com"
    ]
}
```






