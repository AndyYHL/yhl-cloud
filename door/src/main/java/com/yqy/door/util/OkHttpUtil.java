package com.yqy.door.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.door.entity.HttpConfig;
import okhttp3.*;
import okhttp3.internal.framed.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.thymeleaf.expression.Maps;

import javax.validation.constraints.Max;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTTP请求发起和数据解析转换
 * Created by  ansen
 * Create Time 2018年6月10日 下午9:24:50
 * update time 2018-06-10
 */
public class OkHttpUtil {
    private static HttpConfig httpConfig = new HttpConfig();
    public static final MediaType FORM_CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    /**
     * 设置超时
     * @throws IOException
     */
    public static void timeOutPost(String url) throws IOException {
        OkHttpClient okHttpClient = httpConfig.getInstance(false);
        Request request = new Request.Builder().url(url).build();

        Response response = okHttpClient.newCall(request).execute();
        System.out.println("Response completed: " + response);
    }
    /**
     * 同步get请求
     */
    public static String syncGet(String url) throws Exception{
        OkHttpClient okHttpClient = httpConfig.getInstance(false); // 创建OkHttpClient对象
        Request request = new Request.Builder().url(url).build(); // 创建一个请求
        Response response = okHttpClient.newCall(request).execute(); // 返回实体
        if (response.isSuccessful()) { // 判断是否成功
            /**获取返回的数据，可通过response.body().string()获取，默认返回的是utf-8格式；
             * string()适用于获取小数据信息，如果返回的数据超过1M，建议使用stream()获取返回的数据，
             * 因为string() 方法会将整个文档加载到内存中。*/
            String read = new String(response.body().string().getBytes("UTF-8"));
            System.out.println(read); // 打印数据
            return read;
        }else {
            System.out.println("失败"); // 链接失败
            return null;
        }
    }
    static String  readVal ="";
    /**
     * 异步Get请求
     */
    public static String asyncGet(String url) {
        OkHttpClient okHttpClient = httpConfig.getInstance(false); // 创建OkHttpClient对象
        Request request = new Request.Builder().url(url).build(); // 创建一个请求

        okHttpClient.newCall(request).enqueue(new Callback() { // 回调

            public void onResponse(Call call, Response response) throws IOException {
                // 请求成功调用，该回调在子线程
                readVal = response.body().string();
                System.out.println(readVal);
            }

            public void onFailure(Call call, IOException e) {
                readVal = e.getMessage();
                // 请求失败调用
                System.out.println(e.getMessage());
            }
        });
        return readVal;
    }
    /**请求链接
     * Post提交表单
     */
    public static void postFromParameters(String url, Map map) {
        String KEY = "9488373060c8483a3ef6333353fdc7fe"; // 请求参数
        OkHttpClient okHttpClient = httpConfig.getInstance(false); // 创建OkHttpClient对象
        RequestBody formBody = new FormBody.Builder().add("key", KEY).build(); // 表单键值对

        Request request = new Request.Builder()
                .url(url)
                .header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
                .addHeader("content-type", "application/json;charset:utf-8")
                .addHeader("Home", "china")// 自定义的header
                .post(formBody).build(); // 请求
        okHttpClient.newCall(request).enqueue(new Callback() {// 回调

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());//成功后的回调
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());//失败后的回调
            }
        });
    }

    /**
     * Post提交字符串
     * 使用Post方法发送一串字符串，但不建议发送超过1M的文本信息
     */
    public static void postStringParameters(){
        MediaType MEDIA_TYPE = MediaType.parse("text/text; charset=utf-8");
        String url = "http://v.juhe.cn/wepiao/query"; // 请求链接
        OkHttpClient okHttpClient = new OkHttpClient(); // OkHttpClient对象
        String string = "key=9488373060c8483a3ef6333353fdc7fe"; // 要发送的字符串
        /**
         * RequestBody.create(MEDIA_TYPE, string)
         * 第二个参数可以分别为：byte[]，byteString,File,String。
         */
        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE,string)).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }


    public String get(String url){
        String result = "";
        try{
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try{
                if (response != null && response.getStatusLine().getStatusCode()
                        == HttpStatus.SC_OK ){
                    System.out.println(response.getStatusLine());
                    HttpEntity entity = response.getEntity();
                    System.out.println(entity.getContentEncoding());
                    result = readResponse(entity, "gbk");
                }
            }
            finally{
                httpclient.close();
                response.close();
            }

        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }
    public String readResponse(HttpEntity entity, String charset){
        StringBuffer res = new StringBuffer();
        BufferedReader reader = null;
        try{
            if (entity == null){
                return null;
            }
            else{
                reader = new BufferedReader(new InputStreamReader(entity.getContent(),charset));
                String line;
                while ( (line = reader.readLine()) != null){
                    line = line + "\n";
                    res.append(line);
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally{
            try{
                if (reader != null){
                    reader.close();
                }

            }
            catch(Exception e){
                e.toString();
            }
        }
        return res.toString();
    }
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        //String a = syncGet("http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/35.html");
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        String b = okHttpUtil.get("http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/35.html");
        Pattern p = Pattern.compile("<tr class='citytr'><td>[\\s\\S]*?</td></tr>");
        Matcher m = p.matcher(b);
        int i = 1;
        while (m.find()){
            System.out.println(m.group());
            Pattern p1 = Pattern.compile("<a href='([\\s\\S]*?)'>([\\s\\S]*?)</a>");
            Matcher m1 = p1.matcher(m.group());
            Map map = new HashMap();
            StringBuffer sb = new StringBuffer();

            while (m1.find()){
                System.out.println(m1.group(2));
                sb.append(i+","+m1.group(1)+","+m1.group(2)+"|");
                //System.out.println(m1.start()+"--"+m1.end());

            }
            list.add(sb.toString());
            i++;
            //System.out.println("Match " + m.group() +"at positions" + m.start() + "-" + (m.end() - 1));
        }
        List<Map> mapList = getTwo(list);
        for (Map map1: mapList) {
            System.out.println(map1.get("id")+" "+map1.get("url")+" "+map1.get("tid")+" "+map1.get("strv")+" "+map1.get("p"));
        }
    }
    
    
    public static List getTwo(List<String> list) throws Exception{
        List<Map> mapList = new ArrayList<>();
        String id = "";
        String url = "";
        String tid = "";
        String strv = "";
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        int i = 41;
        for (String str: list) {
            //40,35/3599.html,3599|40,35/3599.html,其他通用设备和零件|
            String [] stL = str.split("\\|");
            id = str.split("\\|")[0].split("\\,")[0];
            url = str.split("\\|")[0].split("\\,")[1];
            tid = str.split("\\|")[0].split("\\,")[2];
            strv = str.split("\\|")[1].split("\\,")[2];
            Map map = new HashMap();
            map.put("id",id);
            map.put("url",url);
            map.put("tid",tid);
            map.put("strv",strv);
            map.put("p",0);
            mapList.add(map);
            //String a = syncGet("http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/35.html");
            //http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/35/3501.html
            System.out.println("http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/"+url);
            String b = okHttpUtil.get("http://www.stats.gov.cn/tjsj/tjbz/tjypflml/2010/"+url);

            Pattern p = Pattern.compile("<tr class='countytr'><td>[\\s\\S]*?</td></tr>");
            Matcher m = p.matcher(b);

            while (m.find()){
                System.out.println(m.group());
                Pattern p1 = Pattern.compile("<a href='([\\s\\S]*?)'>([\\s\\S]*?)</a>");
                Matcher m1 = p1.matcher(m.group());
                Map map2 = new HashMap();
                StringBuffer sb = new StringBuffer();

                while (m1.find()){
                    System.out.println(m1.group(2));
                    sb.append(i+","+m1.group(1)+","+m1.group(2)+"|");
                    //System.out.println(m1.start()+"--"+m1.end());

                }
                i++;
                String kkl =  sb.toString();
                Map map1 = new HashMap();
                map1.put("id",i);
                map1.put("url",kkl.split("\\|")[0].split("\\,")[1]);
                map1.put("tid",kkl.split("\\|")[0].split("\\,")[2]);
                map1.put("strv",kkl.split("\\|")[1].split("\\,")[2]);
                map1.put("p",id);
                mapList.add(map1);
                long mls = 1000;
                Thread.sleep(mls);
                //System.out.println("Match " + m.group() +"at positions" + m.start() + "-" + (m.end() - 1));
            }
        }


        return mapList;
    }
}
