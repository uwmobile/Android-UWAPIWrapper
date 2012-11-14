/*
 * Created 12/11/13
 * Author: Bo Yin (bo@uwmobile.ca)
 */
package uwapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.util.Log;

import uwapi.JSONFetcher.JSONFetcherOnCompleteListener;

/*
 * 
 */
public class UWAPIWrapper implements JSONFetcherOnCompleteListener {
	public interface UWAPIWrapperListener {
		public void onRequestComplete(JSONObject result, boolean success);
	}

	private static final String TAG = "UWAPIWrapper";// debug/log tag
	private static final String URL_FORMAT =
			"http://api.uwaterloo.ca/public/v1/?key=%s&service=%s&output=json";
	private static final String URL_FORMAT_WITH_QUERY =
			"http://api.uwaterloo.ca/public/v1/?key=%s&service=%s&q=%s&output=json";

	private String apiKey_;
	
	private UWAPIWrapperListener listener_;
	
	public UWAPIWrapper(String apiKey, UWAPIWrapperListener listener) {
		apiKey_ = apiKey;
		listener_ = listener;
	}

	public void callService(String service) {
		try {
			String queryString = String.format(URL_FORMAT,
					URLEncoder.encode(apiKey_, "utf-8"),
					URLEncoder.encode(service, "utf-8"));
			callWithUrl(queryString);
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, e.toString());
		}
	}
	
	public void callService(String service, String parameter) {
		try {
			String queryString = String.format(URL_FORMAT_WITH_QUERY,
					URLEncoder.encode(apiKey_, "utf-8"),
					URLEncoder.encode(service, "utf-8"),
					URLEncoder.encode(parameter, "utf-8"));
			callWithUrl(queryString);
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, e.toString());
		}
	}

	private void callWithUrl(String url) {
		JSONFetcher fetcher = new JSONFetcher(this);
		fetcher.execute(url);
	}

	public void onJsonFetcherComplete(JSONObject result, boolean success) {
		listener_.onRequestComplete(result, success);
	}
}
