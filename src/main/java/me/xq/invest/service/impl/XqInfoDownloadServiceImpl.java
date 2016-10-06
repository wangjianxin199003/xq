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



    @Override
    public Map<String, String> parseInfo(String jsonString, String xqId) {
        //如果该股票存在
        if(jsonString != null && jsonString.length() >0 ){

            //得到json串
            String stringResult = jsonString.substring(jsonString.indexOf("{"), jsonString.lastIndexOf("}") + 1);
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
        //如果存在该股票
        if(domain != null){

            XqInfo xqInfo = xqInfoDAO.getXqInfoByXqId(domain.getXqId());
            //如果该股票没有更名，直接返回
            if(xqInfo != null && domain.getXqName() == xqInfo.getXqName()){
                return;
                //如果更名，则更新该股票信息
            }else {
                xqInfoDAO.addNewXqInfo(domain);
            }
            //不存在该股票信息，直接返回
        }else {
            return;
        }
    }
}
