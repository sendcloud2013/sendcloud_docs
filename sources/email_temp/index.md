
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

