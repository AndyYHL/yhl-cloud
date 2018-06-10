package com.yqy.door.entity;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpConfig {
    private boolean debug=false;//true:debug模式
    private String userAgent="";//用户代理 它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
    private boolean agent=true;//有代理的情况能不能访问，true:有代理能访问 false:有代理不能访问

    private int connectTimeout=10;//连接超时时间 单位:秒
    private int writeTimeout=10;//写入超时时间 单位:秒
    private int readTimeout=30;//读取超时时间 单位:秒

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public OkHttpClient getInstance(boolean isHeader){
        if(isHeader){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                    .addHeader("Accept-Encoding", "gzip, deflate")
                                    .addHeader("Connection", "keep-alive")
                                    .addHeader("Accept", "*/*")
                                    .addHeader("Cookie", "add cookies here")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(this.connectTimeout, TimeUnit.SECONDS)//设置链接超时
                    .writeTimeout(this.writeTimeout, TimeUnit.SECONDS) // 设置写数据超时
                    .readTimeout(this.readTimeout, TimeUnit.SECONDS) // 设置读数据超时
                    .build();
            return client;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(this.connectTimeout, TimeUnit.SECONDS)//设置链接超时
                .writeTimeout(this.writeTimeout, TimeUnit.SECONDS) // 设置写数据超时
                .readTimeout(this.readTimeout, TimeUnit.SECONDS) // 设置读数据超时
                .build();
        return client;
    }
}
