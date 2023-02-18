package sharedobject;

// java program to demonstrate 
// use of semaphores Locks 

class WThread extends Thread
{ 
	SharedObject sharedObject;

	public WThread(SharedObject sharedObject, String threadName)
	{ 
		super(threadName); 
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
		
		}
		
		// run by thread B 
		else
		{ 
			System.out.println("Starting " + getName());
			try
			{ 

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
		}
	} 
} 

// Driver class 
public class SharedObjectDemo
{ 
	public static void main(String[] args) throws InterruptedException
	{ 

		SharedObject sharedObject = new SharedObject();
		
		// creating two threads with name A and B 
		// Note that thread A will increment the count 
		// and thread B will decrement the count 
		WThread mt1 = new WThread(sharedObject, "A");
		WThread mt2 = new WThread(sharedObject, "B");
		
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

