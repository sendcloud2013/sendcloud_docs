#!/usr/bin/env ruby

require 'rubygems'
require 'rest-client'

def send_mail
        response = RestClient.post "http://api.sendcloud.net/apiv2/mail/send",
        :attachments => File.new("./test.txt",'rb'),
        :apiUser => '...', # 使用api_user和api_key进行验证
        :apiKey => '...',
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromName => "SendCloud",
        :to => "test@ifaxin.com", # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        :subject => "SendCloud ruby webapi common with attachment example",
        :html => '欢迎使用SendCloud'

return response
end

response = send_mail
puts response.code
puts response.to_str
