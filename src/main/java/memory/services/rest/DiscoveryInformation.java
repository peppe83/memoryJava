package memory.services.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//http://localhost:8081/memory/rest/DiscoveryInformation/isAlive
  
@Path("/DiscoveryInformation")
public class DiscoveryInformation {
	protected static Log log = 
	    LogFactory.getLog(DiscoveryInformation.class);

  @GET
  @Path("/isAlive")
  @Produces(MediaType.APPLICATION_JSON)
  public String isAlive() {
    return "{'isAlive':true}";
  }
  
  @GET
  @Path("/echo/{msg}")
  public String echo(@PathParam("msg") String msg) {
    return "{'echo':'"+msg+"'}";
  }
  
//	@GET
//	@Path("/buildLegendLayers")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<String[]> buildLegendLayers(@QueryParam("listUrlsWms") String listUrlsWms, @QueryParam("listLayer") String listLayer, @QueryParam("listSld") String listSld, 
//	                                @QueryParam("listLabel") String listLabel, @QueryParam("time") String time, @QueryParam("locale") String locale) {
//		log.debug("listUrlsWms: "+listUrlsWms);
//		log.debug("listLayer: "+listLayer);
//		log.debug("listSld: "+listSld);
//		log.debug("listLabel: "+listLabel);
//		
//		ArrayList<String[]> listLegend = new ArrayList<String[]>();
//		
//		if (listUrlsWms.equals("") || listLayer.equals("") || listSld.equals("") || listLabel.equals("")) {
//			return listLegend;
//		}
//		
//		String[] layers = listLayer.split(",");
//		String[] slds = listSld.split(",");
//		String[] urlsWms = listUrlsWms.split(",");
//		String[] labels = listLabel.split(",");
//		
//		if (layers.length!=slds.length && layers.length!=urlsWms.length && layers.length!=labels.length) {
//			return listLegend;
//		}
//		
//		for (int i=0; i<layers.length; i++) {
//			String nameLayer = layers[i];
//			String sld = slds[i];
//			String wmsUrl = urlsWms[i];
//			String label = labels[i];
//			String parSld = "";
//			System.out.println(sld);
//			if (sld!=null && !sld.equals("") && !sld.endsWith("undefined")) {
//				parSld = "&SLD="+sld.replace("&", "!-!");
//			}
//			String url = wmsUrl + "?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&TRANSPARENT=true&WIDTH=30&HEIGHT=30&LAYER="+nameLayer+parSld+"&LEGEND_OPTIONS=forceRule:False;dx:0.2;dy:0.2;mx:0.2;my:0.2;borderColor:0000ff;border:true;fontColor:000000;fontSize:11";
//			System.out.println("URL LEGENDA: "+url);
//			
////			if (this instanceof SismicoHandler && currentLayer.getValue().equals(SismicoHandler.LAYER_SCENARIO_MODELLATO_VALUE) && !codRischio.equals("fk_cod_danno")){
////				legends.add(new Legend(currentLayer.getLabel()+" "+SystemUtilities.getStringFromBundle("com.terraria.simulator.sismico.legend.title.suffix"), url));
////			}
////			else{
////				legends.add(new Legend(currentLayer.getLabel(),url));
////			}
//			
//			listLegend.add(new String[]{label, url});
//		}
//		return listLegend;
//  }
}
