以下为各种语言使用 WEBAPI 和 SMTP 两种方式, 请求 SendCloud 发送邮件的示例.

欢迎开发者积极为 SendCloud 拓展各种语言的代码示例.

## Python

[普通发送](email/downloads/python/python_common.py)

[模板发送](email/downloads/python/python_template.py)

[模板发送&&地址列表](email/downloads/python/python_template_maillist.py)

[SMTP发送](email/downloads/python/python_smtp.py)

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

[WEBAPI_代码示例](email/downloads/java/SendCloud.java)

[SMTP_代码示例](email/downloads/java/SendCloudSmtp.java)

## PHP

[普通发送](email/downloads/php/php_common.php)

[普通发送_curl](email/downloads/php/php_curl.php)

[普通发送&&附件](email/downloads/php/php_attachment.php)

[模板发送](email/downloads/php/php_template.php)

[模板发送&&地址列表](email/downloads/php/php_template_maillist.php)

## Ruby

依赖
代码需要安装rest_client
```
gem install rest_client
```

[普通发送](email/downloads/ruby/ruby_common.rb)

[普通发送&&附件](email/downloads/ruby/ruby_attachment.rb)

[模板发送](email/downloads/ruby/ruby_template.rb)

[模板发送&&地址列表](email/downloads/ruby/ruby_template_maillist.rb)

## Perl

依赖
模板发送需要依赖json包
```
从CPAN下载安装
https://metacpan.org/pod/JSON
```

[普通发送](email/downloads/perl/perl_common.pm)

[普通发送&&附件](email/downloads/perl/perl_attachment.pm)

[模板发送](email/downloads/perl/perl_template.pm)

[模板发送&&地址列表](email/downloads/perl/perl_template_maillist.pm)

[SMTP发送](email/downloads/perl/perl_smtp.pm)

## CSharp

[普通发送](email/downloads/csharp/csharp_common.cs)

[模板发送&&附件](email/downloads/csharp/csharp_template.cs)

[SMTP发送](email/downloads/csharp/csharp_smtp.cs)

## Golang

[普通发送](email/downloads/golang/golang_common.go) 感谢 Dannys Han

[模板发送](email/downloads/golang/golang_template.go) 感谢 smartwalle [github](https://github.com/smartwalle/sendcloud)

## Nodejs

[普通发送](https://github.com/shanelau/sendcloud) 感谢 shanelau

