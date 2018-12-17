package uitestcase.bdtest;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import test.automation.WebPage;

public class BD_page extends WebPage {

	public BD_page(WebDriver driver, Logger logger) {
		super(driver, logger);
		this.driver = driver;
	}
	
	public void search_case1() throws InterruptedException{
        safeEnterText("id", "kw", "automation test");
        safeClick("id", "su");
        Thread.sleep(6000);
        String s = safeGetText("class","nums_text");
        Thread.sleep(6000);

        System.out.println(s);
         
	}
	
	public void verify_searchresult()
	{
		String s = safeGetText("class","nums_text");
		System.out.println(s);
	}
	
	public void verify_scrolldownresult()
	{
		String s = safeGetText("xpath","//*[@id='page']/a[10]");
		System.out.println(s);
	}
	
	public void verify_main(){
		
		String s = safeGetValue("id","su");
		System.out.println(s);
		
		
	}
	public void list_window()
	{
		SwtichToWindowUseingTitle("Test automation - Wikipedia");
	}
	
	public void switch_window_back(String handle) throws InterruptedException
	{
		switch_window(handle);
		Thread.sleep(8000);
	}
	
	public String click_firstresult() throws InterruptedException
	{
		safeClick("xpath","//*[@id='1']/h3/a");
		Thread.sleep(8000);
		return driver.getWindowHandle();
		
	}
	
	public void scroll_down() throws InterruptedException
	{
        Scroll_down(driver);
        Thread.sleep(6000);
	}
	public void scroll_up() throws InterruptedException
	{
        Scroll_up(driver);
        Thread.sleep(6000);
	}
	

}
