/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;
public class Rental extends DomainObject
{
	private int daysRented;
	private Tape tape;
	private Customer customer;
	
	public int daysRented() {
		return daysRented;
	}
	public Tape tape() {
		return tape;
	}
	
	public Customer customer() {
		return customer;
	}
	
	public Rental(Tape tape, Customer customer, int daysRented) {
		this.tape = tape;
		this.daysRented = daysRented;
		this.customer = customer;
	}
	
	public void persist() {
		Registrar registrar = new Registrar();
    	registrar.add("Rental", this);
    }
}