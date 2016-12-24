package me.xq.invest.dao;

import me.xq.invest.domain.XqDebt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangjianxin on 16-10-15.
 */
public interface XqDebtDAO {
    Integer addNewDebtInfo(@Param("xqDebtList") List<XqDebt> xqDebtList);
}
