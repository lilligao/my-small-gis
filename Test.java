import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Klasse mit einer Main Methode, in welcher verschiedene Objekte der Klassen
 * Punkt, Segment, Polygon und Coordinate erstellt und in der Klasse GIS
 * verwaltet werden.
 * 
 * @author Paul Vincent Kuper (kuper@kit.edu)
 * @author Lili Gao
 */
public class Test {

	public static void main(String[] args) {

		/** TESTDATENSATZ **/
		
		// Koordinaten der Testdaten
		Coordinate c0 = new Coordinate(100, 100);
		Coordinate c1 = new Coordinate(200, 250);
		Coordinate c2 = new Coordinate(555, 200);
		Coordinate c3 = new Coordinate(555, 230);
		Coordinate c4 = new Coordinate(500, 180);
		Coordinate c5 = new Coordinate(150, 350);
		Coordinate c6 = new Coordinate(1000, 350);
		Coordinate c7 = new Coordinate(450, 150);
		
		Coordinate c8 = new Coordinate(380, 500);
		Coordinate c9 = new Coordinate(50, 500);
		Coordinate c10 = new Coordinate(250, 450);
	


		// Punkte
		Point p0 = new Point(0, c0);
		Point p1 = new Point(1, c1);
		Point p2 = new Point(2, c2);
		Point p3 = new Point(3, c3);
		Point p4 = new Point(4, c4);
		Point p5 = new Point(5, c5);
		Point p6 = new Point(6, c6);
		Point p7 = new Point(7, c7);
		Point p8 = new Point(8, c8);
		Point p9 = new Point(9, c9);
		Point p10 = new Point(10, c10);


		List<Point> points = new LinkedList<Point>();
		
		points.add(p0);
		points.add(p7);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p8);
		points.add(p10);
		points.add(p9);	

	
		Polygon poly = new Polygon(points,0);

		GIS.addGeomObject(p0);
		GIS.addGeomObject(p1);
		GIS.addGeomObject(p2);
		GIS.addGeomObject(p3);
		GIS.addGeomObject(p4);
		GIS.addGeomObject(p5);
		GIS.addGeomObject(p6);
		GIS.addGeomObject(p7);
		GIS.addGeomObject(p8);
		GIS.addGeomObject(p9);
		GIS.addGeomObject(p10);
		
		GIS.addGeomObject(poly);
		
		for (Segment s: poly.getSegments()) {
			System.out.println(s);
		}
		
		
		GIS.startGUI();
	}
}
