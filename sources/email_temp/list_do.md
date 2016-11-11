
邮件地址列表为用户批量发送时使用.
    
你可以使用 API 进行列表的查询, 创建, 修改, 删除操作
    
以及每个列表中地址成员的查询, 添加, 修改, 删除操作
    
- - -

##列表查询    
    
**URL**    
```
http://sendcloud.sohu.com/webapi/list.get.json
```
    
**HTTP请求方式** 
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|address|string|否|列表别称地址|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/list.get.json?api_user=***&api_key=***&limit=2
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|create_at|地址列表创建时间|
|modify_at|地址列表修改时间|
|address|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|members_count|地址列表包含的地址个数|
|description|地址列表的描述信息|
|name|地址列表的名称||
    
**返回值示例**    
```
{
    "message":"success",
    "count":2,
    "lists":[
        {
            "create_at":"2014-11-27 11:18:13",
            "modify_at":"2014-11-28 15:07:33",
            "address":"1121121121@maillist.sendcloud.org",
            "members_count":"92",
            "description":"s",
            "name":"s"
        },
        {
            "create_at":"2014-11-26 21:11:29",
            "modify_at":"2014-11-27 10:03:49",
            "address":"250879858@maillist.sendcloud.org",
            "members_count":"3","description":"250879858",
            "name":"250879858"
        }
    ]
}
```
    
- - -
##列表创建
    
**URL**
```
http://sendcloud.sohu.com/webapi/list.create.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|name|string|是|列表名称|
|description|string|否|对列表的描述信息|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/list.create.json?api_user=***&api_key=***&address=justfortest@maillist.sendcloud.org&name=testlist&description=test
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|create_at|创建时间|
|address|列表别称地址, 使用该别称地址进行调用|
|members_count|列表中地址数|
|name|列表名称|
|description|列表描述信息|
    

**返回值示例**    
```
{
    "message":"success",
    "list":{
            "create_at":"2012-11-02 11:25:12",
            "address":"justfortest@maillist.sendcloud.org",
            "members_count":0,
            "name":"testlist",
            "description":"test"
    }
}
```
    
- - -
##列表修改

**URL**
```
http://sendcloud.sohu.com/webapi/list.update.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
|toAddress|string|否|修改后的别称地址|
|name|string|否|修改后的列表名称|
|description|string|否|修改后的标签名称|
    
注意: 参数必须包含【toAddress】或者【name】或者【description】的组合    
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/list.update.json?api_user=***&api_key=***&address=justfortest@maillist.sendcloud.org&name=newtest
```
    
**返回值说明**
    
无
    

**返回值示例**    
```
{
    "message":"success",
    "list":{
            "modify_at":"2015-03-10 16:11:12",
            "address":"justfortest@maillist.sendcloud.org",
            "name":"newtest",
            "members_count":4
        }
}
```
    
- - -

##列表删除

**URL**
```
http://sendcloud.sohu.com/webapi/list.delete.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|address|string|是|列表别称地址, 使用该别称地址进行调用, 格式为xxx@maillist.sendcloud.org|
    
**请求示例**    
```
http://sendcloud.sohu.com/webapi/list.delete.json?api_user=***&api_key=***&address=newtest@maillist.sendcloud.org
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|del_count|成功删除的个数|
    
**返回值示例**
```
{
    "message":"success",
    "del_count":1
}
```
    
- - -
    
##列表成员查询
    
**URL**
```
http://sendcloud.sohu.com/webapi/list_member.get.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|mail_list_addr|string|是|地址列表调用名称|
|member_addr|string|否|需要查询信息的地址|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
注意: 如果不包含member_addr参数, 返回查询地址列表的所有地址信息; 反之, 只返回该member_addr地址的信息
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/list_member.get.json?api_user=***&api_key=***&mail_list_addr=newtest@maillist.sendcloud.org
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
    "message":"success",
    "total_count":2,    
    "members":[
        {"create_at":"2015-03-10 18:34:44","modify_at":"","address":"newtest1@163.com","name":"newaddress1","subscribed":"true","vars":{"money":"1000"}},
        {"create_at":"2015-03-10 18:34:44","modify_at":"","address":"newtest2@qq.com","name":"newaddress2","subscribed":"true","vars":{"money":"900"}}
    ]
}
```

- - -

##列表成员添加
    
**URL**
```
 http://sendcloud.sohu.com/webapi/list_member.add.json
```
     
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|mail_list_addr|string|是|地址列表调用名称|
|member_addr|string|是|需添加成员的地址, 多个地址使用分号;分开|
|name|string|否|地址所属人名称, 与member_addr一一对应, 多个名称用;分隔|
|vars|string|否|模板替换的变量, 与member_addr一一对应, 变量格式为{"money":"1000"}, 多个用;分隔|
|upsert|string (true, false)|否|是否更新, 当为true时, 如果该member_addr存在, 则更新; 为false时, 如果成员地址存在, 将报重复地址错误, 默认为false|

提示: 每次最多可以添加100个邮件地址; 如果包含name和vars变量, 则必须与member_add的地址数量一致
    
**请求示例(python)**
```
curl --data-urlencode 'vars={"money":"99"};{"money":"900"}' -d 'api_user=***&api_key=***&mail_list_addr=yourlist@maillist.sendcloud.org&member_addr=test1@163.com;test2@qq.com&name=lucy;lily&upsert=true'  http://sendcloud.sohu.com/webapi/list_member.add.json
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|total_counts|创建成功的地址数|
    
**返回值示例**
    
```
{
    "message":"success",
    "total_counts":2
}
```
    
- - -
    
##列表成员更新
    
**URL**
```
http://sendcloud.sohu.com/webapi/list_member.update.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|mail_list_addr|string|是|地址列表调用名称|
|member_addr|string|是|需要更新的地址,多个地址使用分号;分开|
|name|string|否|需要更新的地址对应的名称,多个名称用;分隔|
|vars|string|否|需要更新的地址对应的变量,变量格式为{"money":"1000"}, 多个用;分隔|
    
注意: 如果包含name和vars变量, 则必须与member_add的地址数量一致
    
**请求示例(python)**
```
curl --data-urlencode 'vars={"money":"99"};{"money":"900"}' -d 'api_user=***&api_key=***&mail_list_addr=yourlist@maillist.sendcloud.org&member_addr=test1@163.com;test2@qq.com&name=lucy;lily'  http://sendcloud.sohu.com/webapi/list_member.update.json
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|total_counts|地址列表中的地址数量|
    
**返回值示例**
```
{
    "message":"success",
    "total_counts":4
}
```
    
- - -
    
##列表成员删除
   
**URL**
```
http://sendcloud.sohu.com/webapi/list_member.delete.json
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
|mail_list_addr|string|是|地址列表别称|
|member_addr|string|是|需删除的地址, 多个地址使用分号;分开|
    
**请求示例**
```
http://sendcloud.sohu.com/webapi/list_member.delete.json?api_user=***&api_key=***&address=newtest@maillist.sendcloud.org&member_addr=newtest1@163.com
```
   
**返回值说明**
    
|参数|说明|
|:---|:---|
|del_count|成功删除地址数|
    
**返回值示例**
```
{
    "message":"success",
    "del_count":1
}
```
