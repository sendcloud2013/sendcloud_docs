#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'

def send_mail
        response = RestClient.post "http://api.sendcloud.net/apiv2/mail/sendtemplate",
        :apiUser => "...", # 使用api_user和api_key进行验证
        :apiKey => "...",
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromName => "SendCloud",
        :useAddressList => 'true',
        :to => "test@maillist.sendcloud.org", # 使用地址列表的别称地址
        :templateInvokeName => 'test12346',
        :subject => "SendCloud ruby webapi template maillist example",
        :respEmailId => 'true'

return response
end

response = send_mail
puts response.code
puts response.to_str
