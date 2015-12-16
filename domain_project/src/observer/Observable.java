package observer;

/**
 * @author Yannick Crabb�
 */
public interface Observable {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(String message);
}