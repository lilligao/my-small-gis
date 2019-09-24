/**
 * Repraesentiert eine BoundingBox mit Hilfe von zwei Coordinaten min und max.
 * 
 * @authorm Paul Vincent Kuper
 */
public class BBox {

	// Instanzvariablen
	Coordinate min;
	Coordinate max;

	/**
	 * Konstruktor der Klasse BBox
	 * 
	 * @param min
	 *            - Mininmaler Wert der BBox 
	 * @param max
	 *            - Maximaler Wert der BBox
	 */
	public BBox(Coordinate min, Coordinate max) {
		this.min = min;
		this.max = max;
	}

	public Coordinate getMin() {
		return min;
	}

	public void setMin(Coordinate min) {
		this.min = min;
	}

	public Coordinate getMax() {
		return max;
	}

	public void setMax(Coordinate max) {
		this.max = max;
	}
}
