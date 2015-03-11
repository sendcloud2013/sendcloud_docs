##域名查询
    
通过此接口查询域名信息
    
- - -
###查询
    
**URL**
```
http://sendcloud.sohu.com/webapi/domain.list.json
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
|domain_type|int|否|必须是0(测试域名)或者1(正常域名)|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/domain.list.json?api_user=***&api_key=*** 
```
    
**返回值说明**
    
返回符合条件所有域名信息.
    
|参数|说明|
|:---|:---|
|name|域名名称|
|id|域名ID,deprecated|
    

**返回值示例**
```
{
    "message": "success",
    "domainList": [
        {
            "name": "test1.sendcloud.io",
            "id": 20542
        },
        {
            "name": "test2.sendcloud.me",
            "id": 20545
        },
        {
            "name": "test3.sendcloud.io",
            "id": 20546
        },
        {
            "name": "test3.sendcloud.me",
            "id": 20547
        }
    ]
}
```
    
