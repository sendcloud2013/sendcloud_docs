## 1. 用户接入 SendCloud 的大致流程是怎样的?
    
** 免费用户 ( 每日额度: 200 )**

* 注册帐号, 激活邮件 ( 每日额度: +30 )
* 完善信息 ( 每日额度: +30 )
* 创建[发信域名](../guide/base.md#_3) ( 每日额度: +60 )
* 创建[API_USER](../guide/base.md#api_user) ( 每日额度: +40 ) 
* 创建[模板](../guide/base.md#template) ( 每日额度: +40 ) 

** 付费用户 ( 个人用户, 每日额度: 1000 )**

* [网站认证](../guide/base.md#_6)
* [商业认证](../guide/base.md#_6)
* 充值 ( 需先完成前两项 )

** 付费用户 ( 企业用户, 每日额度: 3000 )**

* [网站认证](../guide/base.md#_6)
* [商业认证](../guide/base.md#_6)
* 充值 ( 需先完成前两项 )

** 付费用户 ( 每日额度: > 6000 )**

* 信誉度在 90 分以上
* 联系客服
    
> SendCloud 为用户准备了测试的域名, API_USER, 模板, 你可以直接测试使用.

- - -    

## 2. 如何理解 mail from, from, reply-to?

**mail_from:** 信封上的发件人, 由「前缀@域名」组成. 此域名就是所谓的[发信域名](../guide/base.md#_3)
 
**from:** 信件内容里的发件人. 用户可以任意填写, 支持别名显示.

「代发」是否出现, 就是看 *mail_from* 和 *from* 是否相同.

**reply-to:** 信件回复的收件人. 用户直接回复邮件时, *reply-to* 就是默认的收件人. 如果用户不指定它, *from* 就是默认的收件人.

    # 如何写别名
    String from = MimeUtility.encodeText("爱发信客服", "UTF-8", "B") + "<support@ifaxin.com>";

- - -

## 3. 邮件中为什么会显示代发?

邮件中存在 *mail_from* 和 *from* 两个概念, 如果这两者不一致, 则 ESP 会在客户端上显示代发, 用以提醒收件人两者的不同.

显示代发如下图: 

![pic](/resources/domain_liubida.cn.png)

    from = 'test@liubida.cn'
    mail_from = '98a47302-beee-11e4-a3b9-00163e12fa99@push.liubida.cn'

如果两者一致, 则不会显示代发, 如下图: 

![pic](/resources/domain_push.liubida.cn.png)

当然, 有些 ESP 并不会要求这两者完全一致, 而是只要求两者的域名相同 ( 比如 QQ 邮箱 ) .

由于 SendCloud 对 *mail_from* 的前缀使用的是随机串, 所以, 如果碰到严苛的 ESP ( *mail_from* 和 *from* 必须完全一致才不显示代发, 比如网易邮箱 ), 那也只能爱莫能助了.

- - -

## 4. 【发送相关-队列状态】中, 有些域的发送被暂停, 显示"域名发信超出额度限制". 是什么原因?

ESP 对于单个域名每天的发信量是有限制的, 这个限制的量我们称之为发信阈值. 当某个域名发信的量到达阈值之后, ESP 就会直接拒信.

这个阈值到底是多少? 我们无从得知, 只知道它是由 ESP 根据此域名的历史发信数据, 用户投诉情况来动态调节的黑盒数据.

基于此种情况, SendCloud 实现了自适应发送策略 (预热发送): 
```
* SendCloud 能够根据 ESP 反馈的信息来判断出当前的发信量是否已经到达了阈值.
* 如果到达阈值, 则此域名下发往此 ESP 的邮件会被暂停, 每隔一段时间重试, 直至邮件发送成功.
* 根据经验, ESP 的阈值在凌晨会被清零. 清零之后, 暂停的邮件队列会自动启动, 继续发送.
* 通过一段时间的预热发送, 此域名的阈值会快速增长, 直至满足用户的日发送需求.
* 您可以一次性投递大量的邮件进入 SendCloud, 然后我们会根据 ESP 反馈信息, 动态实行发送启停, 直至发送完毕.
```

关于阈值, 再多说两句.

ESP 对于域名的阈值是会动态调节的, 如果邮件质量不错, 用户投诉低, 阈值是会快速提升的.

同时, 通过 SendCloud 发出的邮件, 在国内各大 ESP 的阈值的起始值是很高的, 远大于其他产品.

[知乎关于预热的描述](http://zhuanlan.zhihu.com/email/19802487)
- - -

## 5. WebHook 到底有什么作用?

一封邮件发送之后, 会产生各种各样的事件和状态.

例如: 请求成功, 发送成功, 被 ESP 弹回, 用户打开邮件, 点击链接, 或者退订了你的邮件, 甚至举报了您的邮件.

WebHook 都可以将这些事件以及相关数据 POST 给客户设置的 URL. 客户可以在收到数据后, 解析出事件和数据, 做相应的处理.

## 6. WebHook POST 的消息会丢失吗?
    
如果遇到 URL 访问错误或超时, SendCloud 最多会重试 5 次. 每次重试的时间间隔最快为1, 2, 4, 8, 16分钟. 即在消息丢失前, 你至少有半小时的时间来修复 URL.

如果超过重试次数，SendCloud 将会把消息丢弃. 

每次事件处理, 数据解析, 你需要在 3s 内返回状态码 200, 否则, SendCloud 将会重发该条消息。    

- - -

## 7. SMTP 方式接入, 如何使用标签功能?

验证 API_USER 时将标签ID ( label_id ) 拼接在后面即可, 拼接规则:

> api_user + '#' + label_id

不使用标签接入:
```
S: 250-SendCloud Inbound Server Hello, Haraka is at your service.
S: 250-PIPELINING
S: 250-8BITMIME
S: 250-SIZE 16000000
S: 250-STARTTLS
S: 250 AUTH LOGIN

C: AUTH LOGIN base64(api_user)
```

使用标签接入:
```
S: 250-SendCloud Inbound Server Hello, Haraka is at your service.
S: 250-PIPELINING
S: 250-8BITMIME
S: 250-SIZE 16000000
S: 250-STARTTLS
S: 250 AUTH LOGIN

C: AUTH LOGIN base64(api_user + '#' + label_id)
```

python 代码片段:
```
# 不使用标签接入:
s = SMTP('%s:%d' % (HOST, PORT))
s.set_debuglevel(DEBUG_MODE)
if USE_SSL:
    s.starttls()
s.login(API_USER, API_KEY)

# 使用标签接入:
s = SMTP('%s:%d' % (HOST, PORT))
s.set_debuglevel(DEBUG_MODE)
if USE_SSL:
    s.starttls()
s.login(API_USER + '#' + label_id, API_KEY)
```

- - -

## 8. 如何在客户端上使用 SendCloud 发信?

将发件服务器设置为 `smtp.sendcloud.net`, 帐号和密码分别对应 SendCloud 的 API_USER 和 API_KEY. 下图以 Foxmail 客户端为例.

![pic](/resources/foxmail.jpg)

- - -

## 9. SendCloud 的 SMTP 服务只开放了 25 端口么?

考虑到部分云服务商封禁了其内网对外 25 端口的访问, 所以 SendCloud 开放了2525和587端口.

连接HOST及端口号:

* smtp2525.sendcloud.net  端口号： 2525

* smtp587.sendcloud.net  端口号： 587

- - -

## 10. 邮件模板, 模板匹配, 模板发送, 我有点晕?

**邮件模板**: 简单的说, 邮件模板 (template) 就是预先定义的一段邮件内容.

**模板匹配**: 在发送时, 系统会根据邮件内容和模板内容进行匹配, 匹配成功的邮件才能被允许发送, 否则用户会收到 `553 template_validate not match, ...` 的错误提示. 详细的匹配细则参见[模板](../guide/base.md#template).

**模板发送**: 用户在请求接口时, 可以不再传输邮件内容, 只需在参数中指定模板的「调用名称」即可. 另外, 模板也支持变量. 这就是模板发送, 这个功能用户需要调用模板发送API 来使用.

SMTP 和普通发送API 不能也不应该调用模板来发送邮件用.

- - -

## 11. 通过 SendCloud 发送的邮件能否指定 Message-ID ?

可以的. 使用 API 调用发送, `headers` 参数里指定下 `Message-ID` 即可. 举例:

```
{
    "Message-Id": "<uuid...uuid@ifaxincom>", 
    ...
}
```
需要注意的点:

1. 此 `Message-ID` 非 SendCloud 系统中的 [messageId](guide/rule#messageid-emailid), 而是邮件头中的 `Message-ID`.
2. 用户指定的 `Message-ID` 需要符合 RFC 规范. 否则, 一些 ESP 会对 `Message-ID` 做格式的检查. 
3. 使用 `cc`, `bcc` , `xsmtpapi` 扩展字段时, 邮件会发送给多个人的, 如果用户定义了 `Message-ID`, 会导致这些邮件的 `Message-ID` 相同. 这种情况是不推荐的.

- - -

## 12. WebHook 的数据中可否带有我埋在邮件里的信息 ?

可以的. 使用 API 调用发送, 如果参数 headers 中某个 Key 以 "SC-Custom-" 开头, 则这个 Key:Value 会通过 WebHook 返回给用户.  举例:

```
{
    "SC-Custom-key1": "value1", 
    "SC-Custom-key2": "value2", 
    ...
}
```

然后用户收到的 WebHook 数据中就会带有这些信息.

![pic](/resources/faq12.png)

## 13. SendCloud 支持内嵌图片的邮件发送么?

APIV2 是支持的. 代码示例:

``` python
import requests                                                                 

url = "http://api.sendcloud.net/apiv2/mail/send"                         

API_USER = '...'
API_KEY = '...'

params = {                                                                      
    "apiUser": API_USER, # 使用api_user和api_key进行验证                       
    "apiKey" : API_KEY,                                             
    "from" : "sendcloud@sendcloud.org", # 发信人, 用正确邮件地址替代                                        
    "to" : "d@ifaxin.com", # 收件人地址, 用正确邮件地址替代, 多个地址用';'分隔                                
    "subject" : "SendCloud python embed example",                              
    "html": '<p>1st image</p> <img src="cid:image1"> <p>2nd image</p> <img src="cid:image2"/> <p>3rd image</p> <img src="cid:image1"/>',
    "embeddedCid": "image1;image2",
}                                                                               

filename1 = "/path/image1.jpg"
display_filename_1 = "image1"

filename2 = "/path/image2.jpg"
display_filename_2 = "image2"

files = [
    ("embeddedImage", (display_filename_1, open(filename1, 'rb'),'application/octet-stream')),
    ("embeddedImage", (display_filename_2, open(filename2, 'rb'),'application/octet-stream'))
]

r = requests.post(url, files=files, data=params)

print r.text
```

SMTP的代码示例如下:
```         
from email.mime.multipart import MIMEMultipart  
from email.mime.text import MIMEText  
from email.mime.image import MIMEImage  
import smtplib  
  
class MyEmail:  
    def __init__(self):  
        self.smtp = smtplib.SMTP()  
        self.login_username = 'postmaster@testdomain.sendcloud.org'
        self.login_password = '*****'  
        self.sender = 'postmaster@testdomain.sendcloud.org'    # same as login_username  
        self.receiver = 'test123@qq.com'  
        self.host = 'smtp.sencloud.net'  
        self.port = 25
  
    def connect(self):  
        self.smtp.connect(self.host)  
  
    def login(self):  
        self.smtp.login(self.login_username, self.login_password)  
  
    def send(self):  
        msg = MIMEMultipart('related')                 
        msg['From'] = self.sender               
        msg['To'] = self.receiver
        email_tiltle = 'python test inline image'
        msg['Subject'] = email_title           
        content = MIMEText('test image content <img src="cid:image1" alt="xxxxx">', 'html', 'utf-8')
        msg.attach(content) 

        fp = open('./test.png','rb')
        img = MIMEImage(fp.read())
        img.add_header('Content-ID','image1') #the value of content-id is the cid in html
        msg.attach(img)
  
        self.smtp.sendmail(self.sender, receiver, msg.as_string())   
  
    def quit(self):  
        self.smtp.quit()  
  
def send():  
    myemail = MyEmail()  
    myemail.connect()  
    myemail.login()  
    myemail.send()  
    myemail.quit()  
  
if __name__ == "__main__":  
    send()
```    


- - -

## 14. 我想发送会议日历，SendCloud可以实现这个功能吗？

可以的哦，在邮件API 2.0中进入【发送相关】-【发送会议日历】，会有详细的调用接口方法，对照着其中的参数说明来设计您的日历格式，您可以添加会议标题、会议地点、参会者姓名等各种会议信息；

需要注意的是，使用此接口前请先联系客服，开通相关权限后才可以调用哦。[点此查看API文档](../email_v2/send_email.md#_3)


- - -

## 15. 邮件摘要有什么用?怎么使用?

邮件摘要主要是邮件具体内容的一个概括，在较为高级的一些邮箱内，阅读邮件前可以通过邮件摘要了解邮件大致内容，选择是否需要点开该封邮件。如下图红框中的内容就是邮件摘要。

![pic](/resources/u4.png)

当我们不写邮件摘要时，邮箱可能提取部分邮件内容作为邮件的摘要，那么阅读者看到的邮件可能就是如下图这类的情况。显示了不必要的奇怪内容

![pic](/resources/u10.png)

为了用户更好的展示邮件，SendCloud已经实现了便捷的邮件摘要发送功能，使用邮件摘要并不影响原有邮件内容的展示。
使用途径有两种：

1、使用邮件模版，编辑邮件模版时，在邮件摘要内填入需要的内容。

2、使用API[普通发送](../email_v2/send_email.md#_1)或[模板发送](../email_v2/send_email.md#_2)时，对于contentSummary字段进行赋值。

特别说明：

• 邮件标题和邮件摘要字数的总和建议不要超过60个字，可能出现摘要无法完全展示的情况。

• 若邮件标题和邮件摘要字数总和过少，邮箱服务商可能会取用邮件内容进行补充。

• 受限于不同设备和不同邮箱服务商，腾讯、gmail、hotmail邮箱的PC端支持展示邮件摘要，其他邮箱或移动端可能不支持或展示不完整。

• 想要获得更好的邮件摘要的显示，请充分测试后发送哦~


- - -

## 16. Sendcloud消息提醒的机制是怎样的？

为了给用户最大的自由度、更方便的调整发送策略，我们做了自定义式的消息提醒，可以自己来设置相关规则。

**提醒通知**

![pic](/resources/faq-notice.png)

以上通知都有默认阈值。其中触发邮件暂停数超过阈值没有默认选择通知渠道，其余通知选项默认选择邮件通知渠道。

通知渠道可以多选，邮件是任意用户都可选择，微信只有绑定微信的用户才能选择，短信只有付费用户才能选择，邮件和短信都是默认模版。

**审核通知**

![pic](/resources/faq-check.png)

以上通知默认选中‘有结果触发’。通知渠道同提醒通知。

**通知成员**

通知成员最多为4个手机号，4个邮箱，两两不同。发送通知后默认通知所有通知成员。
