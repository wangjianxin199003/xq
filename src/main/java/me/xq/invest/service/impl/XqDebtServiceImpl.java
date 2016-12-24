package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.xq.invest.dao.XqDebtDAO;
import me.xq.invest.domain.XqDebt;
import me.xq.invest.util.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by wangjianxin on 16-10-15.
 */
@Component("xqDebtService")
public class XqDebtServiceImpl {
    @Autowired
    private  XqDebtDAO xqDebtDAO;
    public void downloadXqDebt(String xqId,String url, String[] args) throws Exception{
        //格式化url
        String formatedUrl = String.format(url, args);
        //下载结果数据
        String resultString = DownloadUtils.downloadJsonString(formatedUrl);

        if (resultString  != null && resultString.length() > 0) {

            //得到json串
            JSONObject json = null;
            try {
                //如果不存在该股票，会解析json会出异常，直接返回
                json = JSON.parseObject(resultString);
            } catch (Exception e) {
                return;
            }

            //负债的二维数组
            List<List<String>> debt = new ArrayList<List<String>>();

            //得到负债的json对象
            JSONArray report = json.getJSONArray("report");
            //把json对象转二维数组
            for (int i = 0; i < report.size(); ++i) {
                debt.add(Arrays.asList(report.getJSONArray(i).toArray(new String[] {})));
            }

            //得到title
            String title = json.getJSONArray("title").toString();
            title = title.substring(1, title.length() - 1);

            List<String> titleList = Arrays.asList(title.split(","));

            Integer dateIndex = titleList.indexOf("\"科目\\\\时间\"");

            Integer totalAssetsIndex = titleList.indexOf("\"资产总计\"");

            Integer grossLiabilitiesIndex = titleList.indexOf("\"负债合计\"");

            Integer shareholdersEquityIndex = titleList.indexOf("\"股东权益合计\"");

            Integer recordNum = debt.get(0).size();

            List<XqDebt> xqDebtList = new ArrayList<XqDebt>();

            for(int i = 0; i < recordNum; ++i){
                XqDebt xqDebt = new XqDebt();
                xqDebt.setXqId(xqId);

                xqDebt.setDate(debt.get(dateIndex).get(i));

                String subjectValue  = null;

                if(totalAssetsIndex != -1){
                    subjectValue = debt.get(totalAssetsIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqDebt.setTotalAssets(Double.parseDouble(subjectValue));
                    }
                }

                if(grossLiabilitiesIndex != -1){
                    subjectValue = debt.get(grossLiabilitiesIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqDebt.setGrossLiabilities(Double.parseDouble(subjectValue));
                    }
                }

                if(shareholdersEquityIndex != -1){
                    subjectValue = debt.get(shareholdersEquityIndex).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqDebt.setShareholdersEquity(Double.parseDouble(subjectValue));
                    }
                }
                xqDebtList.add(xqDebt);

            }
            xqDebtDAO.addNewDebtInfo(xqDebtList);

        }

    }
}
