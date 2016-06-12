#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'
require 'json'

def send_mail
        vars = JSON.dump({"to" => ['test@ifaxin.com'], "sub" => { "%code%" => ['123456']} })
        response = RestClient.post "http://api.sendcloud.net/apiv2/mail/sendtemplate",
        :apiUser => "...", # 使用api_user和api_key进行验证
        :apiKey => "...",
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromName => "SendCloud",
        :xsmtpapi => vars,
        :templateInvokeName => 'test12346',
        :subject => "SendCloud ruby webapi template example",
        :respEmailId => 'true'

return response
end

response = send_mail
puts response.code
puts response.to_str
