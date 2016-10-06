package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.xq.invest.dao.XqInfoDAO;
import me.xq.invest.domain.XqInfo;
import me.xq.invest.service.DownloadService;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjianxin on 9/16/16.
 */
@Component("xqInfoDownloadService")
public class XqInfoDownloadServiceImpl extends DownloadService<XqInfo> {
    @Autowired
    private XqInfoDAO xqInfoDAO;

    @Autowired
    private PoolingHttpClientConnectionManager httpClientConnectionManager;

    @Override
    public Map<String, String> downloadInfo (String formatedUrl, String xqId) throws  Exception{

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

        //如果该股票存在
        if(stringResult != null && stringResult.length() >0 ){

            //得到json串
            stringResult = stringResult.substring(stringResult.indexOf("{"), stringResult.lastIndexOf("}") + 1);
            JSONObject json = JSON.parseObject(stringResult);
            Map<String, String> resultMap = new HashMap<String, String>();
            //封装成map
            resultMap.put("xqName", json.getString("name"));
            resultMap.put("xqId", xqId);
            resultMap.put("startDate", json.getString("start"));
            return resultMap;
            //不存在
        }else {
            return  null;
        }

    }

    public void saveObject(XqInfo domain) {
        if(domain != null){
            return;
//            if (xqInfoDAO.countXqByXqId(domain.getXqId()) == 0) {
//
//                xqInfoDAO.addNewXqInfo(domain);
//            }
        }else {
            return;
        }
    }
}
