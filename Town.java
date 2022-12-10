
public class Town implements Comparable<Town>{
	private String name;
	
	public Town(String name) {
		this.name = name;
	}
	
	public Town (Town town) {
		this.name = town.name;
	}

	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}
	
	public int hashCode() {
		int hashCode = 0;
		char[] arr = name.toCharArray();
		for(int i = 0; i < arr.length; i++) {
			hashCode += arr[i]*(i + 1);
		}
		return hashCode;
	}
	
	public boolean equals(Object obj) {
		Town otherTown = (Town) obj;
		if(name.equals(otherTown.getName())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
}
