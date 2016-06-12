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
    var smsKey = $('.smsKey-ipt').val()
    if (!smsKey) {
      return '请输入 smsKey'
    } else {
      return (smsKey + '&' + itemArr.join('&') + '&' + smsKey)
    }
  }
  $('.btn-signature').on('click', function (e) {
    var rawStr = mkStr()
    var signature = md5(rawStr)
    console.log(rawStr)
    $('.raw-str').text(rawStr)
    $('.md5-val').text(signature)
  })
})(this)
