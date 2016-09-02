package com.wly.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;
/**
 * @date 2019/9/2
 * @author wangly
 *
 */
public class HttpUtils {
	public static String get(String path, Handler handler) {
		try {
			if (path != null && !"".equals(path)) {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(1500);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				if (code == 200) {
					InputStream is = conn.getInputStream();
					return StreamTools.readInputStream(is);
				}
			}
		} catch (MalformedURLException e) {
			handler.obtainMessage(Config.NET_ERROR, Config.ADDRESS_NOT_FOND)
			.sendToTarget();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			handler.obtainMessage(Config.NET_ERROR, Config.NET_CONNECTED_ERROR)
			.sendToTarget();
		}
		return "";
	}
}
