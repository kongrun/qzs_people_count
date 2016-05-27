package com.qzs.entity;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ServerMessage {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getDetailedMsg() {
        return detailedMsg;
    }

    public void setDetailedMsg(String detailedMsg) {
        this.detailedMsg = detailedMsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    private int code;
    private String codeStatus;
    private String detailedMsg;
    private Object content;

}
