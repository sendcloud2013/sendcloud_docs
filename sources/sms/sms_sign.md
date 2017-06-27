通过以下接口可以对短信签名进行查询, 添加 修改操作.
- - - 
##查询单个签名
返回短信签名的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/sign/get
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|id|string|是|短信签名id|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net//smsapi/sign/get?smsUser=***&id=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|短信签名id|
|signName|短信签名名称|
|signTypeDesc|签名类型描述|
|verifyStatus|审核状态|
|createTime|短信签名创建时间|
|updateTime|短信签名更新时间|

**返回值示例**
```
{
	"info": {
		"smsSignVo": {
			"createTime": "2015-04-10 10:44:37",
			"id": 0,
			"signName": "闪闪",
			"signTypeDesc": "国内短信",
			"updateTime": "2016-06-14 19:20:35",
			"verifyStatus": "审核通过"
		}
	},
	"statusCode": 200,
	"message": "获取短信签名成功",
	"result": true
}
```

- - -  
##查询多个签名
返回短信模板的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/sign/list
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/sign/list?smsUser=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|短信签名id|
|signName|短信签名名称|
|signTypeDesc|签名类型描述|
|verifyStatus|审核状态|
|createTime|短信签名创建时间|
|updateTime|短信签名更新时间|     

**返回值示例**
```
{
	"info": {
		"voList": [{
				"createTime": "2015-04-10 10:44:37",
				"id": 1427,
				"signName": "闪达",
				"signTypeDesc": "国内短信",
				"updateTime": "2016-06-14 19:20:35",
				"verifyStatus": "审核通过"
			}, {
				"createTime": "2017-06-27 14:24:31",
				"id": 1429,
				"signName": "闪达1",
				"signTypeDesc": "国内短信",
				"updateTime": "2017-06-27 14:24:31",
				"verifyStatus": "待审核"
			}, {
				"createTime": "2017-06-27 14:24:45",
				"id": 1430,
				"signName": "闪达2",
				"signTypeDesc": "国内短信",
				"updateTime": "2017-06-27 14:24:45",
				"verifyStatus": "待审核"
			}
		]
	},
	"statusCode": 200,
	"message": "获取短信签名成功",
	"result": true
}
```

- - -
##添加签名
返回是否添加成功的信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/sign/save
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|signType|Integer|是|签名类型，0表示国内短信，1表示国际短信|
|signName|string|*|短信内容中的中括号里面的签名|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/sign/save?smsUser=***&signName=***&signType=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|短信签名id|
|signName|短信签名名称|
|signTypeDesc|签名类型描述|
|verifyStatus|审核状态|
|createTime|短信签名创建时间|
|updateTime|短信签名更新时间| 

    
**返回值示例**
```
{
	"info": {
		"smsSignVo": {
			"createTime": "2017-06-27 19:31:16",
			"id": 1432,
			"signName": "闪达5",
			"signTypeDesc": "国际短信",
			"updateTime": "2017-06-27 19:31:16",
			"verifyStatus": "待审核"
		}
	},
	"statusCode": 200,
	"message": "短信签名保存成功",
	"result": true
}	"statusCode" : 200
}
```

- - -

##更新签名
返回是否提交成功的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/sign/updatesms
```
    
**HTTP请求方式**
```
post    get
```
  
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|id|Integer|是|签名id|
|signType|Integer|是|签名类型，0表示国内短信，1表示国际短信|
|signName|string|是|短信内容中的中括号里面的签名|
|signature|string|是|签名, 合法性验证|



**请求示例**
```
http://www.sendcloud.net/smsapi/sign/update?smsUser=***&id=***&signName=***&signType=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|id|短信签名id|
|signName|短信签名名称|
|signTypeDesc|签名类型描述|
|verifyStatus|审核状态|
|createTime|短信签名创建时间|
|updateTime|短信签名更新时间| 

**返回值示例**
```
{
	"info": {
		"smsSignVo": {
			"createTime": "2017-06-27 19:31:16",
			"id": 1432,
			"signName": "闪达5",
			"signTypeDesc": "国际短信",
			"updateTime": "2017-06-27 19:31:16",
			"verifyStatus": "待审核"
		}
	},
	"statusCode": 200,
	"message": "短信签名更新成功",
	"result": true
}	"statusCode" : 200
}
```

- - -
