## WEBAPI 普通发送
    
```
<?php

function send_mail() {
        $url = 'http://sendcloud.sohu.com/webapi/mail.send.json';
        $API_USER = '...';
        $API_KEY = '...';

        $param = array(
            'api_user' => $API_USER, # 使用api_user和api_key进行验证
            'api_key' => $API_KEY,
            'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
            'fromname' => 'SendCloud',
            'to' => 'to1@domain.com;to2@domain.com',# 收件人地址, 用正确邮件地址替代, 多个地址用';'分隔  
            'subject' => 'Sendcloud php webapi common example',
            'html' => '欢迎使用SendCloud',
            'resp_email_id' => 'true'
        );
        

        $data = http_build_query($param);

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type: application/x-www-form-urlencoded',
                'content' => $data
        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
}

echo send_mail();
?>
```
    
- - - 

## WEBAPI 普通发送带附件

```
<?php

function send_mail() {
        $url = 'http://sendcloud.sohu.com/webapi/mail.send.json';

        $API_USER = '...';
        $API_KEY = '...';
        $param = array(
            'api_user' => $API_USER, # 使用api_user和api_key进行验证
            'api_key' => $API_KEY,
            'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
            'fromname' => 'SendCloud',
            'to' => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
            'subject' => 'Sendcloud php webapi with attachment example',
            'html' => '欢迎使用SendCloud',
            'resp_email_id' => 'true'
        );

        $file = "./test.php"; #你的附件路径
        $handle = fopen('./test.php','rb');
        $content = fread($handle,filesize($file));

        $eol = "\r\n";
        $data = '';

        $mime_boundary=md5(time());

        // 配置参数
        foreach ( $param as $key => $value ) { 
            $data .= '--' . $mime_boundary . $eol;  
            $data .= 'Content-Disposition: form-data; '; 
            $data .= "name=" . $key . $eol . $eol; 
            $data .= $value . $eol; 
        }

        // 配置文件
        $data .= '--' . $mime_boundary . $eol;
        $data .= 'Content-Disposition: form-data; name="somefile"; filename="filename.txt"' . $eol;
        $data .= 'Content-Type: text/plain' . $eol;
        $data .= 'Content-Transfer-Encoding: binary' . $eol . $eol;
        $data .= $content . $eol;
        $data .= "--" . $mime_boundary . "--" . $eol . $eol; 

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type: multipart/form-data;boundary='.$mime_boundary . $eol,
                'content' => $data
        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
        fclose($handle);
}

echo send_mail();
?>
```

- - -
    
## WEBAPI 模板发送
```
<?php

function send_mail() {
        $url = 'http://sendcloud.sohu.com/webapi/mail.send_template.json';

        $vars = json_encode( array("to" => array('to1@domain.com'),
                                   "sub" => array("code" => Array('123456'))
                                   )
                );

        $API_USER = '...';
        $API_KEY = '...';
        $param = array(
            'api_user' => $API_USER, # 使用api_user和api_key进行验证
            'api_key' => $API_KEY,
            'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
            'fromname' => 'SendCloud',
            'substitution_vars' => $vars,
            'template_invoke_name' => 'sendcloud_template',
            'subject' => 'Sendcloud php webapi template example',
            'resp_email_id' => 'true'
        );
        

        $data = http_build_query($param);

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type: application/x-www-form-urlencoded',
                'content' => $data
        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
}

echo send_mail();
?>
``` 
    
- - -
## WEBAPI 模板 && 地址列表 发送
```
<?php

function send_mail() {
        $url = 'http://sendcloud.sohu.com/webapi/mail.send_template.json';
        
        $API_USER = '...';
        $API_KEY = '...';

        $param = array(
            'api_user' => $API_USER, # 使用api_user和api_key进行验证
            'api_key' => $API_KEY,
            'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
            'fromname' => 'SendCloud',
            'use_maillist' => 'true',
            'to' => 'test@maillist.sendcloud.org',# 使用地址列表的别称地址
            'template_invoke_name' => 'sendcloud_template',
            'subject' => 'Sendcloud php webapi temaplate maillist example',
            'resp_email_id' => 'true'
        );
        

        $data = http_build_query($param);

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type: application/x-www-form-urlencoded',
                'content' => $data
        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
}

echo send_mail();
?>
```
   
- - -
    
## WEBAPI CURL 发送
```
<?php
function send_mail() {
        $API_USER = '...';
        $API_KEY = '...';

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
        curl_setopt($ch, CURLOPT_URL, 'http://sendcloud.sohu.com/webapi/mail.send.json');

        curl_setopt($ch, CURLOPT_POSTFIELDS, array(
                                'api_user' => $API_USER, # 使用api_user和api_key进行验证
                                'api_key' => $API_KEY,
                                'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
                                'fromname' => 'SendCloud',
                                'to' => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
                                'subject' => 'Sendcloud php webapi example',
                                'html' => "欢迎使用SendCloud",
                                'files' => '@./test.txt'));

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
    
## SMTP
    
此代码依赖邮件发送模块,首先需安装pear.
    
pear安装步骤：
    
* 下载安装包: curl -o go-pear.php  http://pear.php.net/go-pear.phar
* 安装: php go-pear.php
    
然后,通过pear来安装依赖库:
    
* pear install Mail    
* pear install Mail_Mime
* pear install Net_SMTP
    
操作完成.    

示例1: 快速发送, 依赖 Mail.php 模块, 不需要获取[messageId](../../email/#messageid-emailid).

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
        
     
示例2: 快速发送, 依赖 `Mail.php`, `Mail/smtp.php` 模块, 需要获取[messageId](../../email/#messageid-emailid).

此时, 我们采取的简单方法是修改相关模块的代码. 如下: 

1. php的lib库中, 找到Mail/smtp.php文件
2. 将 329 行 `send` 函数的返回值做修改. 如下图所示将 `return true` 改为 `return $args`.
      
![pic](/resources/php.png) 
    
接下来您就可以调用以下的代码进行邮件的发送, 并获取messageId了.
    
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

