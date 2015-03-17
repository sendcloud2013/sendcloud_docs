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

```
此代码依赖邮件发送模块,首先需安装pear.
    
pear安装步骤：
    
* 下载安装包: curl -o go-pear.php  http://pear.php.net/go-pear.phar
* 安装: php go-pear.php
    
然后,通过pear来安装依赖库:
    
* pear install Mail    
* pear install Mail_Mime
* pear install Net_SMTP
    
操作完成.    
```
```
示例1:快速发送,如果不需要获取messageID,可使用.
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
        
```
示例2: 如果需要获得每次请求的唯一标示messageId,建议使用
<?php
set_include_path("/usr/local/lib/php/");

include_once "Mail.php";
include_once 'Net/SMTP.php';

function prepareHeaders($headers)
{
    $lines = array();
    $from = null;

    foreach ($headers as $key => $value) {
        if (strcasecmp($key, 'From') === 0) {
            include_once 'Mail/RFC822.php';
            $parser = new Mail_RFC822();
            $addresses = $parser->parseAddressList($value, 'localhost', false);
            if (is_a($addresses, 'PEAR_Error')) {
                return $addresses;
            }

            $from = $addresses[0]->mailbox . '@' . $addresses[0]->host;

            if (strstr($from, ' ')) {
                return false;
            }

            $lines[] = $key . ': ' . $value;
        } elseif (strcasecmp($key, 'Received') === 0) {
            $received = array();
            if (is_array($value)) {
                foreach ($value as $line) {
                    $received[] = $key . ': ' . $line;
                }
            }
            else {
                $received[] = $key . ': ' . $value;
            }
            $lines = array_merge($received, $lines);
        } else {
            if (is_array($value)) {
                $value = implode(', ', $value);
            }
            $lines[] = $key . ': ' . $value;
        }
    }

    return array($from, join("\r\n", $lines));
}

function getMessageId($res)
{
    $list = explode('#',$res);
    return $list[1];
}    


function send($recipients, $headers, $body)
{
    $smtp = &new Net_SMTP('smtpcloud.sohu.com',25);
    $smtp->connect();
    //使用api_user和api_key进行验证
    $res = $smtp->auth("api_user","api_key");

    $headerElements =  prepareHeaders($headers);
    list($from, $textHeaders) = $headerElements;
    if (!empty($headers['Return-Path'])) {
        $from = $headers['Return-Path'];
    }

    $smtp->mailFrom($from);
    
    foreach ($recipients as $recipient){
        $res = $smtp->rcptTo($recipient);
        echo $res;
    }

    $res = $smtp->data($textHeaders . "\r\n\r\n" . $body);
    list(,$args) = $smtp->getResponse();

    $messageId = getMessageId($args);
    echo $messageId;

    $smtp->disconnect();
    return $messageId;
}

$from='testaddress@qq.com';
$to = array('test1@ifaxin.com','test2@ifaxin.com');  
$subject='SendCloud PHP Smtp Example';
$body = "<html><head></head><body>
        <p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>
        </body></html>";

$headers = array (
    'From' => $from,
    'To' => $to,
    'Subject' => $subject,
    'Content-Type' => 'text/html;charset=utf8');

$messageId = send($to, $headers, $body); 

```

