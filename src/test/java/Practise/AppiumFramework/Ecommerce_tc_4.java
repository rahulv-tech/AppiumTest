package Practise.AppiumFramework;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.CheckOutPage;
import PageObjects.FormPage;
import PageObjects.Products;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_tc_4 extends base {

	@Test
	public static void totalValidation() throws IOException, InterruptedException {
		
	
		service=startServer();//here startServer methods returns service we are catching it in service variable and using it to start or stop server
	
		
		AndroidDriver<AndroidElement> driver;
		
	
		driver=Ecommerce_tc_4.Capabilitis("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		FormPage formPage= new FormPage(driver);
		formPage.CountrySelection.click();
		formPage.scrollToText("Argentina",driver);
		//formPage.selectCountry().click();//this is another way to call variable from pageobjects-> formpage class created the method and returning variable
		formPage.selectCountry().click();
		formPage.Name.sendKeys("Rahul");		
		driver.hideKeyboard();
		formPage.Male.click();
		formPage.LetsShop.click();
		
		
		Products p=new Products(driver);
		p.Item1.get(0).click();
		p.Item1.get(0).click();
		p.AddToCart.click();
		Thread.sleep(5000);
		
		CheckOutPage cop=new CheckOutPage(driver);
		
		int count= cop.Checkout.size();
		double sum=0;
		for(int i=0;i<count;i++)
		{
			String amt =cop.Checkout.get(i).getText();
			double finalamt=getAmount(amt);
			sum=sum+finalamt;
		}
		System.out.println(sum);
		String TotalValue= cop.TotalAmount.getText();
		TotalValue=TotalValue.substring(1);
		double totalamount=Double.parseDouble(TotalValue);
		Assert.assertEquals(sum, totalamount);
		
		System.out.println(totalamount);	
		
		service.stop();
		Thread.sleep(20000);
		
		
		
	}
	
	@BeforeTest
	public static void KillAllNodes() throws IOException, InterruptedException
	//this is required when we are running appium on CMD and not by using framework then in "return Service" will return null.	
	//we are not returning anythg in service variable. because of that service.stop will throw null pointer exception so we need to close all connection
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	
	public static double getAmount (String value)
	{
		
		value=value.substring(1);
		double d=Double.parseDouble(value);
		return d;
		
	}
	

}
