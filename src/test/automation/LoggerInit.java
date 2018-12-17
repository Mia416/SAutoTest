package test.automation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LoggerInit {
	
	public static Logger getLog(Class<?> clazz) {
	       
        Logger logger = Logger.getLogger(clazz); 
        logger.removeAllAppenders(); 
        logger.setLevel(Level.ALL); 
        logger.setAdditivity(true); 
        
        FileAppender appender = new RollingFileAppender(); 
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("[%d{yyyy-MM-dd HH:mm:ss}] %p %l : %m%n"); 
        appender.setLayout(layout);
        
        appender.setFile(getTime("yyyy-MM-dd") + ".log");  
        appender.setEncoding("UTF-8"); 
        appender.setAppend(true);  
        appender.activateOptions();  
        
        logger.addAppender(appender); 
        return logger;
    }
    
    private static String getTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
    }

}
