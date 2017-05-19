通过以下接口可以对短信联系人列表的成员进行查询, 添加, 删除, 修改操作.
- - - 
##查询联系人列表成员
返回短信联系人列表成员的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsContactListMember/list
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
|phone|string|否|成员电话|
|userName|string|否|成员名称|
|start|Integer|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|Integer|否|查询个数, 取值区间 [0-100], 默认为 100|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsContactListMember/list?smsUser=***&contactName=***&phone=***&vars=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|成员Id|
|contactlistId|短信联系人列表Id|
|phone|成员电话|
|userName|成员用户名|
|vars|变量|
|createTime|创建时间|


**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "查询成功",
	"info": {
		"total": 102,
		"count": 2,
		"contactMemberList": [{
				"contactlistId": 999,
				"createTime": "2016-10-18 15:13:27",
				"id": 440164,
				"phone": "18********",
				"userName": "",
				"vars": "{\"name\":\"hello\"}"
			}, {
				"contactlistId": 999,
				"createTime": "2016-10-18 15:13:27",
				"id": 440165,
				"phone": "15********",
				"userName": "",
				"vars": "{\"name\":\"world\"}"
			}
		]
	}
}
```

- - -
##添加或者更新联系人列表成员
返回添加或者更新成员的信息
    
**URL**    
```
http://www.sendcloud.net/smsContactListMember/saveOrUpdate
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
|phone|string|是|成员电话|
|userName|string|否|成员名称|
|vars|string|是|变量|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsContactListMember/saveOrUpdate?smsUser=***&contactName=***&phone=***&userName=***&vars=**&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|成员Id|
|contactlistId|短信联系人列表Id|
|phone|成员电话|
|userName|成员用户名|
|vars|变量|
|createTime|创建时间|

    
**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "保存成功",
	"info": {
		"contactMember": {
			"contactlistId": 39,
			"createTime": "2017-05-19 14:09:16",
			"id": 440303,
			"phone": "18971138295",
			"userName": "101",
			"vars": "{101}"
		}
	}
}
```

- - -
##删除短信联系人列表成员
返回是否删除成功
    
**URL**    
```
http://www.sendcloud.net/smsContactListMember/delete
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
|phone|string|是|成员电话|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsContactListMember/delete?smsUser=***&contactName=***&phone=***&signature=***
```

**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "删除成功",
	"info": {}
}
```

- - -