package model.counter;

public class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;       
    }

    public void setCounter(int size) {
        if (size > counter) {
            this.counter = size;
        }
    }

    public void addCounter() {
        counter++;
    }

    public void reduceCounter() {
        counter--;
    }

}