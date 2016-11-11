package sendcloud

import (
    "net/url"
    "bytes"
    "net/http"
    "io/ioutil"
    "encoding/json"
    "errors"
)

const (
    SEND_CLOUD_TEMPLATE_MAIL_API_URL = "http://sendcloud.sohu.com/webapi/mail.send_template.json"
)

var (
    MailApiUser = ""
    MailApiKey  = ""
)

func UpdateApiInfo(apiUser, apiKey string) {
    MailApiUser = apiUser
    MailApiKey  = apiKey
}

func SendMailWithTemplate(template, from, fromName, replyTo, subject string, toList []map[string]string) (bool, error, string) {

    if len(MailApiKey) == 0 || len(MailApiUser) == 0 {
        return false, errors.New("请先配置 api 信息"), ""
    }

    var toMap = map[string]interface{}{}
    var toMailList = make([]string, len(toList))
    var sub = map[string][]string{}

    for index, item := range toList {
        for key, value := range item {
            if key == "to" {
                toMailList[index] = value
            } else {
                if _, ok := sub[key]; !ok {
                    sub[key] = make([]string, len(toList))
                }
                sub[key][index] = value
            }
        }
    }
    toMap["to"] = toMailList
    if len(sub) > 0 {
        toMap["sub"] = sub
    }

    var substitutionVarsBytes, err = json.Marshal(toMap)
    if err != nil {
        return false ,err, ""
    }

    var substitutionVars  = string(substitutionVarsBytes)
    params := url.Values {
        "api_user": {MailApiUser},
        "api_key":  {MailApiKey},
        "from":     {from},
        "fromname": {fromName},
        "replyto":  {replyTo},
        "subject":  {subject},
        "template_invoke_name": {template},
        "substitution_vars":    {substitutionVars},
    }

    var body = bytes.NewBufferString(params.Encode())
    responseHandler, err := http.Post(SEND_CLOUD_TEMPLATE_MAIL_API_URL, "application/x-www-form-urlencoded", body)
    if err != nil {
        return false, err, ""
    }
    defer responseHandler.Body.Close()

    bodyByte, err := ioutil.ReadAll(responseHandler.Body)
    if err != nil {
        return false, err, string(bodyByte)
    }

    var result map[string]interface{}
    err = json.Unmarshal(bodyByte, &result)
    return (result["message"] == "success"), err, string(bodyByte)
}

