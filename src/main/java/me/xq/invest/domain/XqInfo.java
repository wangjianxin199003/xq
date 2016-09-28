package me.xq.invest.domain;

import org.apache.log4j.Logger;

/**
 * Created by wangjianxin on 9/16/16.
 */
public class XqInfo {
    //主键
    private Integer primaryKey;
    //代码
    private String xqId;
    //名字
    private String xqName;
    //上市日期
    private String startDate;
    //退市日期
    private String endDate;

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getXqId() {
        return xqId;
    }

    public void setXqId(String xqId) {
        this.xqId = xqId;
    }

    public String getXqName() {
        return xqName;
    }

    public void setXqName(String xqName) {
        this.xqName = xqName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
