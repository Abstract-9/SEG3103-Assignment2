package ebook.simulator;

import ebook.controller.IEbookingReaction;

public class BasicReactions implements IEbookingReaction {

	@Override
	public void lookupReservation() {
		System.out.println("lookupReservation");
	}

	@Override
	public void displayFlight() {
		System.out.println("displayFlight");
	}

	@Override
	public void errorMessage() {
		System.out.println("errorMessage");
	}

	@Override
	public void askForReservationNumber() {
		System.out.println("askForReservationNumber");
	}

	@Override
	public void askForBaggages() {
		System.out.println("askForBaggages");

	}

	@Override
	public void displayReservationDetails() {
		System.out.println("displayReservationDetails");

	}

	@Override
	public void displayNewFlightInfo() {
		System.out.println("displayNewFlightInfo");

	}

	@Override
	public void askCustomerWishToChange() {
		System.out.println("askCustomerWishToChange");

	}

	@Override
	public void printBoardingPass() {
		System.out.println("printBoardingPass");
	}

	@Override
	public void ejectBoardingPass() {
		System.out.println("ejectBoardingPass");

	}

	@Override
	public void askForNumberOfPieces() {
		System.out.println("askForNumberOfPieces");

	}

	@Override
	public void printBaggageSlips() {
		System.out.println("printBaggageSlips");
	}

	@Override
	public void ejectBaggageSlips() {
		System.out.println("ejectBaggageSlips");

	}

	@Override
	public void displayProceedsToAgentMessage() {
		System.out.println("displayProceedsToAgentMessage");

	}

	@Override
	public void stopAlarm() {
		System.out.println("stopAlarm");
	}

	@Override
	public void startAlarm() {
		System.out.println("startAlarm");
	}

}
