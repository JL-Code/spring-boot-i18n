package org.example.i18n.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * <p>创建时间: 2024/3/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@dingtalk.com" rel="nofollow">蒋勇</a>
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String hello(HttpServletRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        Locale browserBasedLocale = request.getLocale();

        System.out.printf("LocaleContextHolder locale: %s \n", locale);
        System.out.printf("BrowserBasedLocale : %s", browserBasedLocale);

        return messageSource.getMessage("greeting", null, locale);
    }

}
