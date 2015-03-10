##退信列表管理
    
由特定原因(如地址不存在等)被退信的邮件会进入此列表.    
    
你可以对此列表进行查询, 删除, 添加操作
    
- - -
###查询    
    
**URL**    
```
http://sendcloud.sohu.com/webapi/bounces.get
```
    
**返回数据格式**
```
json
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
|days|int|否|过去days天内的统计数据(包含今天), 必须大于0| 
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd, 对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd, 对应时间必须在参数start_date对应时间之后|
|start|int|否|返回数据的起始位置, 如果不设置, 默认为0|
|limit|int|否|限制返回数据的个数. 必须大于0小于100, 如果不设置, 默认为100个|
|email|string|否|查询该地址在退信列表中的详情|
    
提示: 参数中必须包含【email】或【start_date与end_date的组合】或【days】或【start与limit的组合】.
    
请求示例:
```
http://sendcloud.sohu.com/webapi/unsubscribes.get.json?api_user=***&api_key=***&days=100&start=0&limit=3 
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|email|邮件地址|
|email|退信原因|
|create_at|退信时间|
    
**返回值示例** 
```    
{
    "message":"success",
    "bounces":[{"email":"lianzimi123@163.com","reason":"from softbounce","create_at":"2015-02-10 18:30:39"}]
}  
```
- - -
###删除
    

**URL**    
```
http://sendcloud.sohu.com/webapi/bounces.get
```
    
**返回数据格式**
```
json
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
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd, 对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd, 对应时间必须在参数start_date对应时间之后|
|email|string|否|要删除的地址|
