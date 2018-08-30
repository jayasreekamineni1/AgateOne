package logintests;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Logintotopup 
{
	WebDriver driver;
	
	@BeforeMethod
	public void LaunchBrowser()
	{
		
		//Instantiating Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jayas\\Desktop\\Selenium\\chromedriver.exe");
		
		//Create Chrome Browser Driver
		driver = new ChromeDriver();
		driver.get(" http://popbitch.agate.one/2017/10/the-harder-they-fall-2/");
		
	}
	
	@Test
	public void loginandtopup() throws InterruptedException
	{
		
	//getting current url to local variable
				
		String siteurl= driver.getCurrentUrl();
				
	//check if the required Login url is opened
				
		Assert.assertEquals(siteurl,"http://popbitch.agate.one/2017/10/the-harder-they-fall-2/");
	
	//Click on the Login button	
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"post-37523\"]/div/div[2]/span/div/div/div[2]/a[1]")).click();
		String Loginpage= driver.getCurrentUrl();
		
	//check if the Login page has been opened	
		Assert.assertEquals(Loginpage, "https://wallet-notices-staging.agate.io/my-agate/sign-in?publication_name=Popbitch%20Staging&pub_id=popbitch&url_from=http%3A%2F%2Fpopbitch.agate.one%2F2017%2F10%2Fthe-harder-they-fall-2%2F");
				
	//Enter Username and Password
				
		driver.findElement(By.id("email")).sendKeys("test+1534520687551@example.com");
		driver.findElement(By.id("password")).sendKeys("8cjsdk@88");
		driver.findElement(By.id("signInSubmit")).click();
				
	//Check if the user logged in successfully	
		Thread.sleep(5000);
		String Loggedinurl = driver.getCurrentUrl();
		Assert.assertEquals(Loggedinurl, "http://popbitch.agate.one/2017/10/the-harder-they-fall-2/");
	
			
		Thread.sleep(2000);
		String parentwin = driver.getWindowHandle();
	
	//Click on the SVG element
		WebElement svgObject = driver.findElement(By.id("agateposter"));
		Actions builder = new Actions(driver);
		builder.click(svgObject).build().perform();
			
	//Get balance before topup
		Thread.sleep(2000);
		WebElement mapObject2 = driver.findElement(By.xpath("//*[@id=\"wallet\"]/div[2]/div/div[1]/div/div/div/span"));
		String Balance1 = mapObject2.getText();
		
	//Delete the currency(£) symbol
		Pattern p = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
		Matcher m = p.matcher(Balance1);
		m.matches();
		String s_num = m.group(1).replace(",", "");
		System.out.println(s_num);
		    
			
	
			
	//click topup
		Thread.sleep(4000);
		WebElement mapObject = driver.findElement(By.xpath("//*[@id=\"wallet\"]/div[2]/div/button"));
		Actions builder3 = new Actions(driver);
		builder3.click(mapObject).build().perform();
			
			
			
	//change to current window
			Thread.sleep(4000);		
			Set<String> winhan = driver.getWindowHandles();
			System.out.println(winhan);
			for (String windowHandle : driver.getWindowHandles())
			{

	// Switch to pop-up window
			driver.switchTo().window(windowHandle);

			}
			
			
	//click top up
			driver.findElement(By.id("submit-button")).click();
			Thread.sleep(2000);
			
	//Switch to parent window		
			driver.switchTo().window(parentwin);
			
			
	//getting balance after topup
			Thread.sleep(4000);
			WebElement mapObject3 = driver.findElement(By.xpath("//*[@id=\"wallet\"]/div[2]/div/div[1]/div/div/div/span"));
		    String Balance2 = mapObject3.getText();
		  
	//Deleting Currency(£) Symbol	    
		    Pattern p1 = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
		    Matcher m1 = p1.matcher(Balance2);
		    m1.matches();
		    String s_num1 = m1.group(1).replace(",", "");
		    
	//Converting String to Double for Comparision 
		    double iTest = Double.parseDouble(s_num1);
		    
			double iTest2= Double.parseDouble(s_num);
			
			double result = iTest-iTest2;
			
	//Asserting that the Balance has been incremented by £3
			Assert.assertEquals(result, 3.0);
			System.out.println("Your account has been incremented by £"+ result);//£3.0
			
	}

	
	
	
	
}
	
	
	
	
