package com.course.dao.po.query;

import com.course.dao.po.BasePo;


public class BaseQueryBean {
    private String keyword;
    private Byte status;

    public BaseQueryBean() {
        this.status = BasePo.Status.NORMAL.getCode();
        this.keyword = "";
    }

    public String getKeyword() {
        return keyword;
    }

    public BaseQueryBean setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public BaseQueryBean setStatus(Byte status) {
        this.status = status;
        return this;
    }
}
