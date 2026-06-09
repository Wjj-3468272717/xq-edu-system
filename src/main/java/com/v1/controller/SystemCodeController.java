package com.v1.controller;

import com.v1.utils.CheckCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
public class SystemCodeController {

    @RequestMapping("createSystemCode")
    public void createSystemCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
       /* int width = 120;
        int height = 50;
        //创建一个画布对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        //获取随机生成的4位数字和字母组成的字符串
        String code = getCheckCode();
        req.getSession().setAttribute("systemCode", code);
        g.setColor(Color.BLUE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));
        g.drawString(code, 15, 25);
        ImageIO.write(bi, "jpg", res.getOutputStream());*/
        ServletOutputStream outputStream = res.getOutputStream();
        String syscode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        req.getSession().setAttribute("systemCode",syscode);
    }

    public String getCheckCode() {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int index = str.length() - 1;//获取最大索引值
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int random = (int) Math.floor(0 + Math.random() * (index + 1));
            stringBuffer.append(str.charAt(random));
        }
        return stringBuffer.toString();
    }

}
