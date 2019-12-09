package model;

import java.io.Serializable;

public abstract class PersonStore implements Serializable {

	public person orderPerson(){
		person person;
		person = createPerson();
		
		return person;
		
	}
	
	abstract person createPerson();
	

}
