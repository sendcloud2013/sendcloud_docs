use strict;
use LWP::UserAgent;
use HTTP::Request::Common;
use JSON;

my $uri = 'http://api.sendcloud.net/apiv2/mail/sendtemplate';

my $ua = LWP::UserAgent->new;
# See http://search.cpan.org/~gaas/HTTP-Message-6.06/lib/HTTP/Request.pm


my $request = POST $uri,
    Content => [
        apiUser => '...', # 使用api_user和api_key进行验证
        apiKey => '...',
        from => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
        fromName => 'SendCloud',
        useAddressList => 'true',
        to => 'test@maillist.sendcloud.org',# 使用地址列表的别称地址
        templateInvokeName => 'test12346',
        subject => 'SendCloud perl webapi template maillist example',
        respEmailId => 'true'
    ];

my $response = $ua->request($request) ;
if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;
