
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This class has methods about basic containers.
 * 
 * @author Beyzanur Bektan
 *
 */
public class BasicContainer extends Container{
	/**
	 * constructor method for the basic container
	 * 
	 * @param ID id of the basic container
	 * @param weight weight of the basic container
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	}

	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return (getWeight() * 2.50);
	}
	

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

