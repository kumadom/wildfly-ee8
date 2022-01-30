package com.example.app.com.servlet.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class HttpServletDumpWrapper extends HttpServletRequestWrapper {

	private byte[] buffer;

	public HttpServletDumpWrapper(HttpServletRequest request) throws IOException {
        super(request);

        // Request BodyからStreamを取得
        InputStream is = request.getInputStream();

        // Streamをbyte配列に変換し、インスタンス変数に保持
        this.buffer = IOUtils.toByteArray(is);
    }

    // Bodyの取得元をこのメソッドに差替え
    @Override
    public ServletInputStream getInputStream() throws IOException {
        // Streamクラスを初期化して返却
        return new BufferedServletInputStream(this.buffer);
    }

}
