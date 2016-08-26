通过一下接口可以对短信模板进行查询, 添加, 删除, 修改和提交审核操作.
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
|smsUser|string|是|子账号|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/get?smsUser=**&&templateIdStr=**&&signature=**
```

**返回值说明**

|参数|说明|
|:---|:---|
|templateId|短信模板ID|
|templateName|短信模板名称|
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
		"total" : 1,
		"list" : [{
				"templateContent" : "【爱发信】短息api模板",
				"templateCreateTime" : "2016-08-25",
				"templateId" : 00,
				"templateName" : "中秋快乐",
				"templateUpdateTime" : "2016-08-25"
			}
		]
	}
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
|smsUser|string|是|子账号|
|isVerifyStr|string|否|是否通过验证；"0"待审核 "1"审核通过"-1"审核不通过|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/list?smsUser=**&&isVerifyStr=**&&signature=**
```

**返回值说明**

|参数|说明|
|:---|:---|
|templateId|短信模板ID|
|templateName|短信模板名称|
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
				"templateUpdateTime" : "2016-08-25"
			}, {
				"templateContent" : "【爱发信】闪达科技",
				"templateCreateTime" : "2016-08-25",
				"templateId" : 01,
				"templateName" : "国庆快乐",
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
|smsUser|string|是|子账号|
|templateName|string|是|模板名称，不能重复|
|templateText|string|是|模板内容|
|signName|string|是|模板名称，不能重复|
|signPositionStr|string|否|签名位置，"0"代表前置"1"代表后置，默认前置|
|smsTypeStr|string|是|短信类型"0"触发"1"批量|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/addsms?smsUser=**&&templateName=**&&templateText=**&&signName=**&&signPositionStr=**&&smsTypeStr=**&&signature=**
```
    

**返回值示例**
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "插入成功",
	"info" : {}
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
|smsUser|string|是|子账号|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/submitsms?smsUser=**&&templateIdStr=**&&signature=**
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
|smsUser|string|是|子账号|
|templateIdStr|string|是|模板ID|
|templateName|string|是|模板ID|
|templateText|string|否|模板ID|
|signName|string|是|模板ID|
|signPositionStr|string|是|模板ID|
|smsTypeStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/updatesms?smsUser=**&&templateIdStr=**&&templateName=**&&templateText=**&&signName=**&&signPositionStr=**&&smsTypeStr=**&&signature=**
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
|smsUser|string|是|子账号|
|templateIdStr|string|是|模板ID|
|signature|string|是|签名, 合法性验证|


**请求示例**
```
http://www.sendcloud.net/smsapi/deletesms?smsUser=**&&templateIdStr=**&&signature=**
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