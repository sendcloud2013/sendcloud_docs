
用户需要在 SendCloud 中配置发信域名才能正常的发送邮件.
    
你可以使用 API 进行域名的查询, 添加, 修改操作

- - -

## 查询 ( 批量查询 )
    
通过此接口查询域名的基本信息
    
**URL**
```
http://api.sendcloud.net/apiv2/domain/list
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
http://api.sendcloud.net/apiv2/domain/get?apiUser=***&apiKey=***&name=ifaxin.com
```

```
http://api.sendcloud.net/apiv2/domain/get?apiUser=***&apiKey=***&type=0&verify=>3
```
    
**返回值说明**
    
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
        dataList: [
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

- - -

## 添加
    
通过此接口添加域名
    
**URL**
```
http://api.sendcloud.net/apiv2/domain/add
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
|name|string|是|域名名称|

**说明**
```
1. 域名名称不能含有 `sendcloud`, `ifaxin`
2. 域名名称长度不能超过 250 个字符
3. 同一个用户最多能添加 5 个域名
```

**请求示例**
```
http://api.sendcloud.net/apiv2/domain/add?apiUser=***&apiKey=***&name=mail.liubida.cn
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
        data: {
            name: "xxx",
            type: "普通",
            verify: 0,
            verifyKey: "aaaa0892-2b07-4a5b-9536-6200af3587e1-1443422994309",
            prefix: "sctrack",
            dkim.selector: "mail",
            dkim.pubkey: "k=rsa;p=...",
            gmtCreated: "2015-09-28 14:49:54",
            gmtUpdated: "2015-09-28 14:49:54"
        }
    },
    message: "请求成功",
    result: true
}
```
    
- - -

## 修改
    
通过此接口修改域名
    
**URL**
```
http://api.sendcloud.net/apiv2/domain/update
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
|name|string|是|域名名称|
|newName|string|是|新域名名称|

**说明**
```
已有配置项验证通过的域名不能修改
```

**请求示例**
```
http://api.sendcloud.net/apiv2/domain/update?apiUser=***&apiKey=***&name=mail.liubida.cn&name=email.liubida.cn
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
        data: {
            name: "xxx",
            type: "普通",
            verify: 0,
            verifyKey: "aaaa0892-2b07-4a5b-9536-6200af3587e1-1443422994309",
            prefix: "sctrack",
            dkim.selector: "mail",
            dkim.pubkey: "k=rsa;p=...",
            gmtCreated: "2015-09-28 14:49:54",
            gmtUpdated: "2015-09-28 15:49:54"
        }
    },
    message: "请求成功",
    result: true
}
```
    
