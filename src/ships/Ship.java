
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Collections;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import interfaces.IShip;
import ports.Port;

/**
 * class about ship methods
 * 
 * @author Beyzanur Bektan
 *
 */
public class Ship implements IShip, Comparable<Ship>{
	/**
	 * id of the ship
	 */
	private final int ID;
	/**
	 * amount of fuel that ship has
	 */
	private double fuel = 0;
	/**
	 * port where the ship currently is
	 */
	private Port currentPort;
	/**
	 * total weight that ship can carry
	 */
	private int totalWeightCapacity;
	/**
	 * total number of containers that ship can carry
	 */
	private int maxNumberOfAllContainers;
	/**
	 * total number of heavy containers that ship can carry
	 */
	private int maxNumberOfHeavyContainers;
	/**
	 * total number of refrigerated containers that ship can carry
	 */
	private int maxNumberOfRefrigeratedContainers;
	/**
	 * total number of liquid containers that ship can carry
	 */
	private int maxNumberOfLiquidContainers;
	/**
	 * amount of fuel that ship consumes per km
	 */
	private double fuelConsumptionPerKM;
	/**
	 * total weight of all containers that ship currently has
	 */
	private int totalWeight = 0;
	/**
	 * total number of all heavy containers that ship currently has
	 */
	private int currentNumberOfHC;
	/**
	 * total number of all refrigerated containers that ship currently has
	 */
	private int currentNumberOfRC;
	/**
	 * total number of all liquid containers that ship currently has
	 */
	private int currentNumberOfLC;
	
	/**
	 * constructor method for the ship
	 * 
	 * @param ID id of the ship
	 * @param p port where the ship currently is
	 * @param totalWeightCapacity total weight that ship can carry
	 * @param maxNumberOfAllContainers total number of containers that ship can carry
	 * @param maxNumberOfHeavyContainers total number of heavy containers that ship can carry
	 * @param maxNumberOfRefrigeratedContainers total number of refrigerated containers that ship can carry
	 * @param maxNumberOfLiquidContainers total number of liquid containers that ship can carry
	 * @param fuelConsumptionPerKM amount of fuel that ship consumes per km
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int
			maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int
			maxNumberOfRefrigeratedContainers, int
			maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		this.currentPort.current.add(this);
	}
	
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public double getFuel() {
		return fuel;
	}
	
	public int getID() {
		return ID;
	}

	public Port getCurrentPort() {
		return currentPort;
	}
	
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	


	/**
	 * arraylist of all containers that ship currently has
	 */
	public ArrayList<Container> shipCurrentContainers = new ArrayList<Container>();
	/**
	 * arraylist of all basic containers that ship currently has
	 */
	public ArrayList<Container> basicContainersShip = new ArrayList<Container>();
	/**
	 * arraylist of all heavy containers that ship currently has
	 */
	public ArrayList<Container> heavyContainersShip = new ArrayList<Container>();
	/**
	 * arraylist of all refrigerated containers that ship currently has
	 */
	public ArrayList<Container> refrigeratedContainersShip = new ArrayList<Container>();
	/**
	 * arraylist of all liquid containers that ship currently has
	 */
	public ArrayList<Container> liquidContainersShip = new ArrayList<Container>();
	
	
	/**
	 * getCurrentContainers method returns the list of all containers currently in the ship.
	 * 
	 * @return the list of all containers currently in the ship
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(shipCurrentContainers);
		return this.shipCurrentContainers;
	}
	
	public int compareTo(Ship compareShip) {
		if (this.ID < compareShip.getID()) {
			return -1;
		}
		else if (this.ID > compareShip.getID()) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	
	/**
	 * sailTo method checks if a ship successfully sailed to the destination port.
	 * 
	 * @param p the port which ship sails to
	 */	
	@Override
	public boolean sailTo(Port p) {
			double totalConsumptionRates = 0;
			double overallConsumption = 0;
			for (int i = 0; i < shipCurrentContainers.size(); i++) {
				totalConsumptionRates += shipCurrentContainers.get(i).consumption();
				
			}
			
			overallConsumption += this.currentPort.getDistance(p) * (this.fuelConsumptionPerKM + totalConsumptionRates);
			
			if (overallConsumption <= this.fuel){
				this.fuel -= overallConsumption;
				Port oldPort = this.currentPort;
				oldPort.outgoingShip(this);
				this.setCurrentPort(p);
				p.incomingShip(this);
				return true;
			}
			else {
				return false;
			}
		}
		
	
	/**
	 * reFuel method adds fuel to a ship.
	 * 
	 * @param newFuel the amount of fuel
	 */
	@Override
	public void reFuel(double newFuel) {
		this.fuel += newFuel;
		
	}
	
	
	/**
	 * load method returns true if a container was successfully loaded to a ship.
	 * 
	 * @param cont the container that will be loaded to ship
	 */
	@Override
	public boolean load(Container cont) {		
		if (!shipCurrentContainers.contains(cont)) {
			if (this.currentPort.containers.contains(cont)) {
				if ((shipCurrentContainers.size() + 1) <= maxNumberOfAllContainers) {
					if ((this.totalWeight + cont.getWeight()) <= totalWeightCapacity) {
						int control = 0;
						for (int e = 0; e < this.currentPort.current.size(); e++) {
							if (this.currentPort.current.get(e).getCurrentContainers().contains(cont)) {
								control ++;
							}
						}
						if (control == 0) {
						if (cont instanceof BasicContainer) {
							this.getCurrentContainers().add(cont);
							this.currentPort.containers.remove(cont);
							this.totalWeight += cont.getWeight();
							return true;
						}
						else {
								if ((currentNumberOfHC + currentNumberOfRC + currentNumberOfLC + 1) <= maxNumberOfHeavyContainers) {
									if (cont instanceof RefrigeratedContainer) {
										if ((currentNumberOfRC + 1) <= maxNumberOfRefrigeratedContainers) {
											this.getCurrentContainers().add(cont);
											this.currentPort.containers.remove(cont);
											this.totalWeight += cont.getWeight();
											this.currentNumberOfRC += 1;
											this.currentPort.setNumberOfRCPort(this.currentPort.getNumberOfRCPort() - 1);
											return true;
										}
										else {
											return false;
										}
								}
									else if (cont instanceof LiquidContainer) {
										if ((currentNumberOfLC + 1) <= maxNumberOfLiquidContainers) {
											this.getCurrentContainers().add(cont);
											this.currentPort.containers.remove(cont);
											this.totalWeight += cont.getWeight();
											this.currentNumberOfLC += 1;
											this.currentPort.setNumberOfLCPort(this.currentPort.getNumberOfLCPort() - 1);
											return true;
										}
										else {
											return false;
										}
								}
									else {
										this.getCurrentContainers().add(cont);
										this.currentPort.containers.remove(cont);
										this.totalWeight += cont.getWeight();
										this.currentNumberOfHC += 1;
										this.currentPort.setNumberOfHCPort(this.currentPort.getNumberOfHCPort() - 1);
										return true;
									}	
							}
								else {
									return false;
								}					
							}
						}
						else {
							return false;
						}
						}
					else {	
						return false;
					}	
				}
				else {
						return false;
				}	
			
			}
			else {
				return false;
			}
		}	
		else {
			return false;
		}
	}
	
	/**
	 * unLoad method returns true if a container was successfully unloaded from a ship.
	 * 
	 * @param cont the container that will be unloaded from ship
	 */
	@Override
	public boolean unLoad(Container cont) {
		if (shipCurrentContainers.contains(cont)){
			this.getCurrentContainers().remove(cont);
			this.currentPort.containers.add(cont);
			this.totalWeight -= cont.getWeight();
			if (cont instanceof HeavyContainer){
				if (cont instanceof RefrigeratedContainer) {
					this.currentNumberOfRC += 1;
					this.currentPort.setNumberOfRCPort(this.currentPort.getNumberOfRCPort() + 1);
				}
				else if (cont instanceof LiquidContainer) {
					this.currentNumberOfLC += 1;
					this.currentPort.setNumberOfRCPort(this.currentPort.getNumberOfLCPort() + 1);
				}
				else {
					this.currentNumberOfHC += 1;
					this.currentPort.setNumberOfHCPort(this.currentPort.getNumberOfHCPort() + 1);
				}
			}
			return true;
		}
		
		else {
			return false;
		}
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

