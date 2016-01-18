package isen.study.service.stat;

import isen.study.data.stat.interfaces.Stat;
import isen.study.service.database.DBService;
import isen.study.service.database.exceptions.DBServiceConnectionException;

import java.util.List;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class StatService {
	private DBService dbService;

	public StatService(DBService dbService) {
		this.dbService = dbService;
	}

	public void printStats(List<Stat> stats) throws DBServiceConnectionException {
		for(Stat stat : stats){
			this.dbService.executeStat(stat);
			String description = stat.getDescription();
			System.out.println("\n\n====="+description+"\n=====");
		}
	}
}
