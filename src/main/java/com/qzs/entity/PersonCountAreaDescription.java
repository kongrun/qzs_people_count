package com.qzs.entity;

/**
 * Created by Administrator on 2016/5/15.
 */
public class PersonCountAreaDescription {
    private long numAlarm;
    private long  numStay;
    private long numIn;
    private long numOut;
    private long cient_timestamp;

    public long getNumAlarm() {
        return numAlarm;
    }

    public void setNumAlarm(long numAlarm) {
        this.numAlarm = numAlarm;
    }

    public long getNumStay() {
        return numStay;
    }

    public void setNumStay(long numStay) {
        this.numStay = numStay;
    }

    public long getNumIn() {
        return numIn;
    }

    public void setNumIn(long numIn) {
        this.numIn = numIn;
    }

    public long getNumOut() {
        return numOut;
    }

    public void setNumOut(long numOut) {
        this.numOut = numOut;
    }

    public long getCient_timestamp() {
        return cient_timestamp;
    }

    public void setCient_timestamp(long cient_timestamp) {
        this.cient_timestamp = cient_timestamp;
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
