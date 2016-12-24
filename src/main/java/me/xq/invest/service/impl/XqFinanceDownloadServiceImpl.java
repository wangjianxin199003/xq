package me.xq.invest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import me.xq.invest.dao.XqFinanceDAO;
import me.xq.invest.domain.XqFinance;
import me.xq.invest.service.DownloadService;
import me.xq.invest.util.DownloadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by wangjianxin on 16-10-7.
 */
@Component("xqFinanceDownloadService")
public class XqFinanceDownloadServiceImpl{
    @Autowired
    private XqFinanceDAO xqFinanceDAO;

    public void xqDownload(String xqId,String url, String[] args) throws Exception {
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

            //财报的二维数组
            List<List<String>> finance = new ArrayList<List<String>>();

            //得到财报的json对象
            JSONArray report = json.getJSONArray("report");
            //把json对象转二维数组
            for (int i = 0; i < report.size(); ++i) {
                finance.add(Arrays.asList(report.getJSONArray(i).toArray(new String[] {})));
            }

            //title和索引的映射关系
            Map<String,Integer> titleIndexMap = new HashMap<String, Integer>();

            //得到title
            JSONArray title = json.getJSONArray("title");
            //去除title中的""科目\时间"
            title.remove(0);
            //建立索引关系
            for (int i = 0; i < title.size(); ++i){
                titleIndexMap.put(title.getJSONArray(i).toString(), i);
            }

            //得到记录条数
            Integer recordNum = finance.get(0).size();

            //准备存到数据库的中财报列表
            List<XqFinance> financeList = new ArrayList<XqFinance>();

            for (int i = 0; i < recordNum; ++i) {
                XqFinance xqFinance = new XqFinance();
                xqFinance.setXqId(xqId);

                xqFinance.setDate(finance.get(0).get(i));

                Integer titleIndex = null;
                String subjectValue  = null;

                titleIndex = titleIndexMap.get(new String("[\"基本每股收益\",\"元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setBasicEarningsPerShare(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"净利润\",\"万元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setNetProfit(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"净利润同比增长率\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setNetProfitGrowthRate(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"扣非净利润\",\"万元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setNonNetProfit(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"营业总收入\",\"万元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setGrossRevenue(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"营业总收入同比增长率\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setGrossRevenueGrowthRate(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"每股净资产\",\"元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setNetAssetValuePerShare(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"净资产收益率\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setReturnOnEquity(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"净资产收益率-摊薄\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setReturnOnEquityDiluted(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"资产负债比率\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setAssetLiabilityRatio(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"每股资本公积金\",\"元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setCapitalReservePerShare(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"每股未分配利润\",\"元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setUndistributedProfitPerShare(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"每股经营现金流\",\"元\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setOperationalCashFlowPerShare(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"销售毛利率\",\"%\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setGrossProfitRate(Double.parseDouble(subjectValue));
                    }
                }

                titleIndex = titleIndexMap.get(new String("[\"存货周转率\",\"\"]"));
                if(titleIndex != null){
                    subjectValue = finance.get(titleIndex + 1).get(i);
                    if(subjectValue != null && subjectValue.length() > 0){
                        xqFinance.setInventoryTurnover(Double.parseDouble(subjectValue));
                    }
                }

                financeList.add(xqFinance);

            }
            xqFinanceDAO.addNewFinanceInfo(financeList);
        }
    }
}
