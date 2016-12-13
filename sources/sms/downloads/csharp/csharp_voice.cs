System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Security.Cryptography;

namespace SendCloud
{
    class SendCloudVoice
    {

        public static String generate_md5(String str) {
            MD5 md5 = new MD5CryptoServiceProvider();

            //compute hash from the bytes of text
            md5.ComputeHash(Encoding.GetEncoding("utf-8").GetBytes(str));

            //get hash result after compute it
            byte[] result = md5.Hash;

            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < result.Length; i++)
            {
                strBuilder.Append(result[i].ToString("x2"));
            }

            return strBuilder.ToString();
        }

        public static void send()
        {
            String url = "http://www.sendcloud.net/smsapi/sendVoice";

            String smsUser = "";
            String smsKey = "";

            List<KeyValuePair<String, String>> paramList = new List<KeyValuePair<String, String>>();
            paramList.Add(new KeyValuePair<String, String>("smsUser", smsUser));
            paramList.Add(new KeyValuePair<String, String>("phone", "13488888888"));
            paramList.Add(new KeyValuePair<String, String>("code", "123456"));

            paramList.Sort(
                delegate(KeyValuePair<String, String> p1, KeyValuePair<String, String> p2)
                {
                    return p1.Key.CompareTo(p2.Key);
                }
            );

            var param_str = "";
            foreach (var param in paramList)
            {
                param_str += param.Key.ToString() + "=" + param.Value.ToString() + "&";
            }

            String sign_str = smsKey + "&" + param_str + smsKey;
            String sign = generate_md5(sign_str);

            paramList.Add(new KeyValuePair<String, String>("signature", sign));

            HttpClient client = null;
            HttpResponseMessage response = null;

            try
            {
                client = new HttpClient();
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
                if (null != response)
                {
                    response.Dispose();
                }
                if (null != client)
                {
                    client.Dispose();
                }
            }
        }

        static void Main(string[] args)
        {
            send();
            Console.ReadKey();
        }
    }
}
