<?php
function send_mail() {
        $API_USER = '...';
        $API_KEY = '...';

        $ch = curl_init();

        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
        curl_setopt($ch, CURLOPT_URL, 'http://api.sendcloud.net/apiv2/mail/send');

        curl_setopt($ch, CURLOPT_POSTFIELDS, array(
                                'apiUser' => $API_USER, # 使用api_user和api_key进行验证
                                'apiKey' => $API_KEY,
                                'from' => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
                                'fromName' => 'SendCloud',
                                'to' => 'test@ifaxin.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
                                'subject' => 'Sendcloud php webapi example',
                                'html' => "欢迎使用SendCloud",
                                'files' => '@./test.txt')); #附件名称

        $result = curl_exec($ch);

        if($result === false) {
                echo curl_error($ch);
        }
        curl_close($ch);
        return $result;
}

echo send_mail();
?>
