
package memory.system;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;


public class WebSystemUtilities {
  public static final String CONF_PROPERTIES_NAME = "property[@name]";
  public static final String CONF_PROPERTIES_VALUE = "property[@value]";  
  
  private WebSystemUtilities() {
    super();
  }

  public static Properties extractProperties(Configuration config, String prefix) {
    Properties configprops = new Properties();
    String[] props = config.getStringArray(prefix + "." + CONF_PROPERTIES_NAME);
    String[] valus = config.getStringArray(prefix + "." + CONF_PROPERTIES_VALUE);
    for(int i = 0; i < props.length; i++)
      configprops.setProperty(props[i], valus[i]);

   return configprops;
  }  
}
