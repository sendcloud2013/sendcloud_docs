
用户打开或点击的邮件，此用户的地址会进入打开点击列表.

你可以对此列表进行查询操作.
     
- - -

##查询
     
**URL**
```  
http://api.sendcloud.net/apiv2/openandclick/list
```
   
**HTTP请求方式**   
```bash
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|email|string|*|查询该地址在打开点击列表中的详情|
|trackType|string|否|`1`表示打开，`2`表示点击，不传此参数查询打开和点击全部|
|apiUserList|string|否|获取指定API_USER的统计数据，多个API_USER用`；`分开，如:apiUserList=a;b;c|
|labelId|string|否|获取指定标签下的统计数据|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|

提示:

1. 如果指定时间区间, 则是查询此时间区间内的打开点击数据列表. 注意: **startDate 与 endDate 的组合** 或者 **days 参数**, 二者取一. 
2. 查询的天数不超过三个月.
3. 如果指定email, 则是查询此地址在打开点击数据列表中的详细信息. 注意: 此时, 时间区间参数失效.
4. 如果不指定apiUserList，默认查询所有记录.
    
请求示例:
```
http://api.sendcloud.net/apiv2/openandclick/list?apiUser=***&apiKey=***&days=100&start=0&limit=3 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|email|邮件地址|
|trackType|点击或者打开类型|
|url|点击的url|
|apiUser|apiUser名称|
|currTime|点击或者打开的发生时间|
    
返回值示例:
```
{
	"result" : true,
	"statusCode" : 200,
	"message" : "请求成功",
	"info" : {
		"total" : 175,
		"dataList" : [{
				"trackType" : 1,
				"apiUser" : "ifaxin_test",
				"email" : "***@qq.com",
				"url" : "",
				"currTime" : "2016-11-08 10:57:39"
			}, {
				"trackType" : 1,
				"apiUser" : "ifaxin_test",
				"email" : "***@qq.com",
				"url" : "",
				"currTime" : "2016-11-09 19:46:57"
			}, {
				"trackType" : 1,
				"apiUser" : "ifaxin_test",
				"email" : "***@qq.com",
				"url" : "",
				"currTime" : "2016-11-09 21:54:44"
			}
		],
		"count" : 3
	}
}
```

- - -
