
public class Road implements Comparable<Road>{
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
	}
	
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
	}

	@Override
	public int compareTo(Road o) {
		return this.getName().compareTo(o.getName());
	}
	
	public boolean contains(Town town) {
		if(source.equals(town) || destination.equals(town)) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Object r) {
		Road otherRoad = (Road) r;
		if((getSource().equals(otherRoad.getSource()) && getDestination().equals(otherRoad.getDestination())) || (getSource().equals(otherRoad.getDestination()))) {
			return true;
		}
		return false;
	}
	public void flipSourceAndDestination() {
		Town temp = source;
		source = destination;
		destination = temp;
	}
	
	public Town getDestination() {
		return destination;
	}
	
	public String getName() {
		return name;
	}
	
	public Town getSource() {
		return source;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String toString() {
		return source.toString() + " via " + name + " to " + destination.toString() + " " + weight + " mi"; 
	}
}
