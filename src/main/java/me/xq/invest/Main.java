package me.xq.invest;

import me.xq.invest.domain.XqInfo;
import me.xq.invest.service.DownloadService;
import me.xq.invest.service.impl.XqInfoDownloadServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjianxin on 9/27/16.
 */
public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger("main");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-xq-common.xml");
        Integer xqId = 1;
        List<String> xqIdList = new ArrayList<String>();
        while (xqId < 604000) {
            //中小板结束，进入创业板
            if (xqId == 2999) {
                xqId = 300000;
            }
            //创业板结束，进入主板
            if (xqId == 300600) {
                xqId = 600000;
            }
            String xqIdString = String.format("%06d", xqId);
            xqIdList.add(xqIdString);
            ++xqId;
        }

        DownloadService<XqInfo> xqInfoDownloadService = (XqInfoDownloadServiceImpl) context.getBean("xqInfoDownloadService");
        //存放下载错误的股票代码
        List<String> errorXqIdList = new ArrayList<String>();
        int i = 1;
        while (xqIdList.size() > 0) {
            for (String xqIdString : xqIdList) {

                try {
                    logger.warn("开始下载： " + xqIdString);
                    xqInfoDownloadService.xqDownload(XqInfo.class, xqIdString, "http://d.10jqka.com.cn/v2/line/hs_%s/01/last.js", new String[]{xqIdString});
                    logger.warn("下载成功： " + xqIdString);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn("下载失败： " + xqIdString);
                    errorXqIdList.add(xqIdString);
                }
            }
            //清空准备下载的股票列表
            xqIdList.clear();
            //把下载错误的股票代码放入准备下载的股票列表中
            xqIdList.addAll(errorXqIdList);
            for (String error : errorXqIdList) {
                System.out.println(error);
            }
            logger.warn("第" + i + "遍，错误数量： " + errorXqIdList.size());
            //清空错误股票代码
            errorXqIdList.clear();
            i++;
        }

    }
}
