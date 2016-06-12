
## SMTP 请求方式

为开发者提供 SMTP 协议的投递方式, 以下是会话过程. 详细的代码示例, 请移步[这里](../email/code.md#python)

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

## maillistTaskId

当用户使用地址列表发送时, SendCloud 会调用后台服务来发送邮件, 这是一个异步调用过程. 而 SendCloud API 会返回用户一个 `maillistTaskId`, 用户可以凭此值来查询地址列表发送的具体数据.

- - -

## X-SMTPAPI 扩展字段 

X-SMTPAPI 是 SendCloud 为开发者提供的邮件个性化定制的处理方式, 开发者通过这个特殊的 **信头扩展字段**, 可以设置邮件处理方式的很多参数.  一般的, 开发者在使用 SMTP 接入时会使用此字段. 不过, API 的方式也支持此参数. 

SMTP 调用时, 开发者可以在邮件中自行插入各种头域信息, 这是 SMTP 协议所允许的. 而 SendCloud 会检索 **key** 为 `X-SMTPAPI` 的头域信息, 如果发现含有此头域, 则其 **value** 的值可以被解析, 用来改变邮件的处理方式.

格式说明:
```
X-SMTPAPI:value

value 是使用 base64 编码封装过的 JSON 字符串. 代码示例:   
```

[代码示例](../email/downloads/python/python_smtp.py)
```
x_smtpapi = {
    "to": ["d@163.com",'i@163.com'],
    "sub": {
        "%content%": ['nihao0', 'nihao1']
    },
}

msg['SC-Custom-test_key1'] = "value1";
msg['SC-Custom-test_key2'] = "value2";
msg['X-SMTPAPI'] = Header(base64.b64encode(simplejson.dumps(x_smtpapi)))
```

SMTP 服务器会对邮件中 **key** 为 `X-SMTPAPI` 的头域信息做格式检查. 如果不符合上述要求, 则会报 `xsmtpapi error` 的错误.

需要注意的是: 

1. SMTP 调用时, X-SMTPAPI 必须是头域字段的最后一个. 否则, 可能导致 `xsmtpapi error` 的错误.
2. API 调用时, 直接传入 JSON 字符串即可, 无需 base64 编码封装.
3. X-SMTPAPI 的总长度不能超过 1M.

value 封装的 JSON 字符串的结构和用途见下: 

**`to` 含有收件人地址的数组, 指定邮件的收件人**. 
```    
    {
        "to": ["ben@ifaxin.com", "joe@ifaxin.com"]
    }
```    
**注意**:

* 这里的 `to` 会覆盖收件人参数 `to`
* 这里的 `to` 的收件人个数不能超过100

**`sub` 是一个关联数组. **它的 `key` 是「变量」, `value` 是「替换值数组」.

用法解释: 每一个「变量」对应一个「替换值数组」, 在做邮件内容替换时, 每一个「收件人」按其在「收件人数组」中出现的位置使用「替换值数组」中相应位置的值来替换「变量」的值.

参见如下示例: 
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

