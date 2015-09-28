
## 用户信息查询
    
通过此接口查询用户账户信息
    
**URL**
```
http://sendcloud.sohu.com/webapi/userinfo.get.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/userinfo.get.json?apiUser=***&apiKey=*** 
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
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
|apiUser_type|int|否|API_USER的类型: 0(触发), 1(批量)|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/apiUser.list.json?apiUser=***&apiKey=*** 
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

## 域名相关
    
通过此接口查询域名, 增加域名, 修改域名
    
**URL**
```
http://sendcloud.sohu.com/domain/get
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
|name|string|否|域名名称. 多个 name 用 `;` 分隔|
|type|int|否|域名类型: 0 (测试域名), 1(普通域名)|
|verify|string|否|域名验证值. 举例: `verify=1`, 查询验证值为 1 的域名; `verify=>22`, 查询验证值大于等于 22 的域名; `verify=<12`, 查询验证值小于等于 12 的域名|

**说明**

*域名的验证值* 代表 SendCloud 检查用户域名配置是否通过的返回值. SendCloud 会要求用户按照 `邮件设置` -> `域名` 的指引来配置域名记录. 下表是由返回值计算配置项是否通过的方法: 

|类型|计算|说明|
|:---|:---|:---|
|dkim|ret & 1 == 1|dkim 配置通过|
|spf|ret & 2 == 2|spf 配置通过|
|mx|ret & 4 == 4|mx 配置通过|
|cname|ret & 8 ==8|cname 配置通过|
|verifyKey|ret & 16 == 16|verifyKey 配置通过|

举例: 
```
ret = 0  | 没有配置项通过
ret = 3  | dkim, spf 配置通过
ret = 15 | dkim, spf, mx, cname 配置通过
ret = 31 | dkim, spf, mx, cname, verifyKey 配置通过
```

**请求示例**
```
http://sendcloud.sohu.com/domain/get?apiUser=***&apiKey=***&name=ifaxin.com
```

```
http://sendcloud.sohu.com/domain/get?apiUser=***&apiKey=***&type=0&verify=>3
```
    
**返回值说明**
    
返回符合条件所有域名信息.
    
|参数|说明|
|:---|:---|
|name|域名名称|
|type|域名类型|
|verify|域名验证值|
|verifyKey|此域名 VERIFY_KEY 的配置值|
|prefix|此域名 CNAME 配置的前缀|
|dkim.selector|此域名 DKIM 的 selector|
|dkim.pubkey|此域名 DKIM 的 公钥|
|gmtCreated|域名创建时间|
|gmtUpdated|域名修改时间|

**返回值示例**
```
{
    statusCode: 200,
    info: {
        total: 2,
        data: [
            {
                name: "xxx",
                type: "测试",
                verify: 23,
                verifyKey: "d816618d-eb95-46f1-8aa2-652c340c0777-1410433325388",
                prefix: "sctrack",
                dkim.selector: "mail",
                dkim.pubkey: "k=rsa;p=...",
                gmtCreated: "2015-05-21 16:30:52",
                gmtUpdated: "2015-09-02 10:46:54"
            },
            {
                name: "xxx",
                type: "普通",
                verify: 31,
                verifyKey: "9f14aaaa-a634-48b3-ae29-add1aea8e817-1366970248014",
                prefix: "sctrack",
                dkim.selector: "mail",
                dkim.pubkey: "k=rsa;p=...",
                gmtCreated: "2014-10-28 20:44:44",
                gmtUpdated: "2015-09-25 13:49:26"
            }
        ]
    },
    message: "请求成功",
    result: true
}
```
    
