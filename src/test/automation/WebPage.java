package test.automation;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

 

public class WebPage {
	
	protected WebDriver driver = null;
	  protected WebElement elem = null;
	  

	  protected static final int webDriverWaitTime = 20; // this is 20 secs

	  protected Logger logger = null;

	  public WebPage(WebDriver driver, Logger logger) {
	    this.driver = driver;
	    this.logger = logger;
	  }
	  

	  


	  /**
	   * Wait for element to be visible
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected WebElement waitForElementToBeVisible(String locatorKey, String locatorVal) {
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    switch (locatorKey) {
	      case "id":
	        elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorVal)));
	        break;
	      case "xpath":
	        elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorVal)));
	        break;
	      case "css":
	        elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorVal)));
	        break;
	      default:
	        break;
	    }
	    return elem;
	  }

	  /**
	   * Wait for element to be clickable
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected WebElement waitForElementToBeClickable(String locatorKey, String locatorVal) {
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    switch (locatorKey) {
	      case "id":
	        elem = wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVal)));
	        break;
	      case "xpath":
	        elem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVal)));
	        break;
	      case "css":
	        elem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorVal)));
	        break;
	      default:
	        break;
	    }
	    return elem;
	  }

	  /**
	   * Wait for element to be invisible
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   */
	  protected void waitForElementToBeInvisible(String locatorKey, String locatorVal) {
	    boolean disappeared = false;
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    switch (locatorKey) {
	      case "id":
	        disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorVal)));
	        break;
	      case "xpath":
	        disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorVal)));
	        break;
	      case "css":
	        disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorVal)));
	        break;
	      default:
	        break;
	    }
	    if (!disappeared) {
	      logger.error("Timeout waiting for " + locatorVal + "to disappear.");
	    }
	  }

	  /**
	   * Locate an element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected WebElement locateElement(String locatorKey, String locatorVal) {
	    switch (locatorKey) {
	      case "id":
	        elem = driver.findElement(By.id(locatorVal));
	        break;
	      case "xpath":
	        elem = driver.findElement(By.xpath(locatorVal));
	        break;
	      case "css":
	        elem = driver.findElement(By.cssSelector(locatorVal));
	        break;
	      case "name":
	        elem = driver.findElement(By.name(locatorVal));
	        break;
	      case "class":
		    elem = driver.findElement(By.className(locatorVal));
		     break;
	      case "linktext":
		    elem = driver.findElement(By.linkText(locatorVal));
		     break;
	      default:
	        break;
	    }
	    return elem;
	  }

	  /**
	   * Locate elements with same locator value
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected List<WebElement> locateElements(String locatorKey, String locatorVal) {
	    List<WebElement> allElem = null;
	    switch (locatorKey) {
	      case "xpath":
	        allElem = driver.findElements(By.xpath(locatorVal));
	        break;
	      case "css":
	        allElem = driver.findElements(By.cssSelector(locatorVal));
	        break;
	      case "tagname":
	        allElem = driver.findElements(By.tagName(locatorVal));
	        break;
	      case "classname":
	        allElem = driver.findElements(By.className(locatorVal));
	        break;
	      case "partiallinktext":
	        allElem = driver.findElements(By.partialLinkText(locatorVal));
	      default:
	        break;
	    }
	    if (allElem.size() == 0) {
	      logger.trace("No elements with given locator (" + locatorKey + ": " + locatorVal + ") can be found");
	    }
	    return allElem;
	  }

	  /**
	   * Saft click on an element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   */
	  protected void safeClick(String locatorKey, String locatorVal) {
	    waitForElementToBeClickable(locatorKey, locatorVal);
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem = locateElement(locatorKey, locatorVal);
	        elem.click();
	        logger.info("Clicked element " + "[" + locatorKey + ":" + locatorVal + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	  }
	  
	  
	  protected void switch_window(String windowTitle) {
		  
		  driver.switchTo().window(windowTitle);
		  
		  for (String handle1 : driver.getWindowHandles()) {
			  
	          System.out.println(handle1);   
	           
	 
	          }
		  }
	  
	  protected void SwtichToWindowUseingTitle(String strTitle)
      {
          String currentWindow = driver.getWindowHandle();
          Set<String> availableWindows = driver.getWindowHandles();
          if (availableWindows.size() != 0)
          {
              for (String windowId : availableWindows)
              {
            	  System.out.println(driver.switchTo().window(windowId).getTitle());
                  if (driver.switchTo().window(windowId).getTitle() == strTitle)
                  {
                	  driver.switchTo().window(windowId);
                  }
                       
              }
              
          }
         
      }
	  
	  /**
	   * Safe get text of an element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected String safeGetValue(String locatorKey, String locatorVal) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    String text = null;
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem = locateElement(locatorKey, locatorVal);
	        text = elem.getAttribute("value");
	        logger.info("Got the inner text \"" + text + "\" of element " + "[" + locatorKey + ":" + locatorVal + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	    return text;
	  }

	  /**
	   * Safe get text of an element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return
	   */
	  protected String safeGetText(String locatorKey, String locatorVal) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    String text = null;
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem = locateElement(locatorKey, locatorVal);
	        text = elem.getText();
	        logger.info("Got the inner text \"" + text + "\" of element " + "[" + locatorKey + ":" + locatorVal + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	    return text;
	  }

	  /**
	   * Safe enter text to an element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @param content
	   */
	  protected void safeEnterText(String locatorKey, String locatorVal, String content) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem = locateElement(locatorKey, locatorVal);
	        elem.sendKeys(content);
	        logger.info("Entered " + content + " in element " + "[" + locatorKey + ":" + locatorVal + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	  }

	  /**
	   * Move to a desired element
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   */
	  protected void moveToElement(String locatorKey, String locatorVal) {
	    Actions action = new Actions(driver);
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    try {
	      switch (locatorKey) {
	        case "id":
	          action.moveToElement(driver.findElement(By.id(locatorVal))).build().perform();
	          break;
	        case "name":
	          action.moveToElement(driver.findElement(By.name(locatorVal))).build().perform();
	          break;
	        case "xpath":
	          action.moveToElement(driver.findElement(By.xpath(locatorVal))).build().perform();
	          break;
	        case "css":
	          action.moveToElement(driver.findElement(By.cssSelector(locatorVal))).build().perform();
	          break;
	        default:
	          break;
	      }
	      Thread.sleep(500);
	    } catch (WebDriverException e) {
	      logger.info("Exception in locator movement. Please manully check.");
	    } catch (Exception e) {
	      logger.info("Exception in locator movement. Please manually check.");
	    }
	  }

	  /**
	   * Select the option from the dropdown box
	   * 
	   * @param locatorKey for id, xpath or name
	   * @param locatorVal locator of id or xpath
	   * @param optionLabel
	   */
	  protected void selectOption(String locatorKey, String locatorVal, String optionLabel) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    Select select = null;
	    try {
	      switch (locatorKey) {
	        case "id":
	          select = new Select(driver.findElement(By.id(locatorVal)));
	          break;
	        case "name":
	          select = new Select(driver.findElement(By.name(locatorVal)));
	          break;
	        case "xpath":
	          select = new Select(driver.findElement(By.xpath(locatorVal)));
	          break;
	        case "css":
	          select = new Select(driver.findElement(By.cssSelector(locatorVal)));
	          break;
	        case "class":
	          select = new Select(driver.findElement(By.className(locatorVal)));
	          break;
	        default:
	          break;
	      }
	      select.selectByVisibleText(optionLabel);
	    } catch (Exception ex) {
	      logger.info("Exception in locator movement. Please manually check.");
	    }
	  }

	  /**
	   * Check if the element is visible
	   * 
	   * @param locatorKey
	   * @param locatorVal
	   * @return boolean
	   */
	  protected boolean isElementVisible(String locatorKey, String locatorVal) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    boolean value = false;
	    try {
	      if (elem.isDisplayed()) {
	        logger.info("element: " + elem + " is visible");
	        value = true;
	      } else {
	        value = false;
	      }
	    } catch (WebDriverException e) {
	      logger.info("Exception in check element visible. Please manually check.");
	    }
	    return value;
	  }

	  /**
	   * Enter the VALUE(not text) by providing id or xpath & content in a text box
	   * 
	   * @param locatorKey for id, xpath or name
	   * @param locatorVal locator of id or xpath
	   * @param text locator
	   * @return WebElement
	   */
	  protected WebElement safeEnterValue(String locatorKey, String locatorVal, String text) {
	    WebElement elem = null;
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    System.out.println("enterValue is: " + text);
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    try {
	      switch (locatorKey) {
	        case "id":
	          elem = locateElement("id", locatorVal);
	          js.executeScript("document.getElementById('" + locatorVal + "').value='" + text + "';");
	          break;
	        case "xpath":
	          elem = locateElement("xpath", locatorVal);
	          break;
	        case "name":
	          elem = locateElement("name", locatorVal);
	          break;
	        case "class":
	          elem = locateElement("class", locatorVal);
	          break;
	        case "css":
	          elem = locateElement("css", locatorVal);
	          break;
	        default:
	          break;
	      }
	    } catch (Exception ex) {
	      logger.error("Error in entering value. Please manually check.");
	    }
	    return elem;
	  }

	  /**
	   * Get the visible element on the page while there are multi elements with the same locator
	   * 
	   * @param elements
	   * @return
	   */
	  protected WebElement getVisibleElement(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    int iElementsSize = elements.size();
	    System.out.println("iElementsSize is " + iElementsSize);
	    for (int i = 0; i < iElementsSize; i++) {
	      System.out.println("test" + i);
	      try {
	        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(elements.get(i))));
	        break;
	      } catch (Exception e) {
	        // handle exception however you like
	        logger.error("Exception in waiting for " + elements.get(i).getText() + "to be visible.");
	      }
	    }
	    for (WebElement element : elements) {
	      if (element.isDisplayed()) {
	        System.out.println("Found and assigning to this.elem");
	        elem = element;
	        break;
	      }
	    }
	    return elem;
	  }

	  /**
	   * Click on a webelement
	   * 
	   * @param webElement
	   */
	  protected void safeClick(WebElement webElement) {
	    waitForElementToBeClickable(webElement);
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem.click();
	        logger.info("Clicked element " + "[" + webElement.getText() + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	  }

	  /**
	   * Safe enter text to a webelement
	   * 
	   * @param webElement
	   * @param content
	   */
	  protected void safeEnterText(WebElement webElement, String content) {
	    waitForElementToBeVisible(webElement);
	    for (int i = 1; i <= 3; i++) {
	      try {
	        elem.sendKeys(content);
	        logger.info("Entered " + content + " in element " + "[" + webElement.getText() + "]");
	        break;
	      } catch (StaleElementReferenceException stale) {
	        logger.trace("StaleElementReferenceException at attempt #" + i + ", now retry...");
	      }
	    }
	  }

	  /**
	   * Wait for a webelement to be clickable
	   * 
	   * @param webElement
	   * @return
	   */
	  protected WebElement waitForElementToBeClickable(WebElement webElement) {
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    elem = wait.until(ExpectedConditions.elementToBeClickable(webElement));
	    return elem;
	  }

	  /**
	   * Wait for a webelement to be visible
	   * 
	   * @param webElement
	   * @return
	   */
	  protected WebElement waitForElementToBeVisible(WebElement webElement) {
	    WebDriverWait wait = new WebDriverWait(driver, webDriverWaitTime);
	    elem = wait.until(ExpectedConditions.visibilityOf(webElement));
	    return elem;
	  }
	  
	  protected void Scroll_down(WebDriver driver) {
		  JavascriptExecutor jse = (JavascriptExecutor)driver;
		  jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		  }
	  
	  protected void Scroll_up(WebDriver driver) {
		  JavascriptExecutor jse = (JavascriptExecutor)driver;
		  jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
		  }

	  /**
	   * Enter the TEXT by providing id or xpath & content in a text box
	   * 
	   * @param byattribute for id, xpath or name
	   * @param locator locator of id or xpath
	   * @param text locator
	   * @return WebElement
	   */

	  protected WebElement enterText(String byattribute, String locator, String text) {
	    WebElement elem = null;
	    System.out.println("enterText is: " + text);
	    waitForElementToBeVisible(byattribute, locator);
	    // waitUntilElementVisibleByAttribute(byattribute, locator);
	    try {
	      if (byattribute.equalsIgnoreCase("id")) {
	        elem = getElementbyAttribute("id", locator);
	      } else if (byattribute.equalsIgnoreCase("xpath")) {
	        elem = getElementbyAttribute("xpath", locator);
	      } else if (byattribute.equalsIgnoreCase("name")) {
	        elem = getElementbyAttribute("name", locator);
	      } else if (byattribute.equalsIgnoreCase("class")) {
	        elem = getElementbyAttribute("class", locator);
	      } else if (byattribute.equalsIgnoreCase("css")) {
	        elem = getElementbyAttribute("css", locator);
	      }
	      elem.clear();
	      elem.sendKeys(text);
	    } catch (Exception ex) {
	      logger.trace("enterText is failed, please check...");
	    }
	    return elem;
	  }

	  /**
	   * Get the WebElement by providing its attribute
	   * 
	   * @param byattribute --> id, name or xpath
	   * @param locator --> locator field
	   * @return WebElement
	   */
	  protected WebElement getElementbyAttribute(String byattribute, String locator) {
	    WebElement elem = null;
	    try {
	      waitForElementToBeVisible(byattribute, locator);
	      if (byattribute.equalsIgnoreCase("id")) {
	        System.out.println("id locator: " + locator);
	        elem = driver.findElement(By.id(locator));
	      } else if (byattribute.equalsIgnoreCase("name")) {
	        System.out.println("name locator: " + locator);
	        elem = driver.findElement(By.name(locator));
	      } else if (byattribute.equalsIgnoreCase("xpath")) {
	        System.out.println("xpath locator: " + locator);
	        elem = driver.findElement(By.xpath(locator));
	      } else if (byattribute.equalsIgnoreCase("class")) {
	        System.out.println("class locator: " + locator);
	        elem = driver.findElement(By.className(locator));
	      } else if (byattribute.equalsIgnoreCase("css")) {
	        System.out.println("css locator: " + locator);
	        elem = driver.findElement(By.cssSelector(locator));
	      } else if (byattribute.equalsIgnoreCase("linktext")) {
	        System.out.println("linktext locator: " + locator);
	        elem = driver.findElement(By.linkText(locator));
	      }
	    } catch (WebDriverException e) {
	      logger.trace("get element is failed, please check...");
	    }
	    return elem;
	  }

	  protected void safeEnterTextSlowly(String locatorKey, String locatorVal, String content) {
	    waitForElementToBeVisible(locatorKey, locatorVal);
	    elem = locateElement(locatorKey, locatorVal);
	    elem.clear();
	    for (int i = 0; i < content.length(); i++) {
	      try {
	        String subStr = content.substring(i, i + 1);
	        elem.sendKeys(subStr);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }

}
