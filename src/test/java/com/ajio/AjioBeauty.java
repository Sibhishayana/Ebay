package com.ajio;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.util.RetryAnalyzerCount;

import com.ajio.rerun.RetryAnalyser;
import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjioBeauty {
	
static WebDriver driver;



	
	@BeforeSuite(groups="common")
	public void beforSuite() {
		System.out.println("BeforeSuite");
	}
	@AfterSuite(groups="common")
	public void afterSuite() {
		System.out.println("AfterSuite");
	}
	@BeforeTest(groups="common")
	public void beforeTest() {
		System.out.println("BeforeTest");
	}
	@AfterTest(groups="common")
	public void afterTest() {
		System.out.println("AfterTest");
	}
	
	
	@DataProvider(name ="data")
	public Object[][] dataOutdoor(){
		return new Object [][] {{"outdoor"}};
	}
	@DataProvider(name ="data1")
	public Object[][]  skateBoard(){
		return new Object [][] {{"Skateboard"}, {"LongBoard"}};
	}
	@DataProvider(name="data2")
	public Object[][] archery(){
		return new Object [][] {{"Archery", "Bow"}};
	}
	//@Parameters({"browser"})
	@BeforeClass(groups="common")
    //@Test(priority = -1, enabled = false)
	//@Test(priority = -1, invocationCount = 2)//multiple times 
	public void launchUrl() {
	/*	if (arg.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}else {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}*/
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();	
		driver.manage().window().maximize();
		String url = "https://www.ebay.com/";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("APPLICATION LAUNCHED SUCCESSFULLY");
		}else {
			System.out.println("APPLICATION NOT LAUNCH SUCCESSFULLY");
		}
	}
	@BeforeMethod(groups="common")
	public void Before() {
		System.out.println("Before Method");
	}
	@AfterMethod(groups="common")
	public void After() {
		System.out.println("After Method");
	}
	@Test(priority = 0, groups="sanity")
   public void clickOnSports() {
		WebElement sports =driver.findElement(By.xpath("//li[@class='vl-flyout-nav__js-tab']/a[text()='Sports']"));
		
		String sport=sports.getText();
		if (sport.equals("FlySports")) {
			System.out.println("Printed Fly");
		}else {
			System.out.println("Not Printed");
		}
		sports.click();
		
	}
	
	@Test(priority = 1,groups="regression")
   public void validateSportsShop() {
		
		  // public void validateSportsShop(@Optional ("Optional Parameters")String arg) {
			//	System.out.println(arg);
	   List<WebElement> shops=driver.findElements(By.xpath("//div[@class='b-visualnav__title']"));
	   List<String> textShops = new ArrayList<String>();
	   textShops.add("Outdoor Sports");
	   textShops.add("Fitness, Running & Yoga");
	   textShops.add("Indoor Games");
	   textShops.add("Fitness Technology");
	   textShops.add("Fishing");
	   textShops.add("Water Sports");
	   textShops.add("Tennis & Racquet Sports");
	   textShops.add("Boxing, Martial Arts & MMA");
	   textShops.add("Cycling");
	   textShops.add("Winter Sports");
	   textShops.add("Golf Clubs");
	   textShops.add("Golf Bags");
	   textShops.add("Golf Balls");
	   textShops.add("Push-Pull Golf Carts");
	   textShops.add("Golf Club Head Covers");
	   textShops.add("Golf Rangefinders & Scopes");
	   textShops.add("Golf Visors & Hats");
	   textShops.add("Men's Golf Clothing & Shoes");
	   textShops.add("Women's Golf Clothing & Shoes");

	   for(int i=0;i<shops.size();i++) {
		  String actualShop= shops.get(i).getText();
		  String expectedShop =textShops.get(i);
		  if(actualShop.equalsIgnoreCase(expectedShop)) {
			  System.out.println("ACTUAL SHOP MATCHES EXPECTED ONE " +actualShop+" and EXPECTED  "+ expectedShop);
		  }else {
			  System.out.println("ACTUAL NOT MATCHES AN EXPECTED ONE " +actualShop+" and Expected " +expectedShop);
		  }
		  
	   }
   }
	//@Test(priority =2, enabled = false)
	//@Test(priority=2, dataProvider = "data" ,retryAnalyzer = RetryAnalyser.class)
	//@Test(priority=2, dataProvider = "data" )//IAnnotationTransformer
	@Test(priority=2, dataProvider = "data", groups="sanity", retryAnalyzer=RetryAnalyser.class )
	public void clickOnOutdoorGames(String out) {
		WebElement outdoor=driver.findElement(By.xpath("//div[text()='Outdoor Sports']"));
		String outdoorText =outdoor.getText();
		System.out.println(outdoorText);
		outdoor.click();
		//Assert.assertEquals("outdoor", outdoorText);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(outdoorText, "outdoor", "Assertion failure in the Webelement Text");
		soft.assertAll();
		if(outdoorText.equals(out)) {//Outdoor Sports Actual //Outdoor Expected //passing value outdoor
			System.out.println("Printed Outdoor");
		}else {
			System.out.println("Not Printed");
		}
		
		
	}
	//@Test(priority=3,dependsOnMethods = "clickOnOutdoorGames")//skip the testcase depends on methods
	@Test(priority=3,groups="regression")
	public void shopByCategory() {
		List<WebElement> categories=driver.findElements(By.xpath("//p[@class='b-guidancecard__title']"));
		for(int i=0;i<categories.size();i++) {
			String category =categories.get(i).getText();
			System.out.println("Category of outdoor sports: "+category);
		}
	}
	@Test(priority=4, groups="sanity")
	public void skateBoarding() {
		WebElement skateBoard=driver.findElement(By.xpath("//p[text()='Skateboarding & Longboarding']"));
		String skate =skateBoard.getText();
		skateBoard.click();
		System.out.println(" Skate button Is not Clicked "+skate);
	}
	@Test(priority=5, dataProvider = "data1",groups="regression")
	public void boardOrder(String skate) {
		List<WebElement> boards =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for (int i=0;i<boards.size();i++) {
			String board=boards.get(i).getText();
			if(board.contains(skate)) {
				System.out.println("Expected Result Skate Board Only "+ board);
			}else {
				System.out.println("Expected Result Others "+ board);
			}
		}
	}
	@Test(priority =6, dataProvider = "data2",groups="regression")
	public void backPage(String ar, String bo) {
		driver.navigate().back();
		WebElement archery=driver.findElement(By.xpath("//p[text()='Archery']"));
		String arch =archery.getText();
		if( arch.equals(ar)) {
			System.out.println("Archery is Clicked ");
		}else {
			System.out.println("Archery is not Clicked");
		}
		archery.click();
		List<WebElement> bows =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for( WebElement x: bows) {
			String bow = x.getText();
			if ( bow.contains(ar)) {
				System.out.println("Archery Printed: " + bow);
			}else if (bow.contains(bo)){
				System.out.println("Bow Printed: "+bow);
			}else {
				System.out.println("Not Match");
			}
		}
		
	}
	
	@AfterClass(groups="common")
	public void close() {
		driver.quit();
	}
	
	
	
	
   /* static WebDriver driver;
	
	@BeforeSuite
	public void beforSuite() {
		System.out.println("BeforeSuite");
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuite");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}
	
	
	@DataProvider(name ="data")
	public Object[][] dataOutdoor(){
		return new Object [][] {{"outdoor"}};
	}
	@DataProvider(name ="data1")
	public Object[][]  skateBoard(){
		return new Object [][] {{"Skateboard"}, {"LongBoard"}};
	}
	@DataProvider(name="data2")
	public Object[][] archery(){
		return new Object [][] {{"Archery", "Bow"}};
	}
	
	@BeforeClass
    //@Test(priority = -1, enabled = false)
	//@Test(priority = -1, invocationCount = 2)//multiple times 
	public void launchUrl() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = "https://www.ebay.com/";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("APPLICATION LAUNCHED SUCCESSFULLY");
		}else {
			System.out.println("APPLICATION NOT LAUNCH SUCCESSFULLY");
		}
	}
	@BeforeMethod
	public void Before() {
		System.out.println("Before Method");
	}
	@AfterMethod
	public void After() {
		System.out.println("After Method");
	}
	@Test(priority = 0)
   public void clickOnSports() {
		WebElement sports =driver.findElement(By.xpath("//li[@class='vl-flyout-nav__js-tab']/a[text()='Sports']"));
		sports.click();
	}
	@Parameters({"params"})
	@Test(priority = 1, enabled=true)
   public void validateSportsShop(String arg) {
		System.out.println(arg);
	   List<WebElement> shops=driver.findElements(By.xpath("//div[@class='b-visualnav__title']"));
	   List<String> textShops = new ArrayList<String>();
	   textShops.add("Outdoor Sports");
	   textShops.add("Fitness, Running & Yoga");
	   textShops.add("Indoor Games");
	   textShops.add("Fitness Technology");
	   textShops.add("Fishing");
	   textShops.add("Water Sports");
	   textShops.add("Tennis & Racquet Sports");
	   textShops.add("Boxing, Martial Arts & MMA");
	   textShops.add("Cycling");
	   textShops.add("Winter Sports");
	   textShops.add("Golf Clubs");
	   textShops.add("Golf Bags");
	   textShops.add("Golf Balls");
	   textShops.add("Push-Pull Golf Carts");
	   textShops.add("Golf Club Head Covers");
	   textShops.add("Golf Rangefinders & Scopes");
	   textShops.add("Golf Visors & Hats");
	   textShops.add("Men's Golf Clothing & Shoes");
	   textShops.add("Women's Golf Clothing & Shoes");
	   
	 /*  for (int j=0;j<textShops.size();j++){
	      String exp = textShops.get(j);
	      System.out.println(exp);
	     
	}*/
	 /*  for(int i=0;i<shops.size();i++) {
		  String actualShop= shops.get(i).getText();
		  String expectedShop =textShops.get(i);
		  if(actualShop.equalsIgnoreCase(expectedShop)) {
			  System.out.println("ACTUAL SHOP MATCHES EXPECTED ONE " +actualShop+" and EXPECTED  "+ expectedShop);
		  }else {
			  System.out.println("ACTUAL NOT MATCHES AN EXPECTED ONE " +actualShop+" and Expected " +expectedShop);
		  }
		  
	   }
   }
	//@Test(priority =2, enabled = false)
	@Test(priority=2, dataProvider = "data")
	public void clickOnOutdoorGames(String out) {
		WebElement outdoor=driver.findElement(By.xpath("//div[text()='Outdoor Sports']"));
		String outdoorText =outdoor.getText();
		System.out.println(outdoorText);
		//Assert.assertEquals("outdoor", outdoorText);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(outdoorText, "outdoor", "Assertion failure in the Webelement Text");
		soft.assertAll();
		if(outdoorText.equals(out)) {//Outdoor Sports Actual //Outdoor Expected //passing value outdoor
			System.out.println("Printed Outdoor");
		}else {
			System.out.println("Not Printed");
		}
		outdoor.click();
		
	}
	//@Test(priority=3,dependsOnMethods = "clickOnOutdoorGames")//skip the testcase depends on methods
	@Test(priority=3)
	public void shopByCategory() {
		List<WebElement> categories=driver.findElements(By.xpath("//p[@class='b-guidancecard__title']"));
		for(int i=0;i<categories.size();i++) {
			String category =categories.get(i).getText();
			System.out.println("Category of outdoor sports: "+category);
		}
	}
	@Test(priority=4)
	public void skateBoarding() {
		WebElement skateBoard=driver.findElement(By.xpath("//p[text()='Skateboarding & Longboarding']"));
		String skate =skateBoard.getText();
		skateBoard.click();
		System.out.println(" Skate button Is not Clicked "+skate);
	}
	@Test(priority=5, dataProvider = "data1")
	public void boardOrder(String skate) {
		List<WebElement> boards =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for (int i=0;i<boards.size();i++) {
			String board=boards.get(i).getText();
			if(board.contains(skate)) {
				System.out.println("Expected Result Skate Board Only "+ board);
			}else {
				System.out.println("Expected Result Others "+ board);
			}
		}
	}
	@Test(priority =6, dataProvider = "data2")
	public void backPage(String ar, String bo) {
		driver.navigate().back();
		WebElement archery=driver.findElement(By.xpath("//p[text()='Archery']"));
		String arch =archery.getText();
		if( arch.equals(ar)) {
			System.out.println("Archery is Clicked ");
		}else {
			System.out.println("Archery is not Clicked");
		}
		archery.click();
		List<WebElement> bows =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for( WebElement x: bows) {
			String bow = x.getText();
			if ( bow.contains(ar)) {
				System.out.println("Archery Printed: " + bow);
			}else if (bow.contains(bo)){
				System.out.println("Bow Printed: "+bow);
			}else {
				System.out.println("Not Match");
			}
		}
		
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}*/
	
	
	
	
	
	
	
	/*static WebDriver driver;
	@BeforeClass
    //@Test(priority = -1, enabled = false)
	//@Test(priority = -1, invocationCount = 2)//multiple times 
	public void launchUrl() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = "https://www.ebay.com/";
		driver.get(url);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("APPLICATION LAUNCHED SUCCESSFULLY");
		}else {
			System.out.println("APPLICATION NOT LAUNCH SUCCESSFULLY");
		}
	}
/*	@BeforeMethod
	public void Before() {
		System.out.println("Before Method");
	}
	@AfterMethod
	public void After() {
		System.out.println("After Method");
	}*/
	/*@Test(priority = 0)
   public void clickOnSports() {
		WebElement sports =driver.findElement(By.xpath("//li[@class='vl-flyout-nav__js-tab']/a[text()='Sports']"));
		sports.click();
	}*/
	/*@Test(priority = 1)
   public void validateSportsShop() {
	   List<WebElement> shops=driver.findElements(By.xpath("//div[@class='b-visualnav__title']"));
	   List<String> textShops = new ArrayList<String>();
	   textShops.add("Outdoor Sports");
	   textShops.add("Fitness, Running & Yoga");
	   textShops.add("Indoor Games");
	   textShops.add("Fitness Technology");
	   textShops.add("Fishing");
	   textShops.add("Water Sports");
	   textShops.add("Tennis & Racquet Sports");
	   textShops.add("Boxing, Martial Arts & MMA");
	   textShops.add("Cycling");
	   textShops.add("Winter Sports");
	   textShops.add("Golf Clubs");
	   textShops.add("Golf Bags");
	   textShops.add("Golf Balls");
	   textShops.add("Push-Pull Golf Carts");
	   textShops.add("Golf Club Head Covers");
	   textShops.add("Golf Rangefinders & Scopes");
	   textShops.add("Golf Visors & Hats");
	   textShops.add("Men's Golf Clothing & Shoes");
	   textShops.add("Women's Golf Clothing & Shoes");
	   for(int i=0;i<shops.size();i++) {
		  String actualShop= shops.get(i).getText();
		  String expectedShop =textShops.get(i);
		  if(actualShop.equalsIgnoreCase(expectedShop)) {
			  System.out.println("ACTUAL SHOP MATCHES EXPECTED ONE " +actualShop+" and EXPECTED  "+ expectedShop);
		  }else {
			  System.out.println("ACTUAL NOT MATCHES AN EXPECTED ONE " +actualShop+" and Expected " +expectedShop);
		  }
		  
	   }
   }*/
	//@Test(priority =2, enabled = false)
	/*@Test(priority=2)
	public void clickOnOutdoorGames() {
		WebElement outdoor=driver.findElement(By.xpath("//div[text()='Outdoor Sports']"));
		/*String outdoorText =outdoor.getText();
		System.out.println(outdoorText);
		Assert.assertEquals("outdoor", outdoorText);*/
		/*outdoor.click();
	}*/
	//@Test(priority=3,dependsOnMethods = "clickOnOutdoorGames")//skip the testcase depends on methods
	/*@Test(priority=3)
	public void shopByCategory() {
		List<WebElement> categories=driver.findElements(By.xpath("//p[@class='b-guidancecard__title']"));
		for(int i=0;i<categories.size();i++) {
			String category =categories.get(i).getText();
			System.out.println("Category of outdoor sports: "+category);
		}
	}
	@Test(priority=4)
	public void skateBoarding() {
		WebElement skateBoard=driver.findElement(By.xpath("//p[text()='Skateboarding & Longboarding']"));
		String skate =skateBoard.getText();
		skateBoard.click();
		System.out.println(" Skate button Is not Clicked "+skate);
	}
	@Test(priority=5)
	public void boardOrder() {
		List<WebElement> boards =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for (int i=0;i<boards.size();i++) {
			String board=boards.get(i).getText();
			if(board.contains("Skateboard")) {
				System.out.println("Expected Result Skate Board Only "+ board);
			}else {
				System.out.println("Expected Result Others "+ board);
			}
		}
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}
	/*public void screenShot ()throws IOException {
		System.out.println("Screenshot");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\SIBI\\eclipse-workspace\\MyntraTestNG\\src\\test\\java\\com\\ajio\\ebay\\ebay.png");
		FileUtils.copyFile(src, target);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TTTTTTTTEEEEEEESSSSSSSSSSSTTTTTTTTTTNNNNNNNNNNNNGGGGGGGGGG
/*static WebDriver driver;
	@BeforeClass
    //@Test(priority = -1, enabled = false)
	//@Test(priority = -1, invocationCount = 2)//multiple times 
	public void launchUrl() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = "https://www.ebay.com/";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.startsWith(url)) {
			System.out.println("APPLICATION LAUNCHED SUCCESSFULLY");
		}else {
			System.out.println("APPLICATION NOT LAUNCH SUCCESSFULLY");
		}
	}
	@BeforeMethod
	public void Before() {
		System.out.println("Before Method");
	}
	@AfterMethod
	public void After() {
		System.out.println("After Method");
	}
	@Test(priority = 0)
   public void clickOnSports() {
		WebElement sports =driver.findElement(By.xpath("//li[@class='vl-flyout-nav__js-tab']/a[text()='Sports']"));
		sports.click();
	}
	@Test(priority = 1)
   public void validateSportsShop() {
	   List<WebElement> shops=driver.findElements(By.xpath("//div[@class='b-visualnav__title']"));
	   List<String> textShops = new ArrayList<String>();
	   textShops.add("Outdoor Sports");
	   textShops.add("Fitness, Running & Yoga");
	   textShops.add("Indoor Games");
	   textShops.add("Fitness Technology");
	   textShops.add("Fishing");
	   textShops.add("Water Sports");
	   textShops.add("Tennis & Racquet Sports");
	   textShops.add("Boxing, Martial Arts & MMA");
	   textShops.add("Cycling");
	   textShops.add("Winter Sports");
	   textShops.add("Golf Clubs");
	   textShops.add("Golf Bags");
	   textShops.add("Golf Balls");
	   textShops.add("Push-Pull Golf Carts");
	   textShops.add("Golf Club Head Covers");
	   textShops.add("Golf Rangefinders & Scopes");
	   textShops.add("Golf Visors & Hats");
	   textShops.add("Men's Golf Clothing & Shoes");
	   textShops.add("Women's Golf Clothing & Shoes");
	   for(int i=0;i<shops.size();i++) {
		  String actualShop= shops.get(i).getText();
		  String expectedShop =textShops.get(i);
		  if(actualShop.equalsIgnoreCase(expectedShop)) {
			  System.out.println("ACTUAL SHOP MATCHES EXPECTED ONE " +actualShop+" and EXPECTED  "+ expectedShop);
		  }else {
			  System.out.println("ACTUAL NOT MATCHES AN EXPECTED ONE " +actualShop+" and Expected " +expectedShop);
		  }
		  
	   }
   }
	//@Test(priority =2, enabled = false)
	@Test(priority=2)
	public void clickOnOutdoorGames() {
		WebElement outdoor=driver.findElement(By.xpath("//div[text()='Outdoor Sports']"));
		/*String outdoorText =outdoor.getText();
		System.out.println(outdoorText);
		Assert.assertEquals("outdoor", outdoorText);*/
		/*outdoor.click();
	}*/
	//@Test(priority=3,dependsOnMethods = "clickOnOutdoorGames")//skip the testcase depends on methods
	/*@Test(priority=3)
	public void shopByCategory() {
		List<WebElement> categories=driver.findElements(By.xpath("//p[@class='b-guidancecard__title']"));
		for(int i=0;i<categories.size();i++) {
			String category =categories.get(i).getText();
			System.out.println("Category of outdoor sports: "+category);
		}
	}
	@Test(priority=4)
	public void skateBoarding() {
		WebElement skateBoard=driver.findElement(By.xpath("//p[text()='Skateboarding & Longboarding']"));
		String skate =skateBoard.getText();
		skateBoard.click();
		System.out.println(" Skate button Is not Clicked "+skate);
	}
	@Test(priority=5)
	public void boardOrder() {
		List<WebElement> boards =driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		for (int i=0;i<boards.size();i++) {
			String board=boards.get(i).getText();
			if(board.contains("Skateboard")) {
				System.out.println("Expected Result Skate Board Only "+ board);
			}else {
				System.out.println("Expected Result Others "+ board);
			}
		}
	}
	
	@AfterClass
	public void screenShot ()throws IOException {
		System.out.println("Screenshot");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\SIBI\\eclipse-workspace\\MyntraTestNG\\src\\test\\java\\com\\ajio\\ebay\\ebay.png");
		FileUtils.copyFile(src, target);
	}*/
   
	
}
