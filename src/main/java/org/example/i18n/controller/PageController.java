package org.example.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>创建时间: 2024/3/21 </p>
 *
 * @author <a href="mailto:jiangliu0316@dingtalk.com" rel="nofollow">蒋勇</a>
 */
@Controller
public class PageController {
    @GetMapping("/international")
    public String getInternationalPage() {
        return "thymeleaf/international";
    }

}
