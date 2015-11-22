package com.ttsc.data.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {

	private static final Logger log = LoggerFactory
			.getLogger(TestMain.class);
	// 目标url
	private static String URL = "http://118.145.18.236:9999/sms.aspx";
	
	private static String ENCODED ="UTF-8";
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//http://118.145.18.236:9999/sms.aspx
		Map<String,String> map = new HashMap<String,String>();
		map.put("action", "send");
		map.put("userid", "1672");
		map.put("account", "luojy1688");
		map.put("password", "luojianyuan11");
		map.put("mobile", "13524784922,18521797978");
		map.put("content", "【天天生财】 您本次注册的验证码为123456,感谢您的使用！");
		map.put("sendTime", "");
		map.put("extno","");
		urlPost("",map);
	}
	
	/**
	 * post的方式请求资源服务器的数据
	 * 
	 * @param url
	 *            url路径
	 * @param param
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static JSONObject urlPost(String url, Map param) throws Exception {
		JSONObject array = new JSONObject();
		HttpClient htpClient = new HttpClient();  
		try {
			long startTime = System.currentTimeMillis();
			NameValuePair[] nvps = new NameValuePair[param.size()];
			Iterator ite = param.entrySet().iterator();
			int i = 0;
			while (ite.hasNext()) {
				Entry en = (Entry) ite.next();
				String key = en.getKey().toString();
				String value = en.getValue().toString();
				NameValuePair nvp = new NameValuePair(key, value);
				nvps[i] =nvp;
				i++;
			}
			PostMethod  httpPost = new PostMethod(URL+url);
			httpPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,ENCODED);
			httpPost.addParameters(nvps);
		    int statusCode = htpClient.executeMethod(httpPost);
		    byte[] responseBody = httpPost.getResponseBody();
		    String response = new String(responseBody,ENCODED);
		    if(statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_NO_CONTENT){
		    	log.info("response is:"+response);
				throw new Exception("metasys api 报错;response is:"+response);
			}
		    if(responseBody == null || responseBody.length == 0){
            	return null; 
            }
		    long endTime = System.currentTimeMillis();
		    log.info("url is:" + url + ";param" + param + ";cost time:" + (endTime - startTime));
		    array = JSONObject.fromObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

}
