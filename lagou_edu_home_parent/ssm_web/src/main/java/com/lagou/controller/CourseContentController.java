package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/courseContent")
@RestController
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "章节及课时内容查询成功", list);

        return result;

    }

    /**
     * 回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }


    /**
     * 新疆章节信息or修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        //判断是否携带章节id
        if (courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        } else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"修改章节成功",null);
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        courseContentService.updateSectionStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"状态修改成功",map);
    }

    /**
     * 新增$修改课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){

        //判断是否携带id
        if (courseLesson.getId() == null){
            //为null就是新增
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"新增课时成功",null);
        } else {
            //不为null就是修改
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"修改课时成功",null);
        }
    }

}