using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Http;
using System.IO;

namespace SendCloudExample
{
    // 模板发送
    class csharp_template_attachment_v2
    {
        private static StreamContent createStream(String filePath, String paramKey, String fileName)
        {
            FileStream fs = File.OpenRead(filePath);
            StreamContent streamContent = new StreamContent(fs);
            streamContent.Headers.Add("Content-Type", "application/octet-stream");
            String headerValue = "form-data; name=\"" + paramKey + "\"; filename=\"" + fileName + "\"";
            byte[] bytes1 = Encoding.UTF8.GetBytes(headerValue);
            headerValue = "";
            foreach (byte b1 in bytes1)
            {
                headerValue += (Char)b1;
            }
            streamContent.Headers.Add("Content-Disposition", headerValue);
            return streamContent;
        }

        public static void send(String xsmtpapi)
        {
            String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";

            String api_user = "";
            String api_key = "";

            HttpClient client = null;
            HttpResponseMessage response = null;

            try
            {
                client = new HttpClient();

                List<KeyValuePair<String, String>> paramList = new List<KeyValuePair<String, String>>();

                paramList.Add(new KeyValuePair<string, string>("apiUser", api_user));
                paramList.Add(new KeyValuePair<string, string>("apiKey", api_key));
                paramList.Add(new KeyValuePair<string, string>("from", "sendcloud@sendcloud.org"));
                paramList.Add(new KeyValuePair<string, string>("fromName", "SendCloud"));
                paramList.Add(new KeyValuePair<string, string>("xsmtpapi", xsmtpapi));
                paramList.Add(new KeyValuePair<string, string>("subject", "SendCloud c# apiv2 template example"));
                paramList.Add(new KeyValuePair<string, string>("templateInvokeName", "test_template"));

                var multipartFormDataContent = new MultipartFormDataContent();
                foreach (var keyValuePair in paramList)
                {
                    multipartFormDataContent.Add(new StringContent(keyValuePair.Value), String.Format("\"{0}\"", keyValuePair.Key));
                }

                multipartFormDataContent.Add(createStream("D:\\附件2.txt", "attachments", "附件名称2.txt"));

                multipartFormDataContent.Add(createStream("D:\\附件1.txt", "attachments", "附件名称1.txt"));

                response = client.PostAsync(url, multipartFormDataContent).Result;
                String result = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("result:{0}", result);
            }
            catch (Exception e)
            {
                Console.WriteLine("\nException Caught!");
                Console.WriteLine("Message :{0} ", e.Message);
            }
            finally
            {
                if (null != client)
                {
                    client.Dispose();
                }
            }
        }

        static void Main1(string[] args)
        {
            String xsmtpapi = "{\"to\": [\"test@163.com\", \"test@qq.com\"], \"sub\" : { \"%name%\" : [\"name1\", \"name2\"], \"%money%\" : [\"1000\", \"2000\"]}}";
            send(xsmtpapi);
            Console.ReadKey();
        }
    }
}

