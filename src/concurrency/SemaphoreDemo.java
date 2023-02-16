package concurrency;

// java program to demonstrate 
// use of semaphores Locks 

import java.util.concurrent.Semaphore;

class WorkerThread extends Thread
{ 
	Semaphore sem;
	SharedObject sharedObject;

	public WorkerThread(Semaphore sem, SharedObject sharedObject, String threadName)
	{ 
		super(threadName); 
		this.sem = sem;
		this.sharedObject = sharedObject;
	}

	@Override
	public void run() { 
		
		// run by thread A 
		if(this.getName().equals("A")) 
		{ 
			System.out.println("Starting " + getName());
			try
			{ 
				// First, get a permit. 
				System.out.println(getName() + " is waiting for a permit.");
			
				// acquiring the lock 
				sem.acquire(); 
			
				System.out.println(getName() + " gets a permit.");
		
				// Now, accessing the shared resource. 
				// other waiting threads will wait, until this 
				// thread release the lock 
				for(int i=0; i < 5; i++) 
				{ 
					sharedObject.count++;
					System.out.println(getName() + ": " + sharedObject.count);
		
					// Now, allowing a context switch -- if possible. 
					// for thread B to execute 
					Thread.sleep(10); 
				} 
			} catch (InterruptedException exc) { 
					System.out.println(exc); 
				} 
		
				// Release the permit. 
				System.out.println(getName() + " releases the permit.");
				sem.release(); 
		} 
		
		// run by thread B 
		else
		{ 
			System.out.println("Starting " + getName());
			try
			{ 
				// First, get a permit. 
				System.out.println(getName() + " is waiting for a permit.");
			
				// acquiring the lock 
				sem.acquire(); 
			
				System.out.println(getName() + " gets a permit.");
		
				// Now, accessing the shared resource. 
				// other waiting threads will wait, until this 
				// thread release the lock 
				for(int i=0; i < 5; i++) 
				{ 
					sharedObject.count--;
					System.out.println(getName() + ": " + sharedObject.count);
		
					// Now, allowing a context switch -- if possible. 
					// for thread A to execute 
					Thread.sleep(10); 
				} 
			} catch (InterruptedException exc) { 
					System.out.println(exc); 
				} 
				// Release the permit. 
				System.out.println(getName() + " releases the permit.");
				sem.release(); 
		} 
	} 
} 

// Driver class 
public class SemaphoreDemo 
{ 
	public static void main(String args[]) throws InterruptedException 
	{ 
		// creating a Semaphore object 
		// with number of permits 1 
		Semaphore sem = new Semaphore(1);
		SharedObject sharedObject = new SharedObject();
		
		// creating two threads with name A and B 
		// Note that thread A will increment the count 
		// and thread B will decrement the count 
		WorkerThread mt1 = new WorkerThread(sem, sharedObject, "A");
		WorkerThread mt2 = new WorkerThread(sem, sharedObject, "B");
		
		// stating threads A and B 
		mt1.start(); 
		mt2.start(); 
		
		// waiting for threads A and B 
		mt1.join(); 
		mt2.join(); 
		
		// count will always remain 0 after 
		// both threads will complete their execution 
		System.out.println("count: " + sharedObject.count);
	} 
} 

