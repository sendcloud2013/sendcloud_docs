<?php

function send_mail() {
        $url = 'http://api.sendcloud.net/apiv2/mail/send';

        $API_USER = '...';
        $API_KEY = '...';
        $param = array(
            'apiUser' => $API_USER, # 使用api_user和api_key进行验证
            'apiKey' => $API_KEY,
            'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
            'fromName' => 'SendCloud',
            'to' => 'test1@ifaxin.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
            'subject' => 'Sendcloud php webapi with attachment example',
            'html' => '欢迎使用SendCloud',
            'respEmailId' => 'true'
        );

        $file = "./test.txt"; #你的附件路径
        $handle = fopen('./test.txt','rb');
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
        $data .= 'Content-Disposition: form-data; name="attachments"; filename="filename.txt"' . $eol;
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

