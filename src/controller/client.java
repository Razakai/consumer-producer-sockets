package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.Server;
import model.Buffer;
import model.Observing;
import model.heroFactory;
import model.person;

public class client extends Thread{

	   private heroFactory factory = new heroFactory();
	   private Observing observe = new Observing("", null);
	   private Buffer sharedLocation;
	   
	   
	   public client(Buffer sharedLocation) {
		   this.sharedLocation = sharedLocation;
	   }
	  
	
	public void run(){
		try {
			
		Socket socket = new Socket("127.0.0.1", Server.PORT); // creates new socket localhost an port number
		
		observe.registerObserver(factory); // registers factory as observer
		
		
		ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream()); // creates ObjectInput and output streams linked to the socket
		ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
		
		
		for(int i = 0; i < 5; i++) { // deals with 5 villans
			System.out.println(i+ " hero side");
		person recvPacket = (person)inStream.readObject(); // waits until it receives the Person object form the server
		System.out.println(recvPacket.getName()+ "  hero client"); // prints the person name
		observe.setPresent(recvPacket.getName(), outStream); // set present the persons name in the observer also passing in the objectoutputstream so it can send info back to the server
		sharedLocation.get(); // sets boolean as villan is defeated
		//}
		}
		outStream.close();
		socket.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
