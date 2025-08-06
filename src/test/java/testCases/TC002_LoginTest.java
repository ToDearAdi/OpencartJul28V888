package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("*** Starting TC002_LoginTest ***");
		
	  try
	   {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My Account Page
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyPageExists();
		
		//Verify message
		Assert.assertEquals(targetPage, true, "Login failed...!");
		//Assert.assertTrue(targetPage);//We can put above or this one for verification
	   }
	  catch (Exception e)
	  {
		  Assert.fail();
	  }
	   
		logger.info("*** Finished TC002_LoginTest ***");
	}
}