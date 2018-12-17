package uitestcase;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static org.testng.Assert.fail;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.Reporter;

public class UITestCase extends TestCase {
	
    protected static String chromeBinary = null;
    protected static String chromeDriver = null;

    protected static boolean mobHttpProxy = false;
    protected static String chainedProxyHost = null;
    protected static int chainedProxyPort = -1;

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
            System.setProperty("webdriver.chrome.driver", chromeDriver);

            ChromeOptions options = new ChromeOptions();
            options.setBinary(chromeBinary);
            options.addArguments(new String[]{"disable-infobars", "--no-sandbox"});
            options.addArguments("--lang=en-us");    
            options.addArguments("--start-maximized");
            if (prop.getProperty("chrome.options") != null) {
                options.addArguments(new String[]{prop.getProperty("chrome.options")});
            }
            if (driver != null || proxy != null) {
                logger.info("Cleaning up remaining driver & proxy before starting new ones");
                cleanup();
            }
          

            driver = new ChromeDriver(options);
            logger.info("Driver is instantiated");

      
            Thread.sleep(2 * 1000);
            //driver.manage().window().setPosition(new Point(0, 0));
            //Dimension d = new Dimension(1300, 1117);
            //driver.manage().window().setSize(d);
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
            if(mobHttpProxy && proxy != null) {
                try {
                    File harDump = new File("results/" + currCaseName + "_har");
                    proxy.getHar().writeTo(harDump);
                } catch (IOException ex) {
                    logger.error("Error when creating har dump", ex);
                }
            }
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
