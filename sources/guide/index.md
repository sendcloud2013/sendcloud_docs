

*需要注意的一般名词*
**需要注意的SendCloud专属名词**
「简单解释」

# 引言
邮件是一个岁月悠久的服务, 在这个行业中, 众多前辈们制定了很多的行业标准. 所以, 想要让您的邮件顺利的送达, **遵循游戏规则** 是非常必要的o~.

## 服务方式
### 邮件类型

**触发邮件**: 在某种场景下, 由事件触发的邮件发送. 举例: 注册激活, 密码找回, 站内通知, 信息确认, 账单寄送等.

**批量邮件**: 商家对会员发送的通知邮件. SendCloud 要求收件地址来源自会员注册, 发送内容是用户主动订阅, 且不带有广告营销性质.

### SendCloud的服务机制

SendCloud 为用户提供了 SMTP 和 WEBAPI 两种调用接口的方式, 用户可以根据业务场景或个人喜好选择任一方式接入

* 用户编写程序, 调用 SendCloud 的接口, 传输邮件数据
* 请求成功之后, SendCloud 会对邮件数据进行处理(排队调度, 速率控制, 变量替换, 追踪), 之后邮件会被调度到相应的外发机器「outbound」
* 最终, 由 outbound 和邮件服务商通讯, 将邮件投递出去

### 通过SMTP接入
使用SMTP协议传输数据到SendCloud的邮件服务器(smtpcloud.sohu.com:25)

* 用户可以编写程序连接邮件服务器, 发送邮件 
* 用户配置客户端连接邮件服务器, 只需修改用户名, 密码和 SMTP 配置即可

### 通过WEBAPI接入

WEBAPI是使用HTTP接入SendCloud服务的一种方式. 开发者可以利用SendCloud提供的HTTP接口, 调用SendCloud的服务. 

目前WEBAPI提供了邮件发送(触发/触发), 域名查询, 个人信息, 地址列表(CRUD), 统计查询, 标签(CRUD), 退信列表(CRUD), 取消订阅列表(CRUD) 等功能. 

开发者只需要选择熟悉的开发语言, 实现客户程序, 就可以方便地和SendCloud进行通信.

### 验证信息

* API_USER

    * API_USER是调用接口发信时的帐号

    * 创建API_USER时, 你需要指定其「类型」(触发/批量), 「发信域名」, 「追踪选项」

    * 类型: 触发类型的API_USER只能发送触发邮件, 批量类型的API_USER只能发送批量邮件

    * 发信域名: 配置之后, 使用此API_USER发信的发信域名就被确定了

    * 追踪选项: 选择之后, 使用此API_USER发信, 就可以收集用户相应的追踪信息

* API_KEY

    * API_KEY是调用接口发信时的密码

    * 成功注册后, SendCloud会生成API_KEY

    * API_KEY可以重置, 重置之后立即生效

    * 所有API_USER共用一个API_KEY


## 发送配置

### 发信域名

发信域名是一封邮件的出处. 在smtp会话过程中, 即是mail from的后缀

    mail from: test@liubida.cn
    250 sender test@liubida.cn OK

此时, liubida.com*就是这封邮件的*发信域名*. 发信域名的概念很重要, 因为很多邮件服务器就是根据发信域名来确定*发信的频度*, *每天发信的数量*等重要指标的.
「注册帐号」
「liubdia.cn」
「」

SendCloud为免费用户生成了「测试域名」, 方便用户直接进行测试发送. 测试域名的命名规则: '随机串.sendcloud.org'.
当用户正式接入服务时, 需要在SendCloud平台上配置自己的发信域名

* 正式接入SendCloud平台用户, 应该拥有自己服务的域名(比如liubida.cn)
* 你可以使用liubida.cn的子域来作为自己的发信域名(比如push.liubida.cn)
* 通过`设置/域名/新增发信域名`来创建自己的发信域名

***

使用测试域名: **W0YoeJHepAzA7v1JDuG6e8reehqEuPoP.sendcloud.org** 发送的邮件
![pic](../resources/domain_test.png)

* 前缀: beb31150-bef0-11e4-9dbb-00163e15002f
* 发信域名: W0YoeJHepAzA7v1JDuG6e8reehqEuPoP.sendcloud.org
* mail from的组成: 前缀@发信域名 (beb31150-bef0-11e4-9dbb-00163e15002f@W0YoeJHepAzA7v1JDuG6e8reehqEuPoP.sendcloud.org)

***

使用自有域名(自己创建的域名): **push.liubida.cn** 发送的邮件
![pic](../resources/domain_liubida.cn.png)

* 前缀: 98a47302-beee-11e4-a3b9-00163e12fa99
* 发信域名: push.liubida.cn
* mail from的组成: 前缀@发信域名(98a47302-beee-11e4-a3b9-00163e12fa99@push.liubida.cn)

如何去掉代发? 域名显示代发, 放在高级里面说明, 这里做个跳转
![pic](../resources/domain_push.liubida.cn.png)

### 发信域名配置

创建发信域名之后, 需要完成必要的配置, 才能使用其进行发信. 这些配置项是保证域名能够优质出信的基础, 如果使用未配置的域名发信, 出信量和到达率都会很糟糕.

* **VERIFY_KEY** (必配)

VERIFY_Key是SendCloud分配给每个账户的域名唯一标示, 防止域名被其他账户盗用

* **SPF** (必配) [wiki解释](http://zh.wikipedia.org/wiki/Sender_Policy_Framework)

SPF是为了防范垃圾邮件而提出来的一种DNS记录类型, 用于登记某个域名拥有的用来外发邮件的所有IP地址.

* **MX** (必配)

MX是邮件交换记录, 它指向一个邮件服务器, 用于电子邮件系统发邮件时根据收信人的地址后缀来定位邮件服务器

* **CNAME**

CNAME是链接跳转记录. SendCloud需要此配置来为你收集用户的打开, 点击和退订等反馈信息. 
此项是选配项. 如果不配置, SendCloud只能为你收集打开和退订. 只有在配置此项之后, SendCloud才能为你收集用户的所有反馈信息(打开, 点击和退订).

* **DKIM**  [wiki解释](http://zh.wikipedia.org/wiki/DKIM)

DKIM是防止欺诈邮件的一个重要技术手段, 通常发送方会在电子邮件的标头插入DKIM-Signature及电子签名资讯, 而接收方则透过DNS查询得到公钥后进行验证. 建议配置, 特别是国外域比较多的用户.

`正是因为发信域名如此的重要, 所以SendCloud要求收费用户必须创建自己的发信域名, 并且完成配置, 才能正式接入.`

![pic](../resources/9.png)

### 样本审核(sample)

为了杜绝用户利用 SendCloud 平台发送色情, 欺诈等非法邮件, SendCloud 要求用户将每次发送的邮件提交审核. 流程如下:

* 用户将邮件内容复制, 创建一个样本, 并提交审核
* 此样本审核通过之后, 用户就可以发送此类邮件

SendCloud 在接收用户邮件时, 会对用户邮件和用户样本进行样本验证. 只要能匹配成功一个样本, 既验证通过. 否则, 用户会收到`553 sample_validate not match`的错误提示.

邮件和样本匹配的要素有如下:

1. 发送邮件时使用的API_USER的类型 (触发/批量) 和样本的类型(触发/批量) 一致
2. 邮件的附件个数和样本中声明的附件个数 一致
3. 邮件中图片个数和样本中的图片个数 一致
4. 邮件中每张图片的内容和样本中的相应图片的内容 一致 (如需修改图片内容, 请重新提交审核)
5. 邮件中文本内容和样本文本内容达到一定比例的匹配, 容许两者有差异

对于大多数用户来说, 每封邮件的差异性不大, 所以样本审核没问题. 

如果用户发送的邮件差异性很大, 样本审核成为你接入SendCloud的障碍, 请在系统中提交工单, 会有同学及时为你处理解决此问题.

邮件样本是用户发送内容在SendCloud平台的备案，在样本审核通过后，SendCloud会在您发送邮件时，将邮件样本与发信内容进行匹配，匹配度达到一定的比例，即可成功发送，邮件样本存在【发送相关】-【邮件样本】页面中。


### 模板(template)

「简单解释」
## 发送配置

### 样本(sample)
### 模板(template)

