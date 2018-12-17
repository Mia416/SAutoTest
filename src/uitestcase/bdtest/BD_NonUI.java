package uitestcase.bdtest;

import static org.testng.Assert.fail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import uitestcase.NonUITest;

public class BD_NonUI extends NonUITest {
	
    String BD_page = "https://www.baidu.com";
	
	@BeforeClass
    public void initParams()
	{
	    String BD_page = "https://www.baidu.com";
	}

	@Test
	public void MainPage_NonUI()
	{
		
		 try {
	            driver = initChromeDriver();	            
	            driver.get(BD_page);
	            BD_page bd = new BD_page(driver,logger);
	            bd.verify_main();//verify main page has been loaded.
	            bd.search_case1();//click button to search
	            bd.verify_searchresult(); //verify search result.
	        } catch (Exception ex) {
	            fail(ex.getMessage());
	        }
		 
		
	}

}
