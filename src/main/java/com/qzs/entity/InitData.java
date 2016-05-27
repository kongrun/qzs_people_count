package com.qzs.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/15.
 */
public class InitData {

    private String name;
    private String direction;
    private ArrayList<String> type;
    private InitDescription description;
    private boolean dynamic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public InitDescription getDescription() {
        return description;
    }

    public void setDescription(InitDescription description) {
        this.description = description;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }


//    "init_data":{
//        "id":"init_data",
//                "name":"init_data",
//                "direction":"RES_2_USER",
//                "type":null,
//                "description":{
//            "startDate":"20160501",
//                    "value":"1",
//                    "endDate":"20161010",
//                    "StartTime":"01:01:01",
//                    "type":"year",
//                    "endTime":"12:20:20"
//        },
//        "dynamic":false
//    }

}
