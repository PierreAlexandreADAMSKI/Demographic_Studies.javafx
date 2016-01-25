package isen.study.service.vcard;

import isen.study.app.view.listeners.ReaderChangeListener;
import isen.study.data.Person;
import isen.study.service.database.DBService;
import isen.study.service.util.FilesCrawler;
import isen.study.service.util.VCardReader;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class VCardRecorderService extends Task<Void> {
    private String folderPath;
    private DBService dbService;

    public VCardRecorderService(DBService dbService, String folderPath) {
        this.dbService = dbService;
        this.folderPath = folderPath;
    }

    public void readAndSaveCards() throws Exception {
        Integer progressId = 0;
        List<Path> vCards = FilesCrawler.getFiles(folderPath);
        for (Path path : vCards){
            final Person person = VCardReader.read(path);
	        dbService.save(person);
            updateProgress((double)++progressId, (double)3000);
            updateMessage(person.toString());
        }
	    Thread.sleep(1000);
    }

    @Override
    protected Void call() throws Exception {
        readAndSaveCards();
        return null;
    }
}
