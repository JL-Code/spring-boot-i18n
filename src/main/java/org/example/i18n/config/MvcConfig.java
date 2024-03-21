package org.example.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * <p>创建时间: 2024/3/21 </p>
 *
 * @author <a href="mailto:jiangliu0316@dingtalk.com" rel="nofollow">蒋勇</a>
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 地区解析器，用于解析用户所在地区
     * LocaleResolver 接口具有根据 Session、Cookie、Accept-Language 标头或固定值确定当前区域设置的实现
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 设置默认地区为 US
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * 地区改变拦截器
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}