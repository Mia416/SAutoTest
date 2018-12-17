package uitestcase.cookie;

import static org.testng.Assert.fail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import uitestcase.UITestCase;

public class git_login extends UITestCase {
	
	String ht_page = "https://github.com/login";
	
	@BeforeClass
    public void initParams()
	{
	    String ht_page = "https://github.com/login";
	}
	
	
	@Test
	public void Login_cookie()
	{
        try {
			driver = initChromeDriver();
			 driver.get(ht_page);//open page
			 Thread.sleep(8000);
		        git_page hp = new git_page(driver,logger);
		        hp.sign_in_cookie();
		        Thread.sleep(8000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	            
       
       
        }

	@Test
	public void MainPage()
	{
		
		 try {
	            driver = initChromeDriver();	            
	            driver.get(ht_page);//open page
	            Thread.sleep(8000);
	            git_page hp = new git_page(driver,logger);
	            hp.sign_in();
	            //BD_page bd = new BD_page(driver,logger);
	             
	            

	        } catch (Exception ex) {
	            fail(ex.getMessage());
	        }
		 
		
	}


}
