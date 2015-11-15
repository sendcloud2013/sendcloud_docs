
标签为用户发送邮件提供了另一个维度的统计方式.
    
你可以使用 API 对标签进行查询, 添加, 删除, 修改操作

- - -
##查询 ( 批量查询 )

返回标签的列表信息
    
**URL**    
```
http://api.sendcloud.sohu.com/apiv2/label/list
```
    
**HTTP请求方式** 
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|query|string|否|模糊查询的关键词|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|
    
**请求示例**    
```
http://api.sendcloud.sohu.com/apiv2/label/list?api_user=***&api_key=***&query=edm&start=0&limit=200
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|labelId|标签ID|
|labelName|标签名称|
|gmtCreated|标签创建时间|
|gmtModified|标签修改时间|
    
**返回值示例**    
```
{
  "info": {
    "dataList": [
      {
        "gmtCreated": "2014-08-06 16:18:22",
        "gmtUpdated": "2014-08-06 16:18:22",
        "labelId": ***,
        "labelName": "***"
      },
      {
        "gmtCreated": "2014-08-06 15:13:28",
        "gmtUpdated": "2014-08-06 15:13:28",
        "labelId": ***,
        "labelName": "***"
      }
    ],
    "total": 21,
    "count": 2
  },
  "statusCode": 200,
  "message": "请求成功",
  "result": true
}
```
    
- - -

##查询    
    
**URL**    
```
http://api.sendcloud.sohu.com/apiv2/label/get
```
    
**HTTP请求方式** 
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|labelId|int|是|标签ID|
    
**请求示例**    
```
http://api.sendcloud.sohu.com/apiv2/label/get?api_user=***&api_key=***&labelId=89
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|labelId|标签ID|
|labelName|标签名称|
|gmtCreated|标签创建时间|
|gmtModified|标签修改时间|
    
**返回值示例**    
```
{
  "info": {
    "data": {
      "gmtCreated": "2014-08-06 16:18:22",
      "gmtUpdated": "2014-08-06 16:18:22",
      "labelId": ***,
      "labelName": "***"
    }
  },
  "statusCode": 200,
  "message": "请求成功",
  "result": true
}
```
    
- - -
##添加
    
**URL**
```
http://api.sendcloud.sohu.com/apiv2/label/add
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|labelName|string|是|需要添加的标签名称|
    
**请求示例**    
```
http://api.sendcloud.sohu.com/apiv2/label/add?api_user=***&api_key=***&labelName=test
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|labelId|标签ID|
|labelName|标签名称|
    

**返回值示例**    
```
{
  "statusCode": 200,
  "info": {
    "data": {
      "gmtCreated": "2015-10-19 15:39:27",
      "gmtUpdated": "2015-10-19 15:39:27",
      "labelId": ***,
      "labelName": "test"
    }
  },
  "message": "请求成功",
  "result": true
}
```
    
- - -

##删除

**URL**
```
http://api.sendcloud.sohu.com/apiv2/label/delete
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|labelId|int|是|需要删除的标签ID|
    
**请求示例**    
```
http://api.sendcloud.sohu.com/apiv2/label/delete?api_user=***&api_key=***&labelId=89
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|count|成功删除的个数|
    
**返回值示例**
```
{
  "statusCode": 200,
  "info": {
    "count": 1
  },
  "message": "请求成功",
  "result": true
}
```
    
- - -

##修改

**URL**
```
http://api.sendcloud.sohu.com/apiv2/label/update
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|密码|
|labelId|int|是|需要修改的标签ID|
|labelName|string|是|新的标签名称|
    
**请求示例**    
```
http://api.sendcloud.sohu.com/apiv2/label/update?api_user=***&api_key=***&labelId=89&labelName=testnew
```
    
**返回值说明**
    
|参数|说明|
|:---|:---| 
|count|修改成功的个数|
    

**返回值示例**    
```
{
  "statusCode": 200,
  "info": {
    "count": 1
  },
  "message": "请求成功",
  "result": true
}
----
{
  "statusCode": 40111,
  "info": {},
  "message": "标签名称已经存在",
  "result": false
}
```

