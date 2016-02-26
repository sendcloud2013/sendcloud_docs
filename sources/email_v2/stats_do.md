
##发送数据统计 (每天)

按天统计的邮件数据
    
**URL**    
```
http://api.sendcloud.net/apiv2/statday/list
```
    
**HTTP请求方式**   
```bash
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|apiUserList|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`apiUserList=a;b;c`|
|labelIdList|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`labelIdList=a;b;c`|
|domainList|string|否|获取指定域名下的统计数据, 多个域名用`;`分开, 如:`domainList=a;b;c`|
|aggregate|boolean(1, 0)|否|默认为0. 如果为1, 则返回聚合数据|

提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一
2. 查询的天数不超过 100
3. 当`aggregate`为 1 时, 会将所有维度的数据聚合累加
    
请求示例:
```
http://api.sendcloud.net/apiv2/statday/list?apiUser=***&apiKey=***&days=2&domainList=***;***

http://api.sendcloud.net/apiv2/statday/list?apiUser=***&apiKey=***&startDate=2015-02-01&endDate=2015-02-28&apiUserList=***;***

http://api.sendcloud.net/apiv2/statday/list?apiUser=***&apiKey=***&startDate=2015-02-01&endDate=2015-02-28&apiUserList=***;***&aggregate=1
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|apiUser|子账号|
|domain|收信域名|
|labelId|标签ID|
|labelName|标签名|
|requestNum|请求总数|
|deliveredNum|送达数量|
|clickNum|点击数量|
|openNum|打开数量|
|bounceNum|弹回数量|
|spamReportedNum|垃圾举报数量|
|unsubscribeNum|取消订阅数量|
|uniqueOpensNum|独立打开数量|
|uniqueClicksNum|独立点击数量|
|invalidEmailsNum|无效邮件数量|
|deliveredPercent|送达比例|
|clickPercent|点击比例|
|openPercent|打开比例|
|bouncePercent|弹回比例|
|spamReportedPercent|垃圾举报比例|
|unsubscribePercent|取消订阅比例|
|uniqueOpensPercent|独立打开比例|
|uniqueClicksPercent|独立点击比例|
|invalidEmailsPercent|无效邮件比例|


    
返回值示例:
```
{
    "statusCode":200,
    "info": {
       "dataList": [
         {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "domain": "ifaxin.com",
            "labelId": 0,
            "labelName": null,
            "requestNum": 20,
            "deliveredNum": 0,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 20,
            "deliveredPercent": 0,
            "clickPercent": 0,
            "openPercent": 0,
            "bouncePercent": 0,
            "spamReportedPercent": 0,
            "unsubscribePercent": 0,
            "uniqueOpensPercent": 0,
            "uniqueClicksPercent": 0,
            "invalidEmailsPercent": 100
          },
          {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "domain": "qq.com",
            "labelId": 0,
            "labelName": null,
            "requestNum": 27,
            "deliveredNum": 27,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 0,
            "deliveredPercent": 100,
            "clickPercent": 0,
            "openPercent": 0,
            "bouncePercent": 0,
            "spamReportedPercent": 0,
            "unsubscribePercent": 0,
            "uniqueOpensPercent": 0,
            "uniqueClicksPercent": 0,
            "invalidEmailsPercent": 0
          }
      ]
    },
    "message": "请求成功",
    "result": true
}

# 聚合数据, aggregate = 1
{
    "statusCode":200,
    "info": {
      "dataList": {
        "requestNum": "48",
        "deliveredNum": "28",
        "clickNum": "0",
        "openNum": "0",
        "bounceNum": "0",
        "spamReportedNum": "0",
        "unsubscribeNum": "0",
        "uniqueOpenNum": "0",
        "uniqueClickNum": "0",
        "invalidEmailNum": "20",
        "deliveredPercent": 58.33,
        "clickPercent": 0,
        "openPercent": 0,
        "bouncePercent": 0,
        "spamReportedPercent": 0,
        "unsubscribePercent": 0,
        "uniqueOpensPercent": 0,
        "uniqueClicksPercent": 0,
        "invalidEmailsPercent": 41.66
      },
    "message": "请求成功",
    "result": true
}

```

- - -
    
##发送数据统计 (每小时)
    
按小时统计的邮件数据
    
**URL**    
```
http://api.sendcloud.net/apiv2/stathour/list
```
    
**HTTP请求方式**   
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|apiUserList|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`apiUserList=a;b;c`|
|labelIdList|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`labelIdList=a;b;c`|
    
提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数**, 需二者取一
2. 查询的天数不超过 100

    
请求示例:    
```
http://api.sendcloud.net/apiv2/stathour/list?apiUser=***&apiKey=***&days=2

http://api.sendcloud.net/apiv2/stathour/list?apiUser=***&apiKey=***&startDate=2015-02-01&endDate=2015-02-28&apiUserList=***;***
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|apiUser|API_USER|
|labelId|标签ID|
|sendHour|某小时, 取值区间 [0-23]|
|requestNum|请求总数|
|deliveredNum|送达数量|
|clickNum|点击数量|
|openNum|打开数量|
|bounceNum|弹回数量|
|spamReportedNum|垃圾举报数量|
|unsubscribeNum|取消订阅数量|
|uniqueOpensNum|独立打开数量|
|uniqueClicksNum|独立点击数量|
|invalidEmailsNum|无效邮件数量|
|deliveredPercent|送达比例|
|clickPercent|点击比例|
|openPercent|打开比例|
|bouncePercent|弹回比例|
|spamReportedPercent|垃圾举报比例|
|unsubscribePercent|取消订阅比例|
|uniqueOpensPercent|独立打开比例|
|uniqueClicksPercent|独立点击比例|
|invalidEmailsPercent|无效邮件比例|

返回值示例:
```
# sendHour 没有返回的时间点, 表示那个小时没有数据
{
    

    "statusCode":200,
    "info":{
      "dataList": [
           {
            "sendDate": "2015-03-12",
            "apiUser": "...",
            "labelId": 0,
            "sendHour": 10, # 10点请求的邮件数据
            "requestNum": 8,
            "deliveredNum": 3,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 5,
            "deliveredPercent": 75,
            "clickPercent": 0,
            "openPercent": 0,
            "bouncePercent": 0,
            "spamReportedPercent": 0,
            "unsubscribePercent": 0,
            "uniqueOpensPercent": 0,
            "uniqueClickspercent": 0,
            "invalidEmailsPercent": 62.5
           }
       ],
    "message": "请求成功",
    "result": true
}
```


- - -
    
##无效邮件统计

查询无效邮件的分类统计信息
    
**URL**    
```
http://api.sendcloud.net/apiv2/invalidstat/list
```
    
**HTTP请求方式**   
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|子账号|
|apiKey|string|是|密码|
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|apiUserList|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`apiUserList=a;b;c`|
|labelIdList|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`labelIdList=a;b;c`|
|domainList|string|否|获取指定域名下的统计数据, 多个域名用`;`分开, 如:`domainList=a;b;c`|
|aggregate|int(1, 0)|否|默认为0. 如果为1, 则返回聚合数据|
    
提示:

1. 获取统计数据时, 必须指定时间区间. 即 **startDate 与 endDate 的组合** 或者 **days 参数** 需二者取一
2. 当`aggregate`为 1 时, 会将所有维度的数据聚合累加
    
请求示例:    
```
http://api.sendcloud.net/apiv2/invalidstat/list?apiUser=***&apiKey=***&startDate=2015-03-12&endDate=2015-03-12
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|sendDate|发送日期，格式为yyyy-MM-dd|
|apiUser|子账号|
|labelId|标签ID|
|labelName|标签名称|
|domain|收信域名|
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
  "statusCode":200,
  "info":{
    "dataList":[
       {
        "sendDate":"2016-02-01",
        "apiUser":"postmaster@delong.sendcloud.org",
        "labelId":5285,
        "labelName":"中文测试1", 
        "domain":"gmail.com",
        "sendcloudBlacklist":1,
        "ubsubscribe":0,
        "espUnvaliable":0,
        "recipientAddressError":0,
        "recipientNotFound":0,
        "spamEmail":0,
        "senderOrRecipientRejected":0,
        "others":0
       }
   ]
  },
  "message": "请求成功",
  "result": true
}
```

