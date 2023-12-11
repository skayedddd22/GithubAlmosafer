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

public class Mycasess {
	String TheWebsite = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();
	Random rand = new Random();
	String [] arabicCitiesName = {"دبي","جده"};
	String [] englishCitiesName = {"dubai","jeddah","riyadh"};
	 int randomArabic = rand.nextInt(arabicCitiesName.length);
	int randomEnglish = rand.nextInt(englishCitiesName.length);
	
	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();
		driver.get(TheWebsite);
		WebElement WelcomeScreen = driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		WelcomeScreen.click();
	}
	@Test(enabled = false)
	public void CheckTheLanguage() {
		String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLang = "en";
		softassert.assertEquals(ActualLang,ExpectedLang,"this is to chek language" );
	}
	@Test(enabled = false)
	public void checkTheCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement CurrencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String ActualCurrency =  CurrencyElement.getText();
		softassert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	@Test(enabled = false)
	public void checkContactNumber() {
		String ExpectedContactNumber = "+966554400000";
		WebElement ContactNumberelement=driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualContactNumber = ContactNumberelement.getText();
		softassert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
	}
	@Test(enabled = false)
	public void checkQitafflogo() {
	WebElement checkqittlogo= driver.findElement(By.xpath("//div[@class='sc-dhVevo fMVfql']"));
	boolean isQitafflogoDisplayed = checkqittlogo.isDisplayed();
	System.out.println(isQitafflogoDisplayed );
	
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
	@Test(invocationCount = 1)
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
			WebElement vistorInput = driver.findElement(By.className("tln3e3-1"));
			Select myselector = new Select(vistorInput);
			myselector.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
		}else {
			
			Assert.assertEquals(ActualLanguage, "en");
			SearchHotelInout.sendKeys(englishCitiesName[randomEnglish]);
			 Thread.sleep(2000);
			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();
			WebElement vistorInput = driver.findElement(By.className("tln3e3-1"));
			Select myselector = new Select(vistorInput);
			myselector.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		}
		}
	
	
	@AfterTest
	public void myAfterTest() {
		softassert.assertAll();
	}
}

