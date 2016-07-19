## WEBAPI
    
```
#!/usr/bin/env ruby

require 'rubygems'
require 'digest'
require 'rest_client'

def sorted_hash(aHash)
   return aHash.sort{|a,b| a.to_s <=> b.to_s}
end

def send_mail
        param = {
            "smsUser"=> "***", 
            "templateId"=> "1",
            "phone"=> '13412345678',
            "vars"=>'{"%code%":"123456"}'  
        }
        api_key = "***"
        paramstr = ""
        paramstr << api_key << "&"
        new_param = sorted_hash(param)
        new_param.each do |item|
            key = item[0]
            value = item[1]
            paramstr << key << "=" << value << "&"
        end
        paramstr << api_key
        
        md5 = Digest::MD5.new
        sign = md5.update(paramstr)
        
        response = RestClient.post "http://www.sendcloud.net/smsapi/send?",
            :smsUser => "***",
            :templateId => "11",
            :phone => '13412345678',
            :vars =>'{"%code%":"123456"}',
            :signature => sign

return response
end

response = send_mail
puts response.code
puts response.to_str

```
