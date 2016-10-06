package me.xq.invest;

import me.xq.invest.domain.XqInfo;
import me.xq.invest.service.DownloadService;
import me.xq.invest.service.impl.XqInfoDownloadServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangjianxin on 9/27/16.
 */
public class Main {
    public static void main(String[] args){
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
            ++xqId;

            String xqIdString = String.format("%06d", xqId);
            xqIdList.add(xqIdString);
        }


        final DownloadService<XqInfo> xqInfoDownloadService = (XqInfoDownloadServiceImpl)context.getBean("xqInfoDownloadService");
        final List<String> errorXqIdList = new ArrayList<String>();
        final TaskExecutor taskExecutor = (TaskExecutor)context.getBean("taskExecutor");
        int i = 1;
        while (xqIdList.size() > 0){
            for(String xqIdString : xqIdList){
                final String tempXqIdString = xqIdString;
                taskExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            logger.warn("开始下载： " + tempXqIdString);
                            xqInfoDownloadService.xqDownload(XqInfo.class,tempXqIdString,"http://d.10jqka.com.cn/v2/line/hs_%s/01/last.js",new String[] {tempXqIdString});
                            logger.warn("下载成功： " + tempXqIdString);
                        }catch (Exception e){
                            logger.warn("下载失败： " + tempXqIdString);
                            errorXqIdList.add(tempXqIdString);
                        }
                    }
                });

            }
            xqIdList.clear();
            xqIdList.addAll(errorXqIdList);
            logger.warn("第" + i +"遍，错误数量： " + errorXqIdList.size());
            errorXqIdList.clear();
            i++;

        }
    }
}
