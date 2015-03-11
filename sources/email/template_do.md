##邮件模板管理

[模板]为批量发送相似内容给不同用户提供的方便的入口.
调用此API可以对邮件模板进行查询、删除、更新、添加操作.
    
- - -        
###查询    
返回邮件模板的详细信息
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.get.json
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
|start|int|否|查询起始位置, 大于等于0|
|limit|int|否|查询个数, 0~100|
    
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/template.get.json?api_user=***&api_key=***
```

    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|模板html内容|
|text|模板text内容|
|gmt_created|邮件模板创建时间|
|gmt_modified|邮件模板更新时间|
    
**返回值示例**
```
{
    "message":"success",
    "templateList":[
                    {"invoke_name":"20141031test","name":"20141031test","html":"<p>xxxxx<\/p>\n","text":" ","gmt_created":"2014-10-31 16:00:20","gmt_modified":"2015-03-11 14:45:31"},
                    {"invoke_name":"20141224","name":"20141224","html":"<p>%user_name%<\/p><p>测试测试<\/p>","gmt_created":"","gmt_modified":"2015-03-11 14:45:50"}
                    ]
}
```
 
- - -
    
###删除    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.delete.json
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
http://sendcloud.sohu.com/webapi/template.delete.json?api_user=***&api_key=***&invoke_name=testtemplate1
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
    
###更新    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.update.json
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
|name|string|否|邮件模板名称|
|html|string|否|html格式内容|
|text|string|否|text格式内容|

提示: html内容必须与[邮件样本]相匹配, 内容中可以使用[变量]. html内容过长或有特殊字符应使用Post请求.
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/template.update.json?api_user=***&api_key=***&invoke_name=testtemplate1&html=<p>update</p>
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
    
    
- - -
    
###添加    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.add.json
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
|html|string|是|html格式内容
|text|string|否|text格式内容|
    
提示: html内容必须与[邮件样本]相匹配, 内容中可以使用[变量]. html内容过长或有特殊字符应使用Post请求.
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/template.add.json?api_user=***&api_key=***&invoke_name=testtemplate2&name=test2&html=<p>add new template</p>
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

