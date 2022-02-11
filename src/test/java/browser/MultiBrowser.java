package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultiBrowser {

	WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");

	@Parameters("browserName")
	@BeforeTest
	public void setup(String browserName) {
		System.out.println("Browser name is: " + browserName);
		System.out.println("Trhead id: " + Thread.currentThread().getId());
		

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
			//driver = new FirefoxDriver();
			FirefoxOptions opts = new FirefoxOptions();
			opts.addArguments("-private");
			driver = new FirefoxDriver(opts);

			
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "src/test/resources/driver/msedgedriver.exe");
			//driver = new EdgeDriver();
			EdgeOptions edgeOptions = new EdgeOptions();
	        edgeOptions.addArguments("-inprivate");
	        driver = new EdgeDriver(edgeOptions); 
		}
	}

	@Test
	public void test1() throws InterruptedException {
		/*driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		Thread.sleep(4000);
		try {
			Thread.sleep(4000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		driver.manage().window().maximize();
		driver.navigate().to("https://www.mercadolibre.com.co/");
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Mercado Libre Colombia - Envíos Gratis en el día";
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		String title = driver.getTitle();
		System.out.println("El título de la página es:" + title);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("(//button[normalize-space()='Entendido'])[1]")).click();
		driver.findElement(By.name("as_word")).sendKeys("monitor pc 27 pulgadas");
		driver.findElement(By.xpath("//button/div")).click();
		driver.findElement(By.partialLinkText("Samsung Cf390 Series 27 ''monitor De Escritorio Curvado")).click();
		
	}

	@AfterTest
	public void teardown() {
		driver.close();
		System.out.println("Test Completed Successfuly");
	}

}
