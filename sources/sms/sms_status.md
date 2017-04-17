
##查询
用户发送的短信状态列表
你可以对此列表进行查询操作
    
**URL**    
```
http://www.sendcloud.net/smsapi/status/query
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|phones|string|否|多个电话号码，用`;`分开| 
|smsIds|string|否|短信投递成功时候返回的smsId,多个smsId用`;`分开|
|days|string|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|msgTypeStr|string|否|短信类型，`0`表示国内短信，`1`表示彩信，`2`表示国际短信，`3`表示语音|
|templateId|string|否|模板ID|
|templateName|string|否|模板名称|
|start|string|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|string|否|查询个数, 取值区间 [0-100], 默认为 100|
|signature|string|是|签名, 合法性验证|

提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一
2. 查询的天数不超过 30
3. 每次查询默认只查询100条记录，如果用户查询满足条件的记录数超过100时候，需要人为指定start和limit参数
4. phones个数不超过100个
5. 每次查询得到的对应的记录会根据用户所传参数缓存10分钟。


**请求示例**
```
http://www.sendcloud.net/smsapi/status/query?smsUser=***&days=***&msgTypeStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|msgType|短信类型|
|smsId|短信投递成功时候返回的smsId|
|statusCode|短信投递回应的状态|
|receiver|短信接收人|
|requestTime|请求时间|
|sendLog|发送日志|
|status|发送状态，分为请求、送达、处理失败、发送失败四种状态|
|templateId|模板Id|
|templateName|模板名称|
|updateTime|更新时间|

说明:

1. 返回的statusCode说明：400-499之间的为处理失败，500-499之间的为发送失败


**返回值示例**

```
{
	"info": {
		"voList": [{
				"smsId":"***_**_**_**_**$**",
				"msgType": "国内短信",
				"receiver": "0000000000",
				"requestTime": "2017-02-19 00:21:20",
				"sendLog": "发送成功",
				"smsUser": "*****",
				"status": "送达",
				"statusCode":200,
				"templateId": "000",
				"templateName": "SendCloud验证码",
				"updateTime": "2017-02-19 00:21:21"
			}, {
				"smsId":"***_**_**_**_**$**",
				"msgType": "国内短信",
				"receiver": "0000000000",
				"requestTime": "2017-02-19 00:00:04",
				"sendLog": "smsworker:address in local bounce list",
				"smsUser": "*****",
				"status": "处理失败-局部拦截列表",
				"statusCode":420,
				"templateId": "000",
				"templateName": "Ifaxin验证码",
				"updateTime": "2017-02-19 00:00:05"
			}, {
				"smsId":"***_**_**_**_**$**",
				"msgType": "国内短信",
				"receiver": "0000000000",
				"requestTime": "2017-02-19 00:00:01",
				"sendLog": "发送成功",
				"statusCode":200,
				"smsUser": "*****",
				"status": "送达",
				"templateId": "000",
				"templateName": "SendCloud余额提醒",
				"updateTime": "2017-02-19 00:00:02"
			}
		],
		"voListSize": 3,
		"total": "103"
	},
	"message": "请求成功",
	"result": true,
	"statusCode": 200
}

}
```



- - -