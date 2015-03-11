###WEBAPI
```
#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'

def send_mail
        response = RestClient.post "https://sendcloud.sohu.com/webapi/mail.send.xml",
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

