package com.qzs.dao;


import com.qzs.entity.*;
/**
 * Created by Administrator on 2016/5/14.
 */
public interface IReportDao {

    /**
     * 构造report实体
     * @param param
     * @return
     */
    public ServerMessage constructReportDay(String param);
    /**
     * 构造report实体
     * @param param
     * @return
     */
    public ServerMessage constructReportWeek(String param);
    /**
     * 构造report实体
     * @param param
     * @return
     */
    public ServerMessage constructReportMonth(String param);
    /**
     * 构造report实体
     * @param param
     * @return
     */
    public ServerMessage constructReportSeason(String param);
    /**
     * 构造report实体
     * @param param
     * @return
     */
    public ServerMessage constructReportYear(String param);

    /**
     * 构造统一报表结构（日报，月报，周报，年报，季报）
     * @param params
     * @return
     */

   public  ServerMessage constructReport(String params);
    public QzsList constructQzsList();

    /**
     * 设置重点时刻
     * @param keyTimeJson
     * @return
     */
    public ServerMessage setKeyTime(String keyTimeJson);


    /**
     * 获取重点时刻数据
     * @return
     */
    public ServerMessage readKeyTimeData(String rid,String param );
}
