package uitestcase;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import net.lightbody.bmp.BrowserMobProxy;

public class NonUITest extends TestCase {
	 
 
		
		    protected static String chromeBinary = null;
		    protected static String chromeDriver = null;
		    protected static String phantomjsBinary = null;

	

		    protected WebElement elem = null;
		    protected WebDriver driver = null;
		    protected BrowserMobProxy proxy = null;
		    protected String imagepath = null ;

		    public WebDriver initChromeDriver() throws Exception {

		    	  if (chromeBinary == null) {
		              if(prop.getProperty("chrome.binary") != null) {
		                  chromeBinary = prop.getProperty("chrome.binary");
		                  logger.trace("chromeBinary = " + chromeBinary);
		              } else {
		                  fail("Missing chromeBinary parameter in testng.xml");
		              }
		          } else {
		              logger.trace("chromeBinary has already been initiated");
		          }   	  


		    	  if (phantomjsBinary == null) {
		    	              if(prop.getProperty("phantomjsBinary") != null) {
		    	            	  phantomjsBinary = prop.getProperty("phantomjsBinary");
		    	                  logger.trace("phantomjsBinary = " + imagepath);
		    	              } else {
		    	                  fail("Missing phantomjsBinary parameter in testng.xml");
		    	              }
		    	          } else {
		    	              logger.trace("phantomjsBinary has already been initiated");
		    	          }
		    	  
		    	  if (imagepath == null) {
    	              if(prop.getProperty("imagepath") != null) {
    	            	  imagepath = prop.getProperty("imagepath");
    	                  logger.trace("imagepath = " + imagepath);
    	              } else {
    	                  fail("Missing imagepath parameter in testng.xml");
    	              }
    	          } else {
    	              logger.trace("imagepath has already been initiated");
    	          }
		    
		        if (chromeDriver == null) {
		            if(prop.getProperty("chrome.driver") != null) {
		                chromeDriver = prop.getProperty("chrome.driver");
		                logger.trace("chromeDriver = " + chromeDriver);
		            } else {
		                fail("Missing chromeDriver parameter in in testng.xml");
		            }
		        } else {
		            logger.trace("chromeDriver has already been initiated");
		        }

		    
		        try {
		        	
		        	DesiredCapabilities caps = new DesiredCapabilities();
		        	caps.setJavascriptEnabled(true);                
		        	caps.setCapability("takesScreenshot", true);  
		        	caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,phantomjsBinary);
		        	
		            
		            System.setProperty("phantomjs.binary.path", phantomjsBinary);          
		            driver = new PhantomJSDriver(caps);       
		    	    logger.info("Driver is instantiated");
                    Thread.sleep(2 * 1000);
		          
		        } catch (Exception ex) {
		            logger.error("Failed to instantiate the driver", ex);
		        }
		        return driver;
		    }

		    @AfterMethod
		    public void tearDown(ITestResult result) {
		    	 //takeScreenshot();
		        if (result.getStatus() == ITestResult.FAILURE) {
		            takeScreenshot();
		            String currCaseName = result.getName();
		          
		        }
		        cleanup();
		    }

		    public void takeScreenshot() {
		        try {
		            Thread.sleep(120);
		            Robot r = new Robot();
		            //Saves screenshot to desired path

		            long timeStamp = new Date().getTime();
		            String path =  timeStamp + ".jpg";

		            // Used to get ScreenSize and capture image
		            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		            BufferedImage Image = r.createScreenCapture(capture);
		            ImageIO.write(Image, "jpg", new File(imagepath+path));
		            logger.info("Screenshot " + timeStamp + ".jpg has been saved");
		        } catch (AWTException | IOException | InterruptedException ex) {
		            logger.error("Failed to take screenshot", ex);
		        }
		    }

		    private void cleanup() {
		        try {
		            if(driver != null) {
		                for (String handle : driver.getWindowHandles()) {
		                    driver.switchTo().window(handle);
		                    String title = driver.getTitle();
		                    driver.close();
		                    logger.info("Closed browser tab with title: " + title);
		                    Thread.sleep(1000);
		                }
		            }
		        } catch(Exception exCloseBrowser){
		            logger.error("Closing out browser failed", exCloseBrowser);
		        }
		        //driver quit
		        try {
		            if(driver != null) {
		                driver.quit();
		                logger.info("Completed driver.quit");
		                Thread.sleep(1000);
		            }
		            driver = null;
		        } catch(Exception exQuitBrowser){
		            logger.info("Quitting driver failed", exQuitBrowser);
		        }
		       
		    }	

}
