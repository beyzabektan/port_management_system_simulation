
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * abstract class for the container methods.
 * 
 * @author Beyzanur Bektan
 *
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * id of the container
	 */
	private int ID;
	/**
	 * weight of the container
	 */
	private int weight;

	
	/**
	 * constructor method for the container
	 * 
	 * @param ID id of the container
	 * @param weight weight of the container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	

	public int getID() {
		return ID;
	}

	public int getWeight() {
		return weight;
	}

	
	public int compareTo(Container compareCont) {
		if (this.ID < compareCont.getID()) {
			return -1;
		}
		else if (this.ID > compareCont.getID()) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	/**
	 * consumption method returns fuel consumption required by the container.
	 * 
	 * @return the fuel consumption required by the container
	 */
	public abstract double consumption();
	
	/**
	 * equals method compares types, IDs and weights of two containers.
	 * 
	 * @param other the container that will be checked
	 * @return true if two containers are the same
	 */
	public boolean equals(Container other) {
		if ((this.getClass() == other.getClass()) && (this.ID == other.ID) && (this.weight == other.weight)){
			return true;
		}
		else {
			return false;
		}
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

