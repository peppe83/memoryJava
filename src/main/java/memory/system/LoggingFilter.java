package memory.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class LoggingFilter extends HttpServlet implements Filter {

  private static final long serialVersionUID = -5874911050958265030L;

  protected static Log log =
      LogFactory.getLog(LoggingFilter.class);

  public final static String LOG_FILE_PROPERTY = "log4j.appender.FAMemory.File";
  public final static String LOG_PARAM_CONFFILE = "log:confFile";
  private FilterConfig filterConfig;

  //Handle the passed-in FilterConfig
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  //Process the request/response pair
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    try {
      filterChain.doFilter(request, response);
    } catch(ServletException sx) {
      log.error("Servlet execution threw an exception", sx.getRootCause());
      throw sx;
    } catch(Exception ex) {
      if(ex.getClass().getName().indexOf("ClientAbortException") != - 1 ||
          (ex.toString() != null && ex.toString().indexOf("ClientAbortException") != - 1))
        log.debug("Generic Exception while filtering: ClientAbortException");
      else log.error("Generic Exception while filtering", ex);
    }
  }
  
  public static void configSystemLog(ServletContext ctxSrv, String fileAppender, String msgStartup) {
    File ctxRoot = new File(ctxSrv.getRealPath("/"));
    if(ctxSrv.getInitParameter(LOG_PARAM_CONFFILE) == null) return;

    File log4jconf = new File(ctxRoot, ctxSrv.getInitParameter(LOG_PARAM_CONFFILE));
    if(!log4jconf.exists()) return;

    Properties log4jprops = new Properties();
    try {
      log4jprops.load(new FileInputStream(log4jconf));
    } catch(IOException ex) {
      ex.printStackTrace();
      return;
    }

    File logfile = new File(log4jprops.getProperty(fileAppender));
    try {
      logfile.createNewFile();
    } catch(IOException exc) {}
    if(!logfile.canWrite()) logfile = new File(ctxRoot, log4jprops.getProperty(fileAppender)); 
    log4jprops.setProperty(fileAppender, logfile.getAbsolutePath());

    PropertyConfigurator.configure(log4jprops);
    log.info(msgStartup);
  }

  //Clean up resources
  public void destroy() {}
}
