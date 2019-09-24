import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Unsere Klasse Zeichenflaeche, welche von der Klasse JPanel abstammt.
 * 
 * @author Paul Vincent Kuper (kuper@kit.edu)
 */
public class Zeichenflaeche extends JPanel {

	private List<Geometry> zuZeichnen; 

	/**
	 * Konstruktor der Zeichenflaeche
	 * 
	 * @param zuZeichnen
	 */
	public Zeichenflaeche(List<Geometry> zuZeichnen) {

		// In diesem Fall wollen wir in GIS und der Zeichenflaeche die gleiche
		// Liste verwenden. Deswegen verlinken wir in beiden Klassen auf ein und
		// dieselbe Liste.
		this.zuZeichnen = zuZeichnen;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				Polygon poly = null;
				for (Geometry g: GIS.getAllGeomObjects()) {
					if (g instanceof Polygon)
						poly = (Polygon) g;
				}
				
				Point p =  new Point(0,e.getX(),e.getY());
				int judge = GIS.pointInPolygon(poly, p);
				p.paint(getGraphics(),judge);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		);
		
	}
	
	
	
	/**
	 * Diese Methode wird automatisch aufgerufen, sobald ein repaint
	 * durchgefuehrt wird. Zuerst wird die gleichlautende Methode der Oberklasse
	 * aufgerufen um danach alle Objekte der Liste zuZeichen zu zeichnen.
	 * 
	 * @param g
	 *            - das Graphics Objekt auf welchem gezeichnet werden soll.
	 */
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Geometry geom : zuZeichnen) {
			geom.paint(g);
		}
	}
}
