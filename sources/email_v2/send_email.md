
## 普通发送

**URL**
```  
http://api.sendcloud.net/apiv2/mail/send
```
   
**HTTP请求方式** 
```bash
post
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|from|string|是|发件人地址. 举例: `support@ifaxin.com`, `爱发信支持<support@ifaxin.com>`|  
|to|string|*|收件人地址. 多个地址使用';'分隔, 如 `ben@ifaxin.com;joe@ifaxin.com`|  
|subject|string|是|标题. 不能为空|  
|html|string|*|邮件的内容. 邮件格式为 `text/html`|  
|fromName|string|否|发件人名称. 显示如: `ifaxin客服支持<support@ifaxin.com>`|  
|cc|string|否|抄送地址. 多个地址使用';'分隔|  
|bcc|string|否|密送地址. 多个地址使用';'分隔|  
|replyTo|string|否|设置用户默认的回复邮件地址.  如果 replyTo 没有或者为空, 则默认的回复邮件地址为 from|  
|labelId|int|否|本次发送所使用的标签ID. 此标签需要事先创建|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|attachments|file|否|邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)|  
|xsmtpapi|string|否|SMTP 扩展字段. 详见 [X-SMTPAPI](../guide/rule.md#x-smtpapi)|  
|plain|string|否|邮件的内容. 邮件格式为 `text/plain`|  
|respEmailId|string (true, false)|否|默认值: `true`. 是否返回 [emailId](../guide/rule.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|useNotification|string (true, false)|否|默认值: `false`. 是否使用回执|
|useAddressList|string (true, false)|否|默认值: `false`. 是否使用地址列表发送. 比如: `to=group1@maillist.sendcloud.org;group2@maillist.sendcloud.org`| 

注意:

1. 假设 from 为 `爱发信支持<support@ifaxin.com>`. 如果 fromName 为空, 则系统会将 fromName 设置为"爱发信支持"; 如果 fromName 为非空, 则不作处理.
2. 地址列表发送时, 使用参数 to 指定地址列表, 地址列表中的每个地址是单独发送, 地址列表的个数不能超过 5. 此时参数 cc, bcc, xsmtpapi 失效.
3. 非地址列表发送时, 使用参数 to 指定收件人, 多个收件人是广播发送 (收件人会全部显示). 使用参数 cc 指定抄送人, 参数 bcc 指定密送人.
4. 非地址列表发送时, 使用 xsmtpapi 指定收件人, 多个收件人是单独发送. 此时参数 to, cc, bcc 失效.
5. 参数 to, cc, bcc 的收件人个数不能超过 100, xsmtpapi 中的 to 的收件人个数不能超过 100.
6. html 和 plain 不能同时为空. 如果都不为空, 以 html 的值为优先.
7. subject, html, plain 中都可以使用[变量](../guide/base.md#_4). 由于变量的 '%' 为特殊字符, 做 HTTP 请求时请注意处理.
8. 使用回执功能, 收件人在收到邮件之后, 可以选择是否发送阅读回执到 from 的邮箱地址.
9. 如果参数 headers 中某个 Key 以 "SC-Custom-" 开头, 则这个 Key:Value 会通过 WebHook 返回给用户.

- - - 

## 模板发送

**URL**
```  
http://api.sendcloud.net/apiv2/mail/sendtemplate
```
   
**HTTP请求方式** 
```bash
post
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|from|string|是|发件人地址. 举例: `support@ifaxin.com`, `爱发信支持<support@ifaxin.com>`|  
|to|string|*|地址列表. 在 `useAddressList=true` 时使用|
|xsmtpapi|string|*|SMTP 扩展字段. 详见 [X-SMTPAPI](../guide/rule.md#x-smtpapi)|  
|subject|string|*|邮件标题|  
|templateInvokeName|string|是|邮件模板调用名称| 
|fromName|string|否|发件人名称. 显示如: `ifaxin客服支持<support@ifaxin.com>`|  
|cc|||不支持|  
|bcc|||不支持|  
|replyTo|string|否|设置用户默认的回复邮件地址.  如果 replyTo 没有或者为空, 则默认的回复邮件地址为 from|  
|labelId|int|否|本次发送所使用的标签ID. 此标签需要事先创建|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|attachments|file|否|邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)|  
|respEmailId|string (true, false)|否|默认值: `true`. 是否返回 [emailId](../guide/rule.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|useNotification|string (true, false)|否|默认值: `false`. 是否使用回执|  
|useAddressList|string (true, false)|否|默认值: `false`. 是否使用地址列表发送. 比如: `to=group1@maillist.sendcloud.org;group2@maillist.sendcloud.org`| 

注意:

1. 假设 from 为 `爱发信支持<support@ifaxin.com>`. 如果 fromName 为空, 则系统会将 fromName 设置为"爱发信支持"; 如果 fromName 不为空, 则不作处理.
2. 地址列表发送时, 使用参数 to 指定地址列表, 地址列表中的每个地址是单独发送, 地址列表的个数不能超过 5. 此时参数 xsmtpapi 失效.
3. **非地址列表发送时, 必须使用 xsmtpapi 指定收件人, 多个收件人是单独发送. 此时参数 to 失效.**
4. xsmtpapi 中的 to 的收件人个数不能超过 100.
5. API_USER 类型和模板类型必须一致. 触发或者批量.
6. 只能调用审核通过的模板.
7. 默认取邮件模板的标题作为邮件主题, 除非参数 subject 为非空. 如果两者都为空, 则返回错误.
8. subject, 模板中都可以使用[变量](../guide/base.md#_4). 由于变量的 '%' 为特殊字符, 做 HTTP 请求时请注意处理.
9. 使用回执功能, 收件人在收到邮件之后, 可以选择是否发送阅读回执到 from 的邮箱地址.
10. 如果参数 headers 中某个 Key 以 "SC-Custom-" 开头, 则这个 Key:Value 会通过 WebHook 返回给用户.

**请求, 返回值示例**

普通发送 ( 调用模板 ifaxin_bill )
```
# 模板内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.
#---------------------------------------------------
# 调用模板发送, `%`需要 urlencode
curl -d 'apiUser=***&apiKey=***&from=test@test.com&fromName=liubida&subject=测试&replyTo=reply@test.com&templateInvokeName=ifaxin_bill' --data-urlencode 'xsmtpapi={"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}&headers={"header1": "value1", "header2": "value2"}' http://api.sendcloud.net/apiv2/mail/sendtemplate

# 返回值
{
  "statusCode": 200,
  "info": {
    "emailIdList": [
      "1447054895514_15555555_32350_1350.sc-10_10_126_221-inbound0$ben@ifaxin.com",
      "1447054895514_15555555_32350_1350.sc-10_10_126_221-inbound1$joe@ifaxin.com"
    ]
  },
  "message": "请求成功",
  "result": true
}


# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.

#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.
```
普通发送 ( 调用模板 ifaxin_bill, 调用地址列表 users@maillist.sendcloud.org )
```
curl -d 'apiUser=***&apiKey=***&from=test@test.com&fromName=liubida&to=noexist@maillist.sendcloud.org&subject=测试&replyTo=reply@test.com&templateInvokeName=ifaxin_bill&useAddressList=true' --data-urlencode 'headers={"header1": "value1", "header2": "value2"}' http://api.sendcloud.net/apiv2/mail/sendtemplate

# 返回值
{
  "statusCode": 40863,
  "info": {},
  "message": "to中有不存在的地址列表. 参数to: noexist@maillist.sendcloud.org",
  "result": false
}

curl -d 'apiUser=***&apiKey=***&from=test@test.com&fromName=liubida&to=users@maillist.sendcloud.org&subject=测试&replyTo=reply@test.com&templateInvokeName=ifaxin_bill&useAddressList=true' --data-urlencode 'headers={"header1": "value1", "header2": "value2"}' http://api.sendcloud.net/apiv2/mail/sendtemplate

# 返回值
{
  "statusCode": 40821,
  "info": {
    "maillistTaskId": [
      267131
    ]
  },
  "message": "地址列表任务创建成功",
  "result": true
}
```

- - -


## 发送会议日历

**URL**
```  
http://api.sendcloud.net/apiv2/mail/sendcalendar
```
   
**HTTP请求方式** 
```bash
post
```

**参数说明**    

|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|from|string|是|发件人地址. 举例: `support@ifaxin.com`, `爱发信支持<support@ifaxin.com>`|  
|to|string|*|收件人地址. 多个地址使用';'分隔, 如 `ben@ifaxin.com;joe@ifaxin.com`|  
|subject|string|是|标题. 不能为空|  
|html|string|*|邮件的内容. 邮件格式为 `text/html`|  
|fromName|string|否|发件人名称. 显示如: `ifaxin客服支持<support@ifaxin.com>`|  
|cc|string|否|抄送地址. 多个地址使用';'分隔|  
|bcc|string|否|密送地址. 多个地址使用';'分隔|  
|replyTo|string|否|设置用户默认的回复邮件地址.  如果 replyTo 没有或者为空, 则默认的回复邮件地址为 from|  
|dispositionNotificationTo|||不支持|  
|labelId|int|否|本次发送所使用的标签ID. 此标签需要事先创建|  
|headers|string|否|邮件头部信息. JSON 格式, 比如:`{"header1": "value1", "header2": "value2"}`|  
|attachments|file|否|邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)|  
|xsmtpapi|string|否|SMTP 扩展字段. 详见 [X-SMTPAPI](index.md#x-smtpapi). |  
|plain|string|否|邮件的内容. 邮件格式为 `text/plain`|  
|respEmailId|string (true, false)|否|默认值: `true`. 是否返回 [emailId](index.md#messageid-emailid). 有多个收件人时, 会返回 emailId 的列表|  
|useAddressList|||不支持|
|startTime|string|是|日程开始时间. 形如: yyyy-MM-dd HH:mm:ss|
|endTime|string|是|日程结束时间. 形如: yyyy-MM-dd HH:mm:ss|
|title|string|是|会议标题|
|organizerName|string|是|组织者名称|
|organizerEmail|string|是|组织者邮箱地址|
|location|string|是|会议地点|
|description|string|否|会议描述|
|participatorNames|string|否|参会者姓名. 多个使用';'分隔|
|participatorEmails|string|否|参会者邮箱地址. 多个使用';'分隔|
|uid|string|否|取消日历和更新日历时候需要传递此参数. 其值会在第一次请求发送日历邮件时返回给用户|
|isCancel|string (true, false)|否|默认值: `false`. 取消日历日程|
|isUpdate|string (true, false)|否|默认值: `false`. 更新日历信息|

注意:

1. 假设 from 为 `爱发信支持<support@ifaxin.com>`. 如果 fromName 为空, 则系统会将 fromName 设置为"爱发信支持"; 如果 fromName 为非空, 则不作处理.
2. 非地址列表发送时, 使用参数 to 指定收件人, 多个收件人是广播发送 (收件人会全部显示). 使用参数 cc 指定抄送人, 参数 bcc 指定 密送人.
3. 非地址列表发送时, 使用 xsmtpapi 指定收件人, 多个收件人是单独发送. 此时参数 to, cc, bcc 失效.
4. 参数 to, cc, bcc 的收件人个数不能超过 100, xsmtpapi 中的 to 的收件人个数不能超过 100.
5. html 和 plain 不能同时为空. 如果都不为空, 以 html 的值为优先.
6. subject, html, plain 中都可以使用[变量](../guide/base#_4). 由于变量的 '%' 为特殊字符, 做 HTTP 请求时请注意处理.
7. 参数 participatorNames, participatorEmails 代表所有的参会者. 一般来说, 需要包含邮件的接收者.
8. 使用此接口前请联系客服开通相关权限才能调用

- - - 

##地址列表发送任务查询

**URL**
```
http://api.sendcloud.net/apiv2/mail/taskinfo
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|maillistTaskId|int|是|返回的maillistTaskId|
    
**请求示例**    
```
http://api.sendcloud.net/apiv2/label/update?apiUser=***&apiKey=***&maillistTaskId=1
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|maillistTaskId|返回的maillistTaskId|
|apiUser|调用的apiUser|
|addressList|调用的地址列表的别称|
|memberCount|发送的地址个数|
|gmtCreated|创建时间|
|gmtUpdated|修改时间|
|status|地址列表请求状态|

**返回值示例**    
```
{
  "result": true,
  "statusCode": 200,
  "message": "请求成功",
  "info": {
    "data": {
      "maillistTaskId": ***,
      "apiUser": "***",
      "addressList": "***",
      "memberCount": 300,
      "gmtCreated": "2015-11-26 14:38:09",
      "gmtUpdated": "2015-11-26 14:38:23",
      "status": "请求完毕"
    }
  }
}
```
