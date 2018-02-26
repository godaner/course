package com.course.service.impl;

import com.course.dao.mapper.AdminsMapper;
import com.course.dao.po.Admins;
import com.course.dao.po.BasePo;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.service.AdminService;
import com.course.service.dto.AdminsDto;
import com.course.service.exception.AdminsException;
import com.course.service.exception.CoursesException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.PageBean;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        admins = makeAddAdmins(adminsDto);

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
            return makeListAdminsDto(admins);
        }).collect(toList());
    }

    private AdminsDto makeListAdminsDto(Admins admins) {
        AdminsDto adminsDto = new AdminsDto();
        adminsDto.setAdminId(admins.getId());
        adminsDto.setCreateTime(admins.getCreateTime());
        adminsDto.setDescription(admins.getDescription());
        adminsDto.setPassword(admins.getPassword());
        adminsDto.setStatus(admins.getStatus());
        adminsDto.setUsername(admins.getUsername());
        return adminsDto;
    }

    @Override
    public Long getAdminsCount(PageBean page, AdminsQueryBean query) {
        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return adminsMapper.getAdminsCount(page, query);
    }

    @Override
    public void updateAdmin(AdminsDto adminsDto) {
        if (isEmptyString(adminsDto.getPassword())) {
            throw new AdminsException("updateAdmin password is null !! adminsDto is :" + adminsDto,
                    AdminsException.ErrorCode.PASSWORD_IS_NULL.getCode(),
                    AdminsException.ErrorCode.PASSWORD_IS_NULL.getMsg());
        }
        if (null == this.getAdminByUserId(adminsDto.getAdminId(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()))) {
            throw new AdminsException("updateAdmin admin is not exits !! adminsDto is :" + adminsDto,
                    AdminsException.ErrorCode.USER_IS_NOT_EXITS.getCode(),
                    AdminsException.ErrorCode.USER_IS_NOT_EXITS.getMsg());
        }

        if (adminsMapper.update(makeUpdateAdmin(adminsDto)) != 1) {
            throw new AdminsException("updateAdmin is fail !! adminsDto is :" + adminsDto,
                    AdminsException.ErrorCode.UPDATE_USER_FAIL.getCode(),
                    AdminsException.ErrorCode.UPDATE_USER_FAIL.getMsg());
        }
    }

    @Override
    public void deleteAdmin(Long adminId) {
        Admins admins = this.getAdminByUserId(adminId, Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        if (null != admins) {
            admins.setStatus(BasePo.Status.DELETED.getCode());
            if (adminsMapper.update(admins) != 1) {
                throw new CoursesException("deleteAdmin update is fail !! adminId is : " + adminId);
            }
        }
    }

    @Override
    public AdminsDto getAdminByAdminId(Long adminId) {

        return makeListAdminsDto(this.getAdminByUserId(adminId, Lists.newArrayList(BasePo.Status.NORMAL.getCode())));
    }

    @Override
    public void login(AdminsDto adminsDto) {
        Admins admins = this.getAdminByUsername(adminsDto.getUsername(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        if (null == admins) {
            throw new CoursesException("login admin is not exits !! adminsDto is : " + adminsDto,
                    AdminsException.ErrorCode.USER_IS_NOT_EXITS.getCode(),
                    AdminsException.ErrorCode.USER_IS_NOT_EXITS.getMsg());
        }
        if(!admins.getPassword().equals(adminsDto.getPassword())){
            throw new CoursesException("login password is error !! adminsDto is : " + adminsDto,
                    AdminsException.ErrorCode.PASSWORD_IS_ERROR.getCode(),
                    AdminsException.ErrorCode.PASSWORD_IS_ERROR.getMsg());
        }
    }

    private Admins makeUpdateAdmin(AdminsDto adminsDto) {
        Admins admins = new Admins();
        Integer now = DateUtil.unixTime().intValue();
        admins.setUpdateTime(now);
        admins.setDescription(adminsDto.getDescription());
        admins.setStatus(adminsDto.getStatus());
        admins.setId(adminsDto.getAdminId());
        admins.setUpdateTime(DateUtil.unixTime().intValue());
        admins.setPassword(adminsDto.getPassword());
        return admins;
    }

    private Admins getAdminByUserId(Long adminId, ArrayList<Byte> status) {
        AdminsQueryBean query = new AdminsQueryBean();
        query.setStatus(status);
        query.setAdminId(adminId);
        return adminsMapper.getAdminByUserId(query);
    }

    private Admins makeAddAdmins(AdminsDto adminsDto) {
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
