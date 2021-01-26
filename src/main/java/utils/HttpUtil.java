package utils;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();


    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    private static RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build();

    public static HashMap<String, Object> doGet(String url, Map<String,String> params, HashMap<String, String> headers) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        List<NameValuePair> nvps = new ArrayList<>();
        if(params!=null) {
            params.forEach((key,value) -> {
                nvps.add(new BasicNameValuePair(key, value));
            });
        }
        uriBuilder.setParameters(nvps);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeader("Accept-Encoding", "gzip");
        httpGet.setHeader("Host", "211.64.23.131:8080");
        httpGet.setHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.1.2; PCRT00 Build/N2G48H)");
        httpGet.setHeader("Content-Type", "text/plain");

        if (headers != null) {
            headers.forEach((key, value) -> {
                httpGet.setHeader(key, value);
            });
        }
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpGet);

            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);

                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("data", jsonString);
                hashMap.put("headers",response.getAllHeaders());
                return hashMap;
            } else if (state == HttpStatus.SC_MOVED_TEMPORARILY){
                HttpEntity responseEntity = response.getEntity();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("headers",response.getAllHeaders());
                return hashMap;
            } else {
                //logger.error("请求返回:"+state+"("+url+")");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }



    public static HashMap<String, String> doPost(String url, String params, HashMap<String, String> headers) throws Exception {


        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setConfig(requestConfig);
/*        httpPost.setHeader("Asym-Key", "private");

        httpPost.setHeader("Host", "211.64.23.131:8080");
        httpPost.setHeader("Accept-Encoding","gzip");
        httpPost.setHeader("User-Agent","Dalvik/2.1.0 (Linux; U; Android 7.1.2; PCRT00 Build/N2G48H)");
        httpPost.setHeader("Content-Type", "text/plain");*/
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.setHeader("Accept-Encoding", "gzip");
        httpPost.setHeader("Host", "211.64.23.131:8080");
        httpPost.setHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.1.2; PCRT00 Build/N2G48H)");
        httpPost.setHeader("Content-Type", "text/plain");
        if (headers != null) {
            headers.forEach((key, value) -> {
                httpPost.setHeader(key, value);
            });
        }
        String charSet = "UTF-8";
        StringEntity entity;
        if (params != null)
            entity = new StringEntity(params, charSet);
        else
            entity = new StringEntity(charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);

            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);

                HashMap<String, String> hashMap = new HashMap<>();
/*                Header[] hs = response.getHeaders("Set-Cookie");
                for (Header header : hs) {
                    if (header.getValue().contains("JSESSIONID")) {
                        String str = header.getValue();
                        int s = str.indexOf("JSESSIONID");
                        int e = str.indexOf(";",s);
//                        System.out.println(header.toString());
                        hashMap.put("sessionID", str.substring(s+11,e));
                    }

                }*/
                hashMap.put("data", jsonString);

                return hashMap;
            } else {
                //logger.error("请求返回:"+state+"("+url+")");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
