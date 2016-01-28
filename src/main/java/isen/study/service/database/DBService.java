package isen.study.service.database;

import java.sql.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import isen.study.data.Person;
import isen.study.data.stat.interfaces.Stat;
import isen.study.service.database.exceptions.DBServiceConnectionException;

import java.sql.*;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class DBService {
	/**
	 * Thanks to the query, we insert the data of each vCard
	 */
	private static final String SAVE_QUERY =
			"INSERT INTO `isen_project`.`person` " +
					"(`lastname`, `firstname`, `sex`, `streetname`, `state`, `city`, `bloodtype`, `dateofbirth`) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM `isen_project`.`person` ";
	private static final String DELETE_DUPLICATE = "ALTER IGNORE TABLE person ADD UNIQUE INDEX(lastname,firstname,sex,streetname,state,city,bloodtype,dateofbirth)";

	private MysqlDataSource dataSource;

	public MysqlDataSource getDataSource() {
		return dataSource;
	}

	public DBService(MysqlDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Person person) throws DBServiceConnectionException {
		/**
		 * We prepare & execute the query, then we add for each column the data needed
		 */
		try(Connection connection = this.dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)){

			statement.setString(1,person.getLastName());
			statement.setString(2,person.getFirstName());
			statement.setString(3,person.getSex().toString());
			statement.setString(4,person.getStreetName());
			statement.setString(5,person.getState());
			statement.setString(6,person.getCity());
			statement.setString(7,person.getBloodType());
			statement.setDate(8,Date.valueOf(person.getDateOfBirth()));
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DBServiceConnectionException("DataBase Connection Failed for statement : \n" + SAVE_QUERY, e);
		}
	}

	public void executeStat(Stat stat) throws DBServiceConnectionException {
		try (Connection connection = this.dataSource.getConnection();
		     Statement statement = connection.createStatement()){
			final String query = stat.getQuery();
			ResultSet resultSet = statement.executeQuery(query);
			stat.handle(resultSet);
		} catch (SQLException e) {
			throw new DBServiceConnectionException("DataBase Connection Failed for statement : \n" + stat.getQuery(), e);
		}
	}

	public void clear() throws DBServiceConnectionException {
		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)){
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DBServiceConnectionException("DataBase Connection Failed for statement : \n" + DELETE_QUERY, e);
		}
	}

	public void deleteDublon() throws DBServiceConnectionException {
		try(Connection connection = this.dataSource.getConnection();
		    PreparedStatement statement = connection.prepareStatement(DELETE_DUPLICATE)){
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DBServiceConnectionException("DataBase Connection Failed for statement : \n" + DELETE_DUPLICATE, e);
		}
	}
}
