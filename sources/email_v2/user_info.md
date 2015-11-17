
你可以使用 API 查询用户信息.

- - -

##查询用户信息

**URL**
```
http://api.sendcloud.net/apiv2/userinfo/get
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
    
**请求示例**
```
http://api.sendcloud.net/apiv2/userinfo/get?apiUser=***&apiKey=*** 
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|availableBalance|可用余额|
|balance|余额|
|email|邮箱地址|
|phone|手机号|
|quota|当天请求额度|
|todayUsedQuota|当天已使用的额度|
|regTime|注册时间|
|reputation|信誉度|
|userName|用户名|
|accountType|账号类型|
|websiteAuthStatus|网站认证|
|businessAuthStatus|商业认证|
    
**返回值示例**
```
{
  "result": true,
  "statusCode": 200,
  "message": "请求成功",
  "info": {
    "avaliableBalance": 40.54,
    "balance": 44.02,
    "phone": "189860398721",
    "quota": 2500,
    "regTime": "2013-03-28",
    "email": "f22@sendcloud.im",
    "reputation": 50.26,
    "websiteAuthStatus": true,
    "accountType": "付费用户",
    "userName": "delong",
    "businessAuthStatus": false,
    "todayUsedQuota": 2
  }
}
```
    
