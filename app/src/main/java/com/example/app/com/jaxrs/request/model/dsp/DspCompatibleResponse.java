package com.example.app.com.jaxrs.request.model.dsp;

/**
 * TODO: 仮実装。
 * 業務エラー発生時の応答ボディ？？？
 * 正常応答時にどうなるのかわからないので、とりあえずエラー時の応答ボディとして実装。
 */
public class DspCompatibleResponse {
	private CommonResponseHeader commonResponseHeader;

	public CommonResponseHeader getCommonResponseHeader() {
		return commonResponseHeader;
	}

	public void setCommonResponseHeader(CommonResponseHeader commonResponseHeader) {
		this.commonResponseHeader = commonResponseHeader;
	}
}
