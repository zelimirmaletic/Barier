import com.sun.swing.internal.plaf.basic.resources.basic;

java.util.*;


public class BarierrImplementation
{
    //WE DEFINE CONSTANTS FOR CARS AND ROUNDS
    private static final int NUM_OF_CARS = 5;
    private static final int NUM_OF_ROUNDS = 3;
    
    //NOW WE MAKE A BARRIER CLASS
    public class Barrier
    {
        private final int numberOfRacers;
        private int counter = 0;
        //CONSTRUCTOR
        public Barrier(int numberOfRacers)
        {
            this.numberOfRacers = numberOfRacers;
        }

        //KEEPING TRACK OF ARRIVED CARS
        public synchronized void arrivedToBarrier(int racerID)
        {
            System.out.println("Car "+ racerID +" arrived!");
            counter++;
            if(counter==numberOfRacers)
            {
                System.out.println("ALL CARS ARRIVED!");
                //reset counter for a new round
                counter = 0;
                //notify all cars to start new round
                notifyAll();
            }
            else
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

    }

    public static class Racer extends Thread
    {
        private final int racerID;
        private final int numberOfIterations;
        private final Barrier barrier;

        //MAKE CONSTRUCTOR
        public Racer(int racerID, Barrier barrier, int numberOfIterations)
        {
            this.barrier=barrier;
            this.numberOfIterations=numberOfIterations;
            this.racerID = racerID;
            start();
        }

        public synchronized void run()
        {
            for(int round = 0; round<numberOfIterations;round++)
            {
                
            }
        }
    }

}