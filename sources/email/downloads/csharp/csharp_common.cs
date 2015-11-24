using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using CodeScales.Http;
using CodeScales.Http.Entity;
using CodeScales.Http.Entity.Mime;
using CodeScales.Http.Methods;

namespace SendcloudWebapi
{
    // 普通发送
    public class Common
    {
        public static void send()
        {
            HttpClient client = new HttpClient();
            HttpPost postMethod = new HttpPost(new Uri("http://sendcloud.sohu.com/webapi/mail.send.json"));

            MultipartEntity multipartEntity = new MultipartEntity();
            postMethod.Entity = multipartEntity;

            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_user", "***")); # 使用api_user和api_key进行验证
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_key", "***"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "from", "sendcloud@sendcloud.org")); # 发信人，用正确邮件地址替代
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "fromname", "SendCloud"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "to", "to1@domain.com;to2@domain.com")); # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "subject", "SendCloud c# webapi example"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "html", "欢迎使用SendCloud"));

            HttpResponse response = client.Execute(postMethod);

            Console.WriteLine("Response Code: " + response.ResponseCode);
            Console.WriteLine("Response Content: " + EntityUtils.ToString(response.Entity));
        }
    }
}

