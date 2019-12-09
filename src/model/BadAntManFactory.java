package model;

public class BadAntManFactory extends PersonStore {
	
	@Override
	person createPerson() {
		
			return new BadAntMan();
		
		
	}
	

}