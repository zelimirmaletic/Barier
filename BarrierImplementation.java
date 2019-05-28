
import java.util.Random;

public class BarrierImplementation
{
    //WE DEFINE CONSTANTS FOR CARS AND ROUNDS
    private static final int NUM_OF_CARS = 5;
    private static final int NUM_OF_ROUNDS = 3;
    

    public static void main(String[] args) 
	{
		Barrier barrier = new Barrier(NUM_OF_CARS);
		for (int i = 1; i <= NUM_OF_CARS; i++) 
		{
			new Racer(i, barrier, NUM_OF_ROUNDS);
		}
    }


    //NOW WE MAKE A BARRIER CLASS
    public static class Barrier
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
             for(int round = 0; round < numberOfIterations;round++)
             {
                System.out.println("Car "+ racerID + " starts " + round+1 + " round." );
                try
                {
                    Thread.sleep((new Random()).nextInt(5000));
                }catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                barrier.arrivedToBarrier(racerID);
             }   
             System.out.println("Car " + racerID + " finished the race!");
        }
    }


}
