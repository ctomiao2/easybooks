package com.EasyBook.Common;

import java.util.ArrayList;

public class UnitTestBase {
	private static ArrayList<UnitTestBase> test_cases = new ArrayList<UnitTestBase>();

	public UnitTestBase() {
		test_cases.add(this);
	}

	// sub class need override
	public void run() {
		System.out.println("UnitTestBase::run");
	}

	public static void runAllTestCases() {
		for (UnitTestBase ut : test_cases)
			ut.run();
	}
}


