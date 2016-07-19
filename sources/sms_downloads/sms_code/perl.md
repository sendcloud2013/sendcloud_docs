## WEBAPI
    
```
use strict;
use LWP::UserAgent;
use HTTP::Request::Common;
use URI::Escape;
use Digest::MD5;

my $uri = 'http://www.sendcloud.net/smsapi/send';
my $ua = LWP::UserAgent->new;
my %param = (
    "smsUser" => '***',
    "templateId" => '1',
    "phone" => '13412345678', 
    "vars" => '{"%code%":"123456"}');
my $api_key = "***";
my $param_str = $api_key;

foreach my $item (sort keys %param){
    $param_str .= ("&".$item."=".$param{$item});
}
$param_str .= ("&".$api_key);

my $md5 = Digest::MD5->new;
$md5->add($param_str);
my $sign = $md5->hexdigest;

my $request = POST $uri,
    Content => [
        smsUser => '***', 
        templateId => '1',
        phone => '13412345678', 
        vars => '{"%code%":"123456"}',
        signature => $sign
    ],
    Content_Type => 'application/x-www-form-urlencoded';

my $response = $ua->request($request) ;

if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;

```
