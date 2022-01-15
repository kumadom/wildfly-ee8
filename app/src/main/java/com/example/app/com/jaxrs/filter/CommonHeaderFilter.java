package com.example.app.com.jaxrs.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.core.model.CommonHeader;
import com.example.app.com.jaxrs.constants.AppPriorities;

@Provider
@Priority(AppPriorities.FILTER.COMMON_HEADER_PROCESS)
public class CommonHeaderFilter  implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Inject private Validator validator;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("CommonHeaderFilterのリクエスト処理開始");
		CommonHeader headers = createHeader(requestContext.getHeaders());
		//Set<ConstraintViolation<CommonHeader>> errors = validator.validate(headers);
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
		header.setTraceId(requestHeaders.getFirst("traceId"));
		return header;
	}
}
