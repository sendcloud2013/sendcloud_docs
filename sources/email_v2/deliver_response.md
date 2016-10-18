
## 查询

用户发送的邮件状态列表.你可以对此列表进行查询操作.

**URL**
```  
http://api.sendcloud.net/apiv2/data/emailStatus
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
|email|string|否|收件人地址.|  
|emailIds|string|否|调用api发送邮件成功返回的emailId. 多个地址使用';'分隔| 
|lableId|string|否|用户创建的标签对应的标签ID|  
|days|string|*|过去 days 天内的投递数据，时间不超过30天| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`，和结束时间间隔不超过30天|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`，和起始时间间隔不超过30天|  
|apiUserList|string|否|用户的多个apiUser. 多个apiUser使用';'分隔，如:`apiUserList=a;b;c`|  
|start|string|否|当查询结果超过100个时，指定分页的起始数字,结合limit使用，比如查询第100到200条记录，指定start=100,limit=100|  
|limit|int|否|分页查询时，每页查询的个数，最大不超过100个，不传此参数时默认为100| 


注意:

1. days、startDate、endDate三个参数不能同时为空，要么指定days参数，或者指定startDate、endDate两个参数 
2. 每次查询默认只查询100条记录，如果用户查询满足条件的记录数超过100时候，需要人为指定start和limit参数
3. 每次查询得到的对应的记录会根据用户所传参数缓存10分钟



