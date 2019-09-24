import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class Triangle implements Geometry{
	private int id;
	private Point p1;
	private Point p2;
	private Point p3;
	
	public Triangle(int id, Point p1, Point p2, Point p3) {
		
		this.p1= p1;
		this.p2= p2;
		this.p3= p3;
	}
	public int getID() {
		return id;
	}
	
	public Point getA() {
		return p1;
	}
	
	public Point getB() {
		return p2;
	}
	
	public Point getC() {
		return p3;
	}
	
	public List<Coordinate> getCoordinatesAsList() {

		List<Coordinate> list = new LinkedList<Coordinate>();

		list.add(p1.getCoordinates()[0]);
		list.add(p2.getCoordinates()[0]);
		list.add(p3.getCoordinates()[0]);

		return list;
	}
	
	public BBox getBBOX() {
		
		double xmin = Double.MAX_VALUE, ymin = Double.MAX_VALUE, xmax = Double.MIN_VALUE, ymax = Double.MIN_VALUE;
		List<Coordinate> list = this.getCoordinatesAsList();
		
		for (Coordinate p : list) {

			if (xmin > p.getX())
				xmin = p.getX();
			if (ymin > p.getY())
				ymin = p.getY();
			if (xmax < p.getX())
				xmax= p.getX();
			if (ymax < p.getY())
				ymax = p.getY();
		}
		
		Coordinate min = new Coordinate(xmin, ymin);
		Coordinate max = new Coordinate(xmax, ymax);
		
		BBox box = new BBox(min, max);
		
		return box;
	}
	
public void paint(Graphics g){
    	
    	
    	java.awt.Polygon polygon = new java.awt.Polygon();
    	
    	List<Point> points = new LinkedList<Point>();

		points.add(p1);
		points.add(p2);
		points.add(p3);
    	
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
