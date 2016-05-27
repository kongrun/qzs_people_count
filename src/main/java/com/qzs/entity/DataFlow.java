package com.qzs.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/15.
 */
public class DataFlow {

    private String name;
    private String direction;
    private ArrayList<String> type;
    private Object description;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    //    "person_count_area":{
//        "id":"person_count_area",
//                "name":"person_count_area",
//                "direction":"RES_2_USER",
//                "type":null,
//                "description":{
//                    "numAlarm":"0",
//                    "numStay":"5000",
//                    "numOut":"5000",
//                    "numIn":"0",
//                    "cient_timestamp":"1453880040000"
//        },
//        "dynamic":false
//    },
}
