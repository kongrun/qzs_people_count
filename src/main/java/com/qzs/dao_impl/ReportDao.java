package com.qzs.dao_impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzs.entity.*;
import java.util.ArrayList;
import com.qzs.dao.*;
import com.qzs.utility.*;
/** 构造报表的dao层
 * Created by Administrator on 2016/5/14.
 */

public class ReportDao implements IReportDao{

    String url = Properties.dataCenterURL;
    String searchPlatformUrl = Properties.searchCenterURL;
    /**
     * 构造清真寺列表
     * @return
     */
    @Override
    public QzsList constructQzsList()
    {
        ArrayList<String > list = new ArrayList<String>();
        QzsList qzsList = new QzsList();
        String qzsString = HttpMethod.doGet(searchPlatformUrl,"wd=qingzhensi");
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode jsonNode = mapper.readTree(qzsString);
            JsonNode qzslistNode = jsonNode.path("data");
            for(int i = 0 ;i < qzslistNode.size();i++)
            {
                list.add(qzslistNode.get(i).path("id").asText());
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        qzsList.setQzslist(list);
        return qzsList;
    }

    /**
     * 设置重点时间段
     * @param keyTimeJson
     * @return 是否成功
     */
    @Override
    public ServerMessage setKeyTime(String keyTimeJson) {
        ServerMessage serverMessage = new ServerMessage();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode keyTimes = mapper.readTree(keyTimeJson);
            JsonNode keyTime = keyTimes.get(0);
            //构造重点时刻的具体时间段实体类
//            System.out.println("starttime :" + keyTime.toString());
            InitDescription initDescription = new InitDescription();
            initDescription.setStartDate(keyTime.path("startDate").asText());
            initDescription.setEndDate(keyTime.path("endDate").asText());
            initDescription.setStartTime(keyTime.path("startTime").asText());
            initDescription.setEndTime(keyTime.path("endTime").asText());
            initDescription.setType(keyTime.path("type").asText());
            JsonNode values = keyTime.path("value");
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0;i < values.size();i++)
            {
                list.add(values.get(i).asInt());
            }
            initDescription.setValue(list);
            //构造重点时刻流实体类
            InitData initData = new InitData();
            initData.setName("init_data");
            initData.setDirection("USER_2_RES");
            initData.setDescription(initDescription);

            String keytimejson = mapper.writeValueAsString(initData);
//            System.out.println("iniit :" + keytimejson);

            String params = "rid=" + "QZS01" + "&pid=" + "init_data" +"&check=" + "QZS01" + "&data=" + keytimejson;
            String url1 = "http://10.10.2.236:8080/data/update";
            String result = HttpMethod.doPost(url1,params);
            JsonNode resultJsonNode = mapper.readTree(result);
            serverMessage.setCode(resultJsonNode.path("code").asInt());
            serverMessage.setCodeStatus(resultJsonNode.path("codeStatus").asText());
            serverMessage.setDetailedMsg(resultJsonNode.path("detailedMsg").asText());
            serverMessage.setContent(null);

        }catch(Exception e){
            e.printStackTrace();
            serverMessage.setCode(201);
            serverMessage.setCodeStatus("failed");
            serverMessage.setContent(null);
            serverMessage.setDetailedMsg(e.getMessage());

        }
        return serverMessage;

    }



    /**
     *
     * @return 重点时刻段数据的列表
     */
    @Override
    public ServerMessage readKeyTimeData(String rid,String param ) {

        ServerMessage serverMessage = new ServerMessage();
        ArrayList<DataFlow> keytimedatalist = new ArrayList<DataFlow>();

        ReportDescription description =new ReportDescription();
        try {
            ObjectMapper mapper = new ObjectMapper();
            //解析返回的服务器数据
            JsonNode jsonNode = mapper.readTree(param);
//            System.out.println(jsonNode.toString());

            //重点时间段数据
            JsonNode content = jsonNode.path("content");
            System.out.println(content.toString());

            if(content.size() != 0) {

                JsonNode reportData = content.get(0);
//              System.out.println(reportData.toString());
                JsonNode descriptionJ = reportData.path("description");
//                System.out.println("test"+descriptionJ.toString());
                //获得重点时刻的具体数据
                String startDate = descriptionJ.path("startDate").asText();
                String endDate = descriptionJ.path("endDate").asText();
                String startTime = descriptionJ.path("startTime").asText();
                String endTime = descriptionJ.path("endTime").asText();
                String type = descriptionJ.path("type").asText();
                JsonNode values = descriptionJ.path("value");
                int value = descriptionJ.path("value").get(0).asInt();
                long start = DateFormat.getTimeStamp(startDate + " " + startTime) ;
                long  end = DateFormat.getTimeStamp(endDate + " " + endTime);

                //读取start 和 end 时间段内的重点时刻数据
                long fromTime = start;
                long toTime = DateFormat.getTimeStamp(startDate + " " + endTime);

                while(fromTime >= start && toTime <= end && fromTime < toTime) {
                    String params = "rid=" + rid + "&pid=person_count_area" + "&fromTime=" + fromTime + "&toTime=" + toTime;
                    DataFlow dataFlow;
                    //取本时间段内的重点时刻报表数据
                    if(type.equals("day"))
                    {
                        dataFlow = parse_person_count_area(HttpMethod.doGet(url,params));
                        if(dataFlow != null)
                        {
                            keytimedatalist.add(dataFlow);
//                        System.out.println(keytimedatalist);
                        }
                        fromTime = fromTime + value*24*60*60*1000;
                        toTime = toTime + value*24*60*60*1000;
                    }
                    if(type.equals("week"))
                    {
                        for(int i=0;i<values.size();i++)
                        {
                            if(DateFormat.getWeek(fromTime) == (values.get(i).asInt() - 1))
                            {
                                dataFlow = parse_person_count_area(HttpMethod.doGet(url,params));
                                if(dataFlow != null)
                                {
                                    keytimedatalist.add(dataFlow);
//                        System.out.println(keytimedatalist);
                                }
                                break;
                            }
                        }
                        fromTime = fromTime + 24*60*60*1000;
                        toTime = toTime + 24*60*60*1000;

                    }
                    if(type.equals("month"))
                    {
                        dataFlow = parse_person_count_area(HttpMethod.doGet(url,params));
                        if(dataFlow != null)
                        {
                            keytimedatalist.add(dataFlow);
//                        System.out.println(keytimedatalist);
                        }
                        int month = DateFormat.getDay(fromTime);
                        int year = DateFormat.getYear(fromTime);
                        if((month + value) > 12 ){  year ++;month = (month + value) % 12 ;}
                        else{month ++;}
                       String  monthtemp = "" + (month > 9 ?month:("0" + month));
                        fromTime = DateFormat.getTimeStamp(year + "-" + monthtemp + DateFormat.getTime(fromTime).substring(7,20));
                        toTime =  DateFormat.getTimeStamp(year + "-" + monthtemp + DateFormat.getTime(toTime).substring(7,20));
                    }
                    if(type.equals("year"))
                    {
                        dataFlow = parse_person_count_area(HttpMethod.doGet(url,params));
                        if(dataFlow != null)
                        {
                            keytimedatalist.add(dataFlow);
//                        System.out.println(keytimedatalist);
                        }
                        int year = DateFormat.getYear(fromTime);
                        year = year + value;
                        fromTime = DateFormat.getTimeStamp(year + DateFormat.getTime(fromTime).substring(4,20));
                        toTime = DateFormat.getTimeStamp(year + DateFormat.getTime(toTime).substring(4,20));
                    }
                }
            }

            serverMessage.setContent(keytimedatalist);
            if(keytimedatalist.size() != 0)
            {
                // 获取返回信息
                serverMessage.setCode(200);
                serverMessage.setCodeStatus("ok");
                serverMessage.setDetailedMsg("success");
            }
            else
            {
                serverMessage.setCode(jsonNode.path("code").asInt());
                serverMessage.setCodeStatus(jsonNode.path("codeStatus").asText());
                serverMessage.setDetailedMsg(jsonNode.path("detailedMsg").asText());
            }

        }catch(Exception e){
            e.printStackTrace();
            serverMessage.setCode(201);
            serverMessage.setCodeStatus("failed");
            serverMessage.setContent(null);
            serverMessage.setDetailedMsg(e.getMessage());
        }
        return serverMessage;
    }

    public DataFlow parse_person_count_area(String str)
    {
        long numAlarmMax=0,numInMax=0,numOutMax=0,numStayMax=0,numAlarmMin=0,numInMin=0,numOutMin=0,numStayMin=0;
        DataFlow dataFlow = new DataFlow();
        PersonCountAreaDescription personCountAreaDescription = new PersonCountAreaDescription();
        ObjectMapper mapper = new ObjectMapper();
        //解析返回的服务器数据
        try {
            JsonNode jsonNode = mapper.readTree(str);
            JsonNode content = jsonNode.path("content");
            if(content.size() != 0) {
//                System.out.println("comntem " + content.toString());
                long cient_timestampMax= content.get(0).path("description").path("cient_timestamp").asLong();
                long cient_timestampMin = cient_timestampMax;
                for(int i = 0 ; i<content.size(); i++)
                {
                    //寻找当前时间段内的最大时间内的数据
                    if(content.get(i).path("description").path("cient_timestamp").asLong() > cient_timestampMax)
                    {
                        cient_timestampMax = content.get(i).path("description").path("cient_timestamp").asLong();
                        numAlarmMax = content.get(i).path("description").path("cient_timestamp").asLong();
                        numInMax = content.get(i).path("description").path("numAlarm").asLong();
                        numOutMax = content.get(i).path("description").path("numOut").asLong();
                        numStayMax = content.get(i).path("description").path("numStay").asLong();
//                        personCountAreaDescription.setCient_timestamp(content.get(i).path("description").path("cient_timestamp").asLong() );
//                       personCountAreaDescription.setNumAlarm(content.get(i).path("description").path("numAlarm").asLong());
//                        personCountAreaDescription.setNumIn(content.get(i).path("description").path("numIn").asLong());
//                        personCountAreaDescription.setNumOut(content.get(i).path("description").path("numOut").asLong());
//                        personCountAreaDescription.setNumStay(content.get(i).path("description").path("numStay").asLong());
                    }
                    if(content.get(i).path("description").path("cient_timestamp").asLong() < cient_timestampMin)
                    {
                        numAlarmMin = content.get(i).path("description").path("cient_timestamp").asLong();
                        numInMin = content.get(i).path("description").path("numAlarm").asLong();
                        numOutMin = content.get(i).path("description").path("numOut").asLong();
                        numStayMin = content.get(i).path("description").path("numStay").asLong();
                    }

                }
                        personCountAreaDescription.setCient_timestamp(cient_timestampMax);
                       personCountAreaDescription.setNumAlarm(numAlarmMax - numAlarmMin);
                        personCountAreaDescription.setNumIn(numInMax - numInMin);
                        personCountAreaDescription.setNumOut(numOutMax - numOutMin);
                        personCountAreaDescription.setNumStay(numStayMax - numStayMin);
            dataFlow.setDynamic(false);
                dataFlow.setDescription(personCountAreaDescription);
                dataFlow.setName("key_time_data");
                dataFlow.setType(null);
                dataFlow.setDirection("SERVER_2_USER");
            }
            else {return null;}

        }catch(Exception e)
        {
            e.printStackTrace();

        }
        return dataFlow;
    }


    /**
     * 构造report实体
     * @param param
     * @return
     */
    @Override
    public ServerMessage constructReportDay(String param)
    {
        return constructReport(param);
    }

    /**
     * 构造report实体
     * @param param  怕
     * @return report
     */
    @Override
    public ServerMessage constructReportWeek(String param)
    {
        ServerMessage serverMessage = new ServerMessage();

        return serverMessage;
    }

    /**
     * 构造report实体
     * @param param
     * @return
     */
    @Override
    public ServerMessage constructReportMonth(String param)
    {
        ServerMessage serverMessage = new ServerMessage();

        return serverMessage;
    }

    /**
     * 构造report实体
     * @param param
     * @return
     */
    @Override
    public ServerMessage constructReportSeason(String param)
    {
        ServerMessage serverMessage = new ServerMessage();

        return serverMessage;
    }

    /**
     * 构造report实体
     * @param param
     * @return
     */
    @Override
    public ServerMessage constructReportYear(String param)
    {
        ServerMessage serverMessage = new ServerMessage();

        return serverMessage;
    }


    /**
     * 构造统一的报表结构
     * @param param
     * @return
     */
    @Override
    public ServerMessage constructReport(String param)
    {
        ServerMessage serverMessage = new ServerMessage();
        Report report = new Report();
        ReportDescription description =new ReportDescription();
        try {

            String str = HttpMethod.doGet(url, param);

//            System.out.println(url+"?"+param);

            ObjectMapper mapper = new ObjectMapper();
            //解析返回的日报数据
            JsonNode jsonNode = mapper.readTree(str);
//            System.out.println(jsonNode.toString());
            //获取返回信息
            serverMessage.setCode(jsonNode.path("code").asInt());
            serverMessage.setCodeStatus(jsonNode.path("codeStatus").asText());
            serverMessage.setDetailedMsg(jsonNode.path("detailedMsg").asText());

            //报表的所有数据
            JsonNode content = jsonNode.path("content");
//            System.out.println(content.toString());

            if(content.size() != 0) {
                JsonNode reportData = content.get(0);
                System.out.println(reportData.toString());

//          report = mapper.readValue(reportData.toString(),Report.class);
                JsonNode descriptionJ = reportData.path("description");
//            System.out.println("test"+descriptionJ.toString());

//            ReportDescription descriptionE =  mapper.readValue(description.toString(), ReportDescription.class);
                //报表数据信息
                report.setRid(reportData.path("rid").asText());
                report.setPid(reportData.path("pid").asText());
                report.setName(reportData.path("name").asText());
                report.setTimestamp(reportData.path("timestamp").asLong());
                report.setData(reportData.path("data").asText());

                description.setCient_timestamp(descriptionJ.path("cient_timestamp").asLong());
                description.setNumStayMaxTime(descriptionJ.path("numStayMaxTime").asLong());
                description.setNumStayMax(descriptionJ.path("numStayMax").asLong());
                description.setNumAlarmTotal(descriptionJ.path("numAlarmTotal").asLong());
                description.setNumInTotal(descriptionJ.path("numInTotal").asLong());
                description.setNumOutTotal(descriptionJ.path("numOutTotal").asLong());

            report.setDescription(description);
            }

            serverMessage.setContent(report);

        }catch(Exception e){
            e.printStackTrace();
            serverMessage.setCode(201);
            serverMessage.setCodeStatus("failed");
            serverMessage.setContent(null);
            serverMessage.setDetailedMsg(e.getMessage());
        }


        return serverMessage;
    }



}
