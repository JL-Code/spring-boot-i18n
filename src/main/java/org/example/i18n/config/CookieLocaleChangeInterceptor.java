package org.example.i18n.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import java.util.Locale;

/**
 * 从 Cookie 中获取 Locale，当 Locale 改变时更新 @see CookieLocaleResolver</p>
 * <p>创建时间: 2024/3/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@dingtalk.com" rel="nofollow">蒋勇</a>
 */
public class CookieLocaleChangeInterceptor implements HandlerInterceptor {
    protected final Log logger = LogFactory.getLog(getClass());
    private final String cookieName;

    public CookieLocaleChangeInterceptor(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieName() {
        return cookieName;
    }

    /**
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // Retrieve and parse cookie value.
        Cookie cookie = WebUtils.getCookie(request, getCookieName());
        if (cookie != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException(
                        "No LocaleResolver found: not in a DispatcherServlet request?");
            }
            try {
                localeResolver.setLocale(request, response, parseLocaleValue(cookie));
            } catch (IllegalArgumentException ex) {
                if (isIgnoreInvalidLocale()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Ignoring invalid locale value [" + cookie.getValue() + "]: " + ex.getMessage());
                    }
                } else {
                    throw ex;
                }
            }
        }
        return true;
    }

    public boolean isIgnoreInvalidLocale() {
        return false;
    }

    @Nullable
    protected Locale parseLocaleValue(Cookie cookie) {
        String localeValue = cookie.getValue();
        return StringUtils.parseLocale(localeValue);
    }
}
