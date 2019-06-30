package ebook.controller;

public class EbookingEventNotDefinedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EbookingEventNotDefinedException(String event) {
		super(event);
	}

}
