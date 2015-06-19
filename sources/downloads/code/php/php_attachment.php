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

        $file = "./test.file"; #你的附件路径
        $handle = fopen('./test.file','rb');
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
