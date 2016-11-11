package main

import (
    "bytes"
    "fmt"
    "io/ioutil"
    "net/http"
    "net/url"
)

func SendMail() {
    RequestURI := "http://sendcloud.sohu.com/webapi/mail.send.json"
    //不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
    PostParams := url.Values{
        "api_user": {"***"},
        "api_key":  {"***"},
        "from":     {"service@sendcloud.im"},
        "fromname": {"SendCloud测试邮件"},
        "to":       {"handaoliang@gmail.com"},
        "subject":  {"来自SendCloud的第一封邮件！"},
        "html":     {"你太棒了！你已成功的从SendCloud发送了一封测试邮件，接下来快登录前台去完善账户信息吧！"},
    }
    PostBody := bytes.NewBufferString(PostParams.Encode())
    ResponseHandler, err := http.Post(RequestURI, "application/x-www-form-urlencoded", PostBody)
    if err != nil {
        panic(err)
    }
    defer ResponseHandler.Body.Close()
    BodyByte, err := ioutil.ReadAll(ResponseHandler.Body)
    if err != nil {
        panic(err)
    }
    fmt.Println(string(BodyByte))
}

func main() {
    SendMail()
}
