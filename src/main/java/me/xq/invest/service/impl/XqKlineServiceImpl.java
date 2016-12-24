package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.xq.invest.dao.XqKlineDAO;
import me.xq.invest.domain.XqKline;
import me.xq.invest.util.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangjianxin on 16-10-19.
 */
@Component("xqKlineService")
public class XqKlineServiceImpl {
    @Autowired
    private XqKlineDAO xqKlineDAO;

    public void downloadXqKlineToday(String xqId, String url, String[] args) throws Exception {
        //格式化url
        String formatedUrl = String.format(url, args);
        //下载结果数据
        String resultString = DownloadUtils.downloadJsonString(formatedUrl);
        //如果该股票存在
        if (resultString != null && resultString.length() > 0) {
            //得到json串
            String jsonResult = resultString.substring(resultString.indexOf("{") + 1, resultString.lastIndexOf("}"));
            jsonResult = jsonResult.substring(resultString.indexOf("{"), resultString.lastIndexOf("}")-1);
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            XqKline xqKline = new XqKline();
            xqKline.setXqId(xqId);
            xqKline.setDate(jsonObject.getString("1"));
            xqKline.setOpenPrice(jsonObject.getDouble("7"));
            xqKline.setMaxPrice(jsonObject.getDouble("8"));
            xqKline.setMinPrice(jsonObject.getDouble("9"));
            xqKline.setClosePrice(jsonObject.getDouble("11"));
            xqKline.setTurnOver(jsonObject.getDouble("13"));
            xqKline.setVolume(jsonObject.getDouble("19"));
            xqKline.setTurnOverRate(jsonObject.getDouble("1968584"));
            xqKlineDAO.addNewXqKline(xqKline);
        }else {
            return;
        }

    }
}


