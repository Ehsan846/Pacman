package dk.sdu.imada.oop26.Model;

public interface Subject {
	boolean registerObserver(final Observer observer);
    
	boolean deregisterObserver(final Observer observer);
    
	void notifyObservers(double dt);
}
