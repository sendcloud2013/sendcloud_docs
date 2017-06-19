通过以下接口可以发送短信联系人列表任务以及对短信任务的状态进行查询.
- - - 
##发送短信联系人列表任务
返回短信短信联系人列表任务的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/sendContactListTask
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|contactListName|string|是|联系人列表名称|
|taskName|string|是|任务名称|
|senderName|string|是|发送人名称|
|templateId|Integer|是|短信模板Id|
|executeTime|String|否|短信任务执行时间，格式为`yyyy-MM-dd HH:mm:ss`|
|signature|string|是|签名, 合法性验证|

提示:

1. 如果需要指定短信任务定时执行，需要传入executeTime，如果不传此参数默认为立即执行

**请求示例**
```
http://www.sendcloud.net/smsapi/sendContactListTask?smsUser=***&contactName=***&taskName=***&senderName=***&templateId=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|taskId|任务Id|
|contactListName|联系人列表名称|
|taskName|任务名称|
|senderName|发送人名称|
|templateId|短信模板Id|
|memberCount|本次任务成员个数|
|status|发送状态|
|createTime|任务创建时间|
|endTime|任务结束时间|

**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "联系人列表任务保存成功",
	"info": {
		"smsTask": {
			"contactListName": "test",
			"createTime": "2017-05-19 14:22:57",
			"endTime": "",
			"memberCount": 4,
			"senderName": "sendcloud",
			"status": "等待执行",
			"taskId": 472,
			"taskName": "test",
			"templateId": 999
		}
	}
}
```

- - -  
##查询短信联系人列表任务
返回短信短信联系人列表任务的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/queryContactListTask
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|taskId|Integer|是|任务Id|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/queryContactListTask?smsUser=***&taskId=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|taskId|任务Id|
|contactListName|联系人列表名称|
|taskName|任务名称|
|senderName|发送人名称|
|templateId|短信模板Id|
|memberCount|本次任务成员个数|
|status|发送状态|
|createTime|任务创建时间|
|endTime|任务结束时间|    

**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "查询成功",
	"info": {
		"smsTask": {
			"contactListName": "test",
			"createTime": "2017-05-19 14:22:57",
			"endTime": "",
			"memberCount": 4,
			"senderName": "sendcloud",
			"status": "等待执行",
			"taskId": 472,
			"taskName": "test",
			"templateId": 999
		}
	}
}
```

- - -

