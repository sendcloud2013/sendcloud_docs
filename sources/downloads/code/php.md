### WEBAPI

调用 WEBAPI 
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

使用 curl 方式调用 WEBAPI
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
```
<?php
set_include_path("/path/to/pear");
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

