package com.myntra;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraMen {
	static WebDriver driver;
	//@Test (priority = 0, enabled = false)//ignore test cases method
	//@Test (priority = 0)
	//@Test (priority = 0, invocationCount =  2)//multiple runs in this particular test method
	
	
	@DataProvider(name="data")
	public Object [][] dataName (){
		//return new Object [][] {{"Men"}, {"Women"}};
		return new Object [][] {{"Men", "Women"}};//it throw internal.relector.method.matcher exception
		//To overcome this add the arguments in method String name and String name 1
	}
	@DataProvider (name="data1")
	public Object [][] dataName1(){
		return new Object[][] {{"U.S. Polo Assn."}};
	}
	@BeforeClass
	public void LaunchTheApplication() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String url = "https://www.myntra.com/";
		driver.get(url);
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("Application Launched Successfully: ");
		}else {
			System.out.println("Application launch not Successfully: ");
		}
		}
	@BeforeMethod
	public void before() {
		System.out.println("Before Method"); 
	}
	@AfterMethod
	public void after() {
		System.out.println("After Method");
	}
	@AfterClass
	public void closeTheApplication() {
		driver.quit();
	}
	@Test (priority =1,dataProvider = "data")
	public void clickOnMen(String name, String name1) {
		WebElement men =driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Men']"));
		String menText =men.getText();
		men.click();
		//Assert.assertEquals("Men", menText);// Verifies that the expected and actual values are equal
		//Assertion = validation and verification
		//Hard Assert - Validation 
		//Soft Assert - Verification
		
	  //  SoftAssert soft = new SoftAssert();
	  //  soft.assertEquals(menText, name , "Assertion Failure in the Webelement text");
	   // soft.assertAll();// This method collects all failed assertions and marks the test as failed at the end
		if(menText.equals(name)) {
			System.out.println("Men Is Clicked");
		}else {
			System.out.println("Men Not Clicked");
		}	
		//soft.assertAll();
		
	}
	@Test (priority=2, dependsOnMethods = "clickOnMen")//if click on men methods fails validate men not execute so wll use dependsOnMethod
	//@Test (priority=2)
	public void validateMenPage() {
		List<WebElement> headers =driver.findElements(By.xpath("//h4[@class='text-banner-title']"));
		List<String> texts = new ArrayList<String>();
		texts.add("Biggest Deals On Top Brands");
		texts.add("Categories To Bag");
		texts.add("Explore Top Brands");
		texts.add("Myntra Luxe");
		texts.add("Trending In Indian Wear");
		texts.add("Trending In Sports Wear");
		texts.add("Trending In Footwear");
		texts.add("Trending In Accessories");
		for (int i=0;i<headers.size();i++) {
			String actHeader=headers.get(i).getText();
			String expHeader= texts.get(i);
			if(actHeader.equalsIgnoreCase(expHeader)) {
				System.out.println("Actual Headers Matches Expected Header: "+actHeader +" and Expected Header "+ expHeader);
			}else {
				System.err.println("Actual Headers Not matches Expected One: "+actHeader +" and Expected Header "+ expHeader);
			}
		}
	}
	@Test (priority=3)
	public void clickOnUsPolo() {
		WebElement usPolo=driver.findElement(By.xpath("//img[@src='https://assets.myntassets.com/w_245,c_limit,fl_progressive,dpr_2.0/assets/images/2020/8/31/1dce9c3e-77fa-48f1-85a3-d3c136c1d73e1598892377652-USPA.jpg']"));
		usPolo.click();
	}
	@Test (priority=4, dataProvider  = "data1")
	public void validateUsPoloBrand(String us) {
		List<WebElement> products =driver.findElements(By.tagName("h3"));
		for(int i=0;i<products.size();i++) {
			String product =products.get(i).getText();
			if(product.equals(us)) {
				System.out.println("US POLO RELATED PRODUCTS IS DISPLAYED: " +product);
			}else {
				System.out.println("NOT RELATED TO US Polo RELATED: "+product);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TTTTTTTTTEEEEEEEEEEEESSSSSSSSSSSSSSTTTTTTTTTTTTNNNNNNNNNNNNNNNNNGGGGGGGGGGGGGGGGGGGGGGGGG
	/*static WebDriver driver;
	//@Test (priority = 0, enabled = false)//ignore test cases method
	//@Test (priority = 0)
	//@Test (priority = 0, invocationCount =  2)//multiple runs in this particular test method
	@BeforeClass
	public void LaunchTheApplication() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String url = "https://www.myntra.com/";
		driver.get(url);
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("Application Launched Successfully: ");
		}else {
			System.out.println("Application launch not Successfully: ");
		}
		}
	@BeforeMethod
	public void before() {
		System.out.println("Before Method"); 
	}
	@AfterMethod
	public void after() {
		System.out.println("After Method");
	}
	@AfterClass
	public void closeTheApplication() {
		driver.quit();
	}
	@Test (priority =1)
	public void clickOnMen() {
		WebElement men =driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Men']"));
		String menText =men.getText();
		men.click();
		//Assert.assertEquals("Men", menText);// Verifies that the expected and actual values are equal
		//Assertion = validation and verification
		//Hard Assert - Validation 
		//Soft Assert - Verification
		
	    SoftAssert soft = new SoftAssert();
	    soft.assertEquals(menText, "men" , "Assertion Failure in the Webelement text");
	   // soft.assertAll();// This method collects all failed assertions and marks the test as failed at the end
		if(menText.equals("Men")) {
			System.out.println("Men Is Clicked");
		}else {
			System.out.println("Men Not Clicked");
		}	
		soft.assertAll();
		
	}
	@Test (priority=2, dependsOnMethods = "clickOnMen")//if click on men methods fails validate men not execute so wll use dependsOnMethod
	//@Test (priority=2)
	public void validateMenPage() {
		List<WebElement> headers =driver.findElements(By.xpath("//h4[@class='text-banner-title']"));
		List<String> texts = new ArrayList<String>();
		texts.add("Biggest Deals On Top Brands");
		texts.add("Categories To Bag");
		texts.add("Explore Top Brands");
		texts.add("Myntra Luxe");
		texts.add("Trending In Indian Wear");
		texts.add("Trending In Sports Wear");
		texts.add("Trending In Footwear");
		texts.add("Trending In Accessories");
		for (int i=0;i<headers.size();i++) {
			String actHeader=headers.get(i).getText();
			String expHeader= texts.get(i);
			if(actHeader.equalsIgnoreCase(expHeader)) {
				System.out.println("Actual Headers Matches Expected Header: "+actHeader +" and Expected Header "+ expHeader);
			}else {
				System.err.println("Actual Headers Not matches Expected One: "+actHeader +" and Expected Header "+ expHeader);
			}
		}
	}
	@Test (priority=3)
	public void clickOnUsPolo() {
		WebElement usPolo=driver.findElement(By.xpath("//img[@src='https://assets.myntassets.com/w_245,c_limit,fl_progressive,dpr_2.0/assets/images/2020/8/31/1dce9c3e-77fa-48f1-85a3-d3c136c1d73e1598892377652-USPA.jpg']"));
		usPolo.click();
	}
	@Test (priority=4)
	public void validateUsPoloBrand() {
		List<WebElement> products =driver.findElements(By.tagName("h3"));
		for(int i=0;i<products.size();i++) {
			String product =products.get(i).getText();
			if(product.equals("U.S. Polo Assn.")) {
				System.out.println("US POLO RELATED PRODUCTS IS DISPLAYED: " +product);
			}else {
				System.out.println("NOT RELATED TO US Polo RELATED: "+product);
			}
		}
	}*/
}
