以下为各种语言使用 API V2 发送邮件的示例.


## Python

[普通发送](email_v2/downloads/python/python_common.py)

[模板发送](email_v2/downloads/python/python_template.py)

[模板发送&&地址列表](email_v2/downloads/python/python_template_maillist.py)

## Java

依赖
代码示例需要依赖如下jar包
```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.2</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpmime</artifactId>
    <version>4.2</version>
</dependency>
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20140107</version>
</dependency>
```
[httpclient](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.2) [httpmime](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime/4.2) [org.json](http://mvnrepository.com/artifact/org.json/json/20140107)

[WEBAPI_代码示例](email_v2/downloads/java/SendCloud.java)

[SMTP_代码示例](email_v2/downloads/java/SendCloudSmtp.java)

[SMTP_代码示例2](email_v2/downloads/java/SendCloudSmtp2.java)


## PHP

[普通发送](email_v2/downloads/php/php_common.php)

[普通发送_curl](email_v2/downloads/php/php_curl.php)

[普通发送&&附件](email_v2/downloads/php/php_attachment.php)

[模板发送](email_v2/downloads/php/php_template.php)

[模板发送&&地址列表](email_v2/downloads/php/php_template_maillist.php)

```
SMTP 代码依赖邮件发送模块,首先需安装pear.

pear安装步骤：
下载安装包: curl -o go-pear.php  http://pear.php.net/go-pear.phar
安装: php go-pear.php
然后, 通过pear来安装依赖库:
pear install Mail 
pear install Mail_Mime
pear install Net_SMTP
```    

[SMTP_代码示例1](email/downloads/php/php_smtp_1.php) (快速发送，不需要获取[messageId](../guide/rule/#messageid-emailid))

[SMTP_代码示例2](email/downloads/php/php_smtp_2.php) (需要获取[messageId](../guide/rule/#messageid-emailid))

```
1. php的lib库中, 找到Mail/smtp.php文件
2. 将 329 行 `send` 函数的返回值做修改. 如下图所示将 `return true` 改为 `return $args`.
接下来您就可以调用以下的代码进行邮件的发送, 并获取messageId了.
```
      
![pic](/resources/php.png) 
          

## Ruby

依赖
代码需要安装rest_client
```
gem install rest_client
```

[普通发送](email_v2/downloads/ruby/ruby_common.rb)

[普通发送&&附件](email_v2/downloads/ruby/ruby_attachment.rb)

[模板发送](email_v2/downloads/ruby/ruby_template.rb)

[模板发送&&地址列表](email_v2/downloads/ruby/ruby_template_maillist.rb)

[SMTP_代码示例1](email/downloads/ruby/ruby_smtp_1.rb) (快速发送，不需要获取[messageId](../guide/rule/#messageid-emailid))

[SMTP_代码示例2](email/downloads/ruby/ruby_smtp_2.rb) (需要获取[messageId](../guide/rule/#messageid-emailid))

```
SMTP发送依赖net/smtp.rb模块, 由于模块默认在发送成功时,不返回服务器的信息. 
因此如果需要获得服务器返回的messageId,需要做如下操作. 首先在您的ruby的lib
库中, 找到net/smtp.rb文件, 在915行data函数中加一个返回值,操作如下
```
      
![pic](/resources/ruby2.png)

```
接着在660行send_messages函数中增加一个返回值,修改如下
```

![pic](/resources/ruby1.png)

## Perl

依赖
模板发送需要依赖json包
```
从CPAN下载安装
https://metacpan.org/pod/JSON
```

[普通发送](email_v2/downloads/perl/perl_common.pm)

[普通发送&&附件](email_v2/downloads/perl/perl_attachment.pm)

[模板发送](email_v2/downloads/perl/perl_template.pm)

[模板发送&&地址列表](email_v2/downloads/perl/perl_template_maillist.pm)

[SMTP发送](email/downloads/perl/perl_smtp.pm)

## CSharp

[普通发送](email_v2/downloads/csharp/csharp_common.cs)

[模板发送&&附件](email_v2/downloads/csharp/csharp_template.cs)

[SMTP发送](email/downloads/csharp/csharp_smtp.cs)



