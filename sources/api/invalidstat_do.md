##无效子类统计   
    
查询无效邮件的分类统计信息
    
- - -
    
###查询
    
**URL**    
```
https://sendcloud.sohu.com/webapi/invalidStat.get 
```
    
**返回数据格式**    
```
json
```
    
**HTTP请求方式**   
```
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
|days|int|否|过去days天内的统计数据(包含今天),必须大于0|
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd，对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd，对应时间必须在参数start_date对应时间之后|
|api_user_list|string|否|获取指定api_user下的统计数据,api_user列表，多个用";"分开|
|aggregate|int|否|是否累加统计数据,0或1|
|label_id_list|string|否|获取指定标签下的统计数据,标签列表,多个用";"分开|
|domain_list|string|否|获取指定域名下的统计数据,域名列表,多个用";"分开| 
    
提示:参数中必须包含【start_date与end_date的组合】或days    
    
请求示例:    
```
http://sendcloud.sohu.com/webapi/invalidStat.get.json?api_user=***&api_key=***&start_date=2014-10-01&end_date=2014-11-10 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|sendDate|发送日期，格式为yyyy-MM-dd|
|apiUser|子账号|
|apiUserId|子账号对应的ID|
|labelId|标签ID|
|labelName|标签名称|
|domain|域名|
|sendcloudBlacklist|在sendcloud黑名单的个数|
|ubsubscribe|在取消订阅列表的个数|
|espUnvaliable|esp服务器不可达的个数|
|recipientAddressError|收件人格式错误的个数|
|ipOrDomainRejected|ip或者域名被拒个数|
|recipientNotFound|收件人不存在个数|
|spamEmails|垃圾邮件个数|
|senderOrRecipientRejected|发信人或者收件人被拒个数|
|others|其他|
    
返回值示例:    
```
{
    "message": "success",
    "stats": [
        {
            "sendDate": "2014-11-08",
            "apiUser": "api_user",
            "apiUserId": 53584,
            "labelId": 0,
            "labelName": null,
            "domain": "163.com",
            "sendcloudBlacklist": 1111,
            "ubsubscribe": 1111111,
            "espUnvaliable": 111112,
            "recipientAddressError": 0,
            "ipOrDomainRejected": 0,
            "recipientNotFound": 0,
            "spamEmails": 0,
            "senderOrRecipientRejected": 0,
            "others": 0
        },
        {
            "sendDate": "2014-11-10",
            "apiUser": "api_user",
            "apiUserid": 53584,
            "labelId": 0,
            "labelName": null,
            "domain": "qq.com",
            "sendcloudBlacklist": 0,
            "ubsubscribe": 0,
            "espUnvaliable": 0,
            "recipientAddressError": 0,
            "ipOrDomainRejected": 0,
            "recipientNotFound": 0,
            "spamEmails": 1,
            "senderOrRecipientRejected": 0,
            "others": 0
        }
    ]
}
```
