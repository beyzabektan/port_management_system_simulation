
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;
import ships.Ship;

/**
 * 
 * @author Beyzanur Bektan
 *
 */
public class Main {
	/**
	 * main method is the method where the execution occurs
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		

		
		ArrayList<Container> allContainers = new ArrayList<Container>();
		ArrayList<Ship> allShips = new ArrayList<Ship>();
		ArrayList<Port> allPorts = new ArrayList<Port>();
		
		int N = in.nextInt();
		
		int ccount = 0;
		int scount = 0;
		int pcount = 0;
		
		for (int i = 0; i < N; i ++) {
			int action = in.nextInt();
			
			if (action == 1) {
				int portID = in.nextInt();
				int weightOfCont = in.nextInt();
				if (in.hasNext("R")){
					in.next();
					allContainers.add((ccount), new RefrigeratedContainer((ccount), weightOfCont));
					allPorts.get(portID).containers.add(allContainers.get(ccount));
					allPorts.get(portID).setNumberOfRCPort(allPorts.get(portID).getNumberOfRCPort() + 1);
					ccount ++;
				}
				else if (in.hasNext("L")){
					in.next();
					allContainers.add((ccount), new LiquidContainer((ccount), weightOfCont));
					allPorts.get(portID).containers.add(allContainers.get(ccount));
					allPorts.get(portID).setNumberOfLCPort(allPorts.get(portID).getNumberOfLCPort() + 1);
					ccount ++;
				}
				else {
					if (weightOfCont <= 3000) {
						allContainers.add((ccount), new BasicContainer((ccount), weightOfCont));
						allPorts.get(portID).containers.add(allContainers.get(ccount));
						ccount ++;
					}
					else {
						allContainers.add((ccount), new HeavyContainer((ccount), weightOfCont));
						allPorts.get(portID).containers.add(allContainers.get(ccount));
						allPorts.get(portID).setNumberOfHCPort(allPorts.get(portID).getNumberOfHCPort() + 1);
						ccount ++;
					}
				}				
			}
			
			else if (action == 2) {
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				int d = in.nextInt();
				int e = in.nextInt();
				int f = in.nextInt();
				double g = in.nextDouble();
				allShips.add((scount), new Ship((scount), allPorts.get(a), b, c, d, e, f, g));
				scount ++;				
			}
			
			else if (action == 3) {
				double portX = in.nextDouble();
				double portY = in.nextDouble();
				allPorts.add(pcount, new Port((pcount), portX, portY));
				pcount ++;				
			}
			
			else if (action == 4) {
				int shipID = in.nextInt();
				int contID = in.nextInt();
				allShips.get(shipID).load(allContainers.get(contID));
				
			}
			
			else if (action == 5) {
				int shipID = in.nextInt();
				int contID = in.nextInt();
				allShips.get(shipID).unLoad(allContainers.get(contID));
					
			
			}
			
			else if (action == 6) {
				
				int shipID = in.nextInt();
				int portID = in.nextInt();
				
				allShips.get(shipID).sailTo(allPorts.get(portID));
			}
			
			else if (action == 7) {
				int shipID = in.nextInt();
				double amountOfFuel = in.nextDouble();
				allShips.get(shipID).reFuel(amountOfFuel);
			}			
		}	
		
			
		for (int j = 0; j < allPorts.size(); j++) {
			out.println("Port " + j + ": (" + String.format("%.2f",allPorts.get(j).getX()) + ", " + String.format("%.2f",allPorts.get(j).getY()) + ")");
			Collections.sort(allPorts.get(j).containers);
			Collections.sort(allPorts.get(j).current);
			for (int u = 0; u < allPorts.get(j).containers.size(); u++) {
				if (allPorts.get(j).containers.get(u) instanceof BasicContainer) {
					allPorts.get(j).basicContainers.add(allPorts.get(j).containers.get(u));
				}
				else if (allPorts.get(j).containers.get(u) instanceof RefrigeratedContainer) {
					allPorts.get(j).refrigeratedContainers.add(allPorts.get(j).containers.get(u));
				}
				
				else if (allPorts.get(j).containers.get(u) instanceof LiquidContainer) {
					allPorts.get(j).liquidContainers.add(allPorts.get(j).containers.get(u));
				}
				
				else {
					allPorts.get(j).heavyContainers.add(allPorts.get(j).containers.get(u));
				}
			}
			
			
			if (allPorts.get(j).basicContainers.size() != 0) {
				out.print("  BasicContainer:");
				allPorts.get(j).basicContainers.forEach((z) -> out.print(" " + z.getID()));
				out.println();
			}
			
			if (allPorts.get(j).heavyContainers.size() != 0) {
				out.print("  HeavyContainer:");
				allPorts.get(j).heavyContainers.forEach((z) -> out.print(" " + z.getID()));
				out.println();
			}
			
			if (allPorts.get(j).refrigeratedContainers.size() != 0) {
				out.print("  RefrigeratedContainer:");
				allPorts.get(j).refrigeratedContainers.forEach((z) -> out.print(" " + z.getID()));
				out.println();
			}
			
			if (allPorts.get(j).liquidContainers.size() != 0) {
				out.print("  LiquidContainer:");
				allPorts.get(j).liquidContainers.forEach((z) -> out.print(" " + z.getID()));
				out.println();
			}
			
			
	
			
			
			for (int m = 0; m < allPorts.get(j).current.size(); m++) {
				out.println("  Ship " + allPorts.get(j).current.get(m).getID() + ": " + String.format("%.2f",allShips.get(allPorts.get(j).current.get(m).getID()).getFuel()));
				int n = allPorts.get(j).current.get(m).getID();
				Collections.sort(allShips.get(n).getCurrentContainers());
				for (int u = 0; u < allShips.get(n).getCurrentContainers().size(); u++) {
					if (allShips.get(n).getCurrentContainers().get(u) instanceof BasicContainer) {
						allShips.get(n).basicContainersShip.add(allShips.get(n).getCurrentContainers().get(u));
					}
					else if (allShips.get(n).getCurrentContainers().get(u) instanceof RefrigeratedContainer) {
						allShips.get(n).refrigeratedContainersShip.add(allShips.get(n).getCurrentContainers().get(u));
					}
					
					else if (allShips.get(n).getCurrentContainers().get(u) instanceof LiquidContainer) {
						allShips.get(n).liquidContainersShip.add(allShips.get(n).getCurrentContainers().get(u));
					}
					
					else {
						allShips.get(n).heavyContainersShip.add(allShips.get(n).getCurrentContainers().get(u));
					}
				}
				
				
				
				if (allShips.get(n).basicContainersShip.size() != 0) {
					out.print("    BasicContainer:");
					allShips.get(n).basicContainersShip.forEach((z) -> out.print(" " + z.getID()));
					out.println();
				}
				
				if (allShips.get(n).heavyContainersShip.size() != 0) {
					out.print("    HeavyContainer:");
					allShips.get(n).heavyContainersShip.forEach((z) -> out.print(" " + z.getID()));
					out.println();
				}
				
				if (allShips.get(n).refrigeratedContainersShip.size() != 0) {
					out.print("    RefrigeratedContainer:");
					allShips.get(n).refrigeratedContainersShip.forEach((z) -> out.print(" " + z.getID()));
					out.println();
				}
				
				if (allShips.get(n).liquidContainersShip.size() != 0) {
					out.print("    LiquidContainer:");
					allShips.get(n).liquidContainersShip.forEach((z) -> out.print(" " + z.getID()));
					out.println();
				}
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

