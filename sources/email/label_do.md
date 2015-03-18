
标签为用户发送邮件提供了另一个维度的统计方式.
    
你可以使用 API 进行标签的查询, 添加, 删除, 更新操作
    
- - -
##查询    
    
**URL**    
```
http://sendcloud.sohu.com/webapi/label.get.json
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
|labelId|string|是|标签ID|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/label.get.json?api_user=***&api_key=***&labelId=89
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|labelId|标签ID|
|labelName|标签名称|
    
**返回值示例**    
```
{
    "message":"success", 
    "label":{
            "labelId":89,
            "labelName":test
    }
}
```
    
- - -
##添加
    
**URL**
```
http://sendcloud.sohu.com/webapi/label.create.json
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
|labelName|string|是|需要添加的标签名称|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/label.create.json?api_user=***&api_key=***&labelName=test
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|labelId|标签ID|
|labelName|标签名称|
    

**返回值示例**    
```
{
    "message":"success", 
    "label":{
            "labelId":89,
            "labelName":test
    }
}
```
    
- - -

##删除

**URL**
```
http://sendcloud.sohu.com/webapi/label.delete.json
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
|labelId|int|是|需要删除的标签ID|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/label.delete.json?api_user=***&api_key=***&labelId=89
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|deleteCount|成功删除的个数|
    
**返回值示例**
```
{
    "message":"success",
    "deleteCount":1
}
```
    
- - -
##更新

**URL**
```
http://sendcloud.sohu.com/webapi/label.update.json
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
|labelId|int|是|需要更新的标签ID|
|labelName|string|是|需要更新的标签名称|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/label.update.json?api_user=***&api_key=***&labelId=89&labelName=test
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|updateCount|更新成功的个数|
    

**返回值示例**    
```
{
    "message":"success", 
    "updateCount":1
}
```

