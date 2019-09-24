import java.awt.Graphics;
import java.util.List;

/**
 * Interface Geometry, welches Methoden beschreibt, die jedes Geometrie Objekt
 * besitzen sollte.
 * 
 * @author Paul Vincent Kuper
 */
public interface Geometry {

	/**
	 * Gibt die ID des Objektes zurueck
	 * 
	 * @return ID des Objektes 
	 */
	int getID();

	/**
	 * Gibt die Koordinaten des Objekts als Liste von Objekten des Typs Coordinate
	 * zurueck
	 * 
	 * @return Koordinaten des Objekts als Liste
	 */
	List<Coordinate> getCoordinatesAsList();

	/**
	 * Liefert die BoundingBox (minimal umschreibendes Rechteck) des Objekts in
	 * Form einer BBox zurueck
	 * 
	 * @return BoundingBox (minimal umschreibendes Rechteck) des Objekts
	 */
	BBox getBBOX();
	
	/**
	 * Methode paint, welche das jeweilige Objekt auf dem uebergebenen Graphics
	 * Objekt zeichnet.
	 * 
	 * @param g - Das Graphics Objekt auf dem gezeichnet werden soll.
	 * @return 
	 */
	void paint(Graphics g);
}