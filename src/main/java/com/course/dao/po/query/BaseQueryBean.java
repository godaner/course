package com.course.dao.po.query;

import com.course.dao.po.BasePo;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class BaseQueryBean {
    private String keyword;
    private Byte status = BasePo.Status.NORMAL.getCode();

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
