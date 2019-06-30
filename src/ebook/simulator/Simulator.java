package ebook.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ebook.controller.EbookingControl;
import ebook.controller.EbookingEventNotDefinedException;

public class Simulator {
	
	public static void main(String[] args) {
		// create controller
		EbookingControl controller = new EbookingControl(new BasicReactions());
		// get event and process
		BufferedReader keyboard = new BufferedReader( new InputStreamReader(System.in));
		while (true) {
			System.out.print("Next Event: ");
			try {
				String input = keyboard.readLine();
				if (input.equals("reservation number")) {
					controller.reservationNumber();
				} else if (input.equals("found")) {
					controller.found();
				} else if (input.equals("not found")) {
					controller.notFound();
				} else if (input.equals("CHANGE")) {
					controller.change();
				} else if (input.equals("CONFIRM")) {
					controller.confirm();
				} else if (input.equals("NO")) {
					controller.no();
				} else if (input.equals("YES")) {
					controller.yes();
				} else if (input.equals("number of pieces")) {
					controller.numberOfPieces();
				} else if (input.equals("withdraw documents")) {
					controller.withdrawDocuments();
				} else if (input.equals("TIMEOUT")) {
					controller.timeout();
				} else if (input.equals("cancel")) {
					controller.cancel();
				} else {
					System.out.println("Not a supported event, try again");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EbookingEventNotDefinedException e) {
				e.printStackTrace();
			}
		} 
	}

}
