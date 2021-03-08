package com.mohen.controller;

import com.alibaba.excel.EasyExcel;
import com.mohen.service.UserService;
import com.mohen.util.ExcelUtils;
import com.mohen.domain.User;
import com.mohen.listener.UserDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author MH19
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 在application.yml中配置
     */
    @Value("${excel.path}")
    private String path;

    @Autowired
    private UserService userService;

    @RequestMapping("/toUserListPage")
    public String toUserListPage() {
        return "user/userList";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAll(User user) {
        return userService.getAll();
    }

    /**
     * easyexcel导出Excel到指定位置
     * @return
     */
    @GetMapping("/export2File")
    @ResponseBody
    public String export2File() {
        ExcelUtils.export2File(path, "用户表", "用户信息", User.class, userService.getAll());
        return "导出成功";
    }

    /**
     * easyexcel导出Excel到web
     * @param response
     */
    @GetMapping("/export2Web")
    public void export2Web(HttpServletResponse response) {
        try {
            ExcelUtils.export2Web(response, "用户表", "用户信息", User.class, userService.getAll());
        } catch (Exception e) {
            log.error("报表导出异常:", e);
        }
    }

    /**
     * 将指定位置指定名称的Excel导出到web
     * @param response
     * @param excelName
     * @return
     */
    @GetMapping("/export2Web4File/{excelName}")
    @ResponseBody
    public String export2Web4File(HttpServletResponse response, @PathVariable String excelName) {
        try {
            return ExcelUtils.export2Web4File(response, path, excelName);
        } catch (Exception e) {
            log.error("文件导出异常：", e);
        }

        return "文件导出失败";
    }

    /**
     * easyexcel读取文件
     * @return
     */
    @GetMapping("/read4File")
    @ResponseBody
    public String read4File() {
        String fileName = path + "用户表导入.xlsx";
        EasyExcel.read(fileName, User.class, new UserDataListener(userService)).sheet().doRead();
        return "读取成功";
    }

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "user/upload";
    }

    /**
     * easyexcel上传文件
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new UserDataListener(userService)).sheet().doRead();
        return "上传成功";
    }

}
