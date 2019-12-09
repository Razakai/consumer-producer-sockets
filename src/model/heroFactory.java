package model;
import java.io.ObjectOutputStream;
import java.util.Observable;


import java.util.Observer;

@SuppressWarnings("deprecation")
public class heroFactory implements Observer {
	static private PersonStore goodFlyMan = new GoodFlyManFactory(); // creates hero factory objects
	static private PersonStore goodStrongMan = new GoodStrongManFactory();
	static private PersonStore goodAntMan = new GoodAntManFactory();
	public void heroCreate(String characterType, ObjectOutputStream outStream){ // selects the correct hero for the villan
	      if(characterType == null){
	         //return null;
	      }		
	      if(characterType.equalsIgnoreCase("Bad FlyMan")){
	        // return new heroMark();
	    	  
	    	  person temp = goodFlyMan.createPerson();
	    	 // System.out.println("my name is:"+temp.getName());
	    	  try {
	    		  outStream.writeObject(temp);
	    	  }catch(Exception e) {
	    		  System.out.println(e);
	    	  }
	    	  
	         
	      } else if(characterType.equalsIgnoreCase("Bad StrongMan")){
	        // return new heroJoe();
	    	 person temp = goodStrongMan.createPerson();
	    	// System.out.println("my name is:"+temp.getName());
	    	 try {
	    		  outStream.writeObject(temp);
	    	  }catch(Exception e) {
	    		  System.out.println(e);
	    	  }
	   }	
	      else if(characterType.equalsIgnoreCase("Bad AntMan")){
	         //return new heroPeter();
	    	  person temp = goodAntMan.createPerson();
	    	  //System.out.println("my name is:"+temp.getName());
	    	  try {
	    		  outStream.writeObject(temp);
	    	  }catch(Exception e) {
	    		  System.out.println(e);
	    	  }
	   }
		//return null;
	}

	@Override
	public void update(Observable arg0, Object arg1) { // update method of observable, passing in villan name and file number
		//System.out.println("Hero name:"+ String.valueOf(arg1));
		//System.out.println("Hero name:"+ arg1[0]);
		Object[] objects = (Object[]) arg1;
		heroCreate(String.valueOf(objects[0]), (ObjectOutputStream) objects[1]);
		
	}

	
	

	
		
	

}
