package isen.study.service.util;

import isen.study.service.util.exceptions.CountFilesException;
import isen.study.service.util.exceptions.GetFilesExceptions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class FilesCrawler {
	public static List<Path> getFiles(String sVCardsPath) throws GetFilesExceptions {
		List<Path> vCardPaths = new ArrayList<>();
		if (!Files.notExists(Paths.get(sVCardsPath))) {
			Path vCardsPath = Paths.get(sVCardsPath);
			try (DirectoryStream<Path> vCardsStream = Files.newDirectoryStream(vCardsPath)){
				for (Path vCardEntry : vCardsStream){
					vCardPaths.add(vCardEntry);
				}
			}catch(IOException e) {
				throw new GetFilesExceptions("No Paths to Directory : "+sVCardsPath, e);
			}
		}
		return vCardPaths;
	}
}
