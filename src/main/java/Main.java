import api.SdutApi;
import entity.HeadParamsEntity;
import entity.SdutHeadParamsEntity;
import entity.SubmitCommonEntity;
import entity.User;
import org.apache.http.client.methods.HttpGet;
import utils.BaseSecretUtil;
import utils.CodingTool;
import utils.PropertiesUtil;

import java.awt.print.Pageable;
import java.util.Map;

public class Main {
    private static String PageAeskey = null;
    private static String AccessToken = null;
    private static String pageAccessToken = null;
    private static String UniqueCode = null;
    private static String SessionCode = null;
    private static String userId = null;
    private static String userType = null;
    private static String xxdm = null;
    private static String passwd = null;
    private static String emailUserId = null;
    private static String emailPassWd = null;
    private static SdutApi api = null;
    private static User user = null;

    public static void loadingConfig() throws Exception {
        api = new SdutApi();
        userId = PropertiesUtil.load("userId");
        userType = PropertiesUtil.load("userType");
        xxdm = PropertiesUtil.load("xxdm");
        passwd = PropertiesUtil.load("passwd");
        emailPassWd = PropertiesUtil.load("emailPassWd");
        emailUserId = PropertiesUtil.load("emailUserId");

        user = new User();
        user.setUserId(userId);
        user.setSchoolId(xxdm);
        user.setPassw(passwd);
        user.setCity(PropertiesUtil.load("city"));
        user.setAndroidClientVersionCode(PropertiesUtil.load("androidClientVersionCode"));
        user.setServerApiVersion(PropertiesUtil.load("serverApiVersion"));
        user.setClientVersion(PropertiesUtil.load("clientVersion"));
        user.setDeviceModel(PropertiesUtil.load("deviceModel"));
        user.setDeviceType(PropertiesUtil.load("deviceType"));
        user.setDeviceVersion(PropertiesUtil.load("deviceVersion"));
        user.setImei(PropertiesUtil.load("imei"));
        user.setLongitude(PropertiesUtil.load("longitude"));
        user.setLatitude(PropertiesUtil.load("latitude"));
        user.setProvince(PropertiesUtil.load("province"));
    }

    public static void login() throws Exception {

        System.out.println(CodingTool.object2json(user));
        BaseSecretUtil.setPublicKey(api.publicKey().get("publicKey"));
        Map<String, String> res = api.privateKey();
        BaseSecretUtil.setAesKey(res.get("sk"));
        UniqueCode = res.get("uc");
//        System.out.println("AESKey: "+BaseSecretUtil.getAesKey()+" Unicode: "+UniqueCode+" SessionKey: "+SessionCode);
        AccessToken = BaseSecretUtil.encrypt("{\"clientVersion\":\"3.2.0\",\"sessionKey\":\"\",\"userId\":\"\",\"userType\":\"\",\"xxdm\":\"\"}");
        res =CodingTool.json2hashmap(BaseSecretUtil.aesDecrypt(api.login(user,AccessToken,UniqueCode).get("data")));
        SessionCode = res.get("session_key");
    }

    public static String GenToken() throws Exception {
        HeadParamsEntity headParamsEntity = new HeadParamsEntity();
        headParamsEntity.setClientVersion(user.getClientVersion());
        headParamsEntity.setSessionKey(SessionCode);
        headParamsEntity.setUserId(userId);
        headParamsEntity.setXxdm("sdut");
        headParamsEntity.setUserType(userType);
        return BaseSecretUtil.aesEncrypt(CodingTool.object2json(headParamsEntity));
    }
    public static String GenPageToken() throws Exception {
        SdutHeadParamsEntity sdutHeadParamsEntity = new SdutHeadParamsEntity();
        sdutHeadParamsEntity.setSessionKey(SessionCode);
        sdutHeadParamsEntity.setUserId(userId);
        sdutHeadParamsEntity.setXxdm("sdut");
        sdutHeadParamsEntity.setUserType(userType);
        return BaseSecretUtil.aesEncrypt(CodingTool.object2json(sdutHeadParamsEntity));
    }

    public static void main(String[] args) throws Exception {
        loadingConfig();
        login();
/*        BaseSecretUtil.setAesKey("hKoO6zZnjSRMGAhQvesd8w==");
        BaseSecretUtil.setPublicKey(api.publicKey().get("publicKey"));
        SessionCode = "E873D84249717EC34453218DB016169DA4B3986C";
        UniqueCode = "e1f71332-b632-4884-8044-fb3c80c61127";*/
        AccessToken = GenToken();
        pageAccessToken = GenPageToken();
//        System.out.println(AccessToken + "\n" + UniqueCode);
        System.out.println(pageAccessToken + " " +UniqueCode);
        String informationCollectionPage = api.getLocation(pageAccessToken,UniqueCode);
        System.out.println(informationCollectionPage);


    }
}
