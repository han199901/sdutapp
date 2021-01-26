package utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;


public class SecretUtil {
    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        byte[] bArr3;
        if (bArr == null) {
            k.d(SecretUtil.class, "encryptRSAByPublicKey() null==data");
            throw new Exception("encryptRSAByPublicKey() null==data");
        } else if (bArr2 != null) {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    if (i3 > 117) {
                        bArr3 = instance.doFinal(bArr, i, 117);
                    } else {
                        bArr3 = instance.doFinal(bArr, i, i3);
                    }
                    byteArrayOutputStream.write(bArr3, 0, bArr3.length);
                    i2++;
                    i = i2 * 117;
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            }
        } else {
            k.d(SecretUtil.class,  "encryptRSAByPublicKey() null==privateKeyByte");
            throw new Exception("encryptRSAByPublicKey() null==privateKeyByte");
        }
    }

    public static String a(String str, byte[] bArr) throws Exception {
        if (bArr != null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return a(instance.doFinal(b(str)));
        }
        k.d(SecretUtil.class, "encryptAES() null==encodedKey");
        throw new Exception("encryptAES() null==encodedKey");
    }

    public static String b(String str, byte[] bArr) throws Exception {
        if (bArr != null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return b(instance.doFinal(a(str)));
        }
        k.d(SecretUtil.class,  "decryptAES() null==encodedKey");
        throw new Exception("decryptAES() null==encodedKey");
    }

    //toBase64
    public static String a(byte[] bArr) throws Exception {
        if (bArr != null) {
            return b(Base64.encodeBase64(bArr));
        }
        k.d(SecretUtil.class,  "encodeBase64ByUTF8() null==bytes");
        throw new Exception("encodeBase64ByUTF8() null==bytes");
    }

    public static byte[] a(String str) throws Exception {
        if (str != null) {
            return Base64.decodeBase64(b(str));
        }
        k.d(SecretUtil.class,  "decodeBase64ByUTF8() null==base64String");
        throw new Exception("decodeBase64ByUTF8() null==base64String");
    }

    //toUTF8Bytes
    public static byte[] b(String str) throws Exception {
        if (str != null) {
            return str.getBytes(Charset.forName("UTF-8"));
        }
        k.d(SecretUtil.class,  "toUTF8Bytes() null==data");
        throw new Exception("toUTF8Bytes() null==data");
    }

    public static String b(byte[] bArr) throws Exception {
        if (bArr != null) {
            return new String(bArr, Charset.forName("UTF-8"));
        }
        k.d(SecretUtil.class,  "toUTF8String() null==data");
        throw new Exception("toUTF8String() null==data");
    }

}
