package model;
public  class GoodFlyManFactory extends PersonStore {
	
	@Override
	person createPerson() {
		
			return new model.GoodFlyMan();
		
		
	}

	
	

}
