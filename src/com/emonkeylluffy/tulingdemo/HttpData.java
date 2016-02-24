package com.emonkeylluffy.tulingdemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HttpData extends AsyncTask<String, Void, String>{

	private HttpClient mHttpCilent;
	private HttpGet mHttpGet;
	private HttpResponse mHttpResponse;
	private HttpEntity mHttpEntity;
	private InputStream in;
	private HttpGetDataListener listener;
	
	private String url;
	
	
	public HttpData(String url,HttpGetDataListener listener) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.listener = listener;
	}
	
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			mHttpCilent = new DefaultHttpClient();
			mHttpGet = new HttpGet(url);
			mHttpResponse = mHttpCilent.execute(mHttpGet);
			mHttpEntity = mHttpResponse.getEntity();
			in = mHttpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine())!=null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		listener.getDataUrl(result);
		super.onPostExecute(result);
	}
}
