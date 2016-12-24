package me.xq.invest.domain;

/**
 * Created by wangjianxin on 16-10-15.
 */
public class XqDebt {
    //股票代码
    private String xqId;
    //日期
    private String date;

    //总资产
    private Double totalAssets = 0.0;
    //总负责
    private Double grossLiabilities = 0.0;
    //股东权益
    private Double shareholdersEquity = 0.0;

    public String getXqId() {
        return xqId;
    }

    public void setXqId(String xqId) {
        this.xqId = xqId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getGrossLiabilities() {
        return grossLiabilities;
    }

    public void setGrossLiabilities(Double grossLiabilities) {
        this.grossLiabilities = grossLiabilities;
    }

    public Double getShareholdersEquity() {
        return shareholdersEquity;
    }

    public void setShareholdersEquity(Double shareholdersEquity) {
        this.shareholdersEquity = shareholdersEquity;
    }
}
