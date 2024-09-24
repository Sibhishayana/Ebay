package com.ajio.rerun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{

	
	int fail=0;//i
	int run =3;//j
	@Override
	public boolean retry(ITestResult result) {
		if (fail<run) {
			fail++;
			return true;
		}
		return false;
	}

}
