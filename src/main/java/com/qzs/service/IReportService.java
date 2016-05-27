package com.qzs.service;

import com.qzs.entity.*;

/**
 * Created by Administrator on 2016/5/14.
 */
public interface IReportService {

    /**
     * 读取日报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportDay(String rid,String pid,String date);

    /**
     * 读取周报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage  readReportWeek(String rid,String pid,String date);

    /**
     * 读取月报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage  readReportMonth(String rid,String pid,String date);

    /**
     * 读取季度报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage  readReportSeason(String rid,String pid,String date);



    /**
     * 读取年数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportYear(String rid,String pid,String date);

    /**
     * 设置重点时间段
     * @param keyTime
     * @return
     */
    public ServerMessage setKeyTime(String keyTime);


    /**
     * 获取重点时间段数据
     * @return
     */
    public ServerMessage getKeyTimeData(String rid);


//    public QzsList getQzsList();
}
