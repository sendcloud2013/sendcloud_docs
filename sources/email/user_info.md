
##个人信息查询
    
通过此接口查询用户账户信息
    
**URL**
```
http://sendcloud.sohu.com/webapi/userinfo.get.json
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
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/userinfo.get.json?api_user=***&api_key=*** 
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|quota|当前请求额度|
|usedQuota|已经使用的额度|
|reputation|信誉度|
|balance|余额|
|availableBalance|可用余额|
|userName|用户名|
|accountType|账号类型|
|regTime|注册时间|
    
**返回值示例**
```
{
    "message": "success",
    "userinfo": {
        "quota": 5700,
        "usedQuota": 0,
        "reputation": 65.768845,
        "balance": 0,
        "availableBalance": -2.9,
        "userName": "sendcloud_admin",
        "accountType": "付费用户",
        "regTime": "2013-01-01"
    }
}
```

- - -

## API_USER 查询
    
通过此接口查询用户子子账号信息
    
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
|api_user_type|int|否|API_USER的类型: 0(触发), 1(批量)|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/apiUser.list.json?api_user=***&api_key=*** 
```
    
**返回值说明**
    
返回符合条件的的所有 API_USER

**返回值示例**
```
{
    "message":"success",
    "apiUserList":{
        "apiUser":["test1@test.sendcloud.org","test20150310","test3"]
        }
}
```
    
- - -

##域名查询
    
通过此接口查询域名信息
    
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
|domain_type|int|否|域名类型: 0(测试域名), 1(正常域名)|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/domain.list.json?api_user=***&api_key=*** 
```
    
**返回值说明**
    
返回符合条件所有域名信息.
    
|参数|说明|
|:---|:---|
|name|域名名称|
|id|域名ID|

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
    
