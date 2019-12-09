package application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.client;
import controller.villanClient;
import model.Buffer;
import model.Factory;
import model.SynchronizedBuffer;
import model.person;

public class SharedBufferTest2
{
   public static void main( String[] args )
   {
      // create a CachedThreadPool
      ExecutorService application = Executors.newCachedThreadPool();

      // create SynchronizedBuffer to store boolean
      Buffer sharedLocation = new SynchronizedBuffer();

      System.out.printf( "%-40s%s\t\t%s\n%-40s%s\n\n", "Operation", 
         "Buffer", "Occupied", "---------", "------\t\t--------" );

      // execute the Producer and Consumer tasks
      application.execute( new client( sharedLocation ) ); // creates new thread for both clients, executes run method
      application.execute( new villanClient( sharedLocation ) );

      application.shutdown();
      
      
     /* Factory factory = new Factory();
      person Villan = factory.orderPerson();
      System.out.println("Villan created is: "+Villan.getName());*/
    		  
   } 
}
