package test.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;




public class TestMain {

	//js support--done
	//snapshot support--done
	//proxy support
	//scrolldown support--done
	//new page navigation support---done
	//send email
	//log4j--done
	//sso
	//cookie
	public static void main(String[] args) {


		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
	}

}
