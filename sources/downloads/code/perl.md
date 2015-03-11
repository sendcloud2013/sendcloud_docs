###WEBAPI
```
use strict;
use LWP::UserAgent;
use HTTP::Request::Common;

my $uri = 'https://sendcloud.sohu.com/webapi/mail.send.xml';

my $ua = LWP::UserAgent->new;
# See http://search.cpan.org/~gaas/HTTP-Message-6.06/lib/HTTP/Request.pm
my $request = POST $uri,
    Content => [
        api_user => '***', # 使用api_user和api_key进行验证
        api_key => '***',
        from => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
        fromname => 'SendCloud',
        to => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        subject => 'SendCloud perl webapi example',
        html => '欢迎使用SendCloud'
    ];

my $response = $ua->request($request) ;
if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;
```
    
    
- - -
   
###SMTP
```
#!/usr/bin/perl
                                          
use strict;                                                                     
use Encode;                                                                     
use MIME::Entity;                                                               
use Net::SMTP;                                                                  
use File::Basename;                                                             
use MIME::Base64 qw(encode_base64);                                             

# 对字符串进行UTF-8的base64编码.                                                
sub encode_string_to_ubase64 {                                                  
    my $from_str = shift;                                                       
    my $b_str =  encode_base64($from_str); #base64 string                       
    my $encode_str = "=?UTF-8?B?$b_str?=";                                      
    return $encode_str;                                                         
}                                                                               

# 发信人，用正确邮件地址替代                                                                                     
my $from = 'fromname<sendcloud@sendcloud.com>';
# 收件人地址，用正确邮件地址替代                                        
my @to = ('to1@sendcloud.com', 'to2@sendcloud.com'); 
# 抄送地址，用正确邮件地址替代                   
my @cc = ('cc1@sendcloud.com');                            

my $subject = 'SendCloud Perl Smtp example';                                    

# Create the MIME message that will be sent.                                    
# Check out MIME::Entity on CPAN for more details                               
my $mime = MIME::Entity->build(Type  => 'multipart/alternative',                
                            Encoding => '-SUGGEST',                             
                            From => $from,                                      
                            To => join(";", @to),                               
                            Cc => join(";", @cc),                               
                            Subject => $subject                                 
                           );                                                   

# 添加正文html内容                                                              
my $html = "                                                                    
<html><head></head><body>                                                       
    <p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>                               
</body></html>                                                                  
";                                                                              

$mime->attach(Type => 'text/html;charset=utf8',                                 
            Encoding =>'base64',                                                
            Data => $html);                                                     
# 添加附件                                                                      
my $file1 = '/path/to/file';                                            
my $filename1 = basename($file1);                                               

$mime->attach ( Path      => $file1,                                            
                Type      => 'application/octet-stream',                        
                Encoding  => 'base64',                                          
                Filename  => &encode_string_to_ubase64($filename1)              
) or die "Error adding attachement, name=$filename1!\n";                        

# 连接sendcloud服务器                                                           
my $smtp = Net::SMTP->new('smtpcloud.sohu.com',                                 
                        Port=> 25,                                              
                        Timeout => 60,                                          
                        Hello => "youdomain.com",                               
                        Debug => 0, # change to 1 for debug                     
                        );  

# 使用api_user和api_key进行验证                                                                          
$smtp->auth('api_user', 'api_key');       
if (!$smtp->ok) { # auth fail                                                   
    print $smtp->code(), " ", $smtp->message(), "\n";                           
}                                                                               

# 发送邮件内容                                                                  
$smtp->mail($from);                                                             
$smtp->to(@to);                                                                 
$smtp->cc(@cc);                                                                 
$smtp->data($mime->stringify);                                                  
if (!$smtp->ok) { # send mime data error                                        
    print $smtp->code(), " ", $smtp->message(), "\n";                           
}                                                                               
$smtp->quit(); 
```
