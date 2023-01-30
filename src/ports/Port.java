
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import java.util.ArrayList;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

/**
 * class about port methods
 * 
 * @author Beyzanur Bektan
 *
 */
public class Port implements IPort {
	/**
	 * id of the port
	 */
	private final int ID;
	/**
	 * x coordinate of the port
	 */
	private final double X;
	/**
	 * y coordinate of the port
	 */
	private final double Y;
	/**
	 * arraylist for all containers that the port currently has
	 */
	public ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * arraylist for every ship that has visited the port
	 */
	public ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * arraylist for the ships currently in the port
	 */
	public ArrayList<Ship> current = new ArrayList<Ship>();
	/**
	 * number of heavy containers that port currently has
	 */
	private int numberOfHCPort;
	/**
	 * number of refrigerated containers that port currently has
	 */
	private int numberOfRCPort;
	/**
	 * number of liquid containers that port currently has
	 */
	private int numberOfLCPort;
	
	
	
	public int getNumberOfHCPort() {
		return numberOfHCPort;
	}

	public void setNumberOfHCPort(int numberOfHCPort) {
		this.numberOfHCPort = numberOfHCPort;
	}

	public int getNumberOfRCPort() {
		return numberOfRCPort;
	}

	public void setNumberOfRCPort(int numberOfRCPort) {
		this.numberOfRCPort = numberOfRCPort;
	}

	public int getNumberOfLCPort() {
		return numberOfLCPort;
	}

	public void setNumberOfLCPort(int numberOfLCPort) {
		this.numberOfLCPort = numberOfLCPort;
	}

	/**
	 * constructor method for the port
	 * 
	 * @param ID id of the port
	 * @param X x coordinate of the port
	 * @param Y y coordinate of the port
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * arraylist for all basic containers that the port currently has
	 */
	public ArrayList<Container> basicContainers = new ArrayList<Container>();
	/**
	 * arraylist for all heavy containers that the port currently has
	 */
	public ArrayList<Container> heavyContainers = new ArrayList<Container>();
	/**
	 * arraylist for all refrigerated containers that the port currently has
	 */
	public ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
	/**
	 * arraylist for all liquid containers that the port currently has
	 */
	public ArrayList<Container> liquidContainers = new ArrayList<Container>();
	
	
	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}


	/**
	 * getDistance method calculates the distance between the object itself and another Port.
	 * 
	 * @param other the other port
	 * @return the distance between ports
	 */
	public double getDistance(Port other) {
		return Math.sqrt((this.X-other.X)*(this.X-other.X)+(this.Y-other.Y)*(this.Y-other.Y));
	}
	
	/**
	 * incomingShip method adds this ship to current ArrayList.
	 * 
	 * @param s the ship that sails to port.
	 */
	@Override
	public void incomingShip(Ship s) {
		this.current.add(s);
		
	}
	
	/**
	 *  outgoingShip method adds this ship to history ArrayList and removes it from current ArrayList.
	 * 
	 * @param s the ship that leaves from port.
	 */
	@Override
	public void outgoingShip(Ship s) {
		this.current.remove(s);
		if (! this.history.contains(s)) {
			this.history.add(s);
		}
		
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

