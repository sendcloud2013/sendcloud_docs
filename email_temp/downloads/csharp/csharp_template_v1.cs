
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Http;

namespace SendCloudExampleV1
{
    // 模板发送
    class csharp_template_v1
    {
        public static void send(String substitution_vars)
        {
            String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

            String api_user = "";
            String api_key = "";

            HttpClient client = null;
            HttpResponseMessage response = null;

            try
            {
                client = new HttpClient();

                List<KeyValuePair<String, String>> paramList = new List<KeyValuePair<String, String>>();

                paramList.Add(new KeyValuePair<string, string>("api_user", api_user));
                paramList.Add(new KeyValuePair<string, string>("api_key", api_key));
                paramList.Add(new KeyValuePair<string, string>("from", "sendcloud@sendcloud.org"));
                paramList.Add(new KeyValuePair<string, string>("fromname", "SendCloud"));
                paramList.Add(new KeyValuePair<string, string>("subject", "SendCloud c# webapi template example"));
                paramList.Add(new KeyValuePair<string, string>("template_invoke_name", "***"));
                paramList.Add(new KeyValuePair<string, string>("substitution_vars", substitution_vars));

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

        static void Main1(string[] args)
        {
            String substitution_vars = "{\"to\": [\"test@163.com\", \"test@qq.com\"], \"sub\" : { \"%name%\" : [\"name1\", \"name2\"], \"%money%\" : [\"1000\", \"2000\"]}}";
            send(substitution_vars);
            Console.ReadKey();
        }
    }
}



