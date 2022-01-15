package com.example.app.com.jaxrs.request.model.dsp;

import java.util.List;

import com.example.app.com.jaxrs.request.model.ErrorDetailInfo;

/**
 * DSP互換用の業務エラー発生時の応答ボディ
 */
public class DspCommonResponseHeader {
	private String errorCode;
	private String errorMessage;
	private String userString;
	private List<String> userMap;
	private List<ErrorDetailInfo> errorDetailInfo;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getUserString() {
		return userString;
	}
	public void setUserString(String userString) {
		this.userString = userString;
	}
	public List<String> getUserMap() {
		return userMap;
	}
	public void setUserMap(List<String> userMap) {
		this.userMap = userMap;
	}
	public List<ErrorDetailInfo> getErrorDetailInfo() {
		return errorDetailInfo;
	}
	public void setErrorDetailInfo(List<ErrorDetailInfo> errorDetailInfo) {
		this.errorDetailInfo = errorDetailInfo;
	}
	
}
