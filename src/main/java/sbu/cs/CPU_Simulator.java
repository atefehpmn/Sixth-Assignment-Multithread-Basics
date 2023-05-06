package sbu.cs;

import java.util.*;

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime = processingTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++){
            for (int j = tasks.size() - 1; j > i; j--){
                if (tasks.get(j).processingTime < tasks.get(j - 1).processingTime){
                    Collections.swap(tasks, j, j - 1);
                }
            }
        }
        for (Task task : tasks) {
            task.run();
            executedTasks.add(task.ID);
        }
        return executedTasks;
    }

    public static void main(String[] args) {
    }
}
