package com.EasyBook.Client;

import com.EasyBook.Common.ErrCode;
import com.EasyBook.Common.Response;

enum LoginState {
	None,
	Logining,
	Login 
}

public class UserInfo {
	private String user_name;
	//private String token; // TODO: 暂时不用
	private LoginState login_state;

	public String getUserName() {
		return user_name;
	}

	public boolean is_logining() {
		return login_state == LoginState.Logining;
	}

	public boolean is_login() {
		return login_state == LoginState.Login;
	}

	public void login(String username, String password) {
		if (is_login() || is_logining()) return;
		login_state = LoginState.Logining;
		Response resp = ClientProxy.login(username, password);
		if (ErrCode.fromInt(resp.err_code) == ErrCode.OK) {
			login_state = LoginState.Login;
			user_name = username;
			Global.g_frame.on_login(true);
		} else {
			login_state = LoginState.None;
			Global.g_frame.on_login(false);
		}
	}

	public void sign_up(String username, String password) {
		if (is_login() || is_logining()) return;
		login_state = LoginState.Logining;
		Response resp = ClientProxy.sign_up(username, password);
		if (ErrCode.fromInt(resp.err_code) == ErrCode.OK) {
			login_state = LoginState.Login;
			user_name = username;
			Global.g_frame.on_login(true);
		} else {
			login_state = LoginState.None;
			Global.g_frame.on_register_fail();
		}
	}
}
