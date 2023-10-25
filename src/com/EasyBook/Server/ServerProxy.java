package com.EasyBook.Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.EasyBook.Common.ErrCode;
import com.EasyBook.Common.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ServerProxy {

	private static String readFile(String fileName) {
		final String CHARSET_NAME = "UTF-8";
		StringBuffer strbuffer = new StringBuffer();
		File file = new File(fileName);
		if (!file.exists()) {
			System.err.println("Can't Find " + fileName);
		}
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fis, CHARSET_NAME);
			BufferedReader in  = new BufferedReader(inputStreamReader);
			
			String str;
			while ((str = in.readLine()) != null) {
				strbuffer.append(str);
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	
		return strbuffer.toString();
	}

	private static void writeFile(String fileName, String str) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(str);
			System.out.println("write file succ!");
		} catch (IOException e) {
			System.out.println("write file error: " + e.getMessage());
		}
	}

	private static <T> T getBean(String fileName, Class<T> t) {
		String json_str = readFile(fileName);
		return JSON.parseObject(json_str, t);
	}

	private static <T> List<T> getBeans(String fileName, Class<T> t) {
		String json_str = readFile(fileName);
		return JSON.parseArray(json_str, t);
	}

	public static void try_init_users() {
		if (Global.g_users.isEmpty()) {
			List<UserInfo> user_infos = getBeans("src/com/EasyBook/Server/user.json", UserInfo.class);
			for (int i = 0; i < user_infos.size(); ++i) {
				UserInfo userInfo = user_infos.get(i);
				Global.g_users.put(userInfo.getUserName(), userInfo);
			}
		}
	}

	public static void try_save_users() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		for (String k: Global.g_users.keySet()) {
			userInfos.add(Global.g_users.get(k));
		}
		String json_str = JSON.toJSONString(userInfos);
		writeFile("src/com/EasyBook/Server/user.json", json_str);
	}

	public static String login(String user_name, String password_md5){
		System.out.println("ServerProxy::login, user_name: " + user_name + " parrword: " + password_md5);
		try_init_users();
		Response resp = new Response();
		UserInfo userInfo = Global.g_users.get(user_name);
		if (userInfo != null && userInfo.getPassword().equals(password_md5)) {
			resp.err_code =  ErrCode.toInt(ErrCode.OK);
			resp.data = new JSONObject();
			resp.data.put("user_name", user_name);
		} else {
			resp.err_code = ErrCode.toInt(ErrCode.INCORRECT_PSW_OR_USERNAME);
		}
		return Response.serialize(resp);
	}

	public static String sign_up(String user_name, String password_md5) {
		if (user_name.isEmpty() || password_md5.isEmpty()) return "";
		System.out.println("ServerProxy::sign_up, user_name: " + user_name + " parrword: " + password_md5);
		try_init_users();
		UserInfo userInfo = Global.g_users.get(user_name);
		Response resp = new Response();
		if (userInfo != null) {
			resp.err_code = ErrCode.toInt(ErrCode.USERNAME_HAS_EXISTED);
		} else {
			userInfo = new UserInfo();
			userInfo.setUserName(user_name);
			userInfo.setPassword(password_md5);
			Global.g_users.put(user_name, userInfo);
			try_save_users();
			resp.err_code =  ErrCode.toInt(ErrCode.OK);
			resp.data = new JSONObject();
			resp.data.put("user_name", user_name);
		}
		return Response.serialize(resp);
	}
}
