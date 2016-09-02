package com.wly.utils;

import com.wly.listener.ResultCallBack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;

/**
 * 
 * @author wangly
 *
 */
public class AsyncHttpClient {

	public String url;
	public Context mContext;
	public ResultCallBack callBack;

	public AsyncHttpClient(String url, Context context, ResultCallBack listener) {
		this.url = url;
		this.mContext = context;
		this.callBack = listener;
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			switch (msg.what) {
			case Config.GET_SUCCESS:
				if (result != null) {
					callBack.Succeed(result);
				}
				break;
			case Config.GET_FAILED:
				if (result != null) {
					callBack.Failed(result);
				}
				break;

			case Config.NET_ERROR:
				if (result != null) {
					callBack.Failed(result);
				}
				break;

			default:
				break;
			}
		};
	};

	public void excute() {
		new Thread(new Task()).start();

	}

	class Task implements Runnable {

		@Override
		public void run() {
			if (!NetStateUtils.isNetworkAvailable(mContext)) {
				handler.obtainMessage(Config.NET_ERROR,
						Config.NOT_NETWORK_ERROR).sendToTarget();
				return;
			}
			String result = HttpUtils.get(url, handler);
			if (!"".equals(result) && null != result) {
				handler.obtainMessage(Config.GET_SUCCESS, result)
				.sendToTarget();
			} else {
				handler.obtainMessage(Config.GET_FAILED).sendToTarget();
			}
		}
	}

}
