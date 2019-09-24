import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse, welche eine Polygon Geometrie (2D) repraesentiert.
 * 
 * @author Paul Vincent Kuper
 */
public class Polygon implements Geometry {

	// Instanzvariablen
	List<Point> points;
	int id;

	/**
	 * Konstruktor 
	 * 
	 * @param id
	 *            - ID des Polygons
	 */
	public Polygon(int id) {
		points = new LinkedList<Point>();
		this.id = id;
	}

	/**
	 * Konstruktor
	 * 
	 * @param coords
	 *            - Liste mit Stuetz-Coordinaten des Polygons
	 * @param id
	 *            - ID des Polygons
	 */
	public Polygon(List<Point> points, int id) {

		this.id = id;

		this.points = new LinkedList<Point>();
		for (Point p : points)
			this.points.add(new Point(p));
	}
	
	
	public Polygon(int id, List <Segment> validSegments) {
		
		this.id = id;
		points = new LinkedList<Point>();
		
		Segment current = validSegments.get(0);

		
		for (Segment s: validSegments) {
			for (Segment s2: validSegments) {
				if (current.getEnd().equals(s2.getStart())) {
					points.add(new Point(0, s2.getStart()));
					current = s2;
					break;
				}
			}
		}
		
	}


	/**
	 * Liefert die ID des Polygons
	 * 
	 * @param id
	 *            - ID des Polygons
	 */
	public int getID() {
		return id;
	}

	/**
	 * Gibt die Koordinaten des Objekts als Liste von Objekten des Typs
	 * Coordinate zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	public List<Coordinate> getCoordinatesAsList() {

		List<Coordinate> coords = new LinkedList<Coordinate>();

		for (Point p : points) {
			coords.add(p.getCoordinates()[0]);
		}

		return coords;
	}
	
	public List<Segment> getSegments(){
		List<Segment> segments = new LinkedList<Segment>();
		int id = 0;
		for (Point p: points) {
			if (id < points.size()-1)
				segments.add(new Segment(id,p,points.get(id+1)));
			else if (id == points.size()-1)
				segments.add(new Segment(id,p,points.get(0)));
			id++;
		}
		
		return segments;
	}

	/**
	 * Liefert die BoundingBox (minimal umschreibendes Rechteck) des Objekts in
	 * Form einer BBox zurueck
	 * 
	 * @return BoundingBox (minimal umschreibendes Rechteck) des Objekts
	 */
	
	public BBox getBBOX() {

		if (points == null)
			return null;

		double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;

		for (Point p : points) {

			if (minX > p.getCoordinates()[0].getX())
				minX = p.getCoordinates()[0].getX();
			if (minY > p.getCoordinates()[0].getY())
				minY = p.getCoordinates()[0].getY();
			if (maxX < p.getCoordinates()[0].getX())
				maxX = p.getCoordinates()[0].getX();
			if (maxY < p.getCoordinates()[0].getY())
				maxY = p.getCoordinates()[0].getY();
		}

		Coordinate min = new Coordinate(minX, minY);
		Coordinate max = new Coordinate(maxX, maxY);

		BBox box = new BBox(min, max);
		

		return box;
	}

	/**
	 * Fuegt der Liste der Stuetz-Punkte einen weiteren Punkt hinzu
	 * 
	 * @param c
	 *            - Koordinate
	 */
	public void addPoint(Point p) {
		points.add(p);
	}

	/**
	 * toString Methode des Polygons
	 */
	public String toString() {
		return "Polygon:[" + points + "]";
	}

	/**
	 * Berechnet den Umfang eines Polygons indem die Entfernungen der einzelnen
	 * Stuetzpunkte zusammgenzaehlt werden
	 * 
	 * @return den Umfang als double Wert
	 */
	public double getExtend() {

		double extend = 0;

		for (int i = 0; i < points.size() - 1; i++) {
			Segment tmpSeg = new Segment(42, points.get(i), points.get(i + 1));
			extend = extend + tmpSeg.getLength();
		}

		// Polygon schliessen
		Segment tmpSeg = new Segment(42, points.get(points.size() - 1), points.get(0));
		extend = extend + tmpSeg.getLength();

		return extend;
	}

	/**
	 * Berechnet den Flaecheninhalt des Polygons mit Hilfe der Gaussschen
	 * Trapezformel (s. de.wikipedia.org/wiki/Gau%C3%9Fsche_Trapezformel)
	 * 
	 * @return den Flaecheninhalt des Polygons
	 */
	public double getArea() {

		double area = 0;

		for (int i = 0; i < points.size() - 1; i++) {

			area = Math.abs(area) + (points.get(i).getCoordinates()[0].getY()
					+ points.get(i + 1).getCoordinates()[0].getY())
					* (points.get(i).getCoordinates()[0].getX() - points.get(i + 1).getCoordinates()[0].getX());

		}

		area = area / 2.;

		return Math.abs(area);
	}
	
	/**
	 * Methode paint, welche das jeweilige Objekt auf dem uebergebenen Graphics
	 * Objekt zeichnet.
	 * 
	 * @param g - Das Graphics Objekt auf dem gezeichnet werden soll.
	 * @return 
	 */
    public void paint(Graphics g){
    	
    	// Neues Objekt vom Typ java.awt.Polygon erstellen
    	java.awt.Polygon polygon = new java.awt.Polygon();
    	
    	// Das Polygon Objekt mit den Stuetzpunkten des Polygons erweitern
    	for(Point p : points) {
    	    Coordinate c = p.getCoordinates()[0];
    		polygon.addPoint((int) c.getX(), (int) c.getY());
    	}
    	
    	g.setColor(Color.blue);
    	
    	// Das Polygon gefuellt zeichnen
    	g.drawPolygon(polygon);
    }
}
