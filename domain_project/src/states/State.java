package states;

/**
 * @author Yannick Crabb�
 */
public interface State {
	
	public void delete(String code);
	public void add(String code, String quantity);
	public void cancel();
	public void pay(double amountPaid);
}
