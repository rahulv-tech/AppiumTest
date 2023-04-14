package Practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public static AppiumDriverLocalService startServer()   //https://github.com/appium/java-client/blob/master/src/test/java/io/appium/java_client/android/BaseAndroidTest.java
	{
		boolean flag=checkIfServerIsRunning(4723);
		if(!flag)
		{
			service= AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
	}
	/*to work above above code we need to add some dependencies in pom.xml
	1. SLF4J Simple Binding » 1.7.26
	2. SLF4J API Module » 1.7.26
	3.Apache Commons Lang » 3.9(commons-lang3)
	4. Apache Commons Validator » 1.6
	5.Apache Commons IO » 2.6	
	 */

	public static boolean checkIfServerIsRunning(int port)
	{
		boolean isServerRunning= false;
		ServerSocket serverSocket;
		try {
			serverSocket=new ServerSocket(port);
			serverSocket.close();			
		}catch(IOException e) {
			isServerRunning=true;
		}finally {
			serverSocket=null;
		}

		return isServerRunning;
	}
	public static void startEmulator() throws IOException, InterruptedException
	{
		//C:\Users\OnilVakharia\eclipse-workspace\AppiumFramework\src\main\java\resources

		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");//this method executed 
		//the provided file at runtime
		Thread.sleep(20000);	
	}

	public static AndroidDriver<AndroidElement> Capabilitis (String appName) throws IOException, InterruptedException{

		//capabilities method accepts string.....we are sending appName which we wanted to use in test cases

		System.getProperty("user.dir");//this will give u current path of ur project .....project path will be different from computer to computer
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Practise\\AppiumFramework\\global.properties");
		//fis has now access to file and it can read all the contents of the file.
		Properties prop= new Properties();//properties class is used for mapping....we need to map the value provided by test cases(appName) to the current apk
		prop.load(fis);//u r telling go and read fis object which has all the contents of global properties
		//prop.get("device"); it retrieves value present in device

		File appdir=new File("src");
		File app=new File(appdir,(String) prop.get(appName));
		String device=(String) prop.get("device");
		//String device= System.getProperty("deviceName");//this command will take device name at runtime...mvn test -DdeviceName=emulator 
		if(device.contains("emulator"))
		{
			System.out.println("IN EMULATOR METHOD");
			startEmulator();//here we are sending device name from global properties file if its device then this if will not run if name contains emulator then it calls startEmulator method
			Thread.sleep(2000);
		}

		DesiredCapabilities cap=new DesiredCapabilities();

		cap.setCapability("automationName","uiautomator2");
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", app.getAbsolutePath());
		cap.setCapability("deviceName", device);
		AndroidDriver<AndroidElement> driver= new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);

		return driver;

	}

	public static void getScreenShot(String s) throws IOException
	{
		System.out.println("IN GetScreenshotMethos");
		//File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//here we need to cast our driver..it is androiddriver mode we need to change it to screenshot capturing mode
		//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\"+s+".png"));

		try {
			File scrfile=	((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("WHYYYYYYYYY");
			FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir") + "\\"+ s +".png"));
			System.out.println("WHYYYYYYYYY11111");
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}


	}

}

