
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;


/**
 * This class has methods about liquid containers.
 * 
 * @author Beyzanur Bektan
 *
 */
public class LiquidContainer extends HeavyContainer{
	
	/**
	 * constructor method for the liquid container
	 * 
	 * @param ID id of the liquid container
	 * @param weight weight of the liquid container
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	@Override
	public double consumption() {
		// TODO Auto-generated method stub
		return (getWeight() * 4.00);	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

