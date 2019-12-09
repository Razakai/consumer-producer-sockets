package model;

import java.io.ObjectOutputStream;
import java.util.ArrayList;


import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Observing extends Observable {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	Object[] villan; // observing villan object
	
	
	public Observing (String villanName, ObjectOutputStream outStream) {
		super();
		villan = new Object[]{villanName, outStream}; // init villan object array
	}
	
	public ArrayList<Observer> getObservers(){
		return observers;
	}
	
	
	public void setPresent(String villanName, ObjectOutputStream outStream) { // changes values of villan array
			//System.out.println("Hero name:"+ villanName);
			this.villan[0] = villanName;
			this.villan[1] = outStream;
			setChanged();
			notifyObservers(this);
			//this.villanName = "Not Available";
		
	}
	
	
	public void notifyObservers(Observable observable) { // calls update method of all observers
		System.out.println("Notifying superheros, Possible villan has arrived!");
		for (Observer ob : observers) {
			ob.update(observable, this.villan);
		}
	}
	
	
	
	
	
	
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
}
