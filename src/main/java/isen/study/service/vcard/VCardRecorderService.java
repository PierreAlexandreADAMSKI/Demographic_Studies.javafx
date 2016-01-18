package isen.study.service.vcard;

import isen.study.data.Person;
import isen.study.service.database.DBService;
import isen.study.service.database.exceptions.DBServiceConnectionException;
import isen.study.service.util.FilesCrawler;
import isen.study.service.util.VCardReader;
import isen.study.service.util.exceptions.*;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class VCardRecorderService {

    public static void readAndSaveCards(String folderPath, DBService dbService) throws CountFilesException, GetFilesExceptions, GetAllLinesException, ParseDateOfBirthException, ReadException, DBServiceConnectionException {
        List<Path> vCards = FilesCrawler.getFiles(folderPath);
        for (Path path : vCards){
            final Person person = VCardReader.read(path);
	        dbService.save(person);
        }
    }
}
