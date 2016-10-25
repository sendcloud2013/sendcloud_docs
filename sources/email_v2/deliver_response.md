
## 查询

用户发送的邮件状态列表

你可以对此列表进行查询操作

**URL**
```  
http://api.sendcloud.net/apiv2/data/emailStatus
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
|email|string|否|收件人地址|  
|emailIds|string|否|调用api发送邮件成功返回的emailId. 多个地址使用';'分隔,如:`emailIds=a;b;c`| 
|labelId|string|否|用户创建的标签对应的标签ID|  
|days|string|*|过去 days 天内的投递数据，(days=1表示今天),时间不超过30天| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`，和结束时间间隔不超过30天|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`，和起始时间间隔不超过30天|  
|apiUserList|string|否|用户的多个apiUser. 多个apiUser使用';'分隔，如:`apiUserList=a;b;c`|  
|start|string|否|查询起始位置, 取值区间 [0-], 默认为 0|  
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100| 


注意:

1. 获取投递数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一 
2. 每次查询默认只查询100条记录，如果用户查询满足条件的记录数超过100时候，需要人为指定start和limit参数
3. 每次查询得到的对应的记录会根据用户所传参数缓存10分钟

请求示例:
```
http://api.sendcloud.net/apiv2/data/emailStatus?apiUser=***&apiKey=***&days=2&emailIds=***;***

```

**返回值说明**

|参数|说明|
|:---|:---|
|status|投递状态|
|apiUser|apiUser名称|
|recipients|收件人地址|
|requestTime|请求时间|
|modifiedTime|状态更新时间|
|sendLog|发送日志|



返回值示例:
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "请求成功",
	"info" : {
		"total" : "2",
		"voListSize" : 2,
		"voList" : [{
				"status" : "投递成功",
				"apiUser" : "***",
				"recipients" : "###@qq.com",
				"requestTime" : "2016-10-18 09:31:59",
				"modifiedTime" : "2016-10-18 09:32:08",
				"sendLog" : "successfully delivered"
			}, {
				"status" : "投递成功",
				"apiUser" : "***",
				"recipients" : "###@qq.com",
				"requestTime" : "2016-10-18 09:34:18",
				"modifiedTime" : "2016-10-18 09:34:29",
				"sendLog" : "successfully delivered"
			}
		]
	}
}

```


