package com.course.dao.po.query;

import com.course.dao.po.BasePo;
import com.course.util.ByteConstantVar;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class BaseQueryBean {
    private Byte status = BasePo.Status.NORMAL.getCode();

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
