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
                mailMsg.From = new MailAddress("from@sendcloud.org", "fromname");

                // 邮件主题
                mailMsg.Subject = "Smtp C# Smtp Example";

                // 邮件正文内容
                string text = "欢迎使用SendCloud";
                string html = @"欢迎使用<a href=""http://sendcloud.sohu.com"">SendCloud</a>";
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

