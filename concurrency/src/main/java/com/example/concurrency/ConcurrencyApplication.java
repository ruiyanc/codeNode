package com.example.concurrency;

import com.example.concurrency.example.config.HttpFilter;
import com.example.concurrency.example.config.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xurui
 */
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    /**
     * 多线程测试所用
     * @return
     */
    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
