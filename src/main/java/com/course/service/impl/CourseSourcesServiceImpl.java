package com.course.service.impl;

import com.course.dao.mapper.CourseSourcesMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.CourseSources;
import com.course.service.CourseSourcesService;
import com.course.service.dto.CourseSourcesDto;
import com.course.service.exception.CourseSourcesException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.ProjectConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
@Scope("prototype")
public class CourseSourcesServiceImpl extends BaseService implements CourseSourcesService {
    @Autowired
    private CourseSourcesMapper courseSourcesMapper;

    @Override
    @Autowired
    public void addCourseSource(CourseSourcesDto courseSourcesDto) {
        String name = uuid();
        if (1 != courseSourcesMapper.insert(makeCourseSources(courseSourcesDto, name))) {
            throw new CourseSourcesException("addCourseSource#insert is fail ! courseSourcesDto is :　" + courseSourcesDto,
                    CourseSourcesException.ErrorCode.INSERT_COURSE_SOURCE_FAIL.getCode(),
                    CourseSourcesException.ErrorCode.INSERT_COURSE_SOURCE_FAIL.getMsg());
        }

        File targetFile = new File(ProjectConfig.COURSE_SRC_PATH + name + ".mp4");
        try {
            IOUtils.copy(courseSourcesDto.getSourceFile().getInputStream(), new FileOutputStream(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
            deleteFile(targetFile);
            throw new CourseSourcesException("addCourseSource write file is fail ! courseSourcesDto is :　" + courseSourcesDto,
                    CourseSourcesException.ErrorCode.WRITE_COURSE_SOURCE_FAIL.getCode(),
                    CourseSourcesException.ErrorCode.WRITE_COURSE_SOURCE_FAIL.getMsg());
        }
    }

    private CourseSources makeCourseSources(CourseSourcesDto courseSourcesDto, String name) {
        Integer now = DateUtil.unixTime().intValue();
        CourseSources courseSources = new CourseSources();
        courseSources.setDescription(courseSourcesDto.getCourseSourceDescription());
        courseSources.setDownloadUrl(CourseSources.DOWNLOAD_URL_PREFIX + name);
        courseSources.setWatchUrl(CourseSources.WATCH_URL_PREFIX + name);
        courseSources.setName(courseSourcesDto.getCourseSourceName());
        courseSources.setSort(CourseSources.DEFAULT_SORT);
        courseSources.setCreateTime(now);
        courseSources.setUpdateTime(now);
        courseSources.setStatus(BasePo.Status.NORMAL.getCode());
        return courseSources;
    }
}
