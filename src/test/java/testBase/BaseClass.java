package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClass 
	{
		public static WebDriver driver;
		public Logger logger; //log4j2
		public Properties p;
		
		@BeforeClass(groups={"Sanity", "Regression", "Master",})
		@Parameters({"os", "browser"})		
		public void setup(String os, String br) throws IOException
		{	
			//Loading config.propertie file
			FileReader file=new FileReader(".//src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
					
			logger=LogManager.getLogger(getClass());
			
			//Additional code for GRID Set-up starts here {Remote}
			if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
				DesiredCapabilities capabilities=new DesiredCapabilities();
				
				//os
				if(os.equalsIgnoreCase("windows"))
				{
					capabilities.setPlatform(Platform.WIN10);
				}
				else if (os.equalsIgnoreCase("mac"))
				{
					capabilities.setPlatform(Platform.MAC);
				}
				else
				{
					System.out.println("No matching os");
					return; 
				}
				
				
				//browser
				
				switch (br.toLowerCase())
				{
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				
				default: System.out.println("No matching browser");
				return;
				}
				
				driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			}
			
			//Additional code for GRID Set-up ends here {Local}
			if(p.getProperty("execution_env").equalsIgnoreCase("local"))
				
			switch(br.toLowerCase())
			{
				case "chrome": driver=new ChromeDriver(); break;
				case "edge": driver=new EdgeDriver(); break;
				case "firefox": driver=new FirefoxDriver(); break;
				
				default: System.out.println("Invalid browser name..."); return;
			} 
			//Additional code for GRID Set-up ends here 
			
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL")); //Reading URL from 'config.properties' file
			driver.manage().window().maximize();					
		}
				
		@AfterClass (groups= {"Sanity", "Regression", "Master"})
		public void teardown()
		{
			driver.quit();
		}
		
		
		public String randomString()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		}
		
		public String randomNumber()
		{
			String phonenum=RandomStringUtils.randomNumeric(10);
			return phonenum;
			
		}
		
		public String randomAlphaNumeric()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(3);
			String phonenum=RandomStringUtils.randomNumeric(3);
			return generatedstring+"@"+phonenum;
		}
		
		public String captureScreen(String tname)
		{
			String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
			File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screeshots\\" +tname + "_" + timeStamp;
			File targetFile =new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
		}

	}
