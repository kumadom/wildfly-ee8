package com.example.app.com.jaxrs.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.example.app.com.jaxrs.request.model.dsp.CommonResponseHeader;
import com.example.app.com.jaxrs.request.model.dsp.DspCompatibleResponse;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class DspCompatibleInterceptor implements WriterInterceptor{

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		Object o = context.getEntity();
		logger.log(Level.INFO, o.toString());
		if (o instanceof ErrorResponse) {
			logger.log(Level.INFO, "共通ボディの編集処理に入りました");
			ErrorResponse errorResponse = (ErrorResponse)o;
			List<ErrorDetailInfo> errorDetailInfo = errorResponse.getErrorDetailInfo();
			CommonResponseHeader commonResponseHeader = errorDetailInfo.stream().findFirst().map(e -> {
				CommonResponseHeader cr = new CommonResponseHeader();
				cr.setErrorCode(e.getErrorCode());
				cr.setErrorMessage(e.getErrorMessage());
				cr.setUserMap(e.getErrorInfo());
				cr.setErrorDetailInfo(errorDetailInfo);
				return cr;
			}).orElse(new CommonResponseHeader());
			DspCompatibleResponse res = new DspCompatibleResponse();
			res.setCommonResponseHeader(commonResponseHeader);
			context.setEntity(res);
		}
		context.proceed();
	}

}
