package com.EasyBook.Common;

public enum ErrCode {
	OK,
	INCORRECT_USERNAME,
	INCORRECT_PASSWORD,
	INCORRECT_PSW_OR_USERNAME,
	USERNAME_HAS_EXISTED;

	private static ErrCode[] values = null;

    public static ErrCode fromInt(int i) {
        if(ErrCode.values == null) {
            ErrCode.values = ErrCode.values();
        }
        return ErrCode.values[i];
    }

	public static int toInt(ErrCode err_code) {
		if(ErrCode.values == null) {
            ErrCode.values = ErrCode.values();
        }
		for (int i = 0; i < ErrCode.values.length; ++i) {
			if (ErrCode.values[i] == err_code)
				return i;
		}
		return -1;
	}
}
