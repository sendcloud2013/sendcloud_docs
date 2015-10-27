
邮件地址列表为用户批量发送时使用.
    
你可以使用 API 进行列表的查询, 创建, 修改, 删除操作
    
以及每个列表中地址成员的查询, 添加, 修改, 删除操作
    
- - -

##列表批量查询    
    
**URL**    
```
http://sendcloud.sohu.com/addresslist/list
```
    
**HTTP请求方式** 
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|address|list|否|别名地址的列表, 多个用 `;` 分隔|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
**请求示例**    
```
http://sendcloud.sohu.com/addresslist/list?api_user=***&api_key=***&address=a@maillist.sendcloud.org&limit=2
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|name|地址列表的名称|
|address|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|membersCount|地址列表包含的地址个数|
|gmtCreated|地址列表创建时间|
|gmtUpdated|地址列表修改时间|
    
**返回值示例**    
```
{
    statusCode: 200,
    info: {
        total: 1,
        count: 1,
        data: [{
            gmtCreated: "2015-09-15 20:29:01",
            gmtUpdated: "2015-09-15 20:29:01",
            address: "developers4@sendcloud.com",
            memberCount: 0,
            name: "211"
        }]
    },
    message: "请求成功",
    result: true
}

```
    
- - -

##列表查询    
    
**URL**    
```
http://sendcloud.sohu.com/addresslist/get
```
    
**HTTP请求方式** 
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|address|list|是|别名地址的列表|
    
**请求示例**    
```
http://sendcloud.sohu.com/addresslist/get?api_user=***&api_key=***&address=a@maillist.sendcloud.org&limit=2
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|name|地址列表的名称|
|address|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|membersCount|地址列表包含的地址个数|
|gmtCreated|地址列表创建时间|
|gmtUpdated|地址列表修改时间|
    
**返回值示例**    
```
{
    statusCode: 200,
    info: {
        total: 1,
        count: 1,
        data: [{
            gmtCreated: "2015-09-15 20:29:01",
            gmtUpdated: "2015-09-15 20:29:01",
            address: "developers4@sendcloud.com",
            memberCount: 0,
            name: "211"
        }]
    },
    message: "请求成功",
    result: true
}

```
    
- - -

##列表创建
    
**URL**
```
http://sendcloud.sohu.com/addresslist/add
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|name|string|是|列表名称|
|desc|string|否|对列表的描述信息|
    
**请求示例**    
```
http://sendcloud.sohu.com/addresslist/add?api_user=***&api_key=***&address=justfortest@maillist.sendcloud.org&name=testlist&desc=test
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|address|列表别称地址, 使用该别称地址进行调用|
|membersCount|列表中地址数|
|name|列表名称|
|description|列表描述信息|
|gmtCreated|地址列表创建时间|
|gmtUpdated|地址列表修改时间|

**返回值示例**    
```
{
    statusCode: 200,
    info: {
        data: {
            gmtCreated: "2015-09-28 17:59:15",
            gmtUpdated: "2015-09-28 17:59:15",
            address: "developers41@sendcloud.com",
            memberCount: 0,
            description: "41",
            name: "developer41"
        }
    },
    message: "请求成功",
    result: true
}
```
- - -

##列表删除

**URL**
```
http://sendcloud.sohu.com/addresslist/delete
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
    
**请求示例**    
```
http://sendcloud.sohu.com/addresslist/delete?api_user=***&api_key=***&address=newtest@maillist.sendcloud.org
```
    
**返回值说明**

|参数|说明|
|:---|:---|
|count|成功删除的个数|
    
**返回值示例**
```
{
    statusCode: 200,
    info: {
        count: 1
    },
    message: "请求成功",
    result: true
}
```
    
- - -
##列表修改

**URL**
```
http://sendcloud.sohu.com/addresslist/update
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|newAddress|string|否|修改后的别称地址|
|name|string|否|修改后的列表名称|
|desc|string|否|修改后的列表描述信息|
    
**说明**
```
参数须包含【newAddress】或者【name】或者【description】的组合    
```
    
**请求示例**    
```
http://sendcloud.sohu.com/addresslist/update?api_user=***&api_key=***&address=justfortest@maillist.sendcloud.org&name=newtest
```
    
**返回值说明**

|参数|说明|
|:---|:---|
|count|成功修改的个数|
    
**返回值示例**
```
{
    statusCode: 200,
    info: {
        count: 1
    },
    message: "请求成功",
    result: true
}
    
```
    
- - -
    
##列表成员查询
    
**URL**
```
http://sendcloud.sohu.com/addressmember/list
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
|address|string|是|地址列表调用名称|    
|members|list|否|需要查询信息的地址, 多个地址用 `;` 分隔|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
**请求示例**
```
http://sendcloud.sohu.com/addressmember/list?api_user=***&api_key=***&address=newtest@maillist.sendcloud.org
```        
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|create_at|地址创建时间|
|modify_at|地址修改时间|
|address|邮件地址|
|name|地址所属人名称|
|subscribed|预留字段, deprecated, 忽略|
|vars|变量|
    
**返回值示例**
```

{
    statusCode: 200,
    info: {
        count: 2,
        data: [
        {
            gmtCreated: "2015-09-08 15:30:16",
            gmtUpdated: "2015-09-08 15:30:16",
            address: "developers3@sendcloud.com",
            member: "1"
        },
        {
            gmtCreated: "2015-09-15 20:42:22",
            gmtUpdated: "2015-09-15 20:56:21",
            address: "developers3@sendcloud.com",
            member: "1@1.com",
            vars: {
                1: 1
            }
        }
        ]
    },
    message: "请求成功",
    result: true
}

```

- - -

##列表成员添加
    
**URL**
```
http://sendcloud.sohu.com/addressmember/add
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
|address|string|是|地址列表调用名称|    
|members|list|是|需要添加成员的地址, 多个地址用 `;` 分隔|
|vars|list|否|替换变量, 与 members 一一对应, 变量格式为 {"money":"1000"} , 多个用 `;` 分隔|

**说明**
```
1. 每次请求最多可以添加1000个成员
2. 如果包含 vars 变量, 则必须与 members 的成员数量一致
```
    
**请求示例**
```
http://sendcloud.sohu.com/addressmember/add?apiUser=***&apiKey=***&address=yourlist@maillist.sendcloud.org&members=1@1.com;2@2.com&vars={"money":"99"};{"money":"900"}
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|count|创建成功的地址数|
    
**返回值示例**
    
```
{
    statusCode: 200,
    info: {
        count: 2
    },
    message: "请求成功",
    result: true
}
```
    
- - -
    
##列表成员更新
    
**URL**
```
http://sendcloud.sohu.com/addressmember/update
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
|address|string|是|地址列表调用名称|    
|members|list|是|需要添加成员的地址, 多个地址用 `;` 分隔|
|vars|list|否|替换变量, 与 members 一一对应, 变量格式为 {"money":"1000"} , 多个用 `;` 分隔|

**说明**
```
1. 每次请求最多可以修改1000个成员
2. 如果包含 vars 变量, 则必须与 members 的成员数量一致
```
    
**请求示例**
```
http://sendcloud.sohu.com/addressmember/update?apiUser=***&apiKey=***&address=yourlist@maillist.sendcloud.org&members=1@1.com;2@2.com&vars={"money":"199"};{"money":"1900"}
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|count|成功修改地址数|
    
**返回值示例**
```
{
    statusCode: 200,
    info: {
        count: 2
    },
    message: "请求成功",
    result: true
}
```
    
- - -
    
##列表成员删除
   
**URL**
```
http://sendcloud.sohu.com/addressmember/delete
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
|address|string|是|地址列表调用名称|    
|members|list|是|需要删除成员的地址, 多个地址用 `;` 分隔|
    
**请求示例**
```
http://sendcloud.sohu.com/addressmember/delete?api_user=***&api_key=***&address=newtest@maillist.sendcloud.org&members=3@3.com;4@4.com
```
   
**返回值说明**
    
|参数|说明|
|:---|:---|
|count|成功删除地址数|
    
**返回值示例**
```
{
    statusCode: 200,
    info: {
        count: 2
    },
    message: "请求成功",
    result: true
}
```

