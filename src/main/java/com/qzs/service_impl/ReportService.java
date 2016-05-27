package com.qzs.service_impl;

import com.qzs.dao.IReportDao;
import com.qzs.dao_impl.ReportDao;
import com.qzs.entity.Report;
import com.qzs.entity.ReportDescription;
import com.qzs.entity.ServerMessage;
import com.qzs.utility.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qzs.service.IReportService;
import com.qzs.utility.DateFormat;
import com.qzs.utility.HttpMethod;

/**
 * Created by Administrator on 2016/5/14.
 */
@Service
public class ReportService implements IReportService {


//    @Autowired
    IReportDao iReportDao;


    /**
     * 读取日报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportDay(String rid,String pid,String date)

    {

        //将日期转化成对应的时间段，并取时间段内的数据
        String fromDate = date + " 00:00:00";
        String toDate = date +" 23:59:59";
        long fromTime = DateFormat.getTimeStamp(fromDate);
        long toTime = DateFormat.getTimeStamp(toDate);
//        System.out.println("ok");
        //取数据
        String params = "rid=" + rid + "&pid=" + pid + "&fromTime=" +fromTime +"&toTime=" + toTime;
        iReportDao=new ReportDao();
        return iReportDao.constructReportDay(params);
    }

    /**
     * 读取周报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportWeek(String rid,String pid,String date)
    {
        //将日期转化成对应的时间段，并取时间段内的数据
        String fromDate = date + " 00:00:00";
        long toTime =DateFormat.getTimeStamp(fromDate);
        long fromTime = toTime - (long)(DateFormat.getWeek(toTime)-1) * 24 *60 *60 *1000;
        toTime = toTime + (long)(8 - DateFormat.getWeek(toTime)) * 24 *60 *60 *1000;
        //取数据
        String params = "rid=" + rid + "&pid=" + pid + "&fromTime=" +fromTime +"&toTime=" + toTime;
        iReportDao=new ReportDao();

        return iReportDao.constructReport(params);


    }

    /**
     * 读取月报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportMonth(String rid,String pid,String date)
    {
        String fromDate = date + "-01 00:00:00";
        String datetemp = date.substring(5,7);

        datetemp = "" + ((Integer.parseInt(datetemp)+1) > 9 ?(Integer.parseInt(datetemp)+1) : ("0" + (Integer.parseInt(datetemp)+1) ));
//		System.out.println("yuefen :" + datetemp);
        String toDate = date.substring(0,5) + datetemp + "-01 00:00:00";

        long fromTime = DateFormat.getTimeStamp(fromDate);
        long toTime = DateFormat.getTimeStamp(toDate);

        String params = "rid=" + rid + "&pid=" + pid + "&fromTime=" +fromTime +"&toTime=" + toTime;
        iReportDao=new ReportDao();

        return iReportDao.constructReport(params);


    }

    /**
     * 读取季度报数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportSeason(String rid,String pid,String date)
    {

        String datetemp = date.substring(5,7);//获取对应的season字符串
        String year = date.substring(0,4);//huoqu 年份
        int season = Integer.parseInt(datetemp);
        String fromMonth = "" + (((season- 1) * 3 + 1) > 9 ? ((season- 1) * 3 + 1) : ("0" + ((season- 1) * 3 + 1)) );
        String toMonth = "";
        String toDate = "";
        if(season == 4)
        {
            String year1 = "" +( Integer.parseInt(year) + 1 );
            toMonth = "01";
            toDate = year1 + "-" + toMonth  + "-01 00:00:00";

        }else{

            toMonth = "" + (((season- 1) * 3 + 4) > 9 ? ((season- 1) * 3 + 4) : ("0" + ((season- 1) * 3 + 4)) );
            toDate = year + "-" + toMonth  + "-01 00:00:00";
        }
        String fromDate = year + "-" + fromMonth + "-01 00:00:00";

//			System.out.println("frommonth :" + fromDate + "  todate :" + toDate);
        long fromTime = DateFormat.getTimeStamp(fromDate);
        long toTime = DateFormat.getTimeStamp(toDate);
        String params = "rid=" + rid + "&pid=" + pid + "&fromTime=" +fromTime +"&toTime=" + toTime;
        iReportDao=new ReportDao();

        return  iReportDao.constructReport(params);


    }



    /**
     * 读取年数据
     * @param rid
     * @param pid
     * @param date
     * @return
     */
    public ServerMessage readReportYear(String rid,String pid,String date)
    {
        Report report = new Report();

        String fromDate = date + "-01-01 00:00:00";
        String toDate = date +"-12-31 23:59:59";
        long fromTime = DateFormat.getTimeStamp(fromDate);
        long toTime = DateFormat.getTimeStamp(toDate);
        String params = "rid=" + rid + "&pid=" + pid + "&fromTime=" +fromTime +"&toTime=" + toTime;
        iReportDao=new ReportDao();
        return  iReportDao.constructReport(params);


    }

    @Override
    public ServerMessage setKeyTime(String keyTime) {
        iReportDao=new ReportDao();
        return iReportDao.setKeyTime(keyTime);
    }

    @Override
    public ServerMessage getKeyTimeData(String rid) {

        iReportDao=new ReportDao();
        String params = "rid=QZS01&pid=init_data";
        String keyTime = HttpMethod.doGet(Properties.dataCenterURL,params);

        return iReportDao.readKeyTimeData(rid, keyTime );
    }

}
