package expense.web;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExpenseUITest {

	private static WebDriver _driver;
	
	@BeforeAll
	public static void setUpClass() {
		System.setProperty("webdriver.gecko.driver","/home/user2015/jdk/web-drivers/geckodriver");
		_driver = new FirefoxDriver();		
		_driver.manage().window().maximize();

		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/security/customLogin.xhtml");
		setValue("input_loginForm:username", "user01");
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
	@Order(1)
	public void testCreateEvent1() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/expense/create.xhtml");
		
		WebElement descriptionElement = _driver.findElement(By.id("input_createForm:description"));
		descriptionElement.clear();
		descriptionElement.sendKeys("Monday Coffee");
		descriptionElement.sendKeys(Keys.TAB);

		setValue("input_createForm:amount", "2.45");

		WebElement datelement = _driver.findElement(By.id("createForm:date_Input"));
		datelement.sendKeys(Keys.CONTROL,"a",Keys.DECIMAL);
		datelement.sendKeys("12/02/2019");
	
		_driver.findElement(By.id("createForm:save")).click();
		
		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create was succesful", text);		
	}
	
	@Test
	@Order(2)
	public void testCreateEvent2() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/expense/create.xhtml");
		
		WebElement descriptionElement = _driver.findElement(By.id("input_createForm:description"));
		descriptionElement.clear();
		descriptionElement.sendKeys("Tuesday Coffee");
		descriptionElement.sendKeys(Keys.TAB);

		setValue("input_createForm:amount", "2.45");

		WebElement datelement = _driver.findElement(By.id("createForm:date_Input"));
		datelement.sendKeys(Keys.CONTROL,"a",Keys.DECIMAL);
		datelement.sendKeys("12/03/2019");
	
		_driver.findElement(By.id("createForm:save")).click();
		
		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create was succesful", text);		
	}
	
	@Test
	@Order(3)
	public void testCreateEvent3() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/expense/create.xhtml");
		
		WebElement descriptionElement = _driver.findElement(By.id("input_createForm:description"));
		descriptionElement.clear();
		descriptionElement.sendKeys("Wednesday Coffee");
		descriptionElement.sendKeys(Keys.TAB);

		setValue("input_createForm:amount", "2.45");

		WebElement datelement = _driver.findElement(By.id("createForm:date_Input"));
		datelement.sendKeys(Keys.CONTROL,"a",Keys.DECIMAL);
		datelement.sendKeys("12/04/2019");
	
		_driver.findElement(By.id("createForm:save")).click();
		
		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create was succesful", text);		
	}

	@Test
	@Order(4)
	public void testCreateEvent4() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/expense/create.xhtml");
		
		WebElement descriptionElement = _driver.findElement(By.id("input_createForm:description"));
		descriptionElement.clear();
		descriptionElement.sendKeys("Thursday Coffee");
		descriptionElement.sendKeys(Keys.TAB);

		setValue("input_createForm:amount", "2.45");

		WebElement datelement = _driver.findElement(By.id("createForm:date_Input"));
		datelement.sendKeys(Keys.CONTROL,"a",Keys.DECIMAL);
		datelement.sendKeys("12/05/2019");
	
		_driver.findElement(By.id("createForm:save")).click();
		
		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create was succesful", text);		
	}
	
	@Test
	@Order(5)
	public void testCreateEvent5() throws InterruptedException {		
		_driver.get("https://localhost:8443/dmit2015-expense-simple-multiuser-customform-customauthentication/expense/create.xhtml");
		
		WebElement descriptionElement = _driver.findElement(By.id("input_createForm:description"));
		descriptionElement.clear();
		descriptionElement.sendKeys("Friday Coffee");
		descriptionElement.sendKeys(Keys.TAB);

		setValue("input_createForm:amount", "2.45");

		WebElement datelement = _driver.findElement(By.id("createForm:date_Input"));
		datelement.sendKeys(Keys.CONTROL,"a",Keys.DECIMAL);
		datelement.sendKeys("12/06/2019");
	
		_driver.findElement(By.id("createForm:save")).click();
		
		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String text = _driver.findElement(By.className("bf-message-detail")).getText();
		assertEquals("Create was succesful", text);		
	}
}
