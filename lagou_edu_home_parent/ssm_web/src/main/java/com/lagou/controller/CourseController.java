package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Test;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Test test(){
        Test test = new Test();
        test.setId(1);
        test.setName("测试");
        return test;
    }

    //课程图片上传
    @RequestMapping("/courseUpload")
    @ResponseBody
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目的部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = req.getServletContext().getRealPath("/");
        //D:\apache-tomcat-8.5.56\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName = System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //目录不存在就创造目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录成功...." + filePath);
        }

        //图片就进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        //7.返回
        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;

    }

    /**
     * 添加课程信息及讲师信息
     * 新增和修改要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    @ResponseBody
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        if (courseVo.getId() == null){

            //调用service
            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        } else {

            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }
    }


    /**
     * 课程信息及关联的讲师信息的回显
     */
    @RequestMapping("/findCourseById")
    @ResponseBody
    //get请求不用添加@RequestBody注解
    public ResponseResult findCourseById(@RequestParam Integer id){
        CourseVo courseVo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", courseVo);
        return result;
    }

    /**
     * 课程状态管理
     */
    @ResponseBody
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){

        courseService.updateCourseStatus(id, status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"响应成功",map);
    }

}
