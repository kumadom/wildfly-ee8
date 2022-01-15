package com.example.app.com.jaxrs.request.model.dsp;

/**
 * DSP互換性レスポンス情報
 */
public class DspCompatibleResponse {
	private DspCommonResponseHeader commonResponseHeader;
	private Object appResponse;
	public DspCommonResponseHeader getCommonResponseHeader() {
		return commonResponseHeader;
	}
	public void setCommonResponseHeader(DspCommonResponseHeader commonResponseHeader) {
		this.commonResponseHeader = commonResponseHeader;
	}
	public Object getAppResponse() {
		return appResponse;
	}
	public void setAppResponse(Object appResponse) {
		this.appResponse = appResponse;
	}

}
