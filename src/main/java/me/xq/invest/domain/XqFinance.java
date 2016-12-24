package me.xq.invest.domain;

/**
 * Created by wangjianxin on 16-10-7.
 * 财报
 */
public class XqFinance {

    //股票代码
    private String xqId;
    //财报日期
    private String date;
    //基本每股收益
    private Double basicEarningsPerShare = 0.0;
    //净利润
    private Double netProfit = 0.0;
    //净利润同比增长率
    private Double  netProfitGrowthRate = 0.0;
    //扣非净利润
    private Double nonNetProfit = 0.0;
    //营业总收入
    private Double grossRevenue = 0.0;
    //营业总收入同比增长率
    private Double grossRevenueGrowthRate = 0.0;
    //每股净资产
    private Double netAssetValuePerShare = 0.0;
    //净资产收益率
    private Double returnOnEquity = 0.0;
    //净资产收益率-摊薄
    private Double returnOnEquityDiluted = 0.0;
    //资产负债比率
    private Double assetLiabilityRatio = 0.0;
    //每股资本公积金
    private Double capitalReservePerShare = 0.0;
    //每股未分配利润
    private Double undistributedProfitPerShare = 0.0;
    //每股经营现金流
    private Double operationalCashFlowPerShare = 0.0;
    //销售毛利率
    private Double grossProfitRate = 0.0;
    //存货周转率
    private Double inventoryTurnover = 0.0;

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

    public Double getBasicEarningsPerShare() {
        return basicEarningsPerShare;
    }

    public void setBasicEarningsPerShare(Double basicEarningsPerShare) {
        this.basicEarningsPerShare = basicEarningsPerShare;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getNetProfitGrowthRate() {
        return netProfitGrowthRate;
    }

    public void setNetProfitGrowthRate(Double netProfitGrowthRate) {
        this.netProfitGrowthRate = netProfitGrowthRate;
    }

    public Double getNonNetProfit() {
        return nonNetProfit;
    }

    public void setNonNetProfit(Double nonNetProfit) {
        this.nonNetProfit = nonNetProfit;
    }

    public Double getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(Double grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public Double getGrossRevenueGrowthRate() {
        return grossRevenueGrowthRate;
    }

    public void setGrossRevenueGrowthRate(Double grossRevenueGrothRate) {
        this.grossRevenueGrowthRate = grossRevenueGrothRate;
    }

    public Double getNetAssetValuePerShare() {
        return netAssetValuePerShare;
    }

    public void setNetAssetValuePerShare(Double netAssetValuePerShare) {
        this.netAssetValuePerShare = netAssetValuePerShare;
    }

    public Double getReturnOnEquity() {
        return returnOnEquity;
    }

    public void setReturnOnEquity(Double returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
    }

    public Double getReturnOnEquityDiluted() {
        return returnOnEquityDiluted;
    }

    public void setReturnOnEquityDiluted(Double returnOnEquityDiluted) {
        this.returnOnEquityDiluted = returnOnEquityDiluted;
    }

    public Double getAssetLiabilityRatio() {
        return assetLiabilityRatio;
    }

    public void setAssetLiabilityRatio(Double assetLiabilityRatio) {
        this.assetLiabilityRatio = assetLiabilityRatio;
    }

    public Double getCapitalReservePerShare() {
        return capitalReservePerShare;
    }

    public void setCapitalReservePerShare(Double capitalReservePerShare) {
        this.capitalReservePerShare = capitalReservePerShare;
    }

    public Double getUndistributedProfitPerShare() {
        return undistributedProfitPerShare;
    }

    public void setUndistributedProfitPerShare(Double undistributedProfitPerShare) {
        this.undistributedProfitPerShare = undistributedProfitPerShare;
    }

    public Double getOperationalCashFlowPerShare() {
        return operationalCashFlowPerShare;
    }

    public void setOperationalCashFlowPerShare(Double operationalCashFlowPerShare) {
        this.operationalCashFlowPerShare = operationalCashFlowPerShare;
    }

    public Double getGrossProfitRate() {
        return grossProfitRate;
    }

    public void setGrossProfitRate(Double grossProfitRate) {
        this.grossProfitRate = grossProfitRate;
    }

    public Double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(Double inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }
}
