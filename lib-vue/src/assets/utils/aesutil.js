/**
 * AES 加密算法封装，
 * 密钥长度定位 256 位，
 * 对外提供密钥的生成、加密、解密
 * 本模块测试时使用的是：crypto-js@3.1.9-1，uuid@3.3.2
 */
 
 let CryptJS = require("crypto-js");
 let UUID = require("uuid")
  
  
 export var AESUtil = {
     /**
      * AES 加密
      * @param _content 待加密内容
      * @param _key aesKey, 
      * @param _iv 初始化向量
      * @return 返回经 BASE64 处理之后的密文
      */
     encrypt: function (_content, _key, _iv) {
         // 先以 UTF-8 编码解码参数 返回 any 类型
         let content = CryptJS.enc.Utf8.parse(_content);
         let aesKey = CryptJS.enc.Utf8.parse(_key);
         let iv = CryptJS.enc.Utf8.parse(_iv);
  
         // 加密
         let encrypted = CryptJS.AES.encrypt(content, aesKey, {
             iv: iv,
             mode: CryptJS.mode.CBC,
             padding: CryptJS.pad.Pkcs7
         })
         // console.log(encrypted)
         return CryptJS.enc.Base64.stringify(encrypted.ciphertext);
     },
  
     /**
      * AES 解密
      * @param：_content 待解密的内容[Base64处理过的]
      * @param：解密用的 AES key
      * @param: 初始化向量
      * @return 返回以 UTF-8 处理之后的明文
      */
     decrypt: function (_content, _key, _iv) {    
         // let content = CryptJS.enc.Base64.parse(_content);
         // content = CryptJS.enc.Base64.stringify(content);
         let aesKey = CryptJS.enc.Utf8.parse(_key);
         let iv = CryptJS.enc.Utf8.parse(_iv);
  
         // 解密
         let decrypted = CryptJS.AES.decrypt(_content, aesKey, {
             iv: iv,
             mode: CryptJS.mode.CBC,
             padding: CryptJS.pad.Pkcs7
         })
         // console.log(decrypted)
         return decrypted.toString(CryptJS.enc.Utf8);
     },
  
     /**
      * 获得 AES 密钥
      * @returns 32 字节的AES密钥
      */
     getAesKey: function () {
         let uuid = UUID.v1();
         // console.log(uuid)
         let aeskey = CryptJS.enc.Utf8.parse(uuid)
         aeskey = CryptJS.enc.Base64.stringify(aeskey).substring(2, 34)
         // console.log(aeskey + "\n" + "长度：" + aeskey.length);
         return aeskey;
     },
  
     /**
      * 获得初始化向量
      * @returns 16 字节的初始化向量
      */
     getIv: function () {
         let uuid = UUID.v1();
         let iv = CryptJS.enc.Utf8.parse(uuid);
         iv = CryptJS.enc.Base64.stringify(iv).substring(2, 18);
         // console.log(iv + "\n" + "长度：" + iv.length);
         return iv;
     },
  
     /**
      * 获得 AES key 及 初始化向量 iv
      * 其实 iv 和 aesKey 两者的生成并没有什么关系，两者只是对各自的长度有限制，
      * 这里只是为了方便使用，进行了一个组合返回。
      * @return 返回 iv 和 aesKey 的组合
      */
     getAESKeyAndIv: function () {
         let aesKeyAndIv = {
             "iv": this.getIv(),
             "aesKey": this.getAesKey(),
         }
  
         return aesKeyAndIv;
     }
 }