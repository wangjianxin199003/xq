package me.xq.invest.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by wangjianxin on 9/16/16.
 */
public abstract  class DownloadService<T> {

    @Autowired
    private PoolingHttpClientConnectionManager httpClientConnectionManager;
    /**
     * 格式化url
     * @param url
     * @param args
     * @return
     */
    String formatUrl(String url, String args[]){
        return String.format(url, args);
    }

    /**
     * json转Map
     * @param formatedUrl
     * @return
     */
    public String downloadJsonString(String formatedUrl) throws  Exception{

        //模拟浏览器请求
        CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();

        //httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
        HttpGet httpget = new HttpGet(formatedUrl);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
        httpget.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        response = httpClient.execute(httpget);

        HttpEntity httpEntity = response.getEntity();
        String stringResult = null;
        //返回结果
        stringResult =  EntityUtils.toString(httpEntity);
        return  stringResult;
    }

    public abstract Map<String, String> parseInfo(String jsonString, String xqId);

    /**
     * Map转实体类
     * @param info
     * @param domainClass
     * @return
     */
    T parseObject(Map<String,String> info, Class domainClass){
        T domain =  null;
        if(info != null){
            try {
                domain = (T)domainClass.newInstance();
                BeanUtils.populate(domain, info);
            }catch (Exception e){
                e.printStackTrace();
            }
            return domain;
        }else {
            return  null;
        }
    }

    /**
     * 保存，自己实现
     * @param domain
     */
    public abstract void saveObject(T domain);

    /**
     * 执行
     * @param domainClass
     * @param url
     * @param args
     */
    public void xqDownload(Class<T> domainClass,  String xqId,String url, String[] args) throws Exception{
        //格式化url
        String formatedUrl = formatUrl(url, args);
        //下载Json串
        String jsonString = downloadJsonString(formatedUrl);

        //解析Json串得到Map,key为属性，value为值
        Map<String,String> info = parseInfo(jsonString, xqId);

        //得到实体类对象
        T domain = parseObject(info, domainClass);
        //保存
        saveObject(domain);
    }
}
