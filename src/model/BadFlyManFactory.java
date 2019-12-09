package model;

public class BadFlyManFactory extends PersonStore {
	
	@Override
	person createPerson() {
		
			return new BadFlyMan();
		
		
	}

	
	

}
