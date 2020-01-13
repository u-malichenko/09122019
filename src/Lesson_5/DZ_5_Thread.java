package Lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DZ_5_Thread {
    public static void main(String[] args) {
        WorkInArray myArray = new WorkInArray(30000, 10000000);
        myArray.method1();
        myArray.method2();
        WorkInArray myArrayNew = new WorkInArray(3000, 10000000);
        myArrayNew.method2();
    }
}

class WorkInArray {
    private int size;
    private int numOfThreads;
    private int h;
    float[] arr;
    boolean odd = false;

    public WorkInArray(int numOfThreads, int size) {
        this.numOfThreads = numOfThreads;
        this.size = size;
        this.h = size / numOfThreads;
        arr = new float[size];
        if (size % numOfThreads != 0) {
            odd = true;
        }
    }

    public void method1() {
        fillArray(arr);
        System.out.println("Запуск вычисления в одном потоке, ждите...");
        long a = System.currentTimeMillis();
        countNewValueInArray(arr,0);
        System.out.println(System.currentTimeMillis() - a + " мс. - одном потоке");
    }

    public void method2() {
        fillArray(arr);
        System.out.println("Запуск вычисления мультипоточно, ждите...");
        long a = System.currentTimeMillis();

        float[] rangeArray;
        List<Thread> arrayOfThread = new ArrayList<>(numOfThreads);
        List<float[]> arrayOfArrays = new ArrayList<>(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {

            if (odd && i == (numOfThreads - 1)) { //на последнем шаге если число потоков нечетно, добавим 1 в конец массива, так как нечетные делятся косо
                rangeArray = Arrays.copyOfRange(arr, i * h, (i * h) + h + 1);
            } else{
                rangeArray = Arrays.copyOfRange(arr, i * h, (i * h) + h);
            }
            arrayOfArrays.add(i, rangeArray);

            int finalI = i;
            arrayOfThread.add(i, new Thread(new Runnable() {
                @Override
                public void run() {
                    countNewValueInArray(arrayOfArrays.get(finalI),finalI*h);
                }
            }));
            arrayOfThread.get(finalI).start();
        }
        try {
            for (int i = 0; i < numOfThreads; i++) {
                arrayOfThread.get(i).join();
                System.arraycopy(arrayOfArrays.get(i), 0, arr, i * h, h);
            }
            System.out.println(System.currentTimeMillis() - a + " мс.- мультипоточно("+numOfThreads+" потоков)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void countNewValueInArray(float[] arr,int x) {
        for (int i = 0; i < arr.length; i++, x++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + x / 5) * Math.cos(0.2f + x / 5) * Math.cos(0.4f + x / 2));
        }
    }

    public void fillArray(float[] arr) {
        Arrays.fill(arr, 1);
    }
}
