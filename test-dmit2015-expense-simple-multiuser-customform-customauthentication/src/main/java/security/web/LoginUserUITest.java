package security.web;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginUserUITest {

	private static WebDriver _driver;
	
	@BeforeAll
	public static void setUpClass() {
		// Download geckodriver-v0.26.0-linux64.tar.gz from https://github.com/mozilla/geckodriver/releases
		// and extract to "~/jdk/web-drivers/" folder.

		// For more web browsers visit https://selenium.dev/downloads/
		
		System.setProperty("webdriver.gecko.driver","/home/user2015/jdk/web-drivers/geckodriver");
		_driver = new FirefoxDriver();		

		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/customLogin.xhtml");
		setValue("input_loginForm:username", "user2015");
		setValue("input_loginForm:password", "Password2015");
		_driver.findElement(By.id("loginForm:login")).click();

	}
	
	@AfterAll
	public static void tearDownClass() {
		_driver.quit();
	}
	
	@BeforeEach
	public void setUp() {

	}
	
	private static void setValue(String id, String value) {
		WebElement element = _driver.findElement(By.id(id));
		element.clear();
		element.sendKeys(value);
	}

	@Test
	public void testAddDeveloperUser01() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "dev01");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
		developerGroup.click();
		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
		userGroup.click();
		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}

	@Test
	public void testAddDeveloperUser02() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "dev02");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
		developerGroup.click();
//		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
//		userGroup.click();
//		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
//		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}

	@Test
	public void testAddAdminUser01() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "admin01");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
//		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
//		developerGroup.click();
		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
		userGroup.click();
		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}
	
	@Test
	public void testAddAdminUser02() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "admin02");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
//		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
//		developerGroup.click();
		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
		userGroup.click();
//		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
//		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}
	
	@Test
	public void testAddUser01() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "user01");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
//		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
//		developerGroup.click();
		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
		userGroup.click();
//		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
//		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}

	@Test
	public void testAddUser02() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/LoginUser/create.xhtml");
		setValue("input_createForm:username", "user02");
		setValue("input_createForm:password", "Password2015");
		setValue("input_createForm:confirmedPassword", "Password2015");
		
		WebElement groupElement = _driver.findElement(By.xpath("//*[@id='createForm:groups']//button"));
		groupElement.click();
//		WebElement developerGroup = _driver.findElement(By.name("createForm-groups:0"));
//		developerGroup.click();
		WebElement userGroup = _driver.findElement(By.name("createForm-groups:1"));
		userGroup.click();
//		WebElement adminGroup = _driver.findElement(By.name("createForm-groups:2"));
//		adminGroup.click();

		_driver.findElement(By.id("createForm:save")).click();
		
		Thread.sleep(1000);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create new LoginUser was successful", text);		
	}

}
