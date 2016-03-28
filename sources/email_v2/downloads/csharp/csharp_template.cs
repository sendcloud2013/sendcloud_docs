using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Http;

namespace SendCloudExample
{
    // 模板发送
    class csharp_template
    {
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
                paramList.Add(new KeyValuePair<string, string>("fromname", "SendCloud"));
                paramList.Add(new KeyValuePair<string, string>("xsmtpapi", xsmtpapi));
                paramList.Add(new KeyValuePair<string, string>("subject", "SendCloud c# apiv2 template example"));
                paramList.Add(new KeyValuePair<string, string>("templateInvokeName", "test_template"));

                response = client.PostAsync(url, new FormUrlEncodedContent(paramList)).Result;
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

        static void Main(string[] args)
        {
            String xsmtpapi = "{\"to\": [\"test@163.com\", \"test@qq.com\"], \"sub\" : { \"%name%\" : [\"name1\", \"name2\"], \"%money%\" : [\"1000\", \"2000\"]}}";
            send(xsmtpapi);
            Console.ReadKey();
        }
    }
}

