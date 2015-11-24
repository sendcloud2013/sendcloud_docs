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
