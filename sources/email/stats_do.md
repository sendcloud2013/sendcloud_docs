
##数据统计 

- - -

###发送数据统计 (每天)

按天统计的邮件数据
    
**URL**    
```
http://sendcloud.sohu.com/webapi/stats.get.json
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
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|start_date|string|*|开始日期, 格式为`yyyy-MM-dd`|
|end_date|string|*|结束日期, 格式为`yyyy-MM-dd`|
|api_user_list|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`api_user_list=a;b;c`|
|label_id_list|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`label_id_list=a;b;c`|
|domain_list|string|否|获取指定域名下的统计数据, 多个域名用`;`分开, 如:`domain_list=a;b;c`|
|aggregate|int(1, 0)|否|默认为0. 如果为1, 则返回聚合数据|
    
提示:

1. 获取统计数据时, 必须指定时间区间. 即 **start_date 与 end_date 的组合** 或者 **days 参数** 需二者取一
2. 查询的天数不超过 100
3. 当`aggregate`为 1 时, 会将所有维度的数据聚合累加
    
请求示例:
```
curl http://sendcloud.sohu.com/webapi/stats.get.json?api_user=***&api_key=***&days=2&domain_list=***;***

curl http://sendcloud.sohu.com/webapi/stats.get.json?api_user=***&api_key=***&start_date=2015-02-01&end_date=2015-02-28&api_user_list=***;***

curl http://sendcloud.sohu.com/webapi/stats.get.json?api_user=***&api_key=***&start_date=2015-02-01&end_date=2015-02-28&api_user_list=***;***&aggregate=1
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|request|请求总数|
|apiUser|子账号|
|deliveredNum|送达数量|
|clickNum|点击数量|
|openNum|打开数量|
|bounceNum|弹回数量|
|spamReportedNum|垃圾举报数量|
|unsubscribeNum|取消订阅数量|
|uniqueOpensNum|独立打开数量|
|uniqueClicksNum|独立点击数量|
|invalidEmailsNum|无效邮件数量|
|delivered_percent|送达比例|
|click_percent|点击比例|
|open_percent|打开比例|
|bounce_percent|弹回比例|
|spamReported_percent|垃圾举报比例|
|unsubscribe_percent|取消订阅比例|
|uniqueOpens_percent|独立打开比例|
|uniqueClicks_percent|独立点击比例|
|invalidEmails_percent|无效邮件比例|
|domain|域名|
|labelName|标签名|
|labelId|标签ID|
    
返回值示例:
```
{
    "message": "success",
    "stats": [
        {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "labelId": 0,
            "labelName": null,
            "domain": "ifaxin.com",
            "request": 20,
            "deliveredNum": 0,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 20,
            "delivered_percent": 0,
            "click_percent": 0,
            "open_percent": 0,
            "bounce_percent": 0,
            "spamReported_percent": 0,
            "unsubscribe_percent": 0,
            "uniqueOpens_percent": 0,
            "uniqueClicks_percent": 0,
            "invalidEmails_percent": 100
        },
        {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "labelId": 0,
            "labelName": null,
            "domain": "sendcloud.im",
            "request": 27,
            "deliveredNum": 27,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 0,
            "delivered_percent": 100,
            "click_percent": 0,
            "open_percent": 0,
            "bounce_percent": 0,
            "spamReported_percent": 0,
            "unsubscribe_percent": 0,
            "uniqueOpens_percent": 0,
            "uniqueClicks_percent": 0,
            "invalidEmails_percent": 0
        }
    ]
}

# 聚合数据, aggregate = 1
{
    "message": "success",
    "stats": {
        "request": "48",
        "deliveredNum": "28",
        "clickNum": "0",
        "openNum": "0",
        "bounceNum": "0",
        "spamReportedNum": "0",
        "unsubscribeNum": "0",
        "uniqueOpensNum": "0",
        "uniqueClicksNum": "0",
        "invalidEmailsNum": "20",
        "delivered_percent": 58.33,
        "click_percent": 0,
        "open_percent": 0,
        "bounce_percent": 0,
        "spamReported_percent": 0,
        "unsubscribe_percent": 0,
        "uniqueOpens_percent": 0,
        "uniqueClicks_percent": 0,
        "invalidEmails_percent": 41.66
    }
}

```

- - -
    
###发送数据统计 (每小时)
    
按小时统计的邮件数据
    
**URL**    
```
http://sendcloud.sohu.com/webapi/statsHour.get.json
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
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|start_date|string|*|开始日期, 格式为`yyyy-MM-dd`|
|end_date|string|*|结束日期, 格式为`yyyy-MM-dd`|
|api_user_list|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`api_user_list=a;b;c`|
|label_id_list|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`label_id_list=a;b;c`|
    
提示:

1. 获取统计数据时, 必须指定时间区间. 即 **start_date 与 end_date 的组合** 或者 **days 参数**, 需二者取一
2. 查询的天数不超过 100
    
请求示例:    
```
curl http://sendcloud.sohu.com/webapi/stats.get.json?api_user=***&api_key=***&days=2

curl http://sendcloud.sohu.com/webapi/stats.get.json?api_user=***&api_key=***&start_date=2015-02-01&end_date=2015-02-28&api_user_list=***;***
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|apiUser|API_USER|
|labelId|标签ID|
|sendHour|某小时, 取值区间 [0-23]|
|request|请求总数|
|deliveredNum|送达数量|
|clickNum|点击数量|
|openNum|打开数量|
|bounceNum|弹回数量|
|spamReportedNum|垃圾举报数量|
|unsubscribeNum|取消订阅数量|
|uniqueOpensNum|独立打开数量|
|uniqueClicksNum|独立点击数量|
|invalidEmailsNum|无效邮件数量|
|delivered_percent|送达比例|
|click_percent|点击比例|
|open_percent|打开比例|
|bounce_percent|弹回比例|
|spamReported_percent|垃圾举报比例|
|unsubscribe_percent|取消订阅比例|
|uniqueOpens_percent|独立打开比例|
|uniqueClicks_percent|独立点击比例|
|invalidEmails_percent|无效邮件比例|

返回值示例:
```
# sendHour 没有返回的时间点, 表示那个小时没有数据
{
    "message": "success",
    "stats": [
        {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "labelId": 0,
            "sendHour": 10, # 10点请求的邮件数据
            "request": 8,
            "deliveredNum": 3,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 5,
            "delivered_percent": 75,
            "click_percent": 0,
            "open_percent": 0,
            "bounce_percent": 0,
            "spamReported_percent": 0,
            "unsubscribe_percent": 0,
            "uniqueOpens_percent": 0,
            "uniqueClicks_percent": 0,
            "invalidEmails_percent": 62.5
        }
    ]
}
```


- - -
    
###无效邮件统计

查询无效邮件的分类统计信息
    
**URL**    
```
http://sendcloud.sohu.com/webapi/invalidStat.get.json
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
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|start_date|string|*|开始日期, 格式为`yyyy-MM-dd`|
|end_date|string|*|结束日期, 格式为`yyyy-MM-dd`|
|api_user_list|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`api_user_list=a;b;c`|
|label_id_list|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`label_id_list=a;b;c`|
|domain_list|string|否|获取指定域名下的统计数据, 多个域名用`;`分开, 如:`domain_list=a;b;c`|
|aggregate|int(1, 0)|否|默认为0. 如果为1, 则返回聚合数据|
    
提示:

1. 获取统计数据时, 必须指定时间区间. 即 **start_date 与 end_date 的组合** 或者 **days 参数** 需二者取一
2. 当`aggregate`为 1 时, 会将所有维度的数据聚合累加
    
请求示例:    
```
curl http://sendcloud.sohu.com/webapi/invalidStat.get.json?api_user=***&api_key=***&start_date=2015-03-12&end_date=2015-03-12
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|sendDate|发送日期，格式为yyyy-MM-dd|
|apiUser|子账号|
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
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "labelId": 0,
            "labelName": null,
            "domain": "qq.com",
            "sendcloudBlacklist": 12,
            "ubsubscribe": 11,
            "espUnvaliable": 0,
            "recipientAddressError": 0,
            "recipientNotFound": 7,
            "spamEmails": 0,
            "senderOrRecipientRejected": 0,
            "others": 0
        }
    ]
}
```

