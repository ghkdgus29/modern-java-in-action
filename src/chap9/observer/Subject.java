package chap9.observer;

public interface Subject {

    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
