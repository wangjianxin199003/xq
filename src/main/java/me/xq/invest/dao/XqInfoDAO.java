package me.xq.invest.dao;

import me.xq.invest.domain.XqInfo;

/**
 * Created by wangjianxin on 9/16/16.
 */
public interface XqInfoDAO {
    /**
     * 添加一条新的股票信息
     * @param xqInfo
     * @return
     */
    Integer addNewXqInfo(XqInfo xqInfo);

    /**
     * 用股票代码查询数据库中该股票的记录数
     * @param xqId
     * @return
     */
    Integer countXqByXqId(String xqId);
}
