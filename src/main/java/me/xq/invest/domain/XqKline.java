package me.xq.invest.domain;

/**
 * Created by wangjianxin on 16-10-19.
 */
public class XqKline {
    private String xqId;
    private String date;
    private double openPrice = 0.0;
    private double maxPrice = 0.0;
    private double minPrice = 0.0;
    private double closePrice = 0.0;
    private double turnOver = 0.0;
    private double volume = 0.0;
    private double turnOverRate = 0.0;

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

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(double turnOver) {
        this.turnOver = turnOver;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTurnOverRate() {
        return turnOverRate;
    }

    public void setTurnOverRate(double turnOverRate) {
        this.turnOverRate = turnOverRate;
    }
}
