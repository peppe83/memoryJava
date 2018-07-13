package memory.test;
public class Tracking {

//	private String id;
	private String entity_id;
	private String trip_id;
	private String vehicle_id;
	private String label;
	private String time_text;
	private int time_posix;
	private double latitude;
	private double longitude;
	private double bearing;
	private double speed;

	public Tracking() {
	}
	
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getTrip_id() {
		return trip_id;
	}
	
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	
	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getTime_text() {
		return time_text;
	}

	public void setTime_text(String time_text) {
		this.time_text = time_text;
	}
	
	public int getTime_posix() {
		return time_posix;
	}

	public void setTime_posix(int time_posix) {
		this.time_posix = time_posix;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
