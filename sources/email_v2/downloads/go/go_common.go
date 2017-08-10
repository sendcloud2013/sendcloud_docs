package main

import (
    "fmt"
    "net/http"
    "io/ioutil"
    "bytes"
    "net/url"
    //"strings"
)



func SendMail(){
	RequestURI := "http://api.sendcloud.net/apiv2/mail/send"
	PostParams := url.Values{
		"apiUser": {"****"},
		"apiKey":  {"****"},
		"from":     {"*****"},
		"fromName": {"goggo"},
		"to":       {"****"},
		"subject":  {"使用go语言发送代码示例"},
		"html":     {"测试通过golang发送邮件"},
	}
	PostBody := bytes.NewBufferString(PostParams.Encode())
	ResponseHandler, err := http.Post(RequestURI,"application/x-www-form-urlencoded",PostBody)
	if err != nil {
		fmt.Println(err)
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