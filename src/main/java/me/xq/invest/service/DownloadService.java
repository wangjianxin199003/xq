package me.xq.invest.service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by wangjianxin on 9/16/16.
 */
public abstract  class DownloadService<T> {
    /**
     * 格式化url
     * @param url
     * @param args
     * @return
     */
    String formatUrl(String url, String args[]){
        return String.format(url, args);
    }

    /**
     * json转Map
     * @param formatedUrl
     * @return
     */
    public abstract Map<String, String> downloadInfo(String formatedUrl, String xqId);

    /**
     * Map转实体类
     * @param info
     * @param domainClass
     * @return
     */
    T parseObject(Map<String,String> info, Class domainClass){
        T domain =  null;
        try {
            domain = (T)domainClass.newInstance();
            BeanUtils.populate(domain, info);
        }catch (Exception e){
            e.printStackTrace();
        }
        return domain;
    }

    /**
     * 保存，自己实现
     * @param domain
     */
    public abstract void saveObject(T domain);

    /**
     * 执行
     * @param domainClass
     * @param url
     * @param args
     */
    public void xqDownload(Class<T> domainClass,  String xqId,String url, String[] args){
        //格式化url
        String formatedUrl = formatUrl(url, args);
        //解析json得到Map,key为属性，value为值
        Map<String,String> info = downloadInfo(formatedUrl, xqId);
        //得到实体类对象
        T domain = parseObject(info, domainClass);
        //保存
        saveObject(domain);
    }
}
