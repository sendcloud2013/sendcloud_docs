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

