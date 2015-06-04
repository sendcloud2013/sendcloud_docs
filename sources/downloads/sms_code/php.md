## WEBAPI 方式一

```
<?php

function send_sms() {
        $url = 'http://sendcloud.sohu.com/smsapi/send';

        $param = array(
            'smsUser' => '***', 
            'templateId' => '1',
            'phone' => '13412345678',
            'vars' => '{"%code%":"123456"}'
        );

        $sParamStr = "";
        ksort($param);
        foreach ($param as $sKey => $sValue) {
            $sParamStr .= $sKey . '=' . $sValue . '&';
        }
        
        $sParamStr = trim($sParamStr, '&');
        $smskey = '***'
        $sSignature = md5($smskey."&".$sParamStr."&".$smskey);


        $param = array(
            'smsUser' => '***', 
            'templateId' => '1',
            'phone' => '13412345678',
            'vars' => '{"%code%":"123456"}',
            'signature' => $sSignature
        );

        $data = http_build_query($param);
        echo $data;

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type:application/x-www-form-urlencoded',
                'content' => $data

        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
}

echo send_sms();
?>
```
