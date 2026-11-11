package com.redbus.util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebEventListener implements WebDriverListener {
	// --- Navigation Events ---
	@Override
	public void beforeGet(WebDriver driver, String url) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		System.out.println("Navigated to: '" + url + "'");
	}

	@Override
	public void beforeTo(WebDriver.Navigation navigation, String url) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	@Override
	public void afterTo(WebDriver.Navigation navigation, String url) {
		System.out.println("Navigated to: '" + url + "'");
	}

	@Override
	public void beforeBack(WebDriver.Navigation navigation) {
		System.out.println("Navigating back to previous page");
	}

	@Override
	public void afterBack(WebDriver.Navigation navigation) {
		System.out.println("Navigated back to previous page");
	}

	// --- Element Interaction Events ---
	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		System.out.println("Trying to find Element By : " + locator);
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
		System.out.println("Found Element By : " + locator);
	}

	@Override
	public void beforeClick(WebElement element) {
		System.out.println("Trying to click on: " + element);
	}

	@Override
	public void afterClick(WebElement element) {
		System.out.println("Clicked on: " + element);
	}

	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		System.out.println("Typing into: " + element);
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		System.out.println("Typed into: " + element);
	}

	// --- Alert Events ---
	@Override
	public void beforeAccept(Alert alert) {
		System.out.println("Trying to accept the Alert");
	}

	@Override
	public void afterAccept(Alert alert) {
		System.out.println("Alert accepted");
	}

	@Override
	public void beforeDismiss(Alert alert) {
		System.out.println("Trying to dismiss the Alert");
	}

	@Override
	public void afterDismiss(Alert alert) {
		System.out.println("Alert dismissed");
	}

	// --- Window & Frame Events ---
	@Override
	public void beforeWindow(WebDriver.TargetLocator targetLocator, String nameOrHandle) {
		System.out.println("Trying to switch to window/tab: " + nameOrHandle);
	}

	@Override
	public void afterWindow(WebDriver.TargetLocator targetLocator, String nameOrHandle, WebDriver driver) {
		System.out.println("Switched to window/tab: " + nameOrHandle);
	}

	// --- JavaScript Execution Events ---
	@Override
	public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
		System.out.println("Executing JavaScript: " + script);
	}

	@Override
	public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
		System.out.println("JavaScript executed successfully");
	}
}