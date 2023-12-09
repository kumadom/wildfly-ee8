package com.example.app.com.jaxrs.ext;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.app.com.core.log.LoggerName;
import com.example.app.com.core.log.LoggerNameValue;
import com.example.app.com.jaxrs.constants.AppPriorities;
import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;
import com.example.app.com.jaxrs.request.model.ErrorResponse;
import com.example.app.com.jaxrs.request.model.dsp.DspCommonResponseHeader;
import com.example.app.com.jaxrs.request.model.dsp.DspCompatibleResponse;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;

@Provider
@Priority(AppPriorities.PRE_WRITE.DSP_COMPATIBLE_PROCESS)
public class DspCompatibleInterceptor implements WriterInterceptor{

	@Inject @LoggerName(LoggerNameValue.SYSTEM) private Logger logger;
	
	@Context Providers providers;
	
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
//		MessageBodyWriter<?> pro = providers.getMessageBodyWriter(context.getEntity().getClass(), null, null, context.getMediaType());
//		Class<?>clazz = pro.getClass();
//		System.out.println(clazz);
//		Type type = clazz.getGenericInterfaces()[0];
//		System.out.println("Typeのクラス");
//		System.out.println(type);
//		System.out.println(((ParameterizedType)type).getActualTypeArguments()[0]);
//		System.out.println(pro.getClass().toString());
		
		MultivaluedMap<String, Object> headers = context.getHeaders();
		logger.fine("DspCompatibleInterceptorの開始");
		logger.fine("ヘッダー出力開始");
		headers.forEach((k, v) -> {
			logger.fine("ヘッダー名" + k);
			logger.fine("ヘッダー値" + v.toString());
		});
		logger.fine("ヘッダー出力終了");
		Object o = context.getEntity();
		logger.log(Level.INFO, o.toString());
		if (MediaType.APPLICATION_JSON_TYPE.equals(context.getMediaType())) {
			if (o instanceof ErrorResponse) {
				logger.log(Level.FINE, "共通ボディの編集処理に入りました");
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

		}
		logger.fine("DspCompatibleInterceptorの終了");
		context.proceed();
	}

	private DspCompatibleResponse createNormalResponse(Object appResponse) {
		DspCompatibleResponse response = new DspCompatibleResponse();
		response.setAppResponse(appResponse);
		response.setCommonResponseHeader(new DspCommonResponseHeader());
		return response;
	}

}
