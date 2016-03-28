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
   'host'=>'smtp.sendcloud.net',
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

