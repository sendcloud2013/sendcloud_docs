;(function (window, undefined) {
  function mkStr () {
    var itemArr = []
    var checkedBox = $('input:checked')
    checkedBox.each(function (i, item) {
      itemArr.push($(item).data('target'))
    })
    itemArr.sort()
    for (var i = 0; i < itemArr.length; i++) {
      var item = itemArr[i]
      itemArr[i] = item + '=' + $('.' + item + '-ipt').val()
    }
    return itemArr.join('&')
  }
  $('.btn-signature').on('click', function (e) {
    var qStr = mkStr()
    $('.raw-str').text(qStr)

    var rawStr
    var smsKey = $('.smsKey-ipt').val()
    if (!smsKey) {
      $('.raw-str').text('请输入 smsKey')
      return
    } else {
      rawStr = smsKey + '&' + qStr + '&' + smsKey
    }

    var signature = md5(rawStr)
    $('.md5-val').text(signature)
  })
})(this)
