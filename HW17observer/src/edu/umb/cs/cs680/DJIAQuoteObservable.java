package edu.umb.cs.cs680;

public class DJIAQuoteObservable extends Observable{
	
	public void changeQuote(String t, float q){
		this.setChanged();
		this.notifyObservers(event = new DJIAEvent(q, t));
	}

}