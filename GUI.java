import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Unsere Klasse GUI, welche von der Klasse JFrame abstammt und die GUI zu
 * unserem GIS aufbaut.
 *  
 * @author Paul Vincent Kuper (kuper@kit.edu)
 */
public class GUI extends JFrame {

	/**
	 * Der Konstruktor unserer Klasse GUI, in welchem die grafische Oberflaeche
	 * erstellt wird. Hierbei werden hauptsaechlich Methoden der Klasse JFrame
	 * verwendet. Zusaetzlich werden Objekte aus dem Swing bzw. AWT Paket
	 * verwendet und ein Objekt unserer Klasse Zeichenflaeche erstellt.
	 */
	public GUI() {

		// Beschriftung setzen
		setTitle("Mein kleines GIS");

		// Groesse des Fensters festlegen
		setSize(1000, 800);

		// Beim schliessen des Fensters soll sich das Programm beenden:
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Erstellen des Objektes vom Typ Zeichenflaeche
		Zeichenflaeche z = new Zeichenflaeche(GIS.getAllGeomObjects());

		// Nutzen eines Layouts
		setLayout(new BorderLayout());

		// Hinzufuegen der Zeichenflaeche
		add(z, BorderLayout.CENTER);

		// Das Fenster sichtbar machen
		setVisible(true);
		
		
	}
}
