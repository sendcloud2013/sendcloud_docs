
开发者利用**模板**, 可以方便的为不同用户批量发送相似内容.

通过 API 可以对邮件模板进行查询, 添加, 删除, 修改操作.
    
`20150707 SendCloud 平台更新: 模板和样本功能合二为一.  新建模板时增加字段: 邮件标题, 模板类型.`

- - -        

##查询    

返回邮件模板的详细信息
    
**URL**    
```
http://sendcloud.sohu.com/webapi/template.get.json
```
    
**HTTP请求方式**
```bash
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
    
**返回值说明 `20150707 更新`**    

|参数|说明|
|:---|:---|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|模板内容|
|subject|模板标题|
|email_type|模板类型|
|is_verify|审核状态|
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
            "subject":"爱发信201506账单",
            "email_type":1,
            "is_verify":0,
            "gmt_created":"2014-10-31 16:00:20",
            "gmt_modified":"2015-03-11 14:45:31"
        }, {
            "invoke_name":"ifaxin_reg",
            "name":"爱发信用户注册",
            "html":"...",
            "subject":"欢迎注册使用爱发信",
            "email_type":1,
            "is_verify":1,
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
```bash
post    get
```

**参数说明 `20150707 更新`**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|是|邮件模板调用名称|
|name|string|是|邮件模板名称|
|html|string|是|html格式内容|
|text|string|否|text格式内容|
|subject|string|`是`|模板标题|
|email_type|int|是|模板类型|

    
提示: 

1. html 内容中可以使用[变量](index.md#_1)
2. html 内容过长或有特殊字符应使用 post 请求
3. 模板个数最多为50个
    
     
**请求示例**
```
curl -d 'api_user=***&api_key=***&invoke_name=testtemplate&name=test&html=<p>add new template</p>&subject=test_subject&email_type=1' http://sendcloud.sohu.com/webapi/template.add.json
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
```bash
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
```bash
post    get
```

**参数说明 `20150707 更新`**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|invoke_name|string|是|邮件模板调用名称|
|name|string|否|需要修改的邮件模板名称|
|html|string|否|需要修改的html格式内容|
|subject|string|否|需要修改的邮件标题|
|email_type|string|否|需要修改的邮件类型|

提示: 

1. html 内容中可以使用[变量](index.md#_2)
2. html 内容过长或有特殊字符应使用 post 请求
3. 模板更新成功后,状态会变成未审核即is_verify=0
    
    
**请求示例**
```
curl -d 'api_user=***&api_key=***&invoke_name=testtemplate&name=test&html=<p>update template</p>&subject=test&email_type=1' http://sendcloud.sohu.com/webapi/template.update.json
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
