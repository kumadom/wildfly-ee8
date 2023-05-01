package com.example.app.com.jaxrs.container;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.core.model.CommonHeader;
import com.example.app.com.jaxrs.constants.AppPriorities;

@Provider
@Priority(AppPriorities.FILTER.COMMON_HEADER_PROCESS)
public class CommonHeaderFilter  implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	// @Inject private ValidatorFactory validatorfactory;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("CommonHeaderFilterのリクエスト処理開始");
		logger.info("ヘッダー情報");
		requestContext.getHeaders().forEach((k,v) -> System.out.println(k + v));
		logger.info("メソッド");
		System.out.println(requestContext.getMethod());
		CommonHeader headers = createHeader(requestContext.getHeaders());
		
		
		//Set<ConstraintViolation<CommonHeader>> errors = validatorfactory.getValidator().validate(headers);
		//if (!errors.isEmpty()) throw new ConstraintViolationException(errors);
		logger.info("CommonHeaderFilterのリクエスト処理終了");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.info("CommonHeaderFilterのレスポンス処理開始");
		logger.info("CommonHeaderFilterのレスポンス処理終了");
	}

	
	private CommonHeader createHeader(MultivaluedMap<String, String> requestHeaders) {
		CommonHeader header = new CommonHeader();
		header.setTraceId(requestHeaders.getFirst("traceID"));
		System.out.println("--------------------------");
		System.out.println(requestHeaders.getFirst("traceID"));
		System.out.println("--------------------------");
		return header;
	}
}
