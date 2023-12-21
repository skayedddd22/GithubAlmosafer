package Almosaferweb;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class pramametersClass {
	String TheWebsite = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();
	Random rand = new Random();
	String [] arabicCitiesName = {"دبي","جده"};
	String [] englishCitiesName = {"dubai","jeddah","riyadh"};
	 int randomArabic = rand.nextInt(arabicCitiesName.length);
	int randomEnglish = rand.nextInt(englishCitiesName.length);

public void thebeginigofWebsite() {
	driver.manage().window().maximize();
	driver.get(TheWebsite);
	WebElement WelcomeScreen = driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
	WelcomeScreen.click();
}
public void checkthelanguageFunction(String TheLanguagINeedCheck) {
	String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
	String ExpectedLang = TheLanguagINeedCheck;
	softassert.assertEquals(ActualLang,ExpectedLang,"this is to chek language" );
}
public void checkthecurrencyFunction(String ExpectedCurrencyy) {
	String ExpectedCurrencyINeedToCheck = "SAR";
	WebElement CurrencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
	String ActualCurrency =  CurrencyElement.getText();
	softassert.assertEquals(ActualCurrency, ExpectedCurrencyINeedToCheck );
}
public void checkthenumberFunction(String ContactNumber) {
	String expectedContactnumber=ContactNumber;
	WebElement ContactNumberelement = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
	String Actualcontactnumber = ContactNumberelement.getText();
	softassert.assertEquals(Actualcontactnumber, expectedContactnumber);
}
public void checkthelogoFunction(WebElement thelogo) {
	WebElement expextedlogotocheck=thelogo;
	boolean logoDisplayed = expextedlogotocheck.isDisplayed();
	boolean expectedlogoisdisplyed =true;
	softassert.assertEquals(logoDisplayed, expectedlogoisdisplyed);
}


}