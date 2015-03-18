
开发者利用**模板**, 可以方便的为不同用户批量发送相似内容.

通过 API 可以对邮件模板进行查询, 添加, 删除, 修改操作.
    
- - -        

##查询    

返回邮件模板的详细信息
    
**URL**    
```
http://sendcloud.sohu.com/webapi/template.get.json
```
    
**HTTP请求方式**
```
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|否|邮件模板调用名称|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/template.get.json?api_user=***&api_key=***
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|模板内容|
|gmt_created|邮件模板创建时间|
|gmt_modified|邮件模板更新时间|
    
**返回值示例**
```
{
    "message":"success",
    "templateList":[{
            "invoke_name":"ifaxin_bill",
            "name":"爱发信每月账单",
            "html":"...",
            "gmt_created":"2014-10-31 16:00:20",
            "gmt_modified":"2015-03-11 14:45:31"
        }, {
            "invoke_name":"ifaxin_reg",
            "name":"爱发信用户注册",
            "html":"...",
            "gmt_created":"2014-10-31 16:12:20",
            "gmt_modified":"2015-03-11 14:45:50"}
    ]
}
```
 
- - -
    
##添加    
    
**URL**    
```
http://sendcloud.sohu.com/webapi/template.add.json
```
    
**HTTP请求方式**
```
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|是|邮件模板调用名称|
|name|string|是|邮件模板名称|
|html|string|是|html格式内容|
|text|string|否|text格式内容|
    
提示: 

1. html 内容必须与[邮件样本](../guide/base.md#sample)相匹配
2. html 内容中可以使用[变量](index.md#_2)
3. html 内容过长或有特殊字符应使用 post 请求
    
**请求示例**
```
curl -d 'api_user=***&api_key=***&invoke_name=testtemplate&name=test&html=<p>add new template</p>' http://sendcloud.sohu.com/webapi/template.add.json
```
 
**返回值说明**
    
|参数|说明|
|:---|:---|
|addCount|成功添加的模板数量|
    
**返回值示例**
```
{
    "message":"success",
    "addCount":1
}
```

- - -
    
##删除    
    
**URL**    
```
http://sendcloud.sohu.com/webapi/template.delete.json
```
    
**HTTP请求方式**
```
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|是|邮件模板调用名称|
    
**请求示例**
```
curl http://sendcloud.sohu.com/webapi/template.delete.json?api_user=***&api_key=***&invoke_name=testtemplate
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|delCount|成功删除的邮件模板个数|    
    
**返回值示例**
```
{
    "message":"success",
    "delCount":1
}
```
 
- - -
    
##更新    
    
用于更新模板的内容

**URL**    
```
http://sendcloud.sohu.com/webapi/template.update.json
```
    
**HTTP请求方式**
```
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|是|邮件模板调用名称|
|name|string|否|需要修改的邮件模板名称|
|html|string|否|需要修改的html格式内容|

提示: 

1. html 内容必须与[邮件样本](../guide/base.md#sample)相匹配
2. html 内容中可以使用[变量](index.md#_2)
3. html 内容过长或有特殊字符应使用 post 请求
    
**请求示例**
```
curl -d 'api_user=***&api_key=***&invoke_name=testtemplate&name=test&html=<p>update template</p>' http://sendcloud.sohu.com/webapi/template.update.json
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|updateCount|成功更新的模板个数|
    
**返回值示例**
```
{
    "message":"success",
    "updateCount":1
}
```
    
    
