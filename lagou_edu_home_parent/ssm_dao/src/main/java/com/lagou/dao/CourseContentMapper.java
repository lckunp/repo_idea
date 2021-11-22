package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 根据课程id查询关联的章节信息及课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节对应的课程信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 保存章节信息的方法
     */
    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(CourseSection courseSection);

    /**
     * 新建课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);
}
