
## 规则

## WEBAPI 的请求格式

`https://sendcloud.sohu.com/webapi/<模块>.<动作>.<格式>`

模块: 功能模块名称.  比如: mail (邮件模块), list (地址列表) 等.

动作: 每个模块所实现的功能. 比如: send, create 等.

格式: 返回的数据格式, 可以是 JSON 或者 XML 格式. ( 本文档按照 JSON 格式进行说明, XML 格式可自行脑补 )

>     /mail.send.json  # 邮件发送, 返回 JSON
>     /list.create.xml # 地址列表创建, 返回 XML

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

## 变量替换 

SendCloud 支持在邮件中使用「变量」. 

变量的格式: 首尾使用 `%` 包含, 即为用户定义的一个变量. 举例:
```
亲爱的%name%: # name是变量

    欢迎注册爱发信, 这是您的验证码: %active_code%. # active_code是变量
```

变量的用法:

1. 在 *普通发送*, *模板发送* 中使用变量, 来作为占位符
2. 在 *地址列表*, *X-SMTPAPI* 中指定变量的值
3. SendCloud 会根据不同收件人, 来替换相应变量的值

- - -

## X-SMTPAPI 扩展字段 

X-SMTPAPI 是 SendCloud 为开发者提供的邮件个性化定制的处理方式, 开发者通过这个特殊的‵信头扩展字段‵, 可以设置邮件处理方式的很多参数.

一般的, 开发者在使用 SMTP 接入时会使用此字段. 不过, WEBAPI 的方式也支持此参数. 

X-SMTPAPI 是一个 JSON 格式的字符串, 里面包含邮件处理方式的参数. 具体用法见下: 

**`to` 含有收件人地址的数组**. X-SMTPAPI 里的 `to` 会覆盖真实收件人参数 `to, cc, bcc` .
```    
    {
        "to": ["ben@sendcloud.com", "joe@sendcloud.com"]
    }
```    
**`substitution` 是一个[关联数组](http://baike.baidu.com/view/1654988.htm). **它的 `key` 是「变量」, `value` 是「替换值数组」.

用法解释: 每一个「变量」对应一个「替换值数组」, 在做邮件内容替换时, 每一个「收件人」按其在「收件人数组」中出现的位置使用「替换值数组」中相应位置的值来替换「变量」的值.

相信你一定没看懂上面的饶口令, 所以, 请参见如下示例: 
```    
# 邮件内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.
#---------------------------------------------------
# X-SMTPAPI
{
    "to": ["ben@sendcloud.com", "joe@sendcloud.com"],
    "sub":
    {
        "%name%": ["Ben", "Joe"],
        "%money%":[288, 497]
    }
}
#---------------------------------------------------
# ben@sendcloud.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.
#---------------------------------------------------
# joe@sendcloud.com 收到的邮件:
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
    "to": ["ben@sendcloud.com", "joe@sendcloud.com", "bida@sendcloud.com"],
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
# ben@sendcloud.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.

    感谢银牌用户: some words written to silver user, maybe it is verrrrrrrrry long.
#---------------------------------------------------
# joe@sendcloud.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.

    感谢金牌用户: some words written to golden user, maybe it is verrrrrrrrry long, too.
#---------------------------------------------------
# bida@sendcloud.com 收到的邮件:
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



- - -

## 传送门

### 发送相关

* [普通发送 WEBAPI ](send_email.md)
* [普通发送 SMTPAPI ](send_email_smtp.md)
* 模板发送
* 地址列表发送
* 使用地址列表调用模板发送

### 信息管理

* 个人信息查询
* apiUser查询
* 域名查询

### 数据管理

* 邮件列表管理
* 标签管理
* 退信列表管理
* [取消订阅管理](unsubscribe_do.md)
* [垃圾邮件举报查询](spamreported_do.md)
* [发送数据统计](stats_do.md)
* [按小时统计模块](stathour_do.md)
* [无效子类统计](invalidstat_do.md)
* [队列状态查询](queuestatus_do.md)
* [邮件模板管理](template_do.md)

