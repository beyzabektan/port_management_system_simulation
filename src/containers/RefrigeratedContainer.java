
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This class has methods about refrigerated containers.
 * 
 * @author Beyzanur Bektan
 *
 */
public class RefrigeratedContainer extends HeavyContainer{
	
	/**
	 * constructor method for the refrigerated container
	 * 
	 * @param ID id of the refrigerated container
	 * @param weight weight of the refrigerated container
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
	}

	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return (getWeight() * 5.00);	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

