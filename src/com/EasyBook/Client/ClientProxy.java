package com.EasyBook.Client;
import com.EasyBook.Common.Response;
import com.EasyBook.Server.ServerProxy;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;

public class ClientProxy {

	public static Response login(String user_name, String password) {
		String json_str = ServerProxy.login(user_name, password);
		Response resp = Response.deserialize(json_str);
		return resp;
	}

	public static Response sign_up(String user_name, String password) {
		String json_str = ServerProxy.sign_up(user_name, password);
		Response resp = Response.deserialize(json_str);
		return resp;
	}
}
