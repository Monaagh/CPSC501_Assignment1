package mypackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MovieHandler {
	String movieName;
	int movieCode;
	String tapeSN;
	String command;
	
	
	public MovieHandler() {
		movieName = null;
		movieCode = 0;
		tapeSN = null;
	}
	
	public void addMovie(BufferedReader reader) throws IOException {
		movieName = getMovieName(reader);		
		movieCode = getMoviePriceCode(reader);		
		tapeSN = getMovieSerialNumber(reader);	
		
		//System.out.println(movieName + movieCode + tapeSN);
		
		Movie movie = new Movie(movieName, movieCode);
		Tape tape = new Tape(tapeSN, movie); 
		tape.persist();
		System.out.println("Tape added to the database");
		System.out.println("-----------------------------------------------------------------");
		//printInstruction(reader);
	}

	public void printInstruction(BufferedReader reader) throws IOException {
		System.out.println("Please press a key to continue.");
		command = reader.readLine();
		System.out.println("Please choose from the following commands by entering the number");
	}

	public Tape searchTape(String tapeSN, BufferedReader movieReader) throws IOException {
		Tape tape = null;
		Movie movie = null;
		String sn;
		
		String line;
		String[] data;
		while ((line = movieReader.readLine()) != null) {
			data = line.split(",");
			if (data[0].equals(tapeSN)) {
				movie = new Movie(data[1], Integer.parseInt(data[2]));
			    tape = new Tape(data[0], movie);
			}		
		}
		
		if (tape == null) {
			System.out.println("Opps! This tape is not in the database");
		}
			
		return tape;
	}

	public BufferedReader getMovieReader() throws FileNotFoundException {
		String fileName = "tape.txt";
		BufferedReader tapeReader = new BufferedReader(new FileReader(fileName));
		return tapeReader;
	}

	public String getMovieSerialNumber(BufferedReader reader) {
		String sn = null;
		try {
			while (true) {
				System.out.println("Enter tape's serial number:");
				sn = reader.readLine();
				if (!sn.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return sn;
	}


	public int getMoviePriceCode(BufferedReader reader) {
		int code = 0;
		try {
			while (true) {
				System.out.println("Enter movies's price code:");
				String temp = reader.readLine();
				
				if (!temp.isEmpty()) {
					code = Integer.parseInt(temp);
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return code;
	}


	public String getMovieName(BufferedReader reader) {
		String name = null;
		try {
			while (true) {
				System.out.println("Enter movie's name:");
				name = reader.readLine();
				if (!name.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
				System.out.println("Exception:" + e);
		}
		
		return name;
	}

}
