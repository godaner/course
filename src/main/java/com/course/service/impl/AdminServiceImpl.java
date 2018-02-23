package com.course.service.impl;

import com.course.dao.mapper.AdminsMapper;
import com.course.dao.po.Admins;
import com.course.dao.po.BasePo;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.service.AdminService;
import com.course.service.AdminsDto;
import com.course.service.exception.AdminsException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.PageBean;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Scope("prototype")
public class AdminServiceImpl extends BaseService implements AdminService {
    @Autowired
    private AdminsMapper adminsMapper;

    @Override
    public void addAdmin(AdminsDto adminsDto) {

        Admins admins = makeAdmins(adminsDto);

        if (1 != adminsMapper.insert(admins)) {
            throw new AdminsException("addAdmin#insert is fail !! adminsDto is :" + adminsDto);
        }
    }

    @Override
    public List<AdminsDto> getAdmins(PageBean page, AdminsQueryBean query) {

        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return adminsMapper.getAdmins(page, query);
    }

    @Override
    public Long getAdminsCount(PageBean page, AdminsQueryBean query) {
        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return adminsMapper.getAdminsCount(page, query);
    }

    private Admins makeAdmins(AdminsDto adminsDto) {
        Admins admins = new Admins();
        Integer now = DateUtil.unixTime().intValue();
        admins.setCreateTime(now);
        admins.setUpdateTime(now);
        admins.setStatus(adminsDto.getStatus());
        admins.setDescription(admins.getDescription());
        admins.setPassword(admins.getPassword());
        return admins;
    }
}
