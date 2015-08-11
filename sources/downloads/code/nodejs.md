
## WEBAPI

```
npm install sendcloud --save
```

Then

```
var Sendcloud = require('sendcloud');

// init first
var sc = new Sendcloud('apiUser','[secretKey]','bigertech@qq.com','笔戈科技','bgdev_batch');

// send email
sc.send('liuxing@meizu.com','邮件测试','<h1>Hello world!<h1>').then(function(info){
    console.log(info);
});

```

## SMTP
调用发送函数 `sendEmailSmtp(to,subject,html)`即可,所有的参数和WEBAPI一致


## 更多
详细见，[https://github.com/shanelau/sendcloud](https://github.com/shanelau/sendcloud)
