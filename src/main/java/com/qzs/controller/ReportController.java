package com.qzs.controller;
import com.qzs.utility.*;
import com.qzs.service.*;
import com.qzs.dao.*;
import com.qzs.entity.*;
import com.qzs.dao_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/** 报表服务
 * Created by Administrator on 2016/5/14.
 */
@RestController
@RequestMapping("/data/retrieve")
public class ReportController {

    @Autowired
    IReportService iReportService;
//    @Autowired
    IReportDao iReportDao;


    @ModelAttribute//该Controller 的所有方法执行之前会先知先@ModelAttribute 方法
    public void setResponseHeader(HttpServletResponse response)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " service is visited！");
        response.setHeader("Access-Control-Allow-Origin", "*");


    }

    /**
     * 获得清真寺列表
     * @return list
     *
     */
    @RequestMapping(value = "/qzslist",method = RequestMethod.GET)
    public QzsList reportWeekData()
    {
        iReportDao = new ReportDao() ;
        return iReportDao.constructQzsList();

    }

    /**
     * 获取日报数据
     * @param rid d
     * @param pid d
     * @param date d
     * @return report
     */
    @RequestMapping(value = "/reportday",method = RequestMethod.GET)
    public ServerMessage reportDayData(@RequestParam(value = "rid")String rid,
                                @RequestParam(value = "pid")String pid,
                                @RequestParam(value = "date")String date)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 访问日报数据！");

        return iReportService.readReportDay(rid,pid,date);

    }

    @RequestMapping(value = "/reportweek",method = RequestMethod.GET)
    public ServerMessage reportWeekData(@RequestParam(value = "rid")String rid,
                                 @RequestParam(value = "pid")String pid,
                                 @RequestParam(value = "date")String date)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 访问周报数据！");
        return iReportService.readReportDay(rid,pid,date);

    }
    @RequestMapping(value = "/reportmonth",method = RequestMethod.GET)
    public ServerMessage reportMonthData(@RequestParam(value = "rid")String rid,
                                  @RequestParam(value = "pid")String pid,
                                  @RequestParam(value = "date")String date)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 访问月报数据！");
        return iReportService.readReportDay(rid,pid,date);

    }
    @RequestMapping(value = "/reportseason",method = RequestMethod.GET)
    public ServerMessage reportSeasonData(@RequestParam(value = "rid")String rid,
                                   @RequestParam(value = "pid")String pid,
                                   @RequestParam(value = "date")String date)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 访问季报数据！");
        return iReportService.readReportDay(rid,pid,date);

    }
    @RequestMapping(value = "/reportyear",method = RequestMethod.GET)
    public ServerMessage reportYeatData(@RequestParam(value = "rid")String rid,
                                 @RequestParam(value = "pid")String pid,
                                 @RequestParam(value = "date")String date)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 访问年报数据！");
        return iReportService.readReportDay(rid,pid,date);
    }


    /**
     * 设置重点时间段
     * @param keyTimeJson json格式的对象列表
     */
    @RequestMapping(value = "/setkeytime",method = RequestMethod.GET)
    public ServerMessage setKeyTime(@RequestParam(value = "time")String keyTimeJson)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 设置重点时间段！");
        return iReportService.setKeyTime(keyTimeJson);
    }



    /**
     *
     * @return 重点时刻数据
     */
    @RequestMapping(value = "/reportkeytime",method = RequestMethod.GET)
    public ServerMessage reportKeyTimeData(@RequestParam(value = "rid")String rid)
    {
        System.out.println(DateFormat.getTime(System.currentTimeMillis()) + " 读取重点时间段数据！");
        return iReportService.getKeyTimeData(rid);

    }
}
