/**
 * CPSC 501 Assignment1: Refactoring
 * @author: Mona Agh
 */
package mypackage;

public class Tape extends DomainObject
{
	private String _serialNumber;
	private Movie _movie;

	public Tape(String serialNumber, Movie movie) {
		_serialNumber = serialNumber;
		_movie = movie;
	}
	
	public Movie movie() {
		return _movie;
	}
	
	public String getSerial() {
		return _serialNumber;
	}
	
	public void persist() {
		Registrar.add ("Movies", this);
	}
}