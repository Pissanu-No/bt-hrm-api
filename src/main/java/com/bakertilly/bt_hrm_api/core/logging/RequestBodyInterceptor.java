package com.bakertilly.bt_hrm_api.core.logging;

import com.bakertilly.bt_hrm_api.core.service.LoggingService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@Hidden
@RestControllerAdvice
public class RequestBodyInterceptor extends RequestBodyAdviceAdapter {
    private final LoggingService logService;
    private final HttpServletRequest request;

    @Autowired
    public RequestBodyInterceptor(LoggingService logService,
                                  HttpServletRequest request){
        this.logService = logService;
        this.request = request;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        logService.displayReq(request,body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
