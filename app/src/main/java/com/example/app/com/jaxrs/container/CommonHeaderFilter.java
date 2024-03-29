package com.example.app.com.jaxrs.container;

import java.io.IOException;
import java.util.logging.Logger;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.core.model.CommonHeader;
import com.example.app.com.jaxrs.constants.AppPriorities;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(AppPriorities.FILTER.COMMON_HEADER_PROCESS)
public class CommonHeaderFilter  implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	// @Inject private ValidatorFactory validatorfactory;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.fine("CommonHeaderFilterのリクエスト処理開始");
		logger.fine("ヘッダー情報");
		logger.fine("メソッド");
		CommonHeader headers = createHeader(requestContext.getHeaders());
		
		
		//Set<ConstraintViolation<CommonHeader>> errors = validatorfactory.getValidator().validate(headers);
		//if (!errors.isEmpty()) throw new ConstraintViolationException(errors);
		logger.fine("CommonHeaderFilterのリクエスト処理終了");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.fine("CommonHeaderFilterのレスポンス処理開始");
		logger.fine("CommonHeaderFilterのレスポンス処理終了");
	}

	
	private CommonHeader createHeader(MultivaluedMap<String, String> requestHeaders) {
		CommonHeader header = new CommonHeader();
		header.setTraceId(requestHeaders.getFirst("traceID"));
		return header;
	}
}
