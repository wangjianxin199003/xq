package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.xq.invest.dao.XqCashFlowDAO;
import me.xq.invest.domain.XqCashFlow;
import me.xq.invest.domain.XqDebt;
import me.xq.invest.util.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangjianxin on 16-10-16.
 */
@Component("xqCashFlowService")
public class XqCashFlowServiceImpl {
    @Autowired
    private XqCashFlowDAO xqCashFlowDAO;


    public void downloadXqCashFlow (String xqId,String url, String[] args) throws Exception{
        //格式化url
        String formatedUrl = String.format(url, args);
        //下载结果数据
        String resultString = DownloadUtils.downloadJsonString(formatedUrl);

        if (resultString != null && resultString.length() > 0) {

            //得到json串
            JSONObject json = null;
            try {
                //如果不存在该股票，会解析json会出异常，直接返回
                json = JSON.parseObject(resultString);
            } catch (Exception e) {
                return;
            }

            //现金流的二维数组
            List<List<String>> cash = new ArrayList<List<String>>();

            //得到现金流的json对象
            JSONArray report = json.getJSONArray("report");
            //把json对象转二维数组
            for (int i = 0; i < report.size(); ++i) {
                cash.add(Arrays.asList(report.getJSONArray(i).toArray(new String[] {})));
            }

            //得到title
            String title = json.getJSONArray("title").toString();
            title = title.substring(1, title.length() - 1);

            List<String> titleList = Arrays.asList(title.split(","));

            Integer dateIndex = titleList.indexOf("\"科目\\\\时间\"");


            //经营现金流量净额索引
            Integer netCashFlowFromOperatingActivitiesIndex = titleList.indexOf("\"经营现金流量净额\"");

            //投资现金流量净额索引
            Integer netCashFlowFromInvestmentIndex = titleList.indexOf("\"投资现金流量净额\"");

            //筹资现金流量净额索引
            Integer netCashFlowFromFinancingIndex = titleList.indexOf("\"筹资现金流量净额\"");


            Integer recordNum = cash.get(0).size();

            List<XqCashFlow> xqCashList = new ArrayList<XqCashFlow>();

            for (int i = 0 ; i < recordNum; ++i){
                XqCashFlow xqCashFlow = new XqCashFlow();
                xqCashFlow.setXqId(xqId);

                xqCashFlow.setDate(cash.get(dateIndex).get(i));

                String subjectValue  = null;

                if(netCashFlowFromOperatingActivitiesIndex != -1){
                    subjectValue = cash.get(netCashFlowFromOperatingActivitiesIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqCashFlow.setNetCashFlowFromOperatingActivities(Double.parseDouble(subjectValue));
                    }
                }

                if(netCashFlowFromInvestmentIndex != -1){
                    subjectValue = cash.get(netCashFlowFromInvestmentIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqCashFlow.setNetCashFlowFromInvestment(Double.parseDouble(subjectValue));
                    }
                }

                if(netCashFlowFromFinancingIndex != -1){
                    subjectValue = cash.get(netCashFlowFromFinancingIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqCashFlow.setNetCashFlowFromFinancing(Double.parseDouble(subjectValue));
                    }
                }

                xqCashList.add(xqCashFlow);


            }
            xqCashFlowDAO.addNewXqCashInfo(xqCashList);


        }
    }
}
