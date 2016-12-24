package me.xq.invest.domain;

/**
 * Created by wangjianxin on 16-10-16.
 */
public class XqCashFlow {

    private String xqId;

    private String date;

    //经营现金流量净额
    private Double netCashFlowFromOperatingActivities = 0.0;

    //投资现金流量净额
    private Double netCashFlowFromInvestment = 0.0;

    //筹资现金流量净额
    private Double netCashFlowFromFinancing = 0.0;

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

    public Double getNetCashFlowFromOperatingActivities() {
        return netCashFlowFromOperatingActivities;
    }

    public void setNetCashFlowFromOperatingActivities(Double netCashFlowFromOperatingActivities) {
        this.netCashFlowFromOperatingActivities = netCashFlowFromOperatingActivities;
    }

    public Double getNetCashFlowFromInvestment() {
        return netCashFlowFromInvestment;
    }

    public void setNetCashFlowFromInvestment(Double netCashFlowFromInvestment) {
        this.netCashFlowFromInvestment = netCashFlowFromInvestment;
    }

    public Double getNetCashFlowFromFinancing() {
        return netCashFlowFromFinancing;
    }

    public void setNetCashFlowFromFinancing(Double netCashFlowFromFinancing) {
        this.netCashFlowFromFinancing = netCashFlowFromFinancing;
    }
}
