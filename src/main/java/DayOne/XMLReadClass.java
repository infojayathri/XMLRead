package DayOne;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XMLReadClass {
	
	WebDriver driver;
	XMLRead xmlRead = new XMLRead();
	
@BeforeTest
	public void setup() throws ParserConfigurationException, SAXException, IOException
	{
	
	    WebDriverManager.chromedriver().setup();
	  //  System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();

     
		driver.get(xmlRead.xmlRead(".\\Data\\test.xml", "url"));
	}

@Test

	public void testSearch() throws ParserConfigurationException, SAXException, IOException
	{
		driver.findElement(By.name("q")).sendKeys(xmlRead.xmlRead(".\\Data\\test.xml", "searchword"));
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[1]/a/h3")).click();
		
		String webTitle = driver.getTitle();
		
		Assert.assertEquals(webTitle, "Selenium - Web Browser Automation");
	}

@AfterTest
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}

}