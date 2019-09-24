import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse, welche ein 2D Segment repraesentiert.
 * 
 * @author Paul Vincent Kuper (kuper@kit.edu)
 */
public class Segment implements Geometry {

	// Instanzvariablen
	private int id;
	private Coordinate start;
	private Coordinate end;

	/**
	 * Konstruktor
	 * 
	 * @param id
	 *            - ID des Objekts
	 * @param x1
	 *            - x Wert des Start-Punktes 
	 * @param y1
	 *            - y Wert des Start-Punktes
	 * @param x2
	 *            - x Wert des End-Punktes
	 * @param y2
	 *            - y Wert des End-Punktes
	 */
	public Segment(int id, double x1, double y1, double x2, double y2) {

		this.id = id;

		start = new Coordinate(x1, y1);
		end = new Coordinate(x2, y2);
	}

	/**
	 * 
	 * 
	 * @param id
	 *            - ID des Objekts
	 * @param start
	 *            - Koordinate des Start-Punktes
	 * @param end
	 *            - Koordinate des End-Punktes
	 */
	public Segment(int id, Coordinate start, Coordinate end) {

		this.id = id;

		this.start = start;
		this.end = end;
	}
	
	/**
	 * 
	 * 
	 * @param id
	 *            - ID des Objekts
	 * @param start
	 *            - Start-Punkt
	 * @param end
	 *            - End-Punkt
	 */
	public Segment(int id, Point start, Point end) {

		this.id = id;

		this.start = start.getCoordinates()[0];
		this.end = end.getCoordinates()[0];
	}

	/**
	 * Gibt die ID des Objektes zurueck
	 * 
	 * @return ID des Objektes
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Generiert einen String, welcher das Objekt beschreibt.
	 * 
	 * @return - String mit Objektbeschreibung (nur Koordinaten)
	 */
	@Override
	public String toString() {
		return "Segment:[" + start.getX() + "," + start.getY() + ","
				+ end.getX() + "," + end.getY() + "]";
	}

	/**
	 * Gibt die Koordinaten des Objekts als Liste von Objekten des Typs
	 * Coordinate zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	public Coordinate[] getCoorddinates() {

		return new Coordinate[] { start, end };
	}

	/**
	 * Gibt die Koordinaten des Objekts als Liste von Objekten des Typs Coordinate
	 * zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	public List<Coordinate> getCoordinatesAsList() {

		List<Coordinate> list = new LinkedList<Coordinate>();

		list.add(start);
		list.add(end);

		return list;
	}

	/**
	 * Liefert die BoundingBox (minimal umschreibendes Rechteck) des Objekts in
	 * Form einer BBox zurueck
	 * 
	 * @return BoundingBox (minimal umschreibendes Rechteck) des Objekts
	 */
	public BBox getBBOX() {		
		
		double minX, minY, maxX, maxY;
		
		if(start.getX() < end.getX()) {
			minX = start.getX();
			maxX = end.getX();
		}
		else {
			minX = end.getX();
			maxX = start.getX();
		}
		
		if(start.getY() < end.getY()) {
			minY = start.getY();
			maxY = end.getY();
		} else {
			minY = end.getY();
			maxY = start.getY();
		}	
		
		Coordinate min = new Coordinate(minX, minY);
		Coordinate max = new Coordinate(maxX, maxY);
		
		BBox box = new BBox(min, max);
		
		return box;
	}
	
	/**
	 * Berechnet die Laenge des Segments mit Hilfe der BBOX und des Satzes des Pythagoras
	 * 
	 * @return Laenge des Segments
	 */
	public double getLength() {
		
		double length;
		
		BBox box = getBBOX();
		
		double a =  box.max.getX() - box.min.getX();
		double b =  box.max.getY() - box.min.getY();

		length = Math.sqrt(a*a + b*b);
		
		return length;
	}
	
	/**
	 * Methode paint, welche das jeweilige Objekt auf dem uebergebenen Graphics
	 * Objekt zeichnet.
	 * 
	 * @param g - Das Graphics Objekt auf dem gezeichnet werden soll.
	 * @return 
	 */
    public void paint(Graphics g){
    	g.setColor(Color.red);
    	g.drawLine((int)start.getX(),(int) start.getY(), (int) end.getX(), (int) end.getY());
    }

	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}
}
