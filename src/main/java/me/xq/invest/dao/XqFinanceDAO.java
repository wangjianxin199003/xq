package me.xq.invest.dao;

import me.xq.invest.domain.XqFinance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangjianxin on 16-10-9.
 */
public interface XqFinanceDAO {

    Integer addNewFinanceInfo(@Param("xqFinanceList") List<XqFinance> xqFinanceList);
}
