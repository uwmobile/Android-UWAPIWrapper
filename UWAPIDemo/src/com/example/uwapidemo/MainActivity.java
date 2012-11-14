package com.example.uwapidemo;

import org.json.JSONObject;

import uwapi.UWAPIWrapper;
import uwapi.UWAPIWrapper.UWAPIWrapperListener;

import com.example.uwapidemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements UWAPIWrapperListener {
	private static final String API_KEY = "720811f4c8498e729bf3a7b1cb4760cf";
	
	private Button button1;
	private TextView textView1;
	private UWAPIWrapper apiWrapper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView1 = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        
        apiWrapper = new UWAPIWrapper(API_KEY, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void buttonClicked(View view) {
    	apiWrapper.callService("CourseSearch", "CS 135");
    }

	@Override
	public void onRequestComplete(JSONObject result, boolean success) {
		textView1.setText(result.toString());
	}
}
