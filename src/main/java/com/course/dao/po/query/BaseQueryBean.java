package com.course.dao.po.query;

import com.course.dao.po.BasePo;
import org.assertj.core.util.Lists;

import java.util.List;


public class BaseQueryBean {
    private String keyword;
    private List<Byte> status;

    public BaseQueryBean() {
        this.status = Lists.newArrayList(BasePo.Status.NORMAL.getCode());
        this.keyword = "";
    }

    public String getKeyword() {
        return keyword;
    }

    public BaseQueryBean setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public List<Byte> getStatus() {
        return status;
    }

    public BaseQueryBean setStatus(List<Byte> status) {
        this.status = status;
        return this;
    }
}
