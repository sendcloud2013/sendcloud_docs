package main

import (
	"bytes"
	"fmt"
	"io"
	"io/ioutil"
	"mime/multipart"
	"net/http"
	"os"
)

func send() error {
	userfile := "D:\\test.txt"
	fl, err := os.Open(userfile)
	defer fl.Close()
	if err != nil {
		fmt.Println(userfile, err)
		return err
	}
	buf := new(bytes.Buffer)
	w := multipart.NewWriter(buf)

	err = w.WriteField("apiUser", "*****")
	err = w.WriteField("apiKey", "*****")
	err = w.WriteField("from", "*****")
	err = w.WriteField("fromName", "SendCloud测试邮件")
	err = w.WriteField("to", "*****")
	err = w.WriteField("subject", "使用go语言发送代码示例")
	err = w.WriteField("html", "测试通过golang发送邮件")
	fw, err := w.CreateFormFile("attachments", "test.txt")
	if err != nil {
		fmt.Println(err)
		return err
	}

	_, err = io.Copy(fw, fl)
	if err != nil {
		fmt.Println(err)
		return err
	}
	w.Close()

	req, err := http.NewRequest("POST", "http://api.sendcloud.net/apiv2/mail/send", buf)
	if err != nil {
		fmt.Println("req err: ", err)
		return err
	}
	req.Header.Set("Content-Type", w.FormDataContentType())

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()
	BodyByte, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(BodyByte))

	return nil
}

func main() {
	send()
}
