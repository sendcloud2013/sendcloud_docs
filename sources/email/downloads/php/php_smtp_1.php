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

$host = "smtp.sendcloud.net";
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
