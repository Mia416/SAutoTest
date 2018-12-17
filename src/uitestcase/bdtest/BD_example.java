package uitestcase.bdtest;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.automation.WebPage;
import uitestcase.UITestCase;

import static org.testng.Assert.fail;

@Test
public class BD_example extends UITestCase {
	
	String BD_page = "https://www.baidu.com";
	
	@BeforeClass
    public void initParams()
	{
	    String BD_page = "https://www.baidu.com";
	}

	@Test
	public void MainPage()
	{
		
		 try {
	            driver = initChromeDriver();	            
	            driver.get(BD_page);//open page
	            BD_page bd = new BD_page(driver,logger);
	            String winHandleBefore = driver.getWindowHandle();//get currently window handle
	            System.out.println(winHandleBefore);
	            
	            bd.verify_main();//verify main page has been loaded.
	            bd.search_case1();//click button to search
	            bd.verify_searchresult(); //verify search result.
	            bd.scroll_down();//scroll down
	            bd.verify_scrolldownresult(); //verify scroll down result
	            bd.scroll_up();
	            String newwindowhandle = bd.click_firstresult();
	            
	            bd.switch_window_back(winHandleBefore);//back to before page
	            bd.list_window();//
	        } catch (Exception ex) {
	            fail(ex.getMessage());
	        }
		 
		
	}

}
