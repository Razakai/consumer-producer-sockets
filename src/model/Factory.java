package model;

import java.util.Random;

public class Factory {
	static private PersonStore BadFlyMan = new BadFlyManFactory(); // creates villan factories
	static private PersonStore BadStrongMan = new BadStrongManFactory();
	static private PersonStore BadAntMan = new BadAntManFactory();
	static private Random rand = new Random();
	public person orderPerson(){ //randomly generates a new villan object
		int num = rand.nextInt(3)+1;
	      System.out.println("Creating villan now!!");
	      
	       if(num == 1){
	        
	    	  person temp = BadStrongMan.createPerson();
	    	 //System.out.println("my name is:"+temp.getName());
	    	 return temp;
	    	  //writeToFile.write(temp.getName());
	       }
	   	
	      else if(num == 2){
	         
	    	  person temp = BadAntMan.createPerson();
	    	  //System.out.println("my name is:"+temp.getName());
	    	  //writeToFile.write(temp.getName());
	    	  return temp;
	      }
	      
	      else if(num == 3){
		         
		    	  person temp = BadFlyMan.createPerson();
		    	  //System.out.println("my name is:"+temp.getName());
		    	  //writeToFile.write(temp.getName());
		    	  return temp;
		      }
		return null;
	   
	}

}
