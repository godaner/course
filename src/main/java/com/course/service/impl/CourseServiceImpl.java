package com.course.service.impl;

import com.course.dao.mapper.*;
import com.course.dao.po.*;
import com.course.dao.po.custom.CoursesWithSources;
import com.course.dao.po.query.CourseSourcesQueryBean;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.service.CourseService;
import com.course.service.dto.CourseSourcesDto;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.service.exception.CoursesException;
import com.course.service.exception.UsersException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.PageBean;
import com.course.util.ProjectConfig;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class CourseServiceImpl extends BaseService implements CourseService {
    @Autowired
    private CoursesMapper coursesMapper;
    @Autowired
    private CourseSourcesMapper courseSourcesMapper;

    @Autowired
    private UserDownloadCourseMapper userDownloadCourseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserCollectCourseMapper userCollectCourseMapper;

    @Override
    public List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        if (isEmptyList(queryBean.getTagIds())) {
            queryBean.setTagIds(null);
        }
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getCoursesList(pageBean, queryBean).stream().parallel().map(
                courses -> {
                    return makeCourseDto(courses);
                }
        ).collect(toList());
    }

    @Override
    public Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        if (isEmptyList(queryBean.getTagIds())) {
            queryBean.setTagIds(null);
        }
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getCoursesCount(pageBean, queryBean);
    }

    @Override
    @Transactional
    public CoursesDto getCourseInfo(PageBean pageBean, Long courseId) {

        CourseSourcesQueryBean queryBean = new CourseSourcesQueryBean();
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        CoursesWithSources coursesWithSources = coursesMapper.getCourseWithSources(pageBean, queryBean, courseId);
        return makeCourseDto(coursesWithSources);
    }

    @Override
    public void getCourseSrc(String name, Long courseId, HttpServletResponse httpServletResponse) throws Exception {

        Courses courses = coursesMapper.select(courseId);
        if (courses == null) {
            throw new CoursesException("getCourseSrc select is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        courses.setWatchNumber(courses.getWatchNumber() + 1);

        if (coursesMapper.update(courses) <= 0) {
            throw new CoursesException("getCourseSrc update is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + name + ".mp4"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + "default.mp4"), outputStream);
        }
    }


    @Override
    @Transactional
    public void downloadCourseSrc(String name, Long courseId, UsersDto onlineUser, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Courses courses = coursesMapper.select(courseId);
        if (courses == null) {
            throw new CoursesException("getCourseSrc select is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        courses.setDownloadNumber(courses.getDownloadNumber() + 1);

        if (coursesMapper.update(courses) <= 0) {
            throw new CoursesException("getCourseSrc update is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        /**
         * validate session user
         */
        UsersDto sessionUser = (UsersDto) httpServletRequest.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        Users users = null;

        /**
         * add record of user download
         */

        users = this.getUserByUserId(sessionUser.getUserId());
        UserDownloadCourse userDownloadCourse = null;
        if (users != null) {
            userDownloadCourse = makeUserDownloadCourse(users, courses);
        }

        if (userDownloadCourseMapper.insert(userDownloadCourse) != 1) {
            httpServletRequest.setAttribute("msg", "下载失败");
            httpServletRequest.setAttribute("forward", "forward:/courses/" + courseId);
            throw new CoursesException("getCourseSrc insert userDownloadCourse is fail !! name is : " + name + " ,courseId is : " + courseId);
        }

        /**
         * response file
         */

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("video/mp4");
        httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + name + ".mp4");

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + name + ".mp4"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + "default.mp4"), outputStream);
        }
    }

    private UserDownloadCourse makeUserDownloadCourse(Users users, Courses courses) {
        Integer now = DateUtil.unixTime().intValue();
        UserDownloadCourse userDownloadCourse = new UserDownloadCourse();
        userDownloadCourse.setUpdateTime(now);
        userDownloadCourse.setCreateTime(now);
        userDownloadCourse.setCourseId(courses.getId());
        userDownloadCourse.setUserId(users.getId());
        userDownloadCourse.setStatus(BasePo.Status.NORMAL.getCode());
        return userDownloadCourse;
    }

    private CourseSources getCourseSrcByCourseSourceId(Long courseSourceId) {
        CourseSources courseSources = courseSourcesMapper.select(courseSourceId);
        return (courseSources == null || !courseSources.getStatus().equals(BasePo.Status.NORMAL.getCode())) ? null : courseSources;
    }

    @Override
    public void getCourseImg(String name, HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + name + ".png"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + "default.png"), outputStream);
        }
    }

    @Override
    public List<CoursesDto> getCoursesV2(PageBean page, CoursesQueryBean query) {
        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getCoursesV2(page, query).stream().parallel().map(courses -> {
            return makeCoursesDtoV2(courses);
        }).collect(toList());
    }

    private CoursesDto makeCoursesDtoV2(Courses courses) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(courses.getId());
        coursesDto.setDescription(courses.getDescription());
        coursesDto.setDownloadNumber(courses.getDownloadNumber());
        coursesDto.setWatchNumber(courses.getWatchNumber());
        coursesDto.setCollectNumber(courses.getCollectNumber());
        coursesDto.setName(courses.getName());
        coursesDto.setImgUrl(courses.getImgUrl());
        coursesDto.setUpdateTime(courses.getUpdateTime());
        coursesDto.setCreateTime(courses.getCreateTime());
        coursesDto.setStatus(courses.getStatus());
        return coursesDto;
    }

    @Override
    public Long getCoursesCountV2(PageBean page, CoursesQueryBean query) {
        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getUsersCountV2(page, query);
    }

    @Override
    public void updateCourses(CoursesDto coursesDto) {
        if (coursesMapper.update(makeUpdateCourses(coursesDto)) != 1) {
            throw new CoursesException("updateCourses update is fail !! coursesDto is : " + coursesDto);
        }
    }

    @Override
    public void deleteCourse(Long courseId) {
        Courses courses = this.getCourseByCourseId(courseId, Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        if (null != courses) {
            courses.setStatus(BasePo.Status.DELETED.getCode());
            if (coursesMapper.update(courses) != 1) {
                throw new CoursesException("deleteCourse update is fail !! courseId is : " + courseId);
            }
        }
    }

    @Override
    public void updateHeadFile(CoursesDto coursesDto) {
        Courses courses = this.getCourseByCourseId(coursesDto.getCourseId(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        if (null == courses) {
            throw new CoursesException("updateHeadFile course is not exits !! coursesDto is :" + coursesDto,
                    CoursesException.ErrorCode.COURSE_IS_NOT_EXITS.getCode(),
                    CoursesException.ErrorCode.COURSE_IS_NOT_EXITS.getMsg());
        }


        // update file
        if (coursesDto.getCourseImgFile() != null && coursesDto.getCourseImgFile().getSize() > 0) {
            String uuid = uuid();
            //write to db
            courses.setImgUrl(Courses.IMG_URL_PREFIX + uuid);


            if (coursesMapper.update(courses) != 1) {
                throw new CoursesException("updateHeadFile is fail !! coursesDto is :" + coursesDto,
                        CoursesException.ErrorCode.UPDATE_COURSE_FAIL.getCode(),
                        CoursesException.ErrorCode.UPDATE_COURSE_FAIL.getMsg());
            }

            //write to disk
            File targetFile = new File(ProjectConfig.COURSE_IMG_PATH + uuid + ".png");
            try {
                OutputStream outputStream = new FileOutputStream(ProjectConfig.COURSE_IMG_PATH + uuid + ".png");
                IOUtils.copy(coursesDto.getCourseImgFile().getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
                deleteFiles(targetFile);
                throw new UsersException("updateHeadFile upload img is fail !! coursesDto is :" + coursesDto);
            }


        }
    }

    @Override
    public void addCourse(CoursesDto coursesDto) {

        if (isEmptyString(coursesDto.getName())) {
            throw new CoursesException("addCourse name is null !! coursesDto is :" + coursesDto,
                    CoursesException.ErrorCode.COURSE_NAME_IS_NULL.getCode(),
                    CoursesException.ErrorCode.COURSE_NAME_IS_NULL.getMsg());
        }

        Courses courses = makeAddCourse(coursesDto);
        if (1 != coursesMapper.insert(courses)) {
            throw new UsersException("addCourse#insert is fail !! coursesDto is :" + coursesDto);
        }
    }

    private Courses makeAddCourse(CoursesDto coursesDto) {
        Courses courses = new Courses();
        Integer now = DateUtil.unixTime().intValue();
        courses.setStatus(coursesDto.getStatus());
        courses.setDescription(coursesDto.getDescription());
        courses.setName(coursesDto.getName());
        courses.setUpdateTime(now);
        courses.setCreateTime(now);
        courses.setDownloadNumber(Courses.EMPTY_DOWNLOAD_NUMBER);
        courses.setCollectNumber(Courses.EMPTY_COLLECT_NUMBER);
        courses.setWatchNumber(Courses.EMPTY_WATCH_NUMBER);
        courses.setImgUrl(Courses.IMG_URL_PREFIX + uuid());
        return courses;
    }

    private Courses makeUpdateCourses(CoursesDto coursesDto) {
        Courses courses = new Courses();
        courses.setStatus(coursesDto.getStatus());
        courses.setDescription(coursesDto.getDescription());
        courses.setName(coursesDto.getName());
        courses.setId(coursesDto.getCourseId());
        courses.setUpdateTime(DateUtil.unixTime().intValue());
        return courses;
    }

    private CoursesDto makeCourseDto(CoursesWithSources coursesWithSources) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(coursesWithSources.getId());
        coursesDto.setDescription(coursesWithSources.getDescription());
        coursesDto.setDownloadNumber(coursesWithSources.getDownloadNumber());
        coursesDto.setWatchNumber(coursesWithSources.getWatchNumber());
        coursesDto.setCollectNumber(coursesWithSources.getCollectNumber());
        coursesDto.setName(coursesWithSources.getName());
        coursesDto.setImgUrl(coursesWithSources.getImgUrl());
        List<CourseSourcesDto> courseSourcesDtos = coursesWithSources.getCourseSourcesList().stream().parallel().map(courseSources -> {

            CourseSourcesDto courseSourcesDto = new CourseSourcesDto();
            courseSourcesDto.setCourseSourceDescription(courseSources.getDescription());
            courseSourcesDto.setCourseSourceId(courseSources.getId());
            courseSourcesDto.setCourseSourceName(courseSources.getName());
            courseSourcesDto.setCourseSourceSort(courseSources.getSort());
            courseSourcesDto.setCourseWatchSourceUrl(courseSources.getWatchUrl());
            courseSourcesDto.setCourseDownloadSourceUrl(courseSources.getDownloadUrl());

            return courseSourcesDto;
        }).collect(toList());
        coursesDto.setCourseSourcesDtoList(courseSourcesDtos);
        return coursesDto;
    }

    private CoursesDto makeCourseDto(Courses courses) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(courses.getId());
        coursesDto.setDescription(courses.getDescription());
        coursesDto.setDownloadNumber(courses.getDownloadNumber());
        coursesDto.setWatchNumber(courses.getWatchNumber());
        coursesDto.setCollectNumber(courses.getCollectNumber());
        coursesDto.setName(courses.getName());
        coursesDto.setImgUrl(courses.getImgUrl());
        return coursesDto;
    }

    private Courses getCourseByCourseId(Long courseId, List<Byte> status) {
        CoursesQueryBean query = new CoursesQueryBean();
        query.setStatus(status);
        query.setCourseId(courseId);
        return coursesMapper.getCourseByCourseId(query);

    }


    private Users getUserByUserId(Long userId) {
        Users users = usersMapper.select(userId);
        if (users == null || !users.getStatus().equals(BasePo.Status.NORMAL.getCode())) {
            users = null;
        }
        return users;
    }
}
