通过以下接口可以对短信模板进行查询, 添加, 删除, 修改和提交审核操作.
- - - 
##查询单个模板
返回短信模板的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/get
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/get?smsUser=***&templateIdStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|templateId|短信模板ID|
|templateName|短信模板名称|
|msgType|业务类型|
|smsType|内容类型|
|isVerify|是否审核通过|
|templateContent|短信模板内容|
|createTime|短信模板创建时间|
|updateTime|短信模板更新时间|

**返回值示例**
```
{
	"info": {
		"templateId": 00,
		"templateName": "验证码",
		"msgType": "国内短信",
		"smsType": "验证码",
		"templateContent": "【爱发信】短息api模板",
		"isVerify": "审核通过",
		"createTime": "2015-03-23",
		"updateTime": "2017-03-29"
	},
	"message": "请求成功",
	"result": true,
	"statusCode": 200
}
```

- - -  
##查询多个模板
返回短信模板的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/list
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|isVerifyStr|string|否|是否通过验证；"0"待审核 "1"审核通过"-1"审核不通过|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/list?smsUser=***&isVerifyStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|templateId|短信模板ID|
|templateName|短信模板名称|
|msgType|业务类型|
|smsType|内容类型|
|isVerify|是否审核通过|
|templateContent|短信模板内容|
|templateCreateTime|短信模板创建时间|
|templateUpdateTime|短信模板更新时间|     

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "请求成功",
	"info" : {
		"total" : 2,
		"list" : [{
				"templateContent" : "闪达科技【SendCloud】",
				"templateCreateTime" : "2016-08-25",
				"templateId" : 00,
				"templateName" : "中秋快乐",
				"msgType" : "国内短信",
				"smsType" : "验证码",
				"isVerify" : "审核通过",
				"templateUpdateTime" : "2016-08-25"
			}, {
				"templateContent" : "【爱发信】闪达科技",
				"templateCreateTime" : "2016-08-25",
				"templateId" : 01,
				"templateName" : "国庆快乐",
				"msgType" : "国内短信",
				"smsType" : "验证码",
				"isVerify" : "审核通过",
				"templateUpdateTime" : "2016-08-25"
			}
		]
	}
}
```

- - -
##添加模板
返回是否添加成功的信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/addsms
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|templateName|string|是|模板名称，不能重复|
|templateText|string|是|模板内容|
|signName|string|*|短信内容中的中括号里面的签名|
|signId|Integer|*|短信签名的id|
|signPositionStr|string|否|签名位置，"0"代表前置"1"代表后置，默认前置|
|smsTypeStr|string|是|短信内容类型，"0"表示验证码，"1"表示行业通知，"2"表示营销|
|signature|string|是|签名, 合法性验证|

注意:

1. signId为短信签名的id，signId和signName两个参数只需要传一个即可，如果signId不为空，signName自动忽略


**请求示例**
```
http://www.sendcloud.net/smsapi/addsms?smsUser=***&templateName=***&templateText=***&signName=***&signPositionStr=**8&smsTypeStr=***&signature=***
```

**返回值说明**

|参数|说明|
|:---|:---|
|templateId|短信模板ID|

    
**返回值示例**
```
{
	"info" : {
		"templateId" : 3381
	},
	"message" : "插入成功",
	"result" : true,
	"statusCode" : 200
}
```

- - -
##提交审核
返回是否提交成功的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/submitsms
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/submitsms?smsUser=***&templateIdStr=***&signature=***
```

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "提交成功",
	"info" : {}
}
```

- - -
##更新模板
返回是否提交成功的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/updatesms
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|templateIdStr|string|是|模板ID|
|templateName|string|是|模板名称|
|templateText|string|否|模板内容|
|signName|string|*|短信内容中的中括号里面的签名|
|signId|Integer|*|短信签名的id|
|signPositionStr|string|是|签名位置: "0"代表前置, "1"代表后置, 默认前置|
|smsTypeStr|string|是|短信内容类型，"0"表示验证码，"1"表示行业通知，"2"表示营销|
|signature|string|是|签名, 合法性验证|

注意:

1. signId为短信签名的id，signId和signName两个参数只需要传一个即可，如果signId不为空，signName自动忽略


**请求示例**
```
http://www.sendcloud.net/smsapi/updatesms?smsUser=***&templateIdStr=***&templateName=***&templateText=***&signName=***&signPositionStr=***&smsTypeStr=***&signature=***
```

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "更新成功",
	"info" : {}
}
```

- - -
##删除模板
返回是否提交成功的基本信息
    
**URL**    
```
http://www.sendcloud.net/smsapi/deletesms
```
    
**HTTP请求方式**
```
post    get
```
  
**参数说明** 
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|smsUser|string|是|smsUser|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/deletesms?smsUser=***&templateIdStr=***&signature=***
```

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "删除成功",
	"info" : {}
}
```

- - -