package com.yomo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/23
 */
@Controller
public class PageController {
    
    @GetMapping("/main")
    public String getHome(){
        return "redirect:/pages/main.html";
    }
    
}
