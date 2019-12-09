package model;
// Buffer interface specifies methods called by Producer and Consumer.
public interface Buffer
{
   // place int value into Buffer
   public void set() throws InterruptedException; 

   // obtain int value from Buffer
   public boolean get() throws InterruptedException; 
} // end interface Buffer

