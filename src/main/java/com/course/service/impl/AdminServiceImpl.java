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

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class AdminServiceImpl extends BaseService implements AdminService {
    @Autowired
    private AdminsMapper adminsMapper;

    @Override
    public void addAdmin(AdminsDto adminsDto) {
        if (isEmptyString(adminsDto.getPassword())) {
            throw new AdminsException("addAdmin password is null !! adminsDto is :" + adminsDto,
                    AdminsException.ErrorCode.PASSWORD_IS_NULL.getCode(),
                    AdminsException.ErrorCode.PASSWORD_IS_NULL.getMsg());
        }


        Admins admins = this.getAdminByUsername(adminsDto.getUsername(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        if (null != admins) {
            throw new AdminsException("addAdmin username is not exits !! adminsDto is :" + adminsDto,
                    AdminsException.ErrorCode.USERNAME_IS_EXITS.getCode(),
                    AdminsException.ErrorCode.USERNAME_IS_EXITS.getMsg());
        }
        admins = makeAdmins(adminsDto);

        if (1 != adminsMapper.insert(admins)) {
            throw new AdminsException("addAdmin#insert is fail !! adminsDto is :" + adminsDto);
        }
    }

    private Admins getAdminByUsername(String username, List<Byte> status) {
        AdminsQueryBean query = new AdminsQueryBean();
        query.setStatus(status);
        query.setUsername(username);
        return adminsMapper.getAdminByUsername(query);
    }

    @Override
    public List<AdminsDto> getAdmins(PageBean page, AdminsQueryBean query) {

        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return adminsMapper.getAdmins(page, query).stream().parallel().map(admins -> {
            AdminsDto adminsDto = new AdminsDto();
            adminsDto.setAdminId(admins.getId());
            adminsDto.setCreateTime(admins.getCreateTime());
            adminsDto.setDescription(admins.getDescription());
            adminsDto.setPassword(Admins.EMPTY_PWD);
            adminsDto.setStatus(admins.getStatus());
            adminsDto.setUsername(admins.getUsername());
            return adminsDto;
        }).collect(toList());
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
        admins.setDescription(adminsDto.getDescription());
        admins.setPassword(adminsDto.getPassword());
        admins.setUsername(adminsDto.getUsername());
        return admins;
    }
}
