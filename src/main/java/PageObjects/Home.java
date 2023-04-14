package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//this is API demos home page.....so we wil call it home page and we will create all the objects of home page here


public class Home {
	
	public Home(AndroidDriver driver)
	
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
		public WebElement Preference;
	/* above step is nothing but
	 * findElementByXPath("//android.widget.TextView[@text='Preference']")
	 * for iOS we can use @iOSFindBy
	 */
	//so here driver.findElementByXPath("//android.widget.TextView[@text='Preference']") is stored in Preferences 
	// the constructor method and @android.. annotation will concatenate driver.find.... respectively
	//by constructor method we are concatenate driver to findelement....
	
	// for findElements we need to use below step
	//driver.findElementsByClassName("android.widget.Button").get(1).click();
	@AndroidFindBy(className="android.widget.Button")
	public List<WebElement> Button;
	

}
