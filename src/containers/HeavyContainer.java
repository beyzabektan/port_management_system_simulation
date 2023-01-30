
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This class has methods about heavy containers.
 * 
 * @author Beyzanur Bektan
 *
 */
public class HeavyContainer extends Container{
	/**
	 * constructor method for the heavy container
	 * 
	 * @param ID id of the heavy container
	 * @param weight weight of the heavy container
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return (getWeight() * 3.00);	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

