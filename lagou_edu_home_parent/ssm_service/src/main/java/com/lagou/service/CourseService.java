package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 添加课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 课程信息的回显（关联的讲师信息）
     */
    public CourseVo findCourseById(int id);

    /**
     * 修改课程信息及讲师信息
     */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 课程状态管理
     */
    public void updateCourseStatus(int id,int status);

}
