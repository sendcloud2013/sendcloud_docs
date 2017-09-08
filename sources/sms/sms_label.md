通过以下接口可以对短信标签进行查询, 添加 修改和删除操作.
- - - 
##查询单个标签
返回短信标签的基本信息
    
**URL**    
```
http://www.sendcloud.ne/smsapi/label/get
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|labelId|Integer|是|短信标签id|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/label/get?smsUser=***&labelId=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|labelId|短信标签id|
|labelName|短信标签名称|
|createTime|短信标签创建时间|
|updateTime|短信标签更新时间|

**返回值示例**
```
{
	"info": {
		"vo": {
			"createTime": "2017-09-07 14:48:05",
			"labelId": 10,
			"labelName": "test",
			"updateTime": "2017-09-07 14:48:05"
		}
	},
	"message": "查询成功",
	"result": true,
	"statusCode": 200
}
```

- - -  
##查询多个标签
返回短信模板的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/label/list
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|labelName|string|否|标签名称|
|start|Integer|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|Integer|否|查询个数, 取值区间 [0-100], 默认为 100|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/label/list?smsUser=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|total|用户所有短信标签的条数|
|count|本次查询接口的条数|
|labelId|短信标签id|
|labelName|短信标签名称|
|createTime|短信标签创建时间|
|updateTime|短信标签更新时间|     

**返回值示例**
```
{
	"info": {
		"total": 1,
		"count": 1,
		"voList": [{
				"createTime": "2017-09-07 14:48:05",
				"labelId": 10,
				"labelName": "test",
				"updateTime": "2017-09-07 14:48:05"
			}
		]
	},
	"statusCode": 200,
	"message": "查询成功",
	"result": true
}
```

- - -
##添加标签
返回添加成功后的短信标签基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/label/save
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|labelName|string|是|标签名称|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/label/save?smsUser=***&labelName=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|labelId|短信标签id|
|labelName|短信标签名称|
|createTime|短信标签创建时间|
|updateTime|短信标签更新时间|

    
**返回值示例**
```
{
	"info": {
		"vo": {
			"createTime": "2017-09-08 16:31:13",
			"labelId": 11,
			"labelName": "test2",
			"updateTime": "2017-09-08 16:31:13"
		}
	},
	"statusCode": 200,
	"message": "保存成功",
	"result": true
}
```

- - -

##更新标签
返回更新成功之后的短信签名的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/label/update
```
    
**HTTP请求方式**
```
post    get
```
  
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|labelId|Integer|是|短信标签id|
|labelName|string|是|标签名称|
|signature|string|是|签名, 合法性验证|



**请求示例**
```
http://www.sendcloud.net/smsapi/label/update?smsUser=***&laelId=***&labelName=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|labelId|短信标签id|
|labelName|短信标签名称|
|createTime|短信标签创建时间|
|updateTime|短信标签更新时间|

**返回值示例**
```
{
	"info": {
		"vo": {
			"createTime": "2017-09-08 16:31:13",
			"labelId": 11,
			"labelName": "test3",
			"updateTime": "2017-09-08 16:32:36"
		}
	},
	"statusCode": 200,
	"message": "更新成功",
	"result": true
}
```

- - -


##删除标签
返回更新成功之后的短信签名的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/label/delete
```
    
**HTTP请求方式**
```
post    get
```
  
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|labelId|Integer|是|短信标签id|
|signature|string|是|签名, 合法性验证|



**请求示例**
```
http://www.sendcloud.net/smsapi/label/delete?smsUser=***&id=***&signName=***&signType=***&signature=***
```

**返回值示例**
```
{
	"info": {},
	"message": "删除成功",
	"result": true,
	"statusCode": 200
}
```