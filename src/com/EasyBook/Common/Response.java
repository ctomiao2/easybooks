package com.EasyBook.Common;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Response {
	public Integer err_code;
	public JSONObject data;

	public static Response deserialize(String json_str) {
		Response resp = new Response();
		JSONObject jsonObject = new JSONObject(JSON.parseObject(json_str));
		resp.err_code = jsonObject.getIntValue("err_code");
		resp.data = jsonObject.getJSONObject("data");
		return resp;
	}

	public static String serialize(Response resp) {
		String json_str = JSON.toJSONString(resp);
		return json_str;
	}

	public static void test() {
		new ResponseUT();
	}
}

// UnitTest Class
class ResponseUT extends UnitTestBase {

	public void run() {
		Response resp = new Response();
		resp.err_code = ErrCode.toInt(ErrCode.INCORRECT_PASSWORD);
		resp.data = new JSONObject();
		resp.data.put("key1", 1);
		resp.data.put("key2", "a");
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		resp.data.put("key3", arr);
		resp.data.put("key4", new HashMap<String, Integer>() {{
        	put("id", 1);  
        	put("age", 20); 
    	}});

		String json_str = Response.serialize(resp);
		Response resp2 = Response.deserialize(json_str);
		assert(resp.equals(resp2));
		//Response.deserialize
	}
}

