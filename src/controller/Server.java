package controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.person;

public class Server{
	
	public static String file = "battle-zone";
	
	
	public static int fileNum = 1;
	public static final int PORT = 3139;
	public static boolean needHero = false;
	private static final int maxClientsCount = 2;
	private static final ServerThread[] threads = new ServerThread[maxClientsCount];
	private static ServerSocket serverSocket = null;
	  // The client socket.
	  private static Socket socket = null;
	
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		
		while (true) {
		      try {
		        socket = serverSocket.accept();
		        int i = 0;
		        for (i = 0; i < maxClientsCount; i++) {
		          if (threads[i] == null) { // if null that locatin in the array is empty so it fills it
		            (threads[i] = new ServerThread(socket, threads)).start(); // adds new thread to the array starting the serverThread unique for that socket only
		            break;
		          }
		        }
		        if (i == maxClientsCount) {
		          System.out.println("Server is full");
		          socket.close();
		        }
		      } catch (IOException e) {
		        System.out.println(e);
		      }
		    }

	}
}
	
	
	
	
	







	
	class ServerThread extends Thread{
		  
		
		  private String clientName = null;
		  private ObjectOutputStream outStream = null;
		  private ObjectInputStream inStream = null;
		  private Socket socket = null;
		  private final ServerThread[] threads;
		  private int maxClientsCount;
		
		
		
		public ServerThread(Socket socket, ServerThread[] threads) { // constructor of Serverthread
		    this.socket = socket;
		    this.threads = threads;
		    maxClientsCount = threads.length;
		  }
		
		
		
		
		
		
	public void run() {
		try {
		System.out.println("Server is running");
	
		 outStream = new ObjectOutputStream(socket.getOutputStream()); // gets socket object input and output streams
		 inStream = new ObjectInputStream(socket.getInputStream());
	
		 
		 for(int z = 0; z < 5; z++) { // handles battles
		person recvPacket = (person)inStream.readObject(); // reads person object
		String name = recvPacket.getName();
		System.out.println(name);
		String[] parts = name.split(" ");
		 
		 //if parts[0].equals("Good") and file is created
		
		if(parts[0].equals("Good")) { // if name contains good
			File location = new File(Server.file+".txt");
			if(location.exists()) {
				System.out.println("Writting Hero");
			
			
				try(FileWriter fw = new FileWriter("battle-zone.txt", true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw))
					{
						out.println("\n"+recvPacket.getName());
						System.out.println("Hero added");
				    
				    
					} catch (IOException e) {
						System.out.println(e);
				    
					}
			
				try {
					Files.move 
							(Paths.get("battle-zone.txt"),  
									Paths.get("savedTheWorldAgain\\"+Server.file+Server.fileNum+".txt"));
							System.out.println("File renamed and moved successfully"); 
					Server.fileNum += 1;
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Failed to move the file"); 
				} 
				//Server.needHero = false;
			}
			
		}
		
		if(parts[0].equals("Bad")) { // if name contains bad
			File location = new File(Server.file+".txt");
			//if(!location.exists()) {
			//if(location.exists()) {
			System.out.println("Writing Villan");
			try(FileWriter fw = new FileWriter("battle-zone.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(name); // write villan name to battle-zone file
				    System.out.println("Villan added");
				    
				    
				} catch (IOException e) {
					System.out.println(e);
				    
				}
			
			// send the hero client the villan object for it to deal with
			for(int i = 0; i < maxClientsCount; i++) {
				if(threads[i] != null && threads[i] != this) {
					threads[i].outStream.writeObject(recvPacket);
					
				}
			}
			
		}
		 }
			
			
	
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}catch(Exception e) {
		System.out.println(e);
		}
	}
}
		
		

	
	
	
	
