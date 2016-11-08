
##java

为方便Java开发者调试和接入SendCloudAPI,我们提供了基于Java的SDK
点击下载：[邮件_短信SDK](sdk/downloads/sendcloud-sdk.zip)
邮件短信SDK是为了让Java开发者能够在自己的代码里更快捷的使用SendCloud的API发送短信和邮件而开发的SDK工具包


##资源

见不同模块API的参数说明、返回码. 如[邮件API普通发送](../email_v2/send_email.md#_1), [邮件API模板发送](../email_v2/send_email.md#_2), [短信API send](../sms/api.md#send), [短信API返回码](../sms/api.md#_1)


##使用指引
1.配置文件。在使用API之前，用户首先需要对resources目录下的config.properties文件进行配置。如果您发送邮件，请配置api_user和api_key；如果您发送短信，请配置该文件中的sms_user和sms_key。

2.将下载好的SDK放入到您的程序目录。详细使用方法请参考demo目录下的代码示例。    


##代码示例目录
代码示例路径：../sendcloud-sdk/sdk/src/main/java/com/sendcloud/sdk/demo/mail

|文件名          |说明|
|:--------------|:---|
|SendMail.java  |邮件发送示例|
|SendSms.java   |短信发送示例|
|SendVoice      |语言发送示例|
