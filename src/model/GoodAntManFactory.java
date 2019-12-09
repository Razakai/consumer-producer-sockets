package model;
public  class GoodAntManFactory extends PersonStore {
	
	@Override
	person createPerson() {
		
			return new model.GoodAntMan();
		
		
	}

	
	

}