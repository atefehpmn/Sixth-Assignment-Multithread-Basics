package sbu.cs;

public class UseInterrupts
{

    public static class SleepThread extends Thread {
        int sleepCounter;

        public SleepThread(int sleepCounter) {
            super();
            this.sleepCounter = sleepCounter;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            while (this.sleepCounter > 0)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(this.getName() + " has been interrupted.");
                    return;
                }
                finally {
                    this.sleepCounter--;
                    System.out.println("Number of sleeps remaining: " + this.sleepCounter);
                }
            }

        }
    }


    public static class LoopThread extends Thread {
        int value;
        public LoopThread(int value) {
            super();
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " is Active.");

            for (int i = 0; i < 10; i += 3)
            {
                i -= this.value;
                if (this.isInterrupted()){
                    System.out.println(this.getName() + " has been interrupted.");
                    return;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SleepThread sleepThread = new SleepThread(5);
        sleepThread.start();
        Thread.sleep(3000);
        if (sleepThread.isAlive()){
            sleepThread.interrupt();
        }
        LoopThread loopThread = new LoopThread(3);
        loopThread.start();
        Thread.sleep(3000);
        if (loopThread.isAlive()){
            loopThread.interrupt();
        }
    }
}
