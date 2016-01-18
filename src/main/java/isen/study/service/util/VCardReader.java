package isen.study.service.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import isen.study.data.Person;
import isen.study.data.Sex;
import isen.study.service.util.exceptions.GetAllLinesException;
import isen.study.service.util.exceptions.ParseDateOfBirthException;
import isen.study.service.util.exceptions.ReadException;

public class VCardReader {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static Person read(Path path) throws ReadException, GetAllLinesException, ParseDateOfBirthException {
		Person person = new Person();

            List<String> lines = getAllLines(path);
            for (String line : lines) {
                person = parseN(person, line);
                person = parseEmail(person, line);
                person = parseAddress(person, line);
                person = parseDateOfBirth(person, line);
                person = parseBloodType(person, line);
            }

        return person;

	}

	private static List<String> getAllLines(Path path) throws GetAllLinesException {
        List<String> allLines;
		try{
			allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

		} catch (IOException e) {
			throw new GetAllLinesException("Files cannot read"+path.toString(),e);
		}
		return allLines;
	}

	private static Person parseN(Person person, String line) {
        if (line.startsWith("N:")){

            String[] element = line.split(";");
            String lastName = element[0].substring(2,element[0].length());
            person.setLastName(lastName);
            person.setFirstName(element[1]);

	        //System.out.println("Last name : " + person.getLastName()+ " FirstName : "+person.getFirstName());
	        if (line.endsWith("Mr")){
		        person.setSex(Sex.MALE);
	        } else {
		        person.setSex(Sex.FEMALE);

            }
        }
		// TODO if line begins with "N:", then split the line with ";" separator
		// TODO the first element is the lastName, the second is the first name
		// TODO if the fourth element is equal to "Mr", set the sex to Sex.MALE,
		// TODO return the person you got as parameter, with the new values
		return person;
	}

	private static Person parseEmail(Person person, String line) {
		if (line.startsWith("EMAIL:")){
            String email = line.substring(6,line.length());
			person.setEmail(email);
            //System.out.println("Email " + person.getEmail());
		}
		// TODO if line begins with "EMAIL:", apply the correct substring to
		// retrieve the email
		// TODO return the person you got as parameter, with the new values
		// inside
		return person;
	}

	private static Person parseAddress(Person person, String line) {

        if (line.startsWith("ADR:;;")) {

                String[] element = line.split(";");

                person.setStreetName(element[2]);
                person.setCity(element[3]);
                person.setState(element[4]);

                //System.out.println("Street Name : " + person.getStreetName()+ "\nCity : "+person.getCity()+ "\nState : " + person.getState());

            }
		// TODO if line begins with "ADR:", then split the line with ";"
		// separator
		// TODO the third element is the streetName
		// TODO the fourth element is the city
		// TODO the fifth element is the state
		// TODO return the person you got as parameter, with the new values
		// inside
		return person;
	}

	private static Person parseDateOfBirth(Person person, String line) throws ParseDateOfBirthException {

        if (line.startsWith("BDAY:")){
            String date =  line.substring(5);
            LocalDate dateOfBirth = LocalDate.parse(date,formatter);
            person.setDateOfBirth(dateOfBirth);

            //System.out.println("Date Of Birth : "+dateOfBirth.toString());
        }
        // TODO if line begins with "BDAY:", parse the date thanks to
		// LocalDate.parse() and the given formatter
		// TODO return the person you got as parameter, with the new values
		// inside
		return person;
	}

	private static Person parseBloodType(Person person, String line) {

        if (line.startsWith("CATEGORIES:")){
            String bloodType = line.substring(11,line.length());
            person.setBloodType(bloodType);
            //System.out.println("Blood Type : "+bloodType);
        }
        // TODO if line begins with "CATEGORIES:", apply the correct substring
		// to retrieve the email
		// TODO return the person you got as parameter, with the new values
		// inside
		return person;
	}

}
