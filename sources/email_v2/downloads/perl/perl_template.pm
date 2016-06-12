use strict;
use LWP::UserAgent;
use HTTP::Request::Common;
use JSON;

my $uri = 'http://api.sendcloud.net/apiv2/mail/sendtemplate';

my $ua = LWP::UserAgent->new;
# See http://search.cpan.org/~gaas/HTTP-Message-6.06/lib/HTTP/Request.pm

my %sub = ("%code%"=>['123456']);
my %vars = ("to"=>['test@ifaxin.com'],"sub"=>\%sub);
my $vars_str = encode_json\%vars;
print "$vars_str\n";

my $request = POST $uri,
    Content => [
        apiUser => '...', # 使用api_user和api_key进行验证
        apiKey => '...',
        from => 'sendcloud@sendcloud.org', # 发信人，用正确邮件地址替代
        fromName => 'SendCloud',
        xsmtpapi => $vars_str,
        templateInvokeName => 'test12346',
        subject => 'SendCloud perl webapi template example',
        respEmailId => 'true'
    ];

my $response = $ua->request($request) ;
if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;
