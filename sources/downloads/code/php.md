### WEBAPI

```
<?php

function send_mail() {
        $url = 'http://sendcloud.sohu.com/webapi/mail.send.json';

        $param = array(
                'api_user' => '***', # 使用api_user和api_key进行验证
                'api_key' => '***',
                'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
                'fromname' => 'SendCloud',
                'to' => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
                'subject' => 'Sendcloud php webapi example',
                'html' => '<html><head></head><body><p>欢迎使用<a href=\'http://sendcloud.sohu.com\'>SendCloud</a></p></body></html>'
        );

        $options = array(
                'http' => array(
                        'method' => 'POST',
                        'content' => http_build_query($param)));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, false, $context);

        return $result;
}

echo send_mail();
?>
```
    
- - -
    
###使用 curl 方式调用 WEBAPI
```
<?php
function send_mail() {

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
        curl_setopt($ch, CURLOPT_URL, 'http://sendcloud.sohu.com/webapi/mail.send.json');

        curl_setopt($ch, CURLOPT_POSTFIELDS,
                        array(
                                'api_user' => '***', # 使用api_user和api_key进行验证
                                'api_key' => '***',
                                'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
                                'fromname' => 'SendCloud',
                                'to' => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
                                'subject' => 'Sendcloud php webapi example',
                                'html' => "<html><head></head><body><p>欢迎使用<a href=\'http://sendcloud.sohu.com\'>SendCloud</a></p></body></html>"));

        $result = curl_exec($ch);

        if($result === false) {
                echo curl_error($ch);
        }
        curl_close($ch);
        return $result;
}

echo send_mail();
?>
```
    
- - -
    
### SMTP
    
此代码依赖邮件发送模块,首先需安装pear.
    
pear安装步骤：
    
* 下载安装包: curl -o go-pear.php  http://pear.php.net/go-pear.phar
* 安装: php go-pear.php
    
然后,通过pear来安装依赖库:
    
* pear install Mail    
* pear install Mail_Mime
* pear install Net_SMTP
    
操作完成.    
     

示例1:快速发送,依赖 Mail.php 模块, 供不需要获取[messageId](../email/index.md/#_7)时使用.

```
<?php
set_include_path("/usr/local/lib/php/");
require_once "Mail.php";

# 发信人，用正确邮件地址替代
$from = "sendcloud@sendcloud.org";           

# 收件人地址，用正确邮件地址替代
$to = "to@sendcloud.org";                   

$subject = "SendCloud PHP Smtp Example";

# 邮件正文，html格式
$body = "<html><head></head><body>
        <p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>
        </body></html>";

# 使用api_user和api_key进行验证  
$username = "api_user"; 
$password = "api_key";  

$host = "smtpcloud.sohu.com";
$port = 25;

$headers = array (
    'From' => $from,
    'To' => $to,
    'Subject' => $subject,
    'Content-Type' => 'text/html;charset=utf8');

$smtp = Mail::factory(
    'smtp',   
    array (
        'host' => $host,
        'port' => $port,
        'auth' => true,
        'debug' => true,
        'username' => $username,
        'password' => $password));

$mail = $smtp->send($to, $headers, $body);  
if (PEAR::isError($mail)) {   
    echo("<p>" . $mail->getMessage() . "</p>");  
} 
else {   
    echo("<p>Successfully!</p>");  
} 
?>

```
        
     
示例2:
    
需要依赖Mail/smtp.php模块,由于模块默认在发送成功时,不返回服务器的信息. 
因此如果需要获得服务器返回的messageId,需要做如下操作.
首先在您的php的lib库中, 找到Mail/smtp.php文件,
将329行send函数的返回值做修改.如下图所示将return true改为return $args.
      
![pic](../resources/php.png) 
    
接下来您就可以调用以下的代码进行邮件的发送和MessageId的获取了.
    
```
<?php
set_include_path("/usr/local/lib/php/");


include_once 'Mail.php';
include_once 'Mail/smtp.php';

# 发信人，用正确邮件地址替代
$from='testaddress@qq.com';

# 收件人地址，用正确邮件地址替代
$to = array('test1@qq.com','test2@163.com');  

# 邮件标题
$subject='SendCloud PHP Smtp Example';

# 邮件正文,html格式
$body = "<html><head></head><body>
        <p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>
        </body></html>";

# smtp参数, 用正确api_user和api_key验证
$param = array (
   'host'=>'smtpcloud.sohu.com',
   'ip'=>25,
   'auth'=>true,
   'username'=>'api_user',
   'password'=>'api_key');


$headers = array (
    'From' => $from,
    'To' => $to,
    'Subject' => $subject,
    'Content-Type' => 'text/html;charset=utf8');

function getMessageId($res)
{
    $list = explode('#',$res);
    return $list[1];
} 

$mail = new Mail_smtp($param);

$res = $mail->send($to, $headers, $body);
$messageId = getMessageId($res);
echo $messageId;

```

