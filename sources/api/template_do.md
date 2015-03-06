##邮件模板管理
   
对邮件模板进行查询、删除、更新、添加操作    
    
- - -        
###查询    
返回邮件模板的详细信息
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.get
```
    
**返回数据格式**    
```
json
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
|start|int|否|查询起始位置,大于等于0|
|limit|int|否|查询个数,0~100|
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|模板html内容|
|text|模板text内容|
|gmt_created|邮件模板创建时间|
|gmt_modified|邮件模板更新时间|

- - -
    
###删除    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.delete
```
    
**返回数据格式**    
```
json
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
    
**返回值说明**
    
|参数|说明|
|del_count|成功删除的邮件模板个数|    
    
- - -
    
###更新    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.update
```
    
**返回数据格式**    
```
json
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
    
**返回值说明**
    
|参数|说明|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|html格式内容|
|text|text格式内容|
|gmt_created|邮件模板创建时间|
|gmt_modified|邮件模板更新时间|

    
    
- - -
    
###添加    
    
**URL**    
```
https://sendcloud.sohu.com/webapi/template.add
```
    
**返回数据格式**    
```
json
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
|html|string|否|html格式内容
|text|string|否|text格式内容|
    
**返回值说明**
    
|参数|说明|
|invoke_name|邮件模板调用名称|
|name|邮件模板名称|
|html|html格式内容|
|text|text格式内容|
|gmt_created|邮件模板创建时间|
|gmt_modified|邮件模板更新时间|


