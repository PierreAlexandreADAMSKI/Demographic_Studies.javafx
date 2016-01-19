package isen.study.service.vcard;

import com.sun.javafx.tk.Toolkit;
import isen.study.data.Person;
import isen.study.service.database.DBService;
import isen.study.service.database.exceptions.DBServiceConnectionException;
import isen.study.service.util.FilesCrawler;
import isen.study.service.util.VCardReader;
import isen.study.service.util.exceptions.*;
import javafx.concurrent.Task;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class VCardRecorderService {
    private static Integer progressId = 0;
    public static void readAndSaveCards(String folderPath, DBService dbService) throws CountFilesException, GetFilesExceptions, GetAllLinesException, ParseDateOfBirthException, ReadException, DBServiceConnectionException {
        List<Path> vCards = FilesCrawler.getFiles(folderPath);
        for (Path path : vCards){
            final Person person = VCardReader.read(path);
	        dbService.save(person);
            ++progressId;
        }
        return;
    }

    public static Float getProgress(){
        return (progressId / 3000.f);
    }
}
