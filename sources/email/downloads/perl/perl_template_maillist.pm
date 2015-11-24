use strict;
use LWP::UserAgent;
use HTTP::Request::Common;
use JSON;

my $uri = 'http://sendcloud.sohu.com/webapi/mail.send_template.json';

my $ua = LWP::UserAgent->new;
# See http://search.cpan.org/~gaas/HTTP-Message-6.06/lib/HTTP/Request.pm


my $request = POST $uri,
    Content => [
        api_user => '...', # 使用api_user和api_key进行验证
        api_key => '...',
        from => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
        fromname => 'SendCloud',
        use_maillist => 'true',
        to => 'test@maillist.sendcloud.org',# 使用地址列表的别称地址
        template_invoke_name => 'sendcloud_template',
        subject => 'SendCloud perl webapi template maillist example',
        resp_email_id => 'true'
    ];

my $response = $ua->request($request) ;
if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;
