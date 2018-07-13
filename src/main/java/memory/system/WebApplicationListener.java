package memory.system;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import memory.test.Position;
import memory.test.Tracking;


public class WebApplicationListener implements ServletContextListener {
  protected static Log log = 
    LogFactory.getLog(WebApplicationListener.class);

  public final static String PARAM_FILE_APPCONFIG = "file:appConfig";

  private static File ctxRoot;
  private static File appConfigFile;

  public WebApplicationListener() {
    super();
  }

  public void contextInitialized(ServletContextEvent sce) {
    LoggingFilter.configSystemLog(sce.getServletContext(), LoggingFilter.LOG_FILE_PROPERTY, "The WebApp MEMORY-TEST log services are setting-up");
    //Locale.setDefault(Locale.ITALIAN);
    ctxRoot = new File(sce.getServletContext().getRealPath("/"));

    File appFile = new File(ctxRoot, sce.getServletContext().getInitParameter(PARAM_FILE_APPCONFIG));
    if(!appFile.exists() || !appFile.isFile() || !appFile.canRead()) {
      log.error("Configuration file not found for the application");
      return;
    } else appConfigFile = appFile;
    
    buildRealTime();
  }

  private void buildRealTime() {
  	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
  	String toDay = dateFormatter.format(new Date()).replaceAll("/", "_");
  	try {
  		Class.forName("org.postgresql.Driver");
  	} catch (final Exception e) {
  		throw new RuntimeException("Driver failure");
  	}
  	
  	Tracking entity1 = new Tracking();
		entity1.setEntity_id("VOL1");
		entity1.setTrip_id(toDay+"_VOL1");
		entity1.setVehicle_id("GPS1");
		entity1.setLabel("MARIO ROSSI");
		entity1.setTime_posix(10);
		entity1.setBearing(170);

		Tracking entity2 = new Tracking();
		entity2.setEntity_id("VOL2");
		entity2.setTrip_id(toDay+"_VOL2");
		entity2.setVehicle_id("GPS2");
		entity2.setLabel("GIOVANNA BLU");
		entity2.setTime_posix(20);
		entity2.setBearing(170);
		
		Tracking entity3 = new Tracking();
		entity3.setEntity_id("VOL3");
		entity3.setTrip_id(toDay+"_VOL3");
		entity3.setVehicle_id("GPS3");
		entity3.setLabel("GIOVANNI BROWN");
		entity3.setTime_posix(20);
		entity3.setBearing(170);
		
		Tracking entity4 = new Tracking();
		entity4.setEntity_id("VOL4");
		entity4.setTrip_id(toDay+"_VOL4");
		entity4.setVehicle_id("GPS4");
		entity4.setLabel("ANTONIO NERI");
		entity4.setTime_posix(20);
		entity4.setBearing(170);
		
		final Position volontario1 = new Position(entity1);
		final Position volontario2 = new Position(entity2);
		final Position volontario3 = new Position(entity3);
		final Position volontario4 = new Position(entity4);
		
//		volontario1.startTracking();
//		volontario2.startTracking();
//		volontario3.startTracking();
//		volontario4.startTracking();
	}

	public void contextDestroyed(ServletContextEvent cse) {

  }

  public static File getFileConfiguration() {
    return appConfigFile;
  }

  public static Configuration getAppConfiguration() {
    try {
      return new XMLConfiguration(appConfigFile);
    } catch(ConfigurationException e) {
      log.debug("Error while load configuration file for the application");
    }
    return null;
  }
}
