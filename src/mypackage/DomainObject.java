/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;
public class DomainObject {
	protected String name = "no name";

	public DomainObject (String name)	{
		this.name = name;
	};

	public DomainObject () {}	
	
	public String name(){
		return name;
	};

	public String toString() {
		return name;
	};

}