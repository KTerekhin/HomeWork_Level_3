package Lesson_4.Task1;

public class Task1 {
    private Object lock = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        runProgram();
    }

    private static void runProgram() {
        Task1 task1 = new Task1();
        new Thread(() -> task1.print('A', 'B')).start();
        new Thread(() -> task1.print('B', 'C')).start();
        new Thread(() -> task1.print('C', 'A')).start();
    }

    public void print(char a, char b) {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != a) {
                        lock.wait();
                    }
                    System.out.print(a);
//                  для наглядности тормозим на 1 секунду
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    currentLetter = b;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
