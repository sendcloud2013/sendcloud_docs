$(function() {
    var wh_config = {
        testUrl: 'http://sendcloud.sohu.com/testapi/webhook'
    }
    $('.btn-test-url').on('click', function(e) {
        if (!$('.input-test-url').val()) {
            messageBox('danger', '请填写 post url', 2000)
            return
        } else if (!validateUrl($('.input-test-url').val())) {
            messageBox('danger', 'url格式不正确，请以http(s)://开头', 2000)
            return
        }
        $.ajax({
            method: 'POST',
            url: wh_config.testUrl + '/testurl',
            data: {
                url: $('.input-test-url').val()
            }
        }).then(function(data) {
            if (data === 'test failed') {
                messageBox('danger', data, 2000)
            } else {
                messageBox('success', data, 2000)
            }
        })
    })

    $('.btn-test-webhook').on('click', function(e) {
        var target = $(this)
        target.attr('disabled', true)
        e.preventDefault()
        if (!$('.receiver').val()) {
            target.attr('disabled', false)
            messageBox('danger', '请填写收件人邮箱地址', 2000)
            return
        } else if (!validateEmail($('.receiver').val())) {
            target.attr('disabled', false)
            messageBox('danger', '邮箱地址格式不正确', 2000)
            return
        }
        if (!$('.input-test-url').val()) {
            target.attr('disabled', false)
            messageBox('danger', '请填写 post url', 2000)
            return
        } else if (!validateUrl($('.input-test-url').val())) {
            target.attr('disabled', false)
            messageBox('danger', 'url格式不正确，请以http(s)://开头', 2000)
            return
        }
        $.ajax({
            method: 'POST',
            url: wh_config.testUrl + '/postdata',
            data: $('.webhook-form').serialize()
        }).then(function(data) {
            target.removeAttr('disabled')
            if (data === 'test failed') {
                messageBox('danger', data, 2000)
            } else {
                messageBox('success', '数据已成功post到你指定的URL上', 2000)
            }
        })
    })

    $('.btn-view-data').on('click', function(e) {
        e.preventDefault()
         if (!$('.receiver').val()) {
            messageBox('danger', '请填写收件人邮箱地址', 2000)
            return
        } else if (!validateEmail($('.receiver').val())) {
            messageBox('danger', '邮箱地址格式不正确', 2000)
            return
        }

        $.ajax({
            method: 'POST',
            url: wh_config.testUrl + '/viewdata',
            data: {
                receiver: $('.receiver').val(),
                data_type: $(this).attr('data-type')
            }
        }).then(function(data) {
            if (data === 'test failed') {
                messageBox('danger', data, 2000)
            } else {
                var block = $('<pre>').addClass('json').html(syntaxHighlight(JSON.stringify(data, null, 4)))
                messageBox('success', block, 0)
            }
        })
    })

    var messageModal = $('#messageModal')
    var messageBox = function(type, msg, time) {
        messageModal.find('.modal-header').removeClass('bg-success bg-danger').addClass('bg-' + type)
        messageModal.find('.modal-body').html(msg)
        messageModal.modal()

        if (time > 0) {
            setTimeout(function() {
                messageModal.modal('hide')
            }, time)
        }
    }

    function validateUrl(url) {
        var re = /^https?\:\/\//i
        return re.test(url)
    }

    function validateEmail(email) {
        var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        return re.test(email);
    }

    function syntaxHighlight(json) {
        json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
            var cls = 'number';
            if (/^"/.test(match)) {
                if (/:$/.test(match)) {
                    cls = 'key';
                } else {
                    cls = 'string';
                }
            } else if (/true|false/.test(match)) {
                cls = 'boolean';
            } else if (/null/.test(match)) {
                cls = 'null';
            }
            return '<span class="' + cls + '">' + match + '</span>';
        });
    }

})
