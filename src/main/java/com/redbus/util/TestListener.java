package com.redbus.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	// Runs once before the <test> tag starts (before any test method)
	@Override
	public void onStart(ITestContext context) {
		System.out.println("===== STARTED  : " + context.getName() + " =====");
	}

	// Runs once after all tests in the <test> tag have finished
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("===== FINISHED : " + context.getName() + " =====");
		System.out.println("Passed  : " + context.getPassedTests().size());
		System.out.println("Failed  : " + context.getFailedTests().size());
		System.out.println("Skipped : " + context.getSkippedTests().size());
	}

	// Runs before each @Test method
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("TEST STARTED : " + getTestName(result));
	}

	// Runs when a @Test method passes
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("TEST PASSED  : " + getTestName(result) + " (" + getDuration(result) + " ms)");
	}

	// Runs when a @Test method fails (exception or failed assertion)
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("TEST FAILED  : " + getTestName(result) + " (" + getDuration(result) + " ms)");
		System.out.println("Reason       : " + result.getThrowable());
		saveScreenshotFor(result);
	}

	// Runs when a @Test method is skipped (for example, a dependency failed)
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("TEST SKIPPED : " + getTestName(result));
		if (result.getThrowable() != null) {
			System.out.println("Reason       : " + result.getThrowable().getMessage());
		}
	}

	// Runs when a test fails but is still inside its successPercentage
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("TEST FAILED (within success percentage): " + getTestName(result));
	}

	// Runs when a test fails because it exceeded its timeOut
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("TEST TIMED OUT: " + getTestName(result));
		saveScreenshotFor(result);
	}

	// ---------- helpers ----------

	private String getTestName(ITestResult result) {
		return result.getTestClass().getRealClass().getSimpleName() + "." + result.getName();
	}

	private long getDuration(ITestResult result) {
		return result.getEndMillis() - result.getStartMillis();
	}

	private void saveScreenshotFor(ITestResult result) {
		try {
			TestUtil.captureScreenshot(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}