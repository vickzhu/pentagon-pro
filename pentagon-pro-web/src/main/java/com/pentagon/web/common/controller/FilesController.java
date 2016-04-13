package com.pentagon.web.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/files")
public class FilesController {

    /**
     * 展示图片
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ModelAndView images(HttpServletRequest request) {
        return new ModelAndView("files/imageList");
    }

    /**
     * 添加图片
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/image/add", method = RequestMethod.GET)
    public ModelAndView addImage(HttpServletRequest request) {
        return new ModelAndView("files/imageAdd");
    }

    /**
     * 添加图片
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/image/add", method = RequestMethod.POST)
    public ModelAndView doAddImage(HttpServletRequest request) {

        return new ModelAndView("redirect:/image/detail");
    }

    @RequestMapping(value = "/image/detail")
    public ModelAndView imageDetail(HttpServletRequest request) {
        return new ModelAndView("files/imageDetail");
    }

    /**
     * 上传图片
     * 
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, MultipartFile imgFile) {

    }

}
