package uitestcase.cookie;

import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.automation.WebPage;

public class git_page extends WebPage{
	
	public git_page(WebDriver driver, Logger logger) {
		super(driver, logger);
		this.driver = driver;
	}
	
	public void add_cookie()
	{
		//driver.manage().addCookie("ID","");
	}
	
	
	public void sign_in_cookie() throws InterruptedException    
	{
		if (waitForElementToBeVisible("id","login_field")!=null)
		{
		    File file = new File("git_Cookies.data");							
            FileReader fileReader;
		    try {
			       fileReader = new FileReader(file);
			       BufferedReader Buffreader = new BufferedReader(fileReader);	
	               String strline;			
	               while((strline=Buffreader.readLine())!=null){									
	                    StringTokenizer token = new StringTokenizer(strline,";");									
	                    while(token.hasMoreTokens()){					
	                      String name = token.nextToken();					
	                      String value = token.nextToken();					
	                      String domain = token.nextToken();					
	                      String path = token.nextToken();					
	                      Date expiry = null;					
	        		
	                     String val;			
	                     if(!(val=token.nextToken()).equals("null"))
			             {		
	        	            expiry = new Date(val);					
	                     }		
	                     Boolean isSecure = new Boolean(token.nextToken()).		
	        		
	        		     booleanValue();		
	                     Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
	                     System.out.println(ck);
	                     driver.manage().addCookie(ck); // This will add the stored cookie to your current session					
	                    }		
	           }		
	        
		      } catch (FileNotFoundException e) {
			      e.printStackTrace();
		    } catch (IOException e) {
			    e.printStackTrace();
		  }			
	      finally
		{
	    	  driver.navigate().refresh();	        
	    	  if (waitForElementToBeVisible("css","span[title='SAutoTest']")!=null)
	    	  {
	    		  System.out.println("PASS");
	    		  
	    	  }
	    	  else
	    	  {
	    		 fail("log in failed");
	    		 System.out.println("log in failed");
	    	  }
		}
		}
	
	}	

	
	public void sign_in() throws InterruptedException, IOException
	{
		
		
		if (waitForElementToBeVisible("id","login_field")!=null)
		{
		    safeEnterText("id","login_field","Mia416");
		    safeEnterText("id","password","*");
		    safeClick("name", "commit");
        
		    if (waitForElementToBeVisible("css","span[title='SAutoTest']")!=null)
		    {
		    	File file = new File("git_Cookies.data");
		        if (file.exists())
		        {
		        	file.delete();
		        }
                file.createNewFile();
                FileWriter fileWrite = new FileWriter(file);							
                BufferedWriter Bwrite = new BufferedWriter(fileWrite);
        
                for(Cookie ck : driver.manage().getCookies())							
                {			
                      Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));																									
                      Bwrite.newLine();             
                }			
                Bwrite.close();			
                fileWrite.close();	
        
                Set<Cookie> cookies = driver.manage().getCookies();
                System.out.println("Size: " + cookies.size());

                Iterator<Cookie> itr = cookies.iterator();
                while (itr.hasNext()) {
                    Cookie cookie = itr.next();
                    System.out.println(cookie.getName() + "\n" + cookie.getPath()
                    + "\n" + cookie.getDomain() + "\n" + cookie.getValue()
                    + "\n" + cookie.getExpiry());
               }
            }    
	   }
	}

}
