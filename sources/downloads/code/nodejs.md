
感谢 shanelau ^_^ , 详细的代码, 点击[这里](https://github.com/shanelau/sendcloud)查看

## 安装依赖包

```
npm install sendcloud --save
```

## 代码调用

```
var sendcloud = require('sendcloud');

sendcloud.init('apiUser', 'apiKey', 'from@from.com', 'fromname', 'apiUserBatch');

// send email
sendcloud.sendEmail('ben@ifaxin.com', '邮件测试', '<h1>Hello world!<h1>').then(function(info){
    console.log(info);
});


```

