package uitestcase;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import java.util.Map;
import java.util.Properties;

public class TestCase {
	
	protected static Properties prop = null;
    protected static Logger logger = null;

    protected static String workspace = null;
    protected static boolean telemetryEnabled = false;
    protected static String t2ProxyServer = null;
    protected static String t2ProxyPort = null;
    protected static String t2Url = null;
    protected static String telemetryKeyStoreFilePath = null;
    protected static String telemetryKeyStorePassword = null;
    protected static String t2FleetName = null;
   

    @BeforeSuite
    public void setup(ITestContext context) throws Exception {

         if(logger == null) {
              //logger = LoggerFactory.getLogger(TestCase.class);
              logger = Logger.getLogger(TestCase.class);
         }
         if(prop == null) {
              prop = new Properties();
              Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
              
              System.out.println("Initiating prop - putting " + params.size() + " parameters to the prop");
              logger.info("Initiating prop - putting " + params.size() + " parameters to the prop");
              prop.putAll(params);
         } else {
              logger.info("prop is already initiated with " + prop.size() + " parameters");
         }
       
         
    }


}
