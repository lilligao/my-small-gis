import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse, welche eine Punkt Geometrie (2D) repraesentiert.
 * 
 * @author Paul Vincent Kuper (kuper@kit.edu)
 */
public class Point implements Geometry {

	// Instanzvariablen:
	private int id;
	private Coordinate coord;

	/**
	 * Konstruktor
	 * 
	 * @param id
	 *            - ID des Objekts
	 * @param x
	 *            - x Wert des Punktes
	 * @param y
	 *            - y Wert des Punktes
	 */
	public Point(int id, double x, double y) {
		this.id = id;
		coord = new Coordinate(x, y);
	}

	/**
	 * Konstruktor
	 * 
	 * @param id
	 *            - ID des Objekts
	 * @param coord
	 *            - Koordinaten des Punktes
	 */
	public Point(int id, Coordinate coord) {
		this.id = id;
		this.coord = coord;
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param p
	 *            - Punkt, welcher kopiert werden soll
	 */
	public Point(Point p) {
		this.id = p.id;
		this.coord = new Coordinate(p.coord); 		
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
		return "Point:[" + coord.getX() + "," + coord.getY() + "]";
	}

	/**
	 * Gibt die Koordinaten des Objekts als Array von Objekten des Typs
	 * Coordinate zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	public Coordinate[] getCoordinates() {

		return new Coordinate[] { coord };
	}

	/**
	 * Gibt die Koordinaten des Objekts als Liste von Objekten des Typs
	 * Coordinate zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	public List<Coordinate> getCoordinatesAsList() {

		List<Coordinate> list = new LinkedList<Coordinate>();

		list.add(coord);

		return list;
	}

	/**
	 * Liefert die BoundingBox (minimal umschreibendes Rechteck) des Objekts in
	 * Form einer BBox zurueck
	 * 
	 * @return BoundingBox (minimal umschreibendes Rechteck) des Objekts
	 */
	public BBox getBBOX() {

		BBox box = new BBox(coord, coord);

		return box;
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
    	g.drawLine((int)coord.getX(),(int) coord.getY(), (int) coord.getX(), (int) coord.getY());
    	g.drawRect((int)coord.getX() - 8,(int) coord.getY() - 8, 16, 16);
    }
    
    
    public void paint(Graphics g, int t){
    	if (t==1)
    		g.setColor(Color.red);
    	else if (t==0)
    		g.setColor(Color.blue);
    	else g.setColor(Color.green);
    	g.drawLine((int)coord.getX(),(int) coord.getY(), (int) coord.getX(), (int) coord.getY());
    	g.drawRect((int)coord.getX() - 8,(int) coord.getY() - 8, 16, 16);
    }
}
