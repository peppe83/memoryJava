package memory.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Position {
	private Tracking entity;

	public Position(Tracking entity) {
		this.entity = entity;
	}

	public void startTracking() {
		TimerTask repeatedTask = new TimerTask() {
      public void run() {
      	
      	Connection connection = null;
      	Statement statement = null;
      	try {      		
      		Date d = new Date();
          float x = getPositionX();
      		float y = getPositionY();
      		int speed = getSpeed();
      		
      		System.out.println("Task performed on " + new Date());
      		System.out.println("id: "+entity.getVehicle_id()+"x: <<" + x + ">> y: <<"+y + ">> speed: <<"+speed+">>");
      		
      		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
	    		String data = dataFormat.format(d);
	    		entity.setTime_text(data);
	    		entity.setLatitude(y);
	    		entity.setLongitude(x);
	    		entity.setSpeed(speed);
	    		
      		String database = "simulator_test";
    			String url = "jupiterfx.intranet.terraria.com";
    			String port = "5432";
    			String databaseURL = "jdbc:postgresql://"+url+":"+port+"/"+database;
    			String username = "simulator";
    			String password = "simulator";

    			connection = DriverManager.getConnection(databaseURL, username , password);
    			String dbName = "tracking";
    			String apice = "'";
    			String sqlInsert = "INSERT INTO " + dbName + " (entity_id, trip_id, vehicle_id, label, time_text, time_posix, latitude, longitude, bearing, speed) "
    					+ "VALUES ("+apice+entity.getEntity_id()+apice+", "+apice+entity.getTrip_id()+apice+", "+apice+entity.getVehicle_id()+apice+", "+apice+
    											entity.getLabel()+apice+", "+apice+entity.getTime_text()+apice+", "+entity.getTime_posix()+", "+
    											entity.getLatitude()+", "+entity.getLongitude()+", "+entity.getBearing()+", "+
    											entity.getSpeed()+");";
    			System.out.println(sqlInsert);
    			
    			statement = connection.createStatement();
    			statement.execute(sqlInsert);

  				String sqlDuplicate = "SELECT deleteduplicate();";
    			System.out.println(sqlDuplicate);
  				statement.executeQuery(sqlDuplicate);
  				
  				String sqlIndexGis = "SELECT creategistindex('"+dbName+"');";
  				System.out.println(sqlIndexGis);
  				statement.executeQuery(sqlIndexGis);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (statement != null) {
							statement.close();
						}
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						
					}
				}
    			
      }
		};
		Timer timer = new Timer("Timer");
		long delay  = 1000L;
		long period = 3000L;
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
	
	public static void main(String[] args) {
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
  	String toDay = dateFormatter.format(new Date()).replaceAll("/", "_");
  	
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
		
		volontario1.startTracking();
		volontario2.startTracking();
		volontario3.startTracking();
		volontario4.startTracking();
	}

	public static float getPositionX() {
		float leftLimit = 10.13F;
    float rightLimit = 10.24F;
    float generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
    return generatedFloat;
	}
	
	public static float getPositionY() {
		float leftLimit = 46.18F;
    float rightLimit = 46.26F;
    float generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
    return generatedFloat;
	}
	
//	var ptLatitudeYTirano = 46.2163;
//	var ptLongitudeXTirano = 10.1688;
	
	public static int getSpeed() {
		int leftLimit = 0;
		int rightLimit = 10;
    Random rand = new Random();
    int generatedInt = rand.nextInt((rightLimit - leftLimit) + 1) + leftLimit;
    return generatedInt;
	}
}