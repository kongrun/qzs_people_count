package com.qzs.entity;

/** 日报，周报，月报，季报，年报实体
 * Created by Administrator on 2016/5/14.
 */
public class Report {

    private String rid;
    private String pid;
    private String name;
    private long timestamp;
    private String data;
    private ReportDescription description;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ReportDescription getDescription() {
        return description;
    }

    public void setDescription(ReportDescription description) {
        this.description = description;
    }
}
//"report_day":{
//        "id":"report_day",
//        "name":"report_day",
//        "direction":"RES_2_USER",
//        "type":null,
//        "description":{
//        "numAlarmTotal":"33",
//        "numStayMax":"5000",
//        "cient_timestamp":"1453880040000",
//        "numOutTotal":"5000",
//        "numStayMaxTime":"1453880040000",
//        "numInTotal":"0"
//        },
//        "dynamic":false
//        },