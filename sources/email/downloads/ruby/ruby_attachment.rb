#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'

def send_mail
        response = RestClient.post "http://sendcloud.sohu.com/webapi/mail.send.json",
        :myfile => File.new("./test.file",'rb'),
        :api_user => '...', # 使用api_user和api_key进行验证
        :api_key => '...',
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromname => "SendCloud",
        :to => "to1@domain.com;to2@domain.com", # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        :subject => "SendCloud ruby webapi common with attachment example",
        :html => '欢迎使用SendCloud',

return response
end

response = send_mail
puts response.code
puts response.to_str
