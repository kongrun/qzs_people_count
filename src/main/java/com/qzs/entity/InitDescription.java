package com.qzs.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/15.
 */
public class InitDescription {

    private String startDate;
    private ArrayList<Integer> value;
    private String endDate;
    private String startTime;
    private String endTime;
    private String type;

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ArrayList<Integer> getValue() {
        return value;
    }

    public void setValue(ArrayList<Integer> value) {
        this.value = value;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //    "startDate":"20160501",
//            "value":"1",
//            "endDate":"20161010",
//            "StartTime":"01:01:01",
//            "type":"year",
//            "endTime":"12:20:20"

}
