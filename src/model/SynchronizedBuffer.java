package model;
// Fig. 26.18: SynchronizedBuffer.java
// Synchronizing access to shared data using Object 
// methods wait and notifyAll.
public class SynchronizedBuffer implements Buffer
{
   //private boolean buffer; // shared by producer and consumer threads
   private boolean occupied = false;// whether the buffer is occupied
   
   // place value into buffer
   public synchronized void set() throws InterruptedException
   {
      // while there are no empty locations, place thread in waiting state
      while ( occupied ) 
      {
         // output thread information and buffer information, then wait
         System.out.println( "Producer tries to write, Producer waits." );
        // displayState( "Buffer full. Producer waits." );
         wait();
      } // end while
        
     // buffer = true; // set new buffer value
        
      // indicate producer cannot store another value
      // until consumer retrieves current buffer value
      occupied = true;
        
      System.out.println("Producer writes");
      
      notifyAll(); // tell waiting thread(s) to enter runnable state
       // end method set; releases lock on SynchronizedBuffer 
     
   }
    
   // return value from buffer
   public synchronized boolean get() throws InterruptedException
   {
      // while no data to read, place thread in waiting state
      while ( !occupied )
      {
         // output thread information and buffer information, then wait
         System.out.println( "Consumer tries to read, Consumer waits" );
        // displayState( "Buffer empty. Consumer waits." );
         wait();
      } // end while
      
      // indicate that producer can store another value 
      // because consumer just retrieved buffer value
      //int bufferNum = this.buffer;
      
      occupied = false;

    //  displayState( "Consumer reads " + buffer );
      
      notifyAll(); // tell waiting thread(s) to enter runnable state
      //buffer = false;
      return true;
   } // end method get; releases lock on SynchronizedBuffer 
    
   // display current operation and buffer state
  /* public void displayState( String operation )
   {
      System.out.printf( "%-40s%d\t\t%b\n\n", operation, buffer, 
         occupied );
   } // end method displayState*/
} // end class SynchronizedBuffer
