package com.example.app.com.jaxrs.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.example.app.com.jaxrs.request.model.dsp.DspCommonResponseHeader;
import com.example.app.com.jaxrs.request.model.dsp.DspCompatibleResponse;

@Provider
@Priority(AppPriorities.PRE_WRITE.DSP_COMPATIBLE_PROCESS)
public class DspCompatibleInterceptor implements WriterInterceptor{

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		logger.info("DspCompatibleInterceptorの開始");
		Object o = context.getEntity();
		logger.log(Level.INFO, o.toString());
		if (o instanceof ErrorResponse) {
			logger.log(Level.INFO, "共通ボディの編集処理に入りました");
			ErrorResponse errorResponse = (ErrorResponse)o;
			List<ErrorDetailInfo> errorDetailInfo = errorResponse.getErrorDetailInfo();
			DspCommonResponseHeader commonResponseHeader = errorDetailInfo.stream().findFirst().map(e -> {
				DspCommonResponseHeader cr = new DspCommonResponseHeader();
				cr.setErrorCode(e.getErrorCode());
				cr.setErrorMessage(e.getErrorMessage());
				cr.setUserMap(e.getErrorInfo());
				cr.setErrorDetailInfo(errorDetailInfo);
				return cr;
			}).orElse(new DspCommonResponseHeader());
			DspCompatibleResponse res = new DspCompatibleResponse();
			res.setCommonResponseHeader(commonResponseHeader);
			context.setEntity(res);
		}else {
			context.setEntity(createNormalResponse(o));
		}
		logger.info("DspCompatibleInterceptorの終了");
		context.proceed();
	}

	private DspCompatibleResponse createNormalResponse(Object appResponse) {
		DspCompatibleResponse response = new DspCompatibleResponse();
		response.setAppResponse(appResponse);
		response.setCommonResponseHeader(new DspCommonResponseHeader());
		return response;
	}

}
