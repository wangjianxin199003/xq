package me.xq.invest;

import me.xq.invest.domain.XqInfo;
import me.xq.invest.service.DownloadService;
import me.xq.invest.service.impl.XqInfoDownloadServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjianxin on 9/27/16.
 */
public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-xq-common.xml");
        DownloadService<XqInfo> xqInfoDownloadService = (XqInfoDownloadServiceImpl)context.getBean("xqInfoDownloadService");
        xqInfoDownloadService.xqDownload(XqInfo.class,"600177","http://d.10jqka.com.cn/v2/line/hs_%s/01/last.js",new String[] {"600177"});
    }
}
