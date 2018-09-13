package brokenLinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FindBrokenLinks {
	
	@Test
	public void VerifyLinks() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\SeleniumProject\\Drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.google.com/");
		
		List<WebElement> links=driver.findElements(By.tagName("a"));
		
		System.out.println("Total Links are "+links.size());
		
		for(int i=0; i<links.size(); i++)
		{
			
			WebElement ele=links.get(i);
			
			String url=ele.getAttribute("href");
			
			verifyLinkActive(url);
		}	

	}
	
	public static void verifyLinkActive(String linkUrl)
	{
		
		try
		{
			URL url=new URL(linkUrl);
			
			HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
			
			httpURLConnect.setConnectTimeout(3000);
			
			httpURLConnect.connect();
			
			if(httpURLConnect.getResponseCode()==200)
			{
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
			}	
			if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
			{
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
			}	
		}catch (Exception e) {
			
		}
	}

}
