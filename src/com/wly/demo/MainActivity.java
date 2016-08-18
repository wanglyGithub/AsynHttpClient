package com.wly.demo;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wly.listener.ResultCallBack;
import com.wly.utils.AsyncHttpClient;
public class MainActivity extends Activity implements OnClickListener, ResultCallBack{
	private Button start;
	private TextView tv_info;
	private  String path="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	private void initView() {
		start=(Button) findViewById(R.id.start);
		tv_info=(TextView) findViewById(R.id.tv_info);
		start.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {

		AsyncHttpClient client=new AsyncHttpClient(path, MainActivity.this,this);
		client.excute();
	}

	@Override
	public void Succeed(String result) {
		tv_info.setText(result);
	}

	@Override
	public void Failed(String result) {
		tv_info.setText(result);
	}


}
