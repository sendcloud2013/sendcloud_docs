###WEBAPI
```
#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'

def send_mail
        response = RestClient.post "http://sendcloud.sohu.com/webapi/mail.send.json",
        :api_user => "***", # 使用api_user和api_key进行验证
        :api_key => "***",
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromname => "SendCloud",
        :to => "to1@domain.com;to2@domain.com", # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        :subject => "SendCloud ruby webapi example",
        :html => '欢迎使用SendCloud'

return response
end

response = send_mail
puts response.code
puts response.to_str
```
    
- - - 
      
###SMTP
       
示例1:快速发送,供不需要获取messageId时使用.
```
require 'rubygems'                                                                                                                                                                       
require 'mail'                                                                  

$KCODE = 'U' #utf8                                                              

Mail.defaults do                                                                
    delivery_method :smtp, {                                                       
        :address   => "smtpcloud.sohu.com",                                     
        :port      => 25,                                                       
        :user_name => "api_user",                     
        :password  => "api_key",                                          
        :authentication => 'login'                                                 
    }                                                                              
end                                                                                

mail = Mail.deliver do                                                          
    to   ['to1@sendcloud.com', 'to2@sendcloud.com']         
    from (Mail::Encodings.b_value_encode 'fromname', 'UTF-8').to_s  + '<sendcloud@sendcloud.com>'
    subject 'SendCloud Ruby Smtp Example'                                          
    html_part do                                                                   
        content_type 'text/html; charset=UTF-8'                                    
        body '<html><head></head><body>'                                           
            + '<p><a href=http://sendcloud.sohu.com>SendCloud!</a></p>'            
            + '</body></html>'                                                     

    end                                                                            
    add_file("/path/to/file")                                              
end
```   
    
示例2:
需要依赖net/smtp.rb模块, 由于模块默认在发送成功时,不返回服务器的信息. 
因此如果需要获得服务器返回的messageId,需要做如下操作. 首先在您的ruby的lib
库中, 找到net/smtp.rb文件, 在915行data函数中加一个返回值,操作如下
      
![pic](../resources/ruby2.png)
      
接着在660行send_messages函数中增加一个返回值,修改如下
     
![pic](../resources/ruby1.png)
    
接下来您就可以调用以下的代码进行邮件的发送和MessageId的获取了.
    
```
require 'net/smtp'

#编辑自己的邮件
message = <<END_OF_MESSAGE
From: yourname<me@fromdomain.com>
To: receiver<you@destdomain.com>
Content-type: text/html
Subject: just for test

<p>add your html here</p>
END_OF_MESSAGE

from = 'me@fromdomain.com'
receiver = 'you@destdomain.com'

def getMessageId(res)
    return res.split('#')[1]
end

#调用api_user和api_key发送邮件
Net::SMTP.start('smtpcloud.sohu.com', 25,'localhost', 'api_user', 'api_key',:login) do |smtp|
    res = smtp.send_message message, from, receiver
    messageId = getMessageId(res)
    puts messageId
end

```

