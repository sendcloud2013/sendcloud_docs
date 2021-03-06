﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;

namespace SendCloudExample
{
    // 普通发送
    class csharp_common_v2
    {

        public static void send(String tos)
        {
            String url = "http://api.sendcloud.net/apiv2/mail/send";

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
                paramList.Add(new KeyValuePair<string, string>("to", tos));
                paramList.Add(new KeyValuePair<string, string>("subject", "SendCloud c# apiv2 example"));
                paramList.Add(new KeyValuePair<string, string>("html", "欢迎使用SendCloud"));

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

        static void Main2(string[] args)
        {
            String tos = "to1@sendcloud.org;to2@sendcloud.org";
            send(tos);
            Console.ReadKey();
        }

    }


}

