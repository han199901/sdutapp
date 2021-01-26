package api;

import entity.User;
import org.apache.http.Header;
import utils.BaseSecretUtil;
import utils.CodingTool;
import utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class SdutApi {

    private static final String loginApi  = "http://211.64.23.131:8080/mobileapi_ydxy/open/auth/login";
    private static final String asymKeyApi  = "http://211.64.23.131:8080/mobileapi_ydxy/open/security/asym/key";
    private static final String newsApi  = "http://211.64.23.131:8080/thirdpart_ydxy/sdlg/news/index";
    private static final String informationCollection = "http://211.64.23.131:8080/mobileapi_ydxy/api/lapp/springboard";


    public Map<String,String> privateKey() throws Exception {
        HashMap<String,String> result = new HashMap<>();
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Asym-Key", "private");
        //生成随机 AES64 密钥，转换为 Json 格式
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("sk", BaseSecretUtil.a());
        String jsonStr = CodingTool.hashmap2json(hashMap);

        HashMap<String, String> res = HttpUtil.doPost(asymKeyApi,BaseSecretUtil.encrypt(jsonStr),headers);
        HashMap<String, String> resObj = CodingTool.json2hashmap(res.get("data"));
        if(resObj.get("code").equals("0")) {
            result.put("sk",hashMap.get("sk")); //aes key
            result.put("uc",BaseSecretUtil.c(resObj.get("data"),hashMap.get("sk"))); //Unique-Code
        }
        return result;
    }

    public Map<String,String> publicKey() throws Exception {
        HashMap<String,String> result = new HashMap<>();
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Asym-Key", "public");

        HashMap<String, Object> res = HttpUtil.doGet(asymKeyApi,null,headers);
        HashMap<String, String> resObj = CodingTool.json2hashmap((String) res.get("data"));
        if(resObj.get("code").equals("0")) {
            result.put("publicKey",resObj.get("data"));
        }
        return result;
    }
    public String getLocation(String token, String uc) throws Exception {
        String result = null;
        HashMap<String,String> headers = new HashMap<>();
        headers.put("access-token", token);
        headers.put("unique-code",uc);
        headers.put("Connection","keep-alive");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Linux; Android 7.1.2; PCRT00 Build/N2G48H; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.70 Mobile Safari/537.36");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,en-US;q=0.9");
        headers.put("X-Requested-With","com.lysoft.android.lyyd.report.mobile.sdlg");
        HashMap<String,String> params = new HashMap<>();
        params.put("yyid","xxsj_front");
        HashMap<String, Object> res = HttpUtil.doGet(informationCollection,params,headers);
        Header[] resObj = (Header[]) res.get("headers");
        for (Header header:resObj) {
            if(header.getName().equals("Location")) {
                result = header.getValue();
                break;
            }
        }

        return result;
    }

    

    public HashMap<String, String> login(User user, String token, String uc) throws Exception {
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Access-Token",token);
        headers.put("Unique-Code",uc);
//        System.out.println(CodingTool.object2json(user));
//        System.out.println(BaseSecretUtil.encrypt2(CodingTool.object2json(user)));
        String resStr = HttpUtil.doPost(loginApi,BaseSecretUtil.encrypt2(CodingTool.object2json(user)),headers).get("data");
        HashMap<String, String> res = CodingTool.json2hashmap(resStr);
        return res;

    }
}
