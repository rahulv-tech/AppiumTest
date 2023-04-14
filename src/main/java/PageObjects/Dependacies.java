package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Dependacies {
	
	
	
public Dependacies (AndroidDriver driver)
	
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

@AndroidFindBy(id="android:id/checkbox")
public WebElement CheckBox;

@AndroidFindBy(className="android.widget.CheckBox")
public WebElement Wifi;

@AndroidFindBy(xpath="//android.widget.TextView[@text='WiFi settings']")
public WebElement Wifisettings;

@AndroidFindBy(className="android.widget.EditText")
public WebElement EditText;


}
