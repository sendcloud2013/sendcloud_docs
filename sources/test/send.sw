swagger: '2.0'
info:
  title: SendCloud APIv2
  description: SendCloud Mail API Version II. Just have a try.
  version: 2.0.0
host: 127.0.0.1:8080
schemes:
  - http
basePath: /apiv2
produces:
  - application/json
paths:
  /mail/send:
    post:
      summary: 发送普通邮件
      description: |
        支持发送普通邮件.我是描述. 
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: from
          in: query
          description: 发件人地址
          required: true
          type: string
          format: email
          default: test@sendcloud.org
        - name: to
          in: query
          description: 收件人地址
          required: true
          type: string
          format: email
        - name: subject
          in: query
          description: 邮件主题
          required: true
          type: string
          format: 
          default: 测试邮件
        - name: html
          in: query
          description: 邮件内容 ( text/html )
          required: true
          type: string
          format: 
        - name: fromName
          in: query
          description: 发件人名称
          required: false
          type: string
          format: 
        - name: cc
          in: query
          description: 抄送地址
          required: false
          type: string
          format: email
        - name: bcc
          in: query
          description: 密送地址
          required: false
          type: string
          format: email
        - name: relplyTo
          in: query
          description: 默认的回复地址
          required: false
          type: string
          format: email
        - name: dispositionNotificationTo
          in: query
          description: 回执地址
          required: false
          type: string
          format: email
        - name: labelId
          in: query
          description: 标签ID
          required: false
          type: integer
          format: 
        - name: headers
          in: query
          description: 用户自定义邮件头部
          required: false
          type: string
          format: json
        - name: attachments
          in: formData
          description: 附件
          required: false
          type: file
          format: 
        - name: xsmtpapi
          in: query
          description: SMTP 扩展字段
          required: false
          type: string
          format: json
        - name: plain
          in: query
          description: 邮件内容 ( text/plain )
          required: false
          type: string
          format: 
        - name: respEmailId
          in: query
          description: 是否返回 emailId
          required: false
          type: string
          format: true false
          default: false
        - name: useAddressList
          in: query
          description: 是否使用地址列表发送
          required: false
          type: string
          format: true false
        - name: gzipCompress
          in: query
          description: 是否对邮件内容使用 gzip 压缩
          required: false
          type: string
          format: true false
      tags:
        - mail
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'
  
  /label/list:
    get:
      summary: 标签的列表查询
      description: |
        标签的列表查询 
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: query
          in: query
          description: 模糊查询的关键词
          required: false
          type: string
          format: 
        - name: start
          in: query
          description: 查询的 start 序号
          required: false
          type: integer
          format: 
        - name: limit
          in: query
          description: 查询的 limit 限制
          required: false
          type: integer
          format: [0-100]
      tags:
        - label
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'
  /label/get:
    get:
      summary: 查询标签
      description: |
        查询标签
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: labelId
          in: query
          description: 标签ID
          required: true
          type: integer
          format: 
      tags:
        - label
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'
  /label/add:
    get:
      summary: 添加标签
      description: |
        添加标签
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: labelName
          in: query
          description: 标签名称
          required: true
          type: string
          format: 
      tags:
        - label
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'
  /label/delete:
    get:
      summary: 删除标签
      description: |
        删除标签
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: labelId
          in: query
          description: 标签ID
          required: true
          type: integer
          format: 
      tags:
        - label
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'       
  /label/update:
    get:
      summary: 修改标签
      description: |
        修改标签
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: labelId
          in: query
          description: 标签ID
          required: true
          type: integer
          format: 
        - name: labelName
          in: query
          description: 标签名称
          required: true
          type: string
          format: 
      tags:
        - label
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'             
            
  
  /template/list:
    get:
      summary: 邮件模板的列表查询
      description: |
        邮件模板的列表查询
      parameters:
        - name: apiUser
          in: query
          description: API_USER
          required: true
          type: string
          format: 
        - name: apiKey
          in: query
          description: API_KEY
          required: true
          type: string
          format: 
        - name: invokeName
          in: query
          description: 邮件模板的调用名称
          required: false
          type: string
          format: 
        - name: templateType
          in: query
          description: 邮件模板类型
          required: false
          type: integer
          format: 0 | 1
        - name: templateStat
          in: query
          description: 邮件模板状态
          required: false
          type: integer
          format: -2 | -1 | 0 | 1
      tags:
        - template
      responses:
        '200':
          description: 请求结果
          schema:
            $ref: '#/definitions/Result'
definitions:
  Result:
    type: object
    properties:
      result:
        type: boolean
        description: 请求成功与否
      statusCode:
        type: integer
        description: 状态码
      message:
        type: string
        description: 状态信息
      info: 
        type: object
        description: 返回结果
  Product:
    type: object
    properties:
      product_id:
        type: string
        description: 'Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.'
      description:
        type: string
        description: Description of product.
      display_name:
        type: string
        description: Display name of product.
      capacity:
        type: string
        description: 'Capacity of product. For example, 4 people.'
      image:
        type: string
        description: Image URL representing the product.
  PriceEstimate:
    type: object
    properties:
      product_id:
        type: string
        description: 'Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles'
      currency_code:
        type: string
        description: '[ISO 4217](http://en.wikipedia.org/wiki/ISO_4217) currency code.'
      display_name:
        type: string
        description: Display name of product.
      estimate:
        type: string
        description: 'Formatted string of estimate in local currency of the start location. Estimate could be a range, a single number (flat rate) or "Metered" for TAXI.'
      low_estimate:
        type: number
        description: Lower bound of the estimated price.
      high_estimate:
        type: number
        description: Upper bound of the estimated price.
      surge_multiplier:
        type: number
        description: Expected surge multiplier. Surge is active if surge_multiplier is greater than 1. Price estimate already factors in the surge multiplier.
  Profile:
    type: object
    properties:
      first_name:
        type: string
        description: First name of the Uber user.
      last_name:
        type: string
        description: Last name of the Uber user.
      email:
        type: string
        description: Email address of the Uber user
      picture:
        type: string
        description: Image URL of the Uber user.
      promo_code:
        type: string
        description: Promo code of the Uber user.
  Activity:
    type: object
    properties:
      uuid:
        type: string
        description: Unique identifier for the activity
  Activities:
    type: object
    properties:
      offset:
        type: integer
        format: int32
        description: Position in pagination.
      limit:
        type: integer
        format: int32
        description: Number of items to retrieve (100 max).
      count:
        type: integer
        format: int32
        description: Total number of items available.
      history:
        type: array
        items:
          $ref: '#/definitions/Activity'

