package ebook.controller;

/**
 * The implementation of this interface provides the reactions for Ebooking system. 
 * 
 * @author ssome
 *
 */
public interface IEbookingReaction {

	void lookupReservation();

	void displayFlight();

	void errorMessage();

	void askForReservationNumber();

	void askForBaggages();

	void displayReservationDetails();

	void displayNewFlightInfo();

	void askCustomerWishToChange();

	void printBoardingPass();

	void ejectBoardingPass();

	void askForNumberOfPieces();

	void printBaggageSlips();

	void ejectBaggageSlips();

	void displayProceedsToAgentMessage();

	void stopAlarm();

	void startAlarm();

}
