require 'net/smtp'

message = <<END_OF_MESSAGE
From: 472588572@qq.com
To: i@sendcloud.im
Content-type: text/html
Subject: test message

<p>This is a test message</p>
END_OF_MESSAGE

from = '472588572@qq.com'
receiver = 'i@sendcloud.im'

def getMessageId(res)
    return res.split('#')[1]
end

Net::SMTP.start('61.135.132.113', 25,'localhost', 'postmaster@jianglian-trigger.sendcloud.org', 'U2cH6FdHTuELdMyr',:login) do |smtp|
    res = smtp.send_message message, from, receiver
    messageId = getMessageId(res)
    puts messageId
end


