
## WEBAPI 的请求格式

`http://sendcloud.sohu.com/webapi/<模块>.<动作>.<格式>`

模块: 功能模块名称.  比如: mail (邮件模块), list (地址列表) 等.

动作: 每个模块所实现的功能. 比如: send, create 等.

格式: 返回的数据格式, 可以是 JSON 或者 XML 格式. ( 本文档按照 JSON 格式进行说明, XML 格式可自行脑补 )

     /mail.send.json  # 邮件发送, 返回 JSON
     /list.create.xml # 地址列表创建, 返回 XML

**提示**: 所有的 WEBAPI 都支持 HTTPS. 
- - -

## WEBAPI 的响应格式

服务器根据 WEBAPI 请求时的格式, 来确定响应数据的格式. 如果请求时没有指定返回格式, API 会报错. 

WEBAPI 返回的信息, 示例如下: 
```
    # 请求成功
    {
        "message": "success",
        ...
    }

    # 请求失败
    {
        "message": "error",
        "errors": {},
    }
```
- - -

## SMTP 请求方式

为开发者提供 SMTP 协议的投递方式, 以下是会话过程. 详细的代码示例, 请移步[这里](../downloads/code/python.md#smtp)

``` 
S: 220 SendCloud Inbound Server ESMTP Haraka 2.2.4 ready

C: ehlo ifaxin.com

S: 250-SendCloud Inbound Server Hello, Haraka is at your service.
S: 250-PIPELINING
S: 250-8BITMIME
S: 250-SIZE 16000000
S: 250 AUTH LOGIN

C: AUTH LOGIN base64(api_user)

S: 334 UGFzc3dvcmQ6

C: base64(api_key)

S: 235 Authentication successful

C: mail FROM:<support@ifaxin.com>

S: 250 sender <support@ifaxin.com> OK

C: rcpt TO:<ben@ifaxin.com>

S: 250 recipient <ben@ifaxin.com> OK

C: data

S: 354 go ahead, make my day

C: ... ...
C: .

S: 250 #1426390015358_15_6484_8661.sc-10_10_127_51-inbound#Queued

C: quit

S: 221 SendCloud Inbound Server closing connection. Have a jolly good day
```

- - -

## messageId 和 emailId

`messageId` 是提交一次请求, 返回的消息编号.

`emailId` 是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人.

两者的计算关系如下:
```
to = [A, B, C]
emailId_A = messageId + to.index(A) + '$' + A
emailId_B = messageId + to.index(B) + '$' + B
emailId_C = messageId + to.index(C) + '$' + C

# 注意: position 不做位数补齐
```
举例如下: 
```
# to = ["ben@ifaxin.com", "joe@ifaxin.com", "bida@ifaxin.com", ... , "lianzimi@ifaxin.com"]
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound  # messageId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound0$ben@ifaxin.com  # emailId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound1$joe@ifaxin.com  # emailId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound2$bida@ifaxin.com  # emailId
...
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound99$lianzimi@ifaxin.com  # emailId
```
- - -

## 变量替换 

SendCloud 支持在邮件中使用「变量」. 

变量的格式: 首尾使用 `%` 包含, 即为用户定义的一个变量. 举例:
```
亲爱的%name%: # name是变量

    欢迎注册爱发信, 这是您的验证码: %active_code%. # active_code是变量
```

变量的用法:

1. 在 *普通发送*, *模板发送* 中使用变量, 来作为占位符
2. 在 *地址列表*, *X-SMTPAPI* 中设置变量的值
3. SendCloud 会根据不同收件人, 来替换邮件内容中相应变量的值

**注意**: 邮件标题中的变量不会被替换, 所以不要在标题中设置变量

- - -

## X-SMTPAPI 扩展字段 

X-SMTPAPI 是 SendCloud 为开发者提供的邮件个性化定制的处理方式, 开发者通过这个特殊的 **信头扩展字段**, 可以设置邮件处理方式的很多参数.  一般的, 开发者在使用 SMTP 接入时会使用此字段. 不过, WEBAPI 的方式也支持此参数. 

X-SMTPAPI 是一个 JSON 格式的字符串, 里面包含邮件处理方式的参数. 具体用法见下: 

**`to` 含有收件人地址的数组**. 
```    
    {
        "to": ["ben@ifaxin.com", "joe@ifaxin.com"]
    }
```    
**注意**:

* X-SMTPAPI 里的 `to` 会覆盖收件人参数 `to`
* X-SMTPAPI 中的 `to` 的收件人个数不能超过100

**`sub` 是一个[关联数组](http://baike.baidu.com/view/1654988.htm). **它的 `key` 是「变量」, `value` 是「替换值数组」.

用法解释: 每一个「变量」对应一个「替换值数组」, 在做邮件内容替换时, 每一个「收件人」按其在「收件人数组」中出现的位置使用「替换值数组」中相应位置的值来替换「变量」的值.

相信你一定没看懂上面的饶口令, 所以, 请参见如下示例: 
```
# 邮件内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.

#---------------------------------------------------

# X-SMTPAPI
{
    "to": ["ben@ifaxin.com", "joe@ifaxin.com"],
    "sub":
    {
        "%name%": ["Ben", "Joe"],
        "%money%":[288, 497]
    }
}

#---------------------------------------------------

# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.
    
#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.
```
**`section` 是变量的变量.** 你可以在变量中使用 `section` 的变量, 用以简化很多收件人共有的替换内容.  
```    
# 邮件内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.

    感谢%role%用户: %role_words%.

#---------------------------------------------------

# X-SMTPAPI
{
    "to": ["ben@ifaxin.com", "joe@ifaxin.com", "bida@ifaxin.com"],
    "sub":
    {
        "%name%": ["Ben", "Joe", "Liubida"],
        "%money%":[288, 497, 688], 
        "%role%":["银牌", "金牌", "金牌"]
        "%role_words%":["%silver%", "%golden%", "%golden%"]
    },
    "section":
    {
        "silver": "some words written to silver user, maybe it is verrrrrrrrry long",
        "golden": "some words written to golden user, maybe it is verrrrrrrrry long, too",
    }
}

#---------------------------------------------------

# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.

    感谢银牌用户: some words written to silver user, maybe it is verrrrrrrrry long.

#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.

    感谢金牌用户: some words written to golden user, maybe it is verrrrrrrrry long, too.

#---------------------------------------------------

# bida@ifaxin.com 收到的邮件:
亲爱的Liubida:
    
    您好! 您本月在爱发信的消费金额为: 688 元.

    感谢金牌用户: some words written to golden user, maybe it is verrrrrrrrry long, too.
```    
    
**`apps` 是包含了一组应用名和它们设置的关联数组.** 这些设置会覆盖它们在用户账户中的设置. 
```
{
   "......",
   "filters": {
        "subscription_tracking": { # 退订追踪
            "settings": { "enable": "1" }
        },
        "open_tracking": { # 打开追踪
            "settings": { "enable": "1" }
        },
        "click_tracking": { # 点击追踪
            "settings": { "enable": "1" }
        }
    }
}
```

