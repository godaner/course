package com.course.util;


public class PageBean {
    private Integer start;
    private Integer size;
    private String orderBy;
    private String orderType;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public PageBean() {
        this.setStart(0);
        this.setSize(12);
        this.setOrderBy("create_time");
        this.setOrderType("desc");
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "start=" + start +
                ", size=" + size +
                ", orderBy='" + orderBy + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
