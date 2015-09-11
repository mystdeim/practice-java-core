package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutTest2 {

	public static void main(String[] args) {
		long ms = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Task> list = new ArrayList<>();
        for (int i=1; i<5; i++) list.add(new Task(i));
        
        
        for (Task task : list) {        	
        	Future<Void> f = executor.submit(task);
        	try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
        	
//        	Runnable r = () -> {
//            	Future<Void> f = executor.submit(task);
//            	try {
//    				f.get(1000, TimeUnit.MILLISECONDS);
//    			} catch (InterruptedException e) {
//    				e.printStackTrace();
//    			} catch (ExecutionException e) {
//    				e.printStackTrace();
//    			} catch (TimeoutException e) {
//					e.printStackTrace();
//				}
//        	};
//        	new Thread(r).start();
        	
        }

        
//        executor.submit(new TaskRunnable());
//        executor.submit(new TaskRunnable());

//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        	System.out.print(".");
//        }
        System.out.println();
        System.out.printf("isTerminated by %d!\n", System.currentTimeMillis() - ms);
	}
	
	static class Task implements Callable<Void> {
		public Task(int id) {
			this.id = id;
		}
	    @Override
	    public Void call() throws Exception {
	        System.out.printf("Callable %d started!\n",id);
	    	TimeUnit.MILLISECONDS.sleep(500);
	        System.out.printf("Callable %d finished!\n",id);
	        return null;
	    }
	    private int id;
	}
	
	static class TaskRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("TaskRunnable finished!");
		}		
	}

}
