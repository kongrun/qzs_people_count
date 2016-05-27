package com.qzs.entity;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ReportDescription {

    private long numInTotal;
    private long numOutTotal;
    private long numStayMax;
    private long numStayMaxTime;
    private long numAlarmTotal;
    private long cient_timestamp;

    public long getNumStayMax() {
        return numStayMax;
    }

    public void setNumStayMax(long numStayMax) {
        this.numStayMax = numStayMax;
    }

    public long getNumOutTotal() {
        return numOutTotal;
    }

    public void setNumOutTotal(long numOutTotal) {
        this.numOutTotal = numOutTotal;
    }

    public long getNumStayMaxTime() {
        return numStayMaxTime;
    }

    public void setNumStayMaxTime(long numStayMaxTime) {
        this.numStayMaxTime = numStayMaxTime;
    }

    public long getNumAlarmTotal() {
        return numAlarmTotal;
    }

    public void setNumAlarmTotal(long numAlarmTotal) {
        this.numAlarmTotal = numAlarmTotal;
    }

    public long getCient_timestamp() {
        return cient_timestamp;
    }

    public void setCient_timestamp(long cient_timestamp) {
        this.cient_timestamp = cient_timestamp;
    }

    public long getNumInTotal() {

        return numInTotal;
    }

    public void setNumInTotal(long numInTotal) {
        this.numInTotal = numInTotal;
    }
}
