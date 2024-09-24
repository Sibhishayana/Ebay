package com.ajio;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjioFashion {

	static WebDriver driver;
	
	
	
	
	
	@BeforeClass(groups="common1")
	//@Test(priority =0)
	public void launchUrl() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.ebay.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@Parameters({"params"})
	@Test(priority=1,groups="regression1")
	public void clickOnFashion(@Optional("DefaultValue") String arg) {
		System.out.println(arg);
		WebElement fashion =driver.findElement(By.xpath("//li[@class='vl-flyout-nav__js-tab']/a[text()='Fashion']"));
		fashion.click();
		
	}
	@Parameters({"params", "params1"})
	@Test(priority =2,groups="regression1")
	public void vallidateFashion(@Optional("DefaultValueValidate") String ar, @Optional ("DefaultValueFashion")String ar1) {
		System.out.println(ar+ " "+ar1);
		WebElement fashionDates=driver.findElement(By.xpath("//img[@src='https://i.ebayimg.com/thumbs/images/g/vrAAAOSwUS1lMBeI/s-l640.webp']"));
		fashionDates.click();
	}
	@Parameters({"params1"})
	@Test(priority=3,groups="regression1")
	public void women(@Optional ("Default Parameter")String a) {
		System.out.println(a);
		List<WebElement> womens =driver.findElements(By.xpath("//p[@class='b-guidancecard__title']"));
		for(WebElement x: womens) {
			String women = x.getText();
			System.out.println(women);
		}
	}
	@AfterClass(groups="common1")
//	@Test(priority =2)
	public void close() {
		driver.close();
	}
	
}
