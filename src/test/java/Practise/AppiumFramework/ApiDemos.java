package Practise.AppiumFramework;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import PageObjects.Dependacies;
import PageObjects.Home;
import PageObjects.Preferences;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemos extends base {
	

	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void apiDemo(String input) throws IOException, InterruptedException {
		
		service=startServer();
		
		AndroidDriver<AndroidElement> driver=Capabilitis("ApiDemos");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Home h= new Home(driver);
		h.Preference.click();
		
		Preferences p= new Preferences(driver);
		p.Dependencies.click();
		
		Dependacies d= new Dependacies(driver);
		d.CheckBox.clear();
		
		d.Wifi.click();
		d.Wifisettings.click();
		d.EditText.sendKeys(input);
		h.Button.get(1).click();
	
service.stop();
	}

}
