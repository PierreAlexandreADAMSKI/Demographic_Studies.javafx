package isen.study.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class BundleUtil {
	public static Properties loadProperties(String filePrefix) {
		System.out.println("Loading " + filePrefix + " properties...");
		Properties properties = new Properties();
		InputStream fileStream = BundleUtil.class.getClassLoader().getResourceAsStream(filePrefix+".properties");
		try {
			properties.load(fileStream);
		} catch (IOException e) {
			System.err.println("Erreur lors du chargement du fichier de propriétés");
		}
		return properties;
	}
}
