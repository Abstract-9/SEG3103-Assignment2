package ebook.controller;

public class EbookingControl {
	enum Status {IDLE, LOOKINGUPRESERVATION, DISPLAYINGFLIGHT, WAITFORRESPONSE, 
		WAITFORBAGGAGENUMBERS,WAITFORDOCUMENTSWITHRAWAL, SOUNDINGALARM}
	
	private Status current;
	private IEbookingReaction reactions;
	
	public EbookingControl(IEbookingReaction react) {
		setReactions(react);
		// set initial state
		setCurrent(Status.IDLE); 
	}
	
	public Status getCurrent() {
		return current;
	}
	public void setCurrent(Status current) {
		this.current = current;
	}
	
	public IEbookingReaction getReactions() {
		return reactions;
	}

	public void setReactions(IEbookingReaction reactions) {
		this.reactions = reactions;
	}
	
	public void reservationNumber() throws EbookingEventNotDefinedException {
		if (current == Status.IDLE) {
			reactions.lookupReservation();
			setCurrent(Status.LOOKINGUPRESERVATION);
		} else {
			throw new EbookingEventNotDefinedException("reservationNumber");
		}
	}
	
	public void found() throws EbookingEventNotDefinedException {
		if (current == Status.LOOKINGUPRESERVATION) {
			reactions.displayFlight();
			setCurrent(Status.DISPLAYINGFLIGHT);
		} else {
			throw new EbookingEventNotDefinedException("found");
		}
	}
	
	public void notFound() throws EbookingEventNotDefinedException {
		if (current == Status.LOOKINGUPRESERVATION) {
			reactions.errorMessage();
			reactions.askForReservationNumber();
			setCurrent(Status.IDLE);
		} else {
			throw new EbookingEventNotDefinedException("notFound");
		}
	}
	
	public void confirm() throws EbookingEventNotDefinedException {
		if (current == Status.DISPLAYINGFLIGHT) {
			reactions.askForBaggages();
			setCurrent(Status.WAITFORRESPONSE);
		} else {
			throw new EbookingEventNotDefinedException("confirm");
		}
	}
	
	public void change() throws EbookingEventNotDefinedException {
		if (current == Status.DISPLAYINGFLIGHT) {
			reactions.displayReservationDetails();
			reactions.askCustomerWishToChange();
		} else {
			throw new EbookingEventNotDefinedException("change");
		}
	}
	
	public void cancel() throws EbookingEventNotDefinedException {
		if (current == Status.DISPLAYINGFLIGHT || 
				current == Status.WAITFORBAGGAGENUMBERS ||
				current == Status.WAITFORDOCUMENTSWITHRAWAL ||
				current == Status.WAITFORRESPONSE ) {
			reactions.askForReservationNumber();
			setCurrent(Status.IDLE);
		} else {
			throw new EbookingEventNotDefinedException("cancel");
		}
	}
	
	public void no() throws EbookingEventNotDefinedException {
		if (current == Status.WAITFORRESPONSE) {
			reactions.printBoardingPass();
			reactions.ejectBoardingPass();
			setCurrent(Status.WAITFORDOCUMENTSWITHRAWAL);
		} else {
			throw new EbookingEventNotDefinedException("no");
		}
	}
	
	public void yes() throws EbookingEventNotDefinedException {
		if (current == Status.WAITFORRESPONSE) {
			reactions.askForNumberOfPieces();
			setCurrent(Status.WAITFORBAGGAGENUMBERS);
		} else {
			throw new EbookingEventNotDefinedException("yes");
		}
	}
	
	public void numberOfPieces() throws EbookingEventNotDefinedException {
		if (current == Status.WAITFORBAGGAGENUMBERS) {
			reactions.printBoardingPass();
			reactions.ejectBoardingPass();
			reactions.printBaggageSlips();
			reactions.ejectBaggageSlips();
			reactions.displayProceedsToAgentMessage();
			setCurrent(Status.WAITFORDOCUMENTSWITHRAWAL);
		} else {
			throw new EbookingEventNotDefinedException("numberOfPieces");
		}
	}
	
	public void withdrawDocuments() throws EbookingEventNotDefinedException {
		if (current == Status.WAITFORDOCUMENTSWITHRAWAL) {
			reactions.askForReservationNumber();
			setCurrent(Status.IDLE);
		} else if (current == Status.SOUNDINGALARM) {
			reactions.stopAlarm();
			reactions.askForReservationNumber();
			setCurrent(Status.IDLE);
		} else {
			throw new EbookingEventNotDefinedException("withdrawDocuments");
		}
	}
	
	public void timeout() throws EbookingEventNotDefinedException {
		if (current == Status.WAITFORDOCUMENTSWITHRAWAL) {
			reactions.startAlarm();
			setCurrent(Status.SOUNDINGALARM);
		} else {
			throw new EbookingEventNotDefinedException("timeout");
		}
	}
}
