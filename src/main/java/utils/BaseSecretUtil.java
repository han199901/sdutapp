package utils;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/* compiled from: BaseSecretUtil */
public class BaseSecretUtil extends SecretUtil {
    //SDUT public key
//    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAYYAF5a/+WuXTf3COIrH/Rtxy5qN3fr5hIlIAo3KpfudqooJuG4KpCjscpvmUvIelpbL88qPvqZn4yswnGPQqCyUKCzvaQ4LwxvyyMiGBhITYH2oELV4rZm9IJplbU7gqpkz/5o/Ysqe/qYaMUblxjt4f3X+FBFVBOCHRJ+m6YQIDAQAB";

    private static String publicKey = null;

    private static String aesKey = null;

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getAesKey() {
        return aesKey;
    }

    public static void setPublicKey(String publicKey) {
        BaseSecretUtil.publicKey = publicKey;
    }

    public static void setAesKey(String aesKey) {
        BaseSecretUtil.aesKey = aesKey;
    }

    public static String encrypt(String s) throws Exception {
        return BaseSecretUtil.a(BaseSecretUtil.a(BaseSecretUtil.b(s)),publicKey);
    }

    public static String encrypt2(String s) throws Exception {
        return b(s,aesKey);
    }



    private static KeyGenerator a;

    static {
        try {
            a = KeyGenerator.getInstance("AES");
            a.init(128);
        } catch (NoSuchAlgorithmException e) {
            k.d(SecretUtil.class, "静态代码块 " + e.toString());
        }
    }

    //gen aes key
    public static String a() throws Exception {
        return a(a.generateKey().getEncoded());
    }

    //encryptRSAByPublicKey
    public static String a(String str, String str2) throws Exception {
        return a(a(a(str), a(str2)));
    }

    //encryptAES
    public static String b(String str, String str2) throws Exception {
        return a(str, a(str2));
    }

    //decryptAES
    public static String c(String str, String str2) throws Exception {
        return b(str, a(str2));
    }


    /***   加密过程  ***/
    public static byte[] b(String str) throws Exception {
        return str.getBytes(Charset.forName("UTF-8"));
    }

    public static String a(byte[] bArr) throws Exception {
        return b(Base64.encodeBase64(bArr));
    }


    /*****************/
    public static String aesDecrypt(String data) throws Exception {
        return BaseSecretUtil.c(data,aesKey);
    }

    public static String aesEncrypt(String data) throws Exception {
        return BaseSecretUtil.b(data,aesKey);
    }

}