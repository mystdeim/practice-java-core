package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutTest {

	public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future_first = executor.submit(new Task("first"));
        Future<String> future_second = executor.submit(new Task("second"));

        try {
        	
            System.out.println("Started..");
            System.out.println(future_first.get(3, TimeUnit.SECONDS));
            System.out.println("Finished!");
            
        } catch (TimeoutException e) {
            future_first.cancel(true);
            System.out.println("Terminated!");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException!");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException!");
        }
        
        
        try {
        	
            System.out.println("Started..");
            System.out.println(future_second.get(5, TimeUnit.SECONDS));
            System.out.println("Finished!");
            
        } catch (TimeoutException e) {
            future_first.cancel(true);
            System.out.println("Terminated!");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException!");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException!");
        }

        executor.shutdownNow();
	}
	
	static class Task implements Callable<String> {
		public Task(String str) {
			this.str = str;
		}
	    @Override
	    public String call() throws Exception {
	        Thread.sleep(4000); // Just to demo a long running task of 4 seconds.
	        return str;
	    }
	    private String str;
	}

}
