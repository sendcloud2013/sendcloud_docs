
##android

为方便android开发者调试和接入SendCloudAPI,我们提供了基于android的SDK

点击下载：[邮件_短信SDK](sdk/downloads/android-sdk.zip)

邮件短信SDK是为了让android开发者能够在自己的代码里更快捷的使用SendCloud的API发送短信和邮件而开发的SDK工具包


##使用指引
1.配置。在使用API之前，用户首先需要在sendcloud网站对应用进行配置，在页面添加接口可调用的api_user,api_key,app_sign,app_sercret.其中api_user和api_key是在sendcloud网站申请得到的，app_sign为调用initSDK方法的类的包名，app_sercret为自己设置的密码。调用接口时会验证api_user和app_sercret，同时比较调用的应用的包名是否和app_sign填写的一致,然后核对api_key是否正确。注：api_key在第一次填写正确之后且接口已调通，之后在sendcloud主站重置api_key，不会影响接口调用。

2.接口调用。将下载好的SDK放入到您的程序目录，把jar包作为lib引入，通过方法进行调用。详细使用方法请参考demo目录下的代码示例。    


##代码示例目录
代码示例路径：../AndroidSDKDemo/app/src/main/java/net/sendcloud/sdkDemo

|文件名          |说明|
|:--------------|:---|
|MyApplication.java  |接口初始化|
|Activity/SMSVerifyActivity.java   |短信验证码发送示例|
|Activity/SMSVoiceActivity.java      |短信语言发送示例|
|Activity/SendSMSTemplateActivity.java      |短信模板发送示例|
|Activity/SendMailActivity.java      |普通邮件发送示例|
|Activity/SendMailTemplateActivity.java      |邮件模板发送示例|