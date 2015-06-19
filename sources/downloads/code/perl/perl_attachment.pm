use strict;
use LWP::UserAgent;
use HTTP::Request::Common;

my $uri = 'http://sendcloud.sohu.com/webapi/mail.send.json';

my $ua = LWP::UserAgent->new;
# See http://search.cpan.org/~gaas/HTTP-Message-6.06/lib/HTTP/Request.pm
my $request = POST $uri,
    Content => [
        api_user => '...', # 使用api_user和api_key进行验证
        api_key => '...',
        from => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
        fromname => 'SendCloud',
        to => 'to1@domain.com;to2@domain.com', # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        subject => 'SendCloud perl webapi common with attachment example',
        html => '欢迎使用SendCloud',
        resp_email_id => 'true',
        uploadfile => ['./normal.pm']
    ],
    'Content_Type' => 'form-data';

my $response = $ua->request($request) ;
if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;
