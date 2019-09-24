/**
 * Eine Klasse, welche Koordinaten im 2D repraesentiert.
 * 
 * @author Paul Vincent Kuper (kuper@kit.edu)
 */
public class Coordinate {

	private double x;
	private double y; 
	
	/**
	 * Konstruktor
	 * 
	 * @param x - X-Koordinate
	 * @param y - Y-Koordinate
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param coord - Objekt, welches kopiert werden soll.
	 */
	public Coordinate(Coordinate coord) {
		this.x = coord.x;
		this.y = coord.y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Generiert einen String, welcher das Objekt beschreibt.
	 * 
	 * @return - String mit Objektbeschreibung (nur Koordinaten)
	 */
	@Override
	public String toString() {
		return "Coordinate:[" + x + ", " + y + "]";
	}
}
