通过以下接口可以对短信的投递回应进行查询.
- - - 
##处理失败数据
返回根据TemplateId和msgType进行分组的处理失败数据
    
**URL**    
```
http://www.sendcloud.net/smsapi/data/processFailed
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|days|string|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|msgTypeStr|string|否|短信类型，`0`表示国内短信，`1`表示彩信，`2`表示国际短信，`3`表示语音|
|templateIdsStr|string|否|多个模板ID，用`;`分开, 如:`templateIdsStr=1;2;3`|
|signature|string|是|签名, 合法性验证|

提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一
2. 查询的天数不超过 100
3. msgTypeStr不填的话默认为0

**请求示例**
```
http://www.sendcloud.net/smsapi/data/processFailed?smsUser=***&days=***&msgTypeStr=***&templateIdsStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|sendDate|发送日期|
|templateId|短信模板ID|
|msgType|短信类型|
|globalBlackListNum|全局拦截个数|
|userBlackListNum|局部拦截个数|
|unsubscribleNum|取消订阅个数|
|keyWordFilterNum|关键词过滤个数|
|errorVarsNum|替换变量错误个数|
|varsContentTooLongNum|变量长度超限个数|
|encodingExceptionNum|内容编码错误个数|
|sendTypeUnsupportedNum|短信发送类型不支持个数|


**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "请求成功",
	"info" : {
		"count" : 2,
		"voList" : [{
				"encodingExceptionNum" : "0",
				"errorVarsNum" : "0",
				"globalBlackListNum" : "0",
				"keyWordFilterNum" : "0",
				"msgType" : "0",
				"sendDate" : "2016-09-09",
				"sendTypeUnsupportedNum" : "0",
				"templateId" : "2",
				"unsubscribleNum" : "0",
				"userBlackListNum" : "0",
				"varsContentTooLongNum" : "0"
			}, {
				"encodingExceptionNum" : "0",
				"errorVarsNum" : "0",
				"globalBlackListNum" : "0",
				"keyWordFilterNum" : "0",
				"msgType" : "0",
				"sendDate" : "2016-09-27",
				"sendTypeUnsupportedNum" : "0",
				"templateId" : "2",
				"unsubscribleNum" : "0",
				"userBlackListNum" : "0",
				"varsContentTooLongNum" : "0"
			}
		]
	}
}
```

- - -  
##发送失败数据
返回根据TemplateId和msgType进行分组的发送失败数据
    
**URL**    
```
http://www.sendcloud.net/smsapi/data/sendFailed
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|days|string|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|msgTypeStr|string|否|短信类型，`0`表示国内短信，`1`表示彩信，`2`表示国际短信，`3`表示语音|
|templateIdsStr|string|否|多个模板ID，用`;`分开, 如:`templateIdsStr=1;2;3`|
|signature|string|是|签名, 合法性验证|

提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一
2. 查询的天数不超过 100
3. msgTypeStr不填的话默认为0

**请求示例**
```
http://www.sendcloud.net/smsapi/data/processFailed?smsUser=***&days=***&msgTypeStr=***&templateIdsStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|sendDate|发送日期|
|templateId|短信模板ID|
|msgType|短信类型|
|emptyPhoneNumberNum|空号|
|outOfServiceNum|停机|
|complaintsNum|投诉|
|busyNum|占线| 
|noAnswerNum|无人接听|
|interceptNum|拦截|
|breakDownNum|手机故障|
|notInServiceNum|不在服务区|
|shutDownNum|关机|
|othersNum|其他|   

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "请求成功",
	"info" : {
		"count" : 2,
		"voList" : [{
				"breakDownNum" : "0",
				"busyNum" : "0",
				"complaintsNum" : "0",
				"emptyPhoneNumberNum" : "0",
				"interceptNum" : "3",
				"msgType" : "0",
				"noAnswerNum" : "0",
				"notInServiceNum" : "0",
				"othersNum" : "0",
				"outOfServiceNum" : "0",
				"sendDate" : "2016-09-09",
				"shutDownNum" : "0",
				"templateId" : "2"
			}, {
				"breakDownNum" : "0",
				"busyNum" : "0",
				"complaintsNum" : "0",
				"emptyPhoneNumberNum" : "0",
				"interceptNum" : "0",
				"msgType" : "0",
				"noAnswerNum" : "0",
				"notInServiceNum" : "0",
				"othersNum" : "1",
				"outOfServiceNum" : "0",
				"sendDate" : "2016-09-27",
				"shutDownNum" : "0",
				"templateId" : "2"
			}
		]
	}
}
```

- - -