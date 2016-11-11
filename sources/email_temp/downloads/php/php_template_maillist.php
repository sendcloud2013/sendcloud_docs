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

