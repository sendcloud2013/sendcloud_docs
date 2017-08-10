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
	RequestURI := "http://api.sendcloud.net/apiv2/mail/sendtemplate"
	PostParams := url.Values{
		"apiUser": {"****"},
		"apiKey":  {"****"},
		"from":     {"ab@qq.com"},
		"fromName": {"goggo"},
		"to":       {"ab@maillist.sendcloud.org"},  //to此时为地址列表
		"subject":  {"使用go语言发送代码示例"},
		"templateInvokeName":     {"test_template"},
		"useAddressList":     {"true"},
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