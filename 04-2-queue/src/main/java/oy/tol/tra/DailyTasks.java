package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;
   private static final int MAX_DAILY_TASKS = 100;

   private DailyTasks() {
   }

   /** 
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {

      try {
         
         this.dailyTaskQueue = new QueueImplementation<>();
         readTasks();
         timer = new Timer();

         timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if(dailyTaskQueue.size() > 0){
                  System.out.println("Time to: " + dailyTaskQueue.dequeue());
               }else{
                  timer.cancel();
                  System.out.println("Tasks done for today, see you tommorrow.");
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);

      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {

      int counter = 0;
      String tasks;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");

      for (String task : allTasks) {
         dailyTaskQueue.enqueue(task);
         counter++;
         if(counter >= MAX_DAILY_TASKS){
            break;
         }
      }
      System.out.println("Tasks in queue: " + counter);
   }
}
