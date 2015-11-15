
## API 的请求格式

`http://api.sendcloud.sohu.com/apiv2/<模块>/<动作>`

模块: 功能模块名称.  比如: mail (邮件模块), addresslist (地址列表) 等.

动作: 每个模块所实现的功能. 比如: send, add 等.

**提示**: 所有的 API 都支持 HTTPS.

- - -

## API 返回码

API 返回的结果是 JSON 格式, 示例如下: 

```
# 请求成功
{
  "result": true,
  "statusCode": 200,
  "message": "请求成功",
  "info": {}
}
# 认证失败
{
  "result": false,
  "statusCode": 40005,
  "message": "认证失败",
  "info": {}
}
# 数据获取成功
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

* result: API 请求是否成功
* statusCode: API 返回码
* message: API 返回码的中文解释
* info: 数据信息

API 返回码如下: 

|API 返回码|含义|
|:---------|:---|
|200|请求成功|
|40001|start不能为空|
|40002|start非法|
|40003|limit不能为空|
|40004|limit非法|
|40005|认证失败|
|40006|days格式非法,必须是大于0的正整数|
|40007|startDate格式错误,应该类似'2013-03-19'|
|40008|endDate格式错误,应该类似'2013-03-19'|
|40009|labelIdList不能为空|
|40010|apiUserList不能为空|
|40011|email不能为空|
|40012|email格式非法|
|40013|domainList不能为空|
|40014|标签ID不能为空|
|40015|标签ID格式错误|
|40016|apiUserList格式非法|
|40017|聚合参数格式错误|
|40100|标签创建成功|
|40101|标签创建失败|
|40102|标签ID不能为空|
|40103|标签ID非法|
|40104|标签名称不能为空|
|40105|标签名称的长度应该为1-255个字符|
|40106|标签ID对应的标签不存在|
|40107|标签删除成功|
|40108|标签删除失败|
|40109|标签更新成功|
|40110|标签更新失败|
|40111|query不能为空|
|40112|query的长度的长度应该为1-255个字符|
|40113|标签名称已经存在|
|40201|模版调用名称invokeName不能为空|
|40202|模版调用名称invokeName格式错误|
|40203|模版类型不能为空|
|40204|非法的模板类型, 只能是0或者1|
|40205|templateStat不能为空|
|40206|templateStat非法, 只能是-1, -2, 1, 0中的值|
|40207|name不能为空|
|40208|name格式非法|
|40209|subject不能为空|
|40210|subject格式非法|
|40211|html不能为空|
|40212|html格式非法|
|40213|text不能为空|
|40214|text格式非法|
|40215|模板创建失败|
|40216|模板调用名称对应的模板不存在|
|40217|模板删除失败|
|40218|模板更新失败|
|40219|用户最多只能有50个模板|
|40220|模版调用名称已经存在|
|40221|isSubmitAudit不能为空|
|40222|isSubmitAudit格式错误|
|40223|模板处于待审核状态, 不能修改|
|40224|cancel不能为空|
|40225|cancel格式错误|
|40226|模板处于待审核状态, 无需再次提交|
|40227|模板已经审核通过, 无需再次提交|
|40228|模板处于审核失败状态, 无需撤销审核|
|40229|模板还未提交审核, 无法撤销审核|
|40301|用户不存在|
|40401|取消订阅记录创建成功|
|40402|取消订阅记录创建失败|
|40403|取消订阅记录删除成功|
|40404|取消订阅记录删除失败|
|40501|name不能为空|
|40502|地址列表名称的长度应该为1-48个字符|
|40503|address不能为空|
|40504|地址列表别名的长度应该为1-48个字符|
|40505|地址列表别名已经存在|
|40506|desc不能为空|
|40507|地址列表描述的长度应该为1-250个字符|
|40508|地址列表创建失败|
|40509|newAddress不能为空|
|40510|新的地址列表别名的长度应该为1-48个字符|
|40511|address参数错误|
|40512|members不能为空|
|40513|成员地址的长度应该为1-48个字符|
|40514|成员地址的个数不能小于0|
|40515|成员地址的个数不能超过1000|
|40516|添加成员失败|
|40517|地址列表不属于此用户|
|40518|成员地址不符合邮件地址规范|
|40519|删除成员失败|
|40520|vars不能为空|
|40521|vars参数中变量个数和成员地址个数不相等|
|40522|vars参数不符合JSON字符串语法|
|40601|退信记录删除成功|
|40602|退信记录删除失败|
|40701|分组ID不能为空|
|40702|分组ID格式错误|
|40703|事件类型不能为空|
|40704|事件类型格式错误,没有可用的事件类型|
|40705|url不能为空|
|40706|url格式错误|
|40707|url测试失败|
|40708|url已存在|
|40709|groupId对应的webhook配置未找到|
|40710|webhook配置创建失败|
|40711|webhook配置删除失败|
|40712|webhook配置修改失败|
|40801|发信人地址from不能为空|
|40802|发信人地址from格式错误|
|40803|发信人名称fromname不能为空|
|40804|发信人名称fromname格式错误|
|40805|收件人地址不能为空|
|40806|收件人地址数组中, 存在非法地址|
|40807|收件人地址的数目不能超过100|
|40808|邮件主题subject不能为空|
|40809|邮件主题subject格式错误|
|40810|回复地址replyto不能为空|
|40811|回复地址replyto格式错误|
|40812|xsmtpapi不能为空|
|40813|xsmtpapi格式错误|
|40814|xsmtpapi解析值不能为空|
|40815|xsmtpapi必须含有to字段|
|40816|xsmtpapi中to字段的解析值不能为空|
|40817|xsmtpapi解析错误|
|40818|attachments不能为空|
|40819|附件大小不能超过10485760字节|
|40820|此用户没有使用地址列表的权限|
|40821|地址列表任务创建成功|
|40822|地址列表任务创建失败|
|40823|邮件模板不存在|
|40824|模板未通过审核|
|40825|邮件模板和子账号类型不匹配|
|40826|参数subject和模板主题不能同时为空|
|40827|xsmtpapi中to数组长度不能超过100|
|40828|回执地址不能为空|
|40829|回执地址格式错误|
|40830|plain内容不能为空|
|40831|plain内容格式错误|
|40832|会议起始时间startTime不能为空|
|40833|会议起始时间startTime格式错误|
|40834|会议结束时间endTime不能为空|
|40835|会议结束时间endTime格式错误|
|40836|会议标题title不能为空|
|40837|会议标题title格式错误|
|40838|会议组织者名称不能为空|
|40839|会议组织者名称格式错误|
|40840|会议组织者邮件地址不能为空|
|40841|会议组织者邮件地址格式错误|
|40842|会议地点location不能为空|
|40843|会议地点location格式错误|
|40844|会议描述description不能为空|
|40845|会议描述description格式错误|
|40846|会议参与者名称不能为空|
|40847|会议参与者名称格式不对|
|40848|会议参与者邮件地址不能为空|
|40849|会议参与者邮件地址格式错误|
|40850|会议参与者名称个数和会议参与者邮件地址个数不相等|
|40851|会议邮件拼装失败|
|40852|cc地址不能为空|
|40853|cc地址格式错误|
|40854|CC地址的数目不能超过100|
|40855|bcc地址不能为空|
|40856|bcc地址格式错误|
|40857|BCC地址的数目不能超过100|
|40858|respEmailId不能为空|
|40859|respEmailId格式错误|
|40860|gzipCompress不能为空|
|40861|gzipCompressId格式错误|
|40862|to中有格式错误的地址列表|
|40863|to中有不存在的地址列表|
|40864|地址列表的数目不能超过5|
|40865|html解压失败|
|40866|plain解压失败|
|40867|处理附件发生异常|
|40868|headers不能为空|
|40869|headers格式错误|
|40870|html和plain不能同时为空|
|40871|html格式错误|
|40872|邮件列表地址不能为空|
|40873|useAddressList不能为空|
|40874|useAddressList格式错误|
|40901|邮件发送失败|
|40902|邮件处理发生未知异常|
|40903|邮件发送成功|
|40904|额度检查失败|
|40905|额度检查通过|
|40906|额度检查临时通过|
|40907|该子账号对应的内容不需要进行模板匹配|
|40908|邮件内容和邮件模板匹配不通过|
|40909|邮件内容和邮件模板匹配通过|
|40910|邮件内容和邮件模板匹配临时通过|
|40911|邮件内容和邮件模板匹配时出现编码错误|
|41001|name不能为空串|
|41002|name的长度应该为1-250个字符|
|41003|name不符合域名规则|
|41004|newName不能为空串|
|41005|newName的长度应该为1-250个字符|
|41006|newName不符合域名规则|
|41007|type不能为空串|
|41008|type不符合规则|
|41009|verify不能为空串|
|41010|verify不符合规则|
|41011|verify解析错误|
|41012|用户创建域名不能超过5个|
|41013|name参数错误, 多个域名|
|41014|域名不存在|
|41015|domain创建失败|
|41016|domain修改失败|
|41101|emailType不能为空串|
|41102|emailType不符合规则|
|41103|cType不能为空串|
|41104|cType不符合规则|
|41105|domainName不能为空串|
|41106|domainName不符合规则|
|41107|domainName的长度应该为1-250个字符|
|41108|domainName所属的域名不存在|
|41109|用户信息不存在|
|41110|name不能为空串|
|41111|name不符合规则, name的长度为6-32的字符串, 只能含有(A-Z,a-z,0-9,_)|
|41112|apiUser不能超过10个|
|41113|open不能为空串|
|41114|open不符合规则|
|41115|click不能为空串|
|41116|click不符合规则|
|41117|unsubscribe不能为空串|
|41118|unsubscribe不符合规则|
|41119|apiUser创建失败|
|49901|url格式错误|
|49902|http请求执行异常|
|49903|http请求执行失败|
|49904|http请求执行成功|
|49905|http返回结果解析错误|
|49906|http其他错误|
|501|服务器异常|

- - -

## SMTP 请求方式

为开发者提供 SMTP 协议的投递方式, 以下是会话过程. 详细的代码示例, 请移步[这里](../downloads/code/python/python.md#smtp)

``` 
S: 220 SendCloud Inbound Server ESMTP Haraka 2.2.4 ready

C: ehlo ifaxin.com

S: 250-SendCloud Inbound Server Hello, Haraka is at your service.
S: 250-PIPELINING
S: 250-8BITMIME
S: 250-SIZE 16000000
S: 250 AUTH LOGIN

C: AUTH LOGIN base64(api_user)

S: 334 UGFzc3dvcmQ6

C: base64(api_key)

S: 235 Authentication successful

C: mail FROM:<support@ifaxin.com>

S: 250 sender <support@ifaxin.com> OK

C: rcpt TO:<ben@ifaxin.com>

S: 250 recipient <ben@ifaxin.com> OK

C: data

S: 354 go ahead, make my day

C: ... ...
C: .

S: 250 #1426390015358_15_6484_8661.sc-10_10_127_51-inbound#Queued

C: quit

S: 221 SendCloud Inbound Server closing connection. Have a jolly good day
```

- - -

## messageId 和 emailId

`messageId` 是提交一次请求, 返回的消息编号.

`emailId` 是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人.

两者的计算关系如下:
```
to = [A, B, C]
emailId_A = messageId + to.index(A) + '$' + A
emailId_B = messageId + to.index(B) + '$' + B
emailId_C = messageId + to.index(C) + '$' + C

# 注意: position 不做位数补齐
```
举例如下: 
```
# to = ["ben@ifaxin.com", "joe@ifaxin.com", "bida@ifaxin.com", ... , "lianzimi@ifaxin.com"]
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound  # messageId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound0$ben@ifaxin.com  # emailId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound1$joe@ifaxin.com  # emailId
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound2$bida@ifaxin.com  # emailId
...
1425758592214_4576_32113_9310.sc-10_10_127_105-inbound99$lianzimi@ifaxin.com  # emailId
```
- - -
## taskId

todo

- - -

## X-SMTPAPI 扩展字段 

X-SMTPAPI 是 SendCloud 为开发者提供的邮件个性化定制的处理方式, 开发者通过这个特殊的 **信头扩展字段**, 可以设置邮件处理方式的很多参数.  一般的, 开发者在使用 SMTP 接入时会使用此字段. 不过, WEBAPI 的方式也支持此参数. 

X-SMTPAPI 是一个 JSON 格式的字符串, 里面包含邮件处理方式的参数. 具体用法见下: 

**`to` 含有收件人地址的数组**. 
```    
    {
        "to": ["ben@ifaxin.com", "joe@ifaxin.com"]
    }
```    
**注意**:

* X-SMTPAPI 里的 `to` 会覆盖收件人参数 `to`
* X-SMTPAPI 中的 `to` 的收件人个数不能超过100

**`sub` 是一个[关联数组](http://baike.baidu.com/view/1654988.htm). **它的 `key` 是「变量」, `value` 是「替换值数组」.

用法解释: 每一个「变量」对应一个「替换值数组」, 在做邮件内容替换时, 每一个「收件人」按其在「收件人数组」中出现的位置使用「替换值数组」中相应位置的值来替换「变量」的值.

相信你一定没看懂上面的饶口令, 所以, 请参见如下示例: 
```
# 邮件内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.

#---------------------------------------------------

# X-SMTPAPI
{
    "to": ["ben@ifaxin.com", "joe@ifaxin.com"],
    "sub":
    {
        "%name%": ["Ben", "Joe"],
        "%money%":[288, 497]
    }
}

#---------------------------------------------------

# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.
    
#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.
```
**`section` 是变量的变量.** 你可以在变量中使用 `section` 的变量, 用以简化很多收件人共有的替换内容.  
```    
# 邮件内容
亲爱的%name%:
  
    您好! 您本月在爱发信的消费金额为: %money% 元.

    感谢%role%用户: %role_words%.

#---------------------------------------------------

# X-SMTPAPI
{
    "to": ["ben@ifaxin.com", "joe@ifaxin.com", "bida@ifaxin.com"],
    "sub":
    {
        "%name%": ["Ben", "Joe", "Liubida"],
        "%money%":[288, 497, 688], 
        "%role%":["银牌", "金牌", "金牌"]
        "%role_words%":["%silver%", "%golden%", "%golden%"]
    },
    "section":
    {
        "silver": "some words written to silver user, maybe it is verrrrrrrrry long",
        "golden": "some words written to golden user, maybe it is verrrrrrrrry long, too",
    }
}

#---------------------------------------------------

# ben@ifaxin.com 收到的邮件:
亲爱的Ben:
    
    您好! 您本月在爱发信的消费金额为: 288 元.

    感谢银牌用户: some words written to silver user, maybe it is verrrrrrrrry long.

#---------------------------------------------------

# joe@ifaxin.com 收到的邮件:
亲爱的Joe:
    
    您好! 您本月在爱发信的消费金额为: 497 元.

    感谢金牌用户: some words written to golden user, maybe it is verrrrrrrrry long, too.

#---------------------------------------------------

# bida@ifaxin.com 收到的邮件:
亲爱的Liubida:
    
    您好! 您本月在爱发信的消费金额为: 688 元.

    感谢金牌用户: some words written to golden user, maybe it is verrrrrrrrry long, too.
```    
    
**`apps` 是包含了一组应用名和它们设置的关联数组.** 这些设置会覆盖它们在用户账户中的设置. 
```
{
   "......",
   "filters": {
        "subscription_tracking": { # 退订追踪
            "settings": { "enable": "1" }
        },
        "open_tracking": { # 打开追踪
            "settings": { "enable": "1" }
        },
        "click_tracking": { # 点击追踪
            "settings": { "enable": "1" }
        }
    }
}
```

