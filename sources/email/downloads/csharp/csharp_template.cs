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
    // 模板发送
    class Template
    {
        public static void send()
        {
            HttpClient client = new HttpClient();
            HttpPost postMethod = new HttpPost(new Uri("http://sendcloud.sohu.com/webapi/mail.send_template.json"));

            MultipartEntity multipartEntity = new MultipartEntity();
            postMethod.Entity = multipartEntity;

            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_user", "***"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "api_key", "***"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "from", "sendcloud@sendcloud.org"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "fromname", "SendCloud"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "subject", "SendCloud c# webapi template example"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "template_invoke_name", "***"));
            multipartEntity.AddBody(new StringBody(Encoding.UTF8, "substitution_vars", "{\"to\": [\"test@163.com\", \"test@qq.com\"], \"sub\" : { \"%name%\" : [\"name1\", \"name2\"], \"%money%\" : [\"1000\", \"2000\"]}}"));

            FileInfo fileInfo = new FileInfo(@"c:\1.txt");

            UTF8FileBody fileBody = new UTF8FileBody("file1", "attachment.txt ", fileInfo);
            multipartEntity.AddBody(fileBody);

            HttpResponse response = client.Execute(postMethod);

            Console.WriteLine("Response Code: " + response.ResponseCode);
            Console.WriteLine("Response Content: " + EntityUtils.ToString(response.Entity));

            Console.ReadLine();
        }
    }
}

