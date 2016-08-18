package com.wly.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.os.Handler;

public class HttpUtils {
	public static String get(String path, Handler handler){
		try {
			if(path!=null){
				URL url=new URL(path);
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(1500);
				conn.setRequestMethod("GET");
				int code=conn.getResponseCode();
				if(code==200){
					InputStream is=conn.getInputStream();
					return StreamTools.readInputStream(is);
				}
			}
		} catch (MalformedURLException e) {
			handler.obtainMessage(AsyncHttpClient.NET_ERROR, "地址没有发现").sendToTarget();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			handler.obtainMessage(AsyncHttpClient.NET_ERROR, "网络异常").sendToTarget();
		}
		return null;
	}
}
