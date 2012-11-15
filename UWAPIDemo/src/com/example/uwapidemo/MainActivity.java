/*
 * Created 12/11/13
 * Author: Bo Yin (bo@uwmobile.ca)
 */

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
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements UWAPIWrapperListener {
	// Paste your API key here
	// Get API key from: http://api.uwaterloo.ca/#!/keygen
	private static final String API_KEY = "ENTER YOUR API KEY HERE";

	// UI elements
	private Button button1;
	private TextView textView1;
	private EditText editText1;

	private UWAPIWrapper apiWrapper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Link up UI elements:
		textView1 = (TextView) findViewById(R.id.textView1);
		button1 = (Button) findViewById(R.id.button1);
		editText1 = (EditText) findViewById(R.id.editText1);

		// Initialize an API wrapper object using the API key and this activity
		// as the listener.
		apiWrapper = new UWAPIWrapper(API_KEY, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void buttonClicked(View view) {
		// Call the CourseSearch service using the given course code.
		apiWrapper.callService("CourseSearch", editText1.getText().toString());
	}

	@Override
	public void onUWAPIRequestComplete(JSONObject jsonObject, boolean success) {
		if (success) {
			// Got result JSON
			// See output format at: http://api.uwaterloo.ca/#!/coursesearch
			textView1.setText(jsonObject.toString());
		} else {
			// Request failed (most likely network issue).
			textView1.setText("Request Failed! Check your network.");
		}
	}
}
