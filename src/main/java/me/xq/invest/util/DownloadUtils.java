package me.xq.invest.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by wangjianxin on 16-10-7.
 */
public class DownloadUtils {
    @Autowired
    private static PoolingHttpClientConnectionManager httpClientConnectionManager;

    public static String downloadJsonString(String formatedUrl) throws  Exception{

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


}
