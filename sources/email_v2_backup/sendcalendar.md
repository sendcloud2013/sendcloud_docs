- - -

## 日程发送

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


