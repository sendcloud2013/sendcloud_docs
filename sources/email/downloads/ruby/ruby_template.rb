#!/usr/bin/env ruby

require 'rubygems'
require 'rest_client'
require 'json'

def send_mail
        vars = JSON.dump({"to" => ['to1@domain.com'], "sub" => { "%code%" => ['123456']} })
        response = RestClient.post "http://sendcloud.sohu.com/webapi/mail.send_template.json",
        :api_user => "...", # 使用api_user和api_key进行验证
        :api_key => "...",
        :from => "sendcloud@sendcloud.org", # 发信人，用正确邮件地址替代
        :fromname => "SendCloud",
        :substitution_vars => vars,
        :template_invoke_name => 'sendcloud_template',
        :subject => "SendCloud ruby webapi template example",
        :resp_email_id => 'true'

return response
end

response = send_mail
puts response.code
puts response.to_str
