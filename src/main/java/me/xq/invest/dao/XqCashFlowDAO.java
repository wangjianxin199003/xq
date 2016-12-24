package me.xq.invest.dao;

import me.xq.invest.domain.XqCashFlow;
import me.xq.invest.domain.XqDebt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangjianxin on 16-10-16.
 */
public interface XqCashFlowDAO {

    Integer addNewXqCashInfo(@Param("xqCashFlowList") List<XqCashFlow> xqCashFlowList);
}
