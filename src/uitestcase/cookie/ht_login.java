package uitestcase.cookie;

import static org.testng.Assert.fail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import uitestcase.UITestCase;
import uitestcase.bdtest.BD_page;

public class ht_login extends UITestCase{
	
String ht_page = "https://outlook.live.com/owa/";
	
	@BeforeClass
    public void initParams()
	{
	    String ht_page = "https://outlook.live.com/owa/";
	}
	
	
	@Test
	public void Login_cookie()
	{
        try {
			driver = initChromeDriver();
			 driver.get(ht_page);//open page
			 Thread.sleep(8000);
		        ht_page hp = new ht_page(driver,logger);
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
	            ht_page hp = new ht_page(driver,logger);
	            hp.sign_in();
	            //BD_page bd = new BD_page(driver,logger);
	             
	            

	        } catch (Exception ex) {
	            fail(ex.getMessage());
	        }
		 
		
	}

}
