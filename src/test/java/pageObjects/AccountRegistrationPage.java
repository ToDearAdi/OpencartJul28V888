package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
	{	
		//Constructor
		public AccountRegistrationPage(WebDriver driver) 
		{
			super(driver);
		}
		
		//Locators
		@FindBy(xpath="//input[@name='firstname']")
		WebElement txtFirstname;
		
		@FindBy(xpath="//input[@name='lastname']")
		WebElement txtLastname;
		
		@FindBy(xpath="//input[@name='email']")
		WebElement txtEmail;
		
		@FindBy(xpath="//input[@name='telephone']")
		WebElement txtTelephone;
		
		@FindBy(xpath="//input[@name='password']")
		WebElement txtPassword;
		
		@FindBy(xpath="//input[@name='confirm']")
		WebElement txtConfirmpassword;
		
		@FindBy(xpath="//input[@value='0']")
		WebElement radioSubscribe;
		
		@FindBy(xpath="//input[@type='checkbox']")
		WebElement chkdPolicy;
		
		@FindBy(xpath="//input[@type='submit']")
		WebElement continueBtn;
		
		@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgConfirmation;
		
		//Actions Methods -Corresponding 
		public void setFirstName(String name)
		{
			txtFirstname.sendKeys(name);
		}
		
		public void setLastName(String lname)
		{
			txtLastname.sendKeys(lname);
		}
		
		public void setEmail(String email)
		{
			txtEmail.sendKeys(email);
		}
		
		public void setTelephone(String tel)
		{
			txtTelephone.sendKeys(tel);
		}
		
		public void setPwd(String pwd)
		{
			txtPassword.sendKeys(pwd);
		}
		
		public void setConfPwd(String pwd)
		{
			txtConfirmpassword.sendKeys(pwd);
		}
		
		public void radioSub()
		{
			radioSubscribe.click();
		}
		
		public void chkdPol()
		{
			chkdPolicy.click();
		}
		
		public void cont()
		{
			continueBtn.click();
		}
		
		public String getConfirmationMsg()
		{
			try 
			{
				return (msgConfirmation.getText());
			}
			catch (Exception e) 
			{
				return (e.getMessage());
			}
		}
	
	}
