package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//*[@text='Enter name here']")
	public WebElement Name;
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement CountrySelection;
	
	public void scrollToText(String containedText,AndroidDriver<AndroidElement> driver)
	{
		
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
	}
	
	
	@AndroidFindBy(xpath="//*[@text='Argentina']")
	private WebElement SelectCountry;//now this can be accessed through the methods of this class only(selectCountry method)
	
	@AndroidFindBy(xpath="//*[@text='Male']")
	public WebElement Male;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement LetsShop;
	
	public WebElement selectCountry()
	{
		System.out.println("Trying to find out Agentina Country");
		return SelectCountry;
	}
	
}
