import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 * Klasse, welche ein Geo-Informations System (GIS) repraesentiert.
 * 
 * @author Paul Vincent Kuper
 */
public class GIS {

	// Klassenvariable (die Liste wird beim ersten Aufruf der Klasse
	// initialisiert)
	private static List<Geometry> geometryObjects = new LinkedList<Geometry>();

	private static GUI gui;

	/**
	 * Statische Klassenmethode, welche die Liste geometryObjects zurueckliefert
	 * 
	 * @return - Liste mit allen Geometry Objekten
	 */
	static List<Geometry> getAllGeomObjects() {
		return geometryObjects;
	}

	/**
	 * Statische Klassenmethode, welche ein Objekt vom Typ Geometry der Liste
	 * geometryObjects hinzufuegt.
	 * 
	 * @param geom
	 *            - das hinzuzufuegende Geometry Objekt
	 */
	static void addGeomObject(Geometry geom) {
		geometryObjects.add(geom);
	}

	static void writeAllObjectsToFile() {

		// Erstelle ein neues JFileChooser Objekt
		JFileChooser chooser = new JFileChooser();

		// Dialog zum Oeffnen von Dateien anzeigen
		chooser.showOpenDialog(null);

		// Erstelle ein File Objekt
		File file = chooser.getSelectedFile();

		BufferedWriter bw = null;

		// Sortiere die Geometrien nach Point, Segment und Polygon
		List<Geometry> sortierteListe = new ArrayList<Geometry>();

		// erst die Punkte
		for (Geometry g : geometryObjects)
			if (g instanceof Point)
				sortierteListe.add(g);

		// dann die Segmente
		for (Geometry g : geometryObjects)
			if (g instanceof Segment)
				sortierteListe.add(g);

		// dann die Polygone
		for (Geometry g : geometryObjects)
			if (g instanceof Polygon)
				sortierteListe.add(g);

		if (file != null) {

			// Ist die Datei schon verhanden, lege eine neue an:
			if (file.exists())
				file.delete();

			// Erstelle ein FileWriter Objekt
			FileWriter writer = null;

			// Initialisiere einen FileWriter
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Initialisiere einen BufferedWriter
			bw = new BufferedWriter(writer);

			// Schreibe alle Geometry Objekte in die Datei
			for (Geometry g : sortierteListe) {
				try {
					bw.write(g.toString() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt ein neues Objekt vom Typ GUI und startet damit die GUI
	 */
	public static void startGUI() {
		gui = new GUI();
	}

	public static Polygon computeConvexHull(List<Geometry> geometries) {

		List<Segment> validSegments = new LinkedList<Segment>();
		List<Coordinate> coords = new LinkedList<Coordinate>();
		// Zwischenpunkte sammeln: 
		List<Coordinate> delete = new LinkedList<Coordinate>();
		int id = 0;
		
		
		// First: collect all Coordinates
		for (Geometry g : geometries) {
			coords.addAll(g.getCoordinatesAsList());
		}

		for (Coordinate p : coords) {
			for (Coordinate q : coords) {
				if (p != q) {

					double x1 = p.getX();
					double y1 = p.getY();
					double x2 = q.getX();
					double y2 = q.getY();

					boolean valid = true;
					
					// Sonderfall: Gerade parallel zur Y-Achse
					if (x1 == x2) {
						
						// alle Punkte rechts der Linie?
						for (Coordinate c : coords) {
							if (p != c && q != c) {
								double xA = c.getX();
								double yA = c.getY();
								if (xA < x1)
									valid = false;
							}
						}
						
						// gefundene Kante speichern
						if (valid && y1 < y2) {
							validSegments.add(new Segment(id, p, q));
							id++;
						}
						
						
						valid = true;
						// alle Punkte links der Linie?
						for (Coordinate c : coords) {
							if (p != c && q != c) {
								double xA = c.getX();
								double yA = c.getY();
								if (xA > x1)
									valid = false;
							}
						}
						
						// gefundene Kante speichern
						if (valid && y1 > y2) {
							validSegments.add(new Segment(id, p, q));
							id++;
						}
					}
					

					else {
						// Baue eine Gerade aus p und q
						double m = (y2 - y1) / (x2 - x1);
						double b = y2 - (x2 * m);
						

						for (Coordinate a : coords) {
							if (p != a && q != a) {
								double xA = a.getX();
								double yA = a.getY();

								// Sonderfall: Mehrere Punkte auf einer Geraden
								if (yA == ((m * xA) + b)) {
									if (x1 <= xA && xA <= x2) {
										delete.add(a);
									}
								}

								// unter Hälfte finden (Kanten im Uhrzeigersinn):
								if (x1 > x2)
									if (yA < ((m * xA) + b))
										valid = false;

								// obere Hälfte finden (Kanten im Uhrzeigersinn):
								if (x1 < x2)
									if (yA > ((m * xA) + b))
										valid = false;
							}
						}

						if (valid) {
							validSegments.add(new Segment(id, p, q));
							id++;
						}

					}

				}
			}
		}
		
		List<Segment> deleteSegments = new LinkedList<Segment>();
		// Sonderfall: Mehrere Punkte auf einer Geraden
		for (Segment s: validSegments) {
			for (Coordinate c: delete) {
				if (s.getStart() == c || s.getEnd() == c) {
					deleteSegments.add(s);
					break;
				}					
			}
		}
		
		if (deleteSegments.size()>0) validSegments.removeAll(deleteSegments);
		

		Polygon convexHull = new Polygon(0, validSegments);
		return convexHull;
	}
	
	
	public static int pointInPolygon(Polygon poly, Point p) {
		
		List<Segment> seg = new LinkedList<Segment>();
		List<Segment> delete = new LinkedList<Segment>();
		Coordinate coord = new Coordinate(p.getCoordinatesAsList().get(0));
		seg = poly.getSegments();
		int sp = 0;
		int judge = 0;
		
		
		for (Segment s: seg) {
			
			double x1=s.getStart().getX();
			double y1=s.getStart().getY();
			double x2=s.getEnd().getX();
			double y2=s.getEnd().getY();
			
			double x=coord.getX();
			double y=coord.getY();
			
			Segment partner = null;
			boolean j=true;
			
			if (y==y1) {
				for (Segment par: seg) {
					if (par.getEnd().getY()==y1 && par.getEnd().getX()==x1)
						partner=par;
				}
				double yp = partner.getStart().getY();
				if ((y2-y)*(yp-y)<=0) j=false;
				
			}
			
			
			if (x1 < x && x2 < x)
				delete.add(s);
			else if (y1 < y && y2 < y)
				delete.add(s);
			else if (y1 > y && y2 > y)
				delete.add(s);
			
			else if (x1 > x && x2 > x && j) sp++;
				
			
			else if (x1 <= x && x2 >= x || x1 >= x && x2 <= x) {
				double m = (x2-x1)/(y2-y1);
				double b = x1 - y1*m;
				
				double xs = m*y+b;
				
				if (xs>=x && j)sp++;
				else if (y1 == y && y2 == y) sp++;
				
			}
			else continue;
		}
		
		
		if (delete.size()>0) seg.removeAll(delete);
		
		if (sp%2 == 1) judge = 1;
		
		
		
		return judge;
	}
	
	public static void triangulate(List<Point> punkte) {
		List<Triangle> result = new LinkedList<Triangle>();
		List<Triangle> delete = new LinkedList<Triangle>();
		
		double xmin = Double.MAX_VALUE, ymin = Double.MAX_VALUE, xmax = Double.MIN_VALUE, ymax = Double.MIN_VALUE;
		
		for (Point p : punkte) {

			if (xmin > p.getCoordinates()[0].getX())
				xmin = p.getCoordinates()[0].getX();
			if (ymin > p.getCoordinates()[0].getY())
				ymin = p.getCoordinates()[0].getY();
			if (xmax < p.getCoordinates()[0].getX())
				xmax= p.getCoordinates()[0].getX();
			if (ymax < p.getCoordinates()[0].getY())
				ymax = p.getCoordinates()[0].getY();
		}
		
		
		Point a = new Point(1, (xmax - xmin)/2 + xmin, 2*ymax - ymin);
		Point b = new Point(2, xmin - (xmax-xmin)/2, ymin);
		Point c = new Point(3, xmax + (xmax-xmin)/2, ymin);
		
		Triangle umDreieck = new Triangle(0, a, b, c);
		Triangle tri1;
		Triangle tri2;
		Triangle tri3;
		
		result.add(umDreieck);
		
		for (Point p: punkte) {
			for (int i = result.size()-1; i >= 0; i--) {
				Triangle t = result.get(i);
				List<Point> tri = new LinkedList<Point>();
				tri.add(t.getA());
				tri.add(t.getB());
				tri.add(t.getC());
				Polygon poly = new Polygon(tri,1);
				if (GIS.pointInPolygon(poly, p)==1) {
					umDreieck = t;
					break;
				}
			}
			
			tri1 = new Triangle(1, umDreieck.getA(), umDreieck.getB(), p); 
			tri2 = new Triangle(2, umDreieck.getB(), umDreieck.getC(), p); 
			tri3 = new Triangle(3, umDreieck.getA(), umDreieck.getC(), p);
			result.add(tri1); 
			result.add(tri2); 
			result.add(tri3);
			
		}
		
		for(Triangle t : result) {
			if(t.getA().equals(a) || t.getA().equals(b) || t.getA().equals(c))
				delete.add(t); 
			if(t.getB().equals(a) || t.getB().equals(b) || t.getB().equals(c)) 
				delete.add(t);
			if(t.getC().equals(a) || t.getC().equals(b) || t.getC().equals(c)) 
				delete.add(t); 
		}	
		result.removeAll(delete);
		
		for (Triangle t : result) {
			GIS.addGeomObject(t);
		}
		
	}
	
	
	
}
