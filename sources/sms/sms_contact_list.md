通过以下接口可以对短信联系人列表进行查询, 添加, 删除, 更新操作.
- - - 
##查询联系人列表
返回短信短信联系人列表的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/smsContactList/list
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|contactListName|string|否|联系人列表名称|
|start|Integer|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|Integer|否|查询个数, 取值区间 [0-100], 默认为 100|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/smsContactList/list?smsUser=***&contactName=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|contactListId|短信联系人列表Id|
|contactListName|短信联系人列表名称|
|description|描述|
|memberCount|此联系人列表对应的成员个数|
|createTime|短信联系人列表创建时间|
|updateTime|短信联系人列表更新时间|

**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "查询成功",
	"info": {
		"total": 6,
		"count": 6,
		"contactLists": [{
				"contactListId": 998,
				"contactListName": "test",
				"createTime": "2016-05-10 15:18:38",
				"description": "测试",
				"memberCount": 4,
				"updateTime": "2016-05-11 10:04:20"
			}, {
				"contactListId": 999,
				"contactListName": "test2",
				"createTime": "2016-06-29 12:54:59",
				"description": "test",
				"memberCount": 1,
				"updateTime": "2016-06-29 12:54:59"
			}
		]
	}
}
```

- - -
##添加联系人列表
返回添加成功的联系人列表信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/smsContactList/save
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
|description|string|否|描述|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/smsContactList/save?smsUser=***&contactName=***&description=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|contactListId|短信联系人列表Id|
|contactListName|短信联系人列表名称|
|description|描述|
|memberCount|此联系人列表对应的成员个数|
|createTime|短信联系人列表创建时间|
|updateTime|短信联系人列表更新时间|

    
**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "保存成功",
	"info": {
		"contact": {
			"contactListId": 40,
			"contactListName": "test_3",
			"createTime": "2017-05-19 10:17:03",
			"description": "添加联系人列表测试",
			"memberCount": 0,
			"updateTime": "2017-05-19 10:17:03"
		}
	}
}
```


- - -
##更新联系人列表
返回更新成功的联系人列表的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/smsContactList/update
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|contactListName|string|是|原联系人列表名称|
|newContactListName|string|是|新联系人列表名称|
|description|string|否|描述|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/smsContactList/update?smsUser=***&contactName=***&newContactName=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|contactListId|短信联系人列表Id|
|contactListName|短信联系人列表名称|
|description|描述|
|memberCount|此联系人列表对应的成员个数|
|createTime|短信联系人列表创建时间|
|updateTime|短信联系人列表更新时间|


**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "更新成功",
	"info": {
		"contact": {
			"contactListId": 35,
			"contactListName": "test",
			"createTime": "2016-08-02 14:06:10",
			"description": "hello",
			"memberCount": 28,
			"updateTime": "2017-05-19 10:55:24"
		}
	}
}
```

- - -
##删除联系人列表
返回是否提交成功的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/smsContactList/delete
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|contactListName|string|是|联系人列表Id|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/smsContactList/delete?smsUser=***&contactName=***&signature=***
```

**返回值示例**
```
{
	"result": true,
	"statusCode": 200,
	"message": "删除联系人列表和对应的成员成功",
	"info": {}
}
```

- - -