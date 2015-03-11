##个人信息查询
    
通过此接口查询用户账户信息
    
- - -
###查询
    
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
|quota|当前请求额度
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
    
