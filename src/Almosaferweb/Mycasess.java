package Almosaferweb;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Mycasess extends pramametersClass {
	
	
	@BeforeTest
	public void SetUp() {
		thebeginigofWebsite();
	}
	@Test(description = "this is a happy scenrio",priority =1)
	public void CheckTheDefultLanguageEn() {
		checkthelanguageFunction("en");
	}
	@Test(description = "this is a sad scenrio",priority =2)
	public void CheckTheDefultLanguageAr() {
		checkthelanguageFunction("ar");
	}
	@Test(description = "this is to check that currency is SAR",priority=3)
	public void checkTheCurrency() {
		checkthecurrencyFunction("SAR");
	}
	@Test(enabled = true)
	public void checkContactNumber() {
		checkthenumberFunction("+966554400000");
		
	}
	@Test()
	public void checklogoapple() {
		checkthelogoFunction(driver.findElement(By.xpath("//img[@alt='apple-pay']")));
	
	}
	@Test(enabled = false)
	public void HotelTableNotSelectorTest() {
		WebElement HotelTob = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualSelectAreaValue=HotelTob.getAttribute("aria-selected");
	String ExpectedSelctorAreaValue ="false";
	Assert.assertEquals(ActualSelectAreaValue, ExpectedSelctorAreaValue);
	}
	@Test()
	public void CheckTheDepatureAndArriveDate() {
		WebElement ActualDepatureDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		int ActualDepatureDateValue = Integer.parseInt(ActualDepatureDate.getText());
		int ActualReturnDateValue = Integer.parseInt(ActualReturnDate.getText());
		LocalDate today =  LocalDate.now();
		int ExpectedDepatureDate = today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate = today.plusDays(2).getDayOfMonth();
		Assert.assertEquals(ActualReturnDateValue, ExpectedReturnDate);
		Assert.assertEquals(ActualDepatureDateValue, ExpectedDepatureDate);
		String dayElementOnTheWebsite = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']")).getText().toUpperCase();
	    Assert.assertEquals(dayElementOnTheWebsite, today.plusDays(1).getDayOfWeek().toString());
	}
	@Test(enabled=false)
	public void changeTheLanguageOfTheWebsiteRandomly() throws InterruptedException {
		String []myUrls = {"https://www.almosafer.com/ar?ncr=1"," https://www.almosafer.com/en"};
		int randomIndex = rand.nextInt(myUrls.length);
		driver.get(myUrls[randomIndex]);	
	
		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		Thread.sleep(2000);
		WebElement SearchHotelInout = driver.findElement(By.className("phbroq-2"));
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		if(driver.getCurrentUrl().contains("ar")) {
		
			Assert.assertEquals(ActualLanguage, "ar");
			 SearchHotelInout.sendKeys(arabicCitiesName[randomArabic]);
			 Thread.sleep(2000);
			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();
			
		}
		
		else {
			
			Assert.assertEquals(ActualLanguage, "en");
			SearchHotelInout.sendKeys(englishCitiesName[randomEnglish]);
			 Thread.sleep(2000);
			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();
		}
		WebElement vistorInput = driver.findElement(By.className("tln3e3-1"));
		Select myselector = new Select(vistorInput);
	    int randomIndexForVistor =rand.nextInt(2);
	    myselector.selectByIndex(randomIndexForVistor);
	    WebElement Searchbutton = driver.findElement(By.className("sc-1vkdpp9-6"));
	    Searchbutton.click();
	    
	    Thread.sleep(35000);
	    String Hotelsearchresult = driver.findElement(By.className("sc-cClmTo")).getText();
	    if(driver.getCurrentUrl().contains("ar")) {
	    
	   boolean ActualResult= Hotelsearchresult.contains("وجدنا");
	   boolean ExpectedResult =true;
	   Assert.assertEquals(ActualResult, ExpectedResult);
	   WebElement lowerprice =driver.findElement(By.className(" bkDVyx"));
	   lowerprice.click();
	    }else {
	    boolean ActualResult=Hotelsearchresult.contains("found");
	    boolean ExpectedResult =true;
		   Assert.assertEquals(ActualResult, ExpectedResult);
		   WebElement lowerprice =driver.findElement(By.className("jurIdk"));
		   lowerprice.click();
		   Thread.sleep(3000);
	    }
	    WebElement pricesection =driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col"));
	    List<WebElement>myprices =pricesection.findElements(By.className("Price_Value"));
	  
	    	int lowprice = Integer.parseInt(myprices.get(0).getText());
	    	int highprice = Integer.parseInt(myprices.get(myprices.size()-1).getText());
	    	System.out.println(lowprice +"this is the lowest price");
	    	System.out.println(highprice+"this is the highest price");
	    	Assert.assertEquals(highprice>lowprice, true);
	    
		}
	
	
	@AfterTest
	public void myAfterTest() {
		softassert.assertAll();
	}
}

