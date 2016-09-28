package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.xq.invest.dao.XqInfoDAO;
import me.xq.invest.domain.XqInfo;
import me.xq.invest.service.DownloadService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjianxin on 9/16/16.
 */
@Component("xqInfoDownloadService")
public class XqInfoDownloadServiceImpl extends DownloadService<XqInfo> {
    @Autowired
    private XqInfoDAO xqInfoDAO;

    @Override
    public Map<String, String> downloadInfo(String formatedUrl, String xqId) {

        //模拟浏览器请求
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget = new HttpGet(formatedUrl);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        }catch (Exception e){
            e.printStackTrace();

        }
        HttpEntity httpEntity = response.getEntity();
        String stringResult = null;
        try {
            //返回结果
            stringResult =  EntityUtils.toString(httpEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        //得到json串
        stringResult = stringResult.substring(stringResult.indexOf("{"), stringResult.lastIndexOf("}") + 1);
        JSONObject json = JSON.parseObject(stringResult);
        Map<String, String> resultMap = new HashMap<String, String>();
        //封装成map
        resultMap.put("xqName", json.getString("name"));
        resultMap.put("xqId", xqId);
        resultMap.put("startDate", json.getString("start"));
        return resultMap;

    }

    public void saveObject(XqInfo domain) {
        xqInfoDAO.addNewXqInfo(domain);
    }
}
