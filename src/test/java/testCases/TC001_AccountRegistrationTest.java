package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
	{
		@Test(groups={"Sanity", "Regression", "Master"})
		public void verify_account_registration()
		{
			logger.info("*** Starting TC001_AccountRegistrationTest ***");
			
			try
			{
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("*** Clicked on 'My Account Link' ***");
			
			hp.clickRegister();
			logger.info("*** Clicked on 'Register Link' ***");
			
			AccountRegistrationPage arp=new AccountRegistrationPage(driver);
			
			logger.info("*** Providing customer details ***");
			arp.setFirstName(randomString().toUpperCase());
			arp.setLastName(randomString().toUpperCase());
			arp.setEmail(randomString()+"@gmail.com");
			arp.setTelephone(randomNumber());
			
			//String password AplhaNumeric
			String password=randomAlphaNumeric();
			arp.setPwd(password);
			arp.setConfPwd(password);
			
			arp.radioSub();
			arp.chkdPol();
			arp.cont();
			
			logger.info("*** Validating expected message ***");
			String confmsg=arp.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			}
			catch (Exception e)
			{
				logger.error("Test failed...");
				logger.debug("Debug logs...");
				Assert.fail();
			}
			
			logger.info("*** Finished TC001_AccountRegistrationTest ***");
		}
	}
