

## FAQ

### 1. 如何理解 mail from, from, reply-to?

mail_from: 信封上的发件人. 由「前缀@域名」组成. 它的域名就是所谓的「发信域名」.
 
from: 信件内容里的发件人. 用户可以任意填写, 支持别名显示.

    String from = MimeUtility.encodeText("爱发信客服", "UTF-8", "B") + \
                  "<support@ifaxin.com>";

「代发」是否出现, 就是看 *mail_from* 和 *from* 是否相同.

reply-to: 信件回复的收件人. 用户直接回复邮件时, *reply-to* 就是默认的收件人. 如果用户不指定它, *from* 就是默认的收件人.

<span id="jump_daifa"></span>
### 2. 为什么会有代发?

邮件中存在 *mail_from* 和 *from* 两个概念.  如果这两者不一致, 则 ESP 会在客户端上显示代发, 用以提醒收件人.

![pic](../resources/domain_liubida.cn.png)

如果两者一致, 则不会显示代发.

![pic](../resources/domain_push.liubida.cn.png)

当然, 有些 ESP 并不会要求这两者的完全一致, 而是只要求两者的域名相同, 就不会显示'代发'. ( 比如 QQ 邮箱 )

由于 SendCloud 对 *mail_from* 的前缀使用的是随机串, 所以, 如果碰到严苛的 ESP ( *mail_from* 和 *from* 必须完全一致才不显示代发, 比如网易邮箱 ), 那也只能爱莫能助了.

如果你对不显示代发有非常强烈的需求, 可以联系我, 帮你再想想办法.

### 3. QQ域被暂停, why?

QQ预热机制

### 4. WebHook 的相关问题?

### 5. 可以配置客户端来使用 SendCloud 发信么?

以下为待删除的. 

此功能依赖于「地址列表」, 用户点击某个「地址列表」的「订阅样式」, 就可以进入配置页面.

