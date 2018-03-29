package net.no5no3.microservices.english.util;

import com.amazonaws.util.json.Jackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class HttpUtil {
	private static int timeoutS=15;
	private static String JSESSIONID=null;

	public static Map httpGet(final String url, final Map<String,Object> params) throws Exception {
		StringBuilder sb=new StringBuilder();
		if(params!=null){
			for(String key: params.keySet()){
				if(sb.length()>0)
					sb.append("&");
				sb.append(URLEncoder.encode(key,"utf-8"));
				sb.append("=");
				sb.append(URLEncoder.encode(params.get(key).toString(),"utf-8"));
			}
		}

		return Jackson.fromJsonString(httpGet(
				params!=null?((url.indexOf("?")!=-1)?url+"&":url+"?")+sb.toString():url),Map.class);
	}

	public static String httpGet(final String url) throws Exception{
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn=null;
		InputStream is = null;
		BufferedReader br =null;
		try {
			URL urlFull = new URL(url);
			conn = (HttpURLConnection) urlFull.openConnection();
			conn.setReadTimeout(timeoutS*1000);
			conn.setConnectTimeout(timeoutS*1000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setUseCaches(false);
	
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			
			if(JSESSIONID!=null)
				conn.setRequestProperty("Cookie", String.format("JSESSIONID=%s", JSESSIONID));
			
			conn.connect();
			
			String cookies=conn.getHeaderField("Set-Cookie");
			if(cookies!=null){
				Pattern p = Pattern.compile("JSESSIONID=(.*?)[;$]");
				Matcher m = p.matcher(cookies);
				if(m.find())
					JSESSIONID=m.group(1);
			}
								
			is = conn.getInputStream();
			
			String Encoding=conn.getHeaderField("Content-Encoding");
			if(Encoding!=null&&Encoding.equals("gzip")){
				GZIPInputStream gin=new GZIPInputStream(is); 
				br = new BufferedReader(new InputStreamReader(gin, "UTF-8"));
			}else{
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			}
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
	
			return sb.toString();
		} catch (Exception e) {
			throw e;
		}finally{
			try {
				if(br!=null)
					br.close();
				if(is!=null)
					is.close();
				if(conn!=null){
					conn.disconnect();
					conn=null;
				}
			} catch (IOException e) {
			}
		}
	}

	public static String httpPost(final String url, final String payload) throws Exception {
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn=null;
		InputStream is = null;
		BufferedReader br =null;
		try {
			URL urlFull = new URL(url);
			conn = (HttpURLConnection) urlFull.openConnection();
			conn.setReadTimeout(timeoutS*1000);
			conn.setConnectTimeout(timeoutS*1000);// 60秒超时时间
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.connect();
	
			conn.getOutputStream().write(payload.getBytes("UTF-8"));
			
			is = conn.getInputStream();
			
			String Encoding=conn.getHeaderField("Content-Encoding");
			if(Encoding!=null&&Encoding.equals("gzip")){
				GZIPInputStream gin=new GZIPInputStream(is); 
				br = new BufferedReader(new InputStreamReader(gin, "UTF-8"));
			}else{
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			}
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
	
			return sb.toString();
		}catch (Exception e) {
			throw e;
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(is!=null){
					is.close();
				}
				if(conn!=null){
					conn.disconnect();
					conn=null;
				}
			} catch (IOException e) {
			}
		}
	}
}
