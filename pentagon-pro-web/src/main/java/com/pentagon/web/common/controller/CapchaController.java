package com.pentagon.web.common.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gandalf.framework.util.CaptchaUtil;
import com.pentagon.web.tool.SessionStore;

@Controller
public class CapchaController {
	
	@RequestMapping(value = "/captcha.jpgx", method = RequestMethod.GET)
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String captchaCode = CaptchaUtil.generateVerifyCode(4);
        SessionStore.setLoginCaptcha(captchaCode);
        ServletOutputStream out = response.getOutputStream();
        CaptchaUtil.outputImage(100, 38, out, captchaCode);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
	
}
