##api_user查询
    
通过此接口查询用户子子账号信息
    
- - -
###查询
    
**URL**
```
http://sendcloud.sohu.com/webapi/apiUser.list.json
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
|api_user_type|int|否|必须是0(触发)或者1(批量)|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/apiUser.list.json?api_user=***&api_key=*** 
```
    
**返回值说明**
    
返回符合条件的的api_user
    

**返回值示例**
```
{
    "message":"success",
    "apiUserList":{
        "apiUser":["test1@test.sendcloud.org","test20150310","test3"]
        }
}
```
    
