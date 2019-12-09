package controller;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.Server;
import model.Buffer;
import model.Factory;
import model.person;


public class villanClient extends Thread {

	
	private Factory factory = new Factory();
	private Buffer sharedLocation;
	
	
	public villanClient(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}

	
	public void run(){
		try {
		Socket socket = new Socket("127.0.0.1", Server.PORT);
		
		ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
		//ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
		
		for(int i = 0; i < 5; i++) {
			System.out.println(i+ " villan side");
			person villan = factory.orderPerson();
			//System.out.println("Villan created: ");
			outStream.writeObject(villan);
			sharedLocation.set();
			Thread.sleep(5000);
		//}
		//person recvPacket = (person)inStream.readObject();
		//System.out.println(recvPacket.getName()+" villan client");
		}
		outStream.close();
		socket.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}