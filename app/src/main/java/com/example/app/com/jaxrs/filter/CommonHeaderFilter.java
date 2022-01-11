package com.example.app.com.jaxrs.filter;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;

@Provider
@Priority(Priorities.HEADER_DECORATOR + 100)
public class CommonHeaderFilter  implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Inject private Validator validator;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("SampleFilterのリクエスト開始");
		CommonHeader headers = createHeader(requestContext.getHeaders());
		Set<ConstraintViolation<CommonHeader>> errors = validator.validate(headers);

		if (!errors.isEmpty()) throw new ConstraintViolationException(errors);
		// validator.validateValue(String.class, null, requestContext, null)
//		logger.info(he.getHeaderString("Accept"));
//		MultivaluedMap<String, String> headers =  requestContext.getHeaders();
// 		logger.info("キー値");
//		logger.info("バリュー値");
//		if (headers.get("hoge") == null) logger.info("Nullでした！"); 
//		headers.keySet().stream().map(k -> (List<String>)headers.get(k)).forEach(v -> v.forEach(n -> logger.info(n)));
		logger.info("SampleFilterのリクエスト終了");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		logger.info("SampleFilterのレスポンス開始");
	}

	
	private CommonHeader createHeader(MultivaluedMap<String, String> requestHeaders) {
		CommonHeader header = new CommonHeader();
		header.setTraceId(requestHeaders.getFirst("traceId"));
		logger.info(requestHeaders.toString());
		logger.info(requestHeaders.getClass().getName());
		return header;
	}
}
