// Author: Trevor Lilly
// Date: 01/11/2022
// Program Name: Lilly_Drone
// Description: A program that simulates a drone, allowing it to move along a X, Y, Z axis and change orientation.

import java.util.Scanner;
import static java.lang.System.out;

public class Lilly_Drone {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		boolean continueToRun = true;
		// operates the choice menu
		do { char choice = processMenu();
		// creating drone object
		DroneControls drone = new Drone();
		switch (choice) {
		
		case '1':
			// this raises up the drone's Z coordinate
			drone.up();
			break;
		
		case '2':
			// this lowers the drone's Z coordinate
			drone.down();
			break;
			
		case '3':
			// this moves the drone's X or Y coordinate forward depending on orientation
//            north(orientation 0): x0, y++
//            east(orientation 1):  x++, y0
//            south(orientation2): x0, y--
//            west(orientation 3): x--, y0
			drone.forward();
			break;
		
		case '4':
			// this moves the drone's X or Y coordinate backwards depending on orientation. 
//          north(orientation 0): x0, y--
//          east(orientation 1):  x--, y0
//          west(orientation 2): x++, y0
//          south(orientation 3): x0, y++
			drone.backward();
			break;
			
		case '5':
			// this rotates the drone's orientation counter-clockwise. decrements the orientation counter lower.
			// orientation counter should only go down to zero. if it goes past zero, it'll go back to three, mimicking the drone spinning around.
			drone.left();
			break;
		
		case '6':
			// this rotates the drone's orientation clockwise. increments the orientation counter upwards. 
			// orientation should only increment up to three, then it'll go back to zero. this mimics a drone spinning around in the air. 
			drone.right();
			break;
			
		case '7':
			// prints the XYZ of the drone's coordinates and the orientation
			drone.coordinates();
			break;
			
		case '8':
			continueToRun = false;
		}
		} while (continueToRun);
		out.println("Thanks for using the drone program!");
	}
	
	interface DroneControls {
		void up();
		void down();
		void coordinates();
		void forward();
		void backward();
		void left();
		void right();
	}
	
	// make as many drones as you want! 
	static class Drone implements DroneControls {
		static int x;
		static int y;
		static int z;
		static int orientation;
		
		public void up() {
			z++;
		}

		@Override
		public void coordinates() {
			out.println("Current coordinates are: [X = " + x
					+ ", Y = " + y + ", Z = " + z + 
					"] And the orientation is ");
			
			if (orientation == 0)
				out.println("North\n");
			if (orientation == 1)
				out.print("East\n");
			if (orientation == 2)
				out.print("South\n");
			if (orientation == 3)
				out.print("West\n");
		}

		@Override
		public void down() {
			z--;
			// having a negative z coordinate doesn't make too much sense to me, wouldn't that mean the drone is underground? 
			if (z < 0)
				z = 0;
		}

		@Override
		public void left() {
			orientation--;
			// the aforementioned check. if it goes lower than 0, it SHOULD just loop back to three
			if (orientation < 0)
				orientation = 3;
		}

		@Override
		public void right() {
			orientation++;
			// the aforementioned check. if it goes over 3, it SHOULD just loop back to zero
			if (orientation > 3)
				orientation = 0;
		}

		@Override
		public void forward() {
			// moving forward north is just ascending the y axis
			if (orientation == 0)
				y++;
			// going forward east is just heading along the x-axis
			if (orientation == 1)
				x++;
			// going south is really just going backwards on the y axis if you think about it
			if (orientation == 2)
				y--;
			// vice versa, moving west on a map is descending the x axis
			if (orientation == 3)
				x--;
		}

		@Override
		public void backward() {
			if (orientation == 0)
				y--;
			if (orientation == 1)
				x--;
			if (orientation == 2)
				y++;
			if (orientation == 3)
				x++;
		}
	}
	
	private static char processMenu() {
		Scanner keyboard  = new Scanner(System.in);
		String response1 = "0";
		char response = response1.charAt(0);
		out.println("Enter 1 to move Drone Up");
		out.println("Enter 2 to move Drone Down");
		out.println("Enter 3 to move Drone Forward");
		out.println("Enter 4 to move Drone Backwards");
		out.println("Enter 5 to move Turn Drone Left");
		out.println("Enter 6 to move Turn Drone Right");
		out.println("Enter 7 to move Print Coordinates");
		out.println("Enter 8 to move Exit The Program");
		out.print(" >>>");
		response1 = keyboard.nextLine();
		response = response1.charAt(0);
		
		if (response != '1' && response != '2' && response != '3' && response != '4' && response != '5'
				&& response != '6' && response != '7' && response != '8'){
			System.out.println("Invalid menu selection. Try again.");
		}
		return response;
	}
}
