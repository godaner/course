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
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;


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

    @Override
    public void deleteCourseSource(String courseSourcesIds) {
        if (isEmptyString(courseSourcesIds)) {
            throw new CourseSourcesException("deleteCourseSource courseSourcesIds is null ! courseSourcesIds is :　" + courseSourcesIds,
                    CourseSourcesException.ErrorCode.DELETE_IDS_IS_NULL.getCode(),
                    CourseSourcesException.ErrorCode.DELETE_IDS_IS_NULL.getMsg());
        }
        List<Long> courseSourcesIdsList = Lists.newArrayList(courseSourcesIds.split(",")).stream().parallel().map(courseSourcesId -> {
            return Long.valueOf(courseSourcesId);
        }).collect(toList());

        if (0 < courseSourcesMapper.updateCourseSourcesStatusByIds(courseSourcesIdsList, BasePo.Status.DELETED.getCode())) {
            throw new CourseSourcesException("deleteCourseSource update course source is fail ! courseSourcesIds is :　" + courseSourcesIds,
                    CourseSourcesException.ErrorCode.UPDATE_COURSE_SOURCE_FAIL.getCode(),
                    CourseSourcesException.ErrorCode.UPDATE_COURSE_SOURCE_FAIL.getMsg());
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
