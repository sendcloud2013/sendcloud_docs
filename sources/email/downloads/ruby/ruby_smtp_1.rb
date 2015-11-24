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
