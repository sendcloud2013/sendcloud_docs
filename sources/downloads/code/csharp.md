###WEBAPI
```
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
    public class Common
    {
        public static void send()
        {
            HttpClient client = new HttpClient();
            HttpPost postMethod = new HttpPost(new Uri("http://sendcloud.sohu.com/webapi/mail.send.xml"));

            MultipartEntity multipartEntity = new MultipartEntity();
            postMethod.Entity = multipartEntity;

            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_user", "***")); # 使用api_user和api_key进行验证
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_key", "***"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "from", "sendcloud@sendcloud.org")); # 发信人，用正确邮件地址替代
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "fromname", "SendCloud"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "to", "to1@domain.com;to2@domain.com")); # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "subject", "SendCloud c# webapi example"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "html", "
欢迎使用SendCloud

"));

            HttpResponse response = client.Execute(postMethod);

            Console.WriteLine("Response Code: " + response.ResponseCode);
            Console.WriteLine("Response Content: " + EntityUtils.ToString(response.Entity));
        }
    }
}
```
    
- - -
    
###SMTP
```
using System;
using System.IO;
using System.Collections.Generic;
using System.Net.Mail;
using System.Net.Mime;
using System.Text;

namespace  SendMailTest
{
    class Example
    {
        static void Main()
        {
          try
            {
                MailMessage mailMsg = new MailMessage();

                // 收件人地址，用正确邮件地址替代
                mailMsg.To.Add(new MailAddress("to@sendcloud.org"));
                // 发信人，用正确邮件地址替代
                mailMsg.From = new MailAddress("from@sendcloud.org", "fronname");

                // 邮件主题
                mailMsg.Subject = "Smtp C# Smtp Example";

                // 邮件正文内容
                string text = "欢迎使用SendCloud";
                string html = @"欢迎使用<a href=""https://sendcloud.sohu.com"">SendCloud</a>";
                mailMsg.AlternateViews.Add(AlternateView.CreateAlternateViewFromString(text, null, MediaTypeNames.Text.Plain));
                mailMsg.AlternateViews.Add(AlternateView.CreateAlternateViewFromString(html, null, MediaTypeNames.Text.Html));

                // 添加附件
                string file = "C:\\file.pdf ";
                Attachment data = new Attachment(file, MediaTypeNames.Application.Octet);
                mailMsg.Attachments.Add(data);

                // 连接到sendcloud服务器
                SmtpClient smtpClient = new SmtpClient("smtpcloud.sohu.com", Convert.ToInt32(25));
                // 使用api_user和api_key进行验证
                System.Net.NetworkCredential credentials = new System.Net.NetworkCredential("api_user", "api_key");
                smtpClient.Credentials = credentials;

                smtpClient.Send(mailMsg);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }
    }
}
```

