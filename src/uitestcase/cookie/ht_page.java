package uitestcase.cookie;

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

public class ht_page extends WebPage{
	
	public ht_page(WebDriver driver, Logger logger) {
		super(driver, logger);
		this.driver = driver;
	}
	
	public void add_cookie()
	{
		//driver.manage().addCookie("ID","");
	}
	
	
	public void sign_in_cookie() throws InterruptedException    
	{
		File file = new File("Hotmail_Cookies.data");							
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
	        Thread.sleep(8000);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	      finally
		{
	    	  driver.navigate().refresh();
	        //driver.get("https://outlook.live.com/owa/");	
	        Thread.sleep(8000);
		}
        

	
	}	

	
	public void sign_in() throws InterruptedException, IOException
	{
		
        safeClick("xpath", "/html/body/section/div/div/div[2]/a[2]");
        Thread.sleep(6000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        safeEnterText("name","loginfmt","xiyurui@hotmail.com");
        Thread.sleep(6000);
        safeClick("id", "idSIButton9");
        Thread.sleep(6000);
        safeEnterText("name","passwd","801106");
        Thread.sleep(6000);
        safeClick("id", "idSIButton9");
        Thread.sleep(6000);
        
        File file = new File("Hotmail_Cookies.data");	
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


 