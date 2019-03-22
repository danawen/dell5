package springapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import springapp.domain.Appointment;



@Repository
@Scope("singleton")
public class AppointmentDao {
	private Logger logger = LoggerFactory.getLogger(AppointmentDao.class);
	
	RowMapper<Appointment> simpleAppointmentMapper = new RowMapper<Appointment>() {

		@Override
		public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Appointment(rs.getInt("id"), rs.getString("title"),rs.getDate("date") , rs.getTime("time"), rs.getString("notes"),rs.getInt("pet_id"),rs.getInt("client_id"));
		}
	};
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	public List<Appointment> list(){
		List<Appointment> queryResult = jdbcTemplate.query(
				"SELECT id, title, date, time,notes,pet_id, client_id FROM appointments",
				simpleAppointmentMapper);
		
		
		return queryResult;
	}
	
	public List<Appointment> appointment(Date date){
		List<Appointment> queryResult = jdbcTemplate.query(
				"SELECT id, title, date, time,notes,pet_id, client_id FROM appointments WHERE date = ?",
				new Object[] {date},
				simpleAppointmentMapper);
		
		
		return queryResult;
	}
	
	public Appointment get(int id) {
		List<Appointment> queryResult = jdbcTemplate.query("SELECT id, title, date, time,notes,pet_id, client_id FROM appointments WHERE id = ? LIMIT 1", 
				new Object[] {id},
				simpleAppointmentMapper);
		
		if(queryResult.isEmpty()) {
			return null;
		}
		
		return queryResult.get(0);
		
		
	}
	
	public Appointment save(Appointment appointment) {
		Integer id = appointment.getId();
		if(id == null) {
			
			KeyHolder holder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement statement = con.prepareStatement("INSERT INTO appointments(title, date, time, notes, client_id, pet_id) VALUES (?, ?, ?, ?, ?, ?)");
					statement.setString(1, appointment.getTitle());
					statement.setDate(2, appointment.getDate());
					statement.setTime(3, appointment.getTime());
					statement.setString(4, appointment.getNotes());
					statement.setInt(5,appointment.getClientId());
					statement.setInt(6,appointment.getPetId());
					return statement;

				}
			}, holder);
			
			id = holder.getKey().intValue();
			
		} else {
			jdbcTemplate.update("UPDATE appointments SET title = ?, date = ? , time = ? , notes = ? , client_id = ? ,pet_id = ?  WHERE id = ?",
					new Object[] {appointment.getTitle(), appointment.getDate(), appointment.getTime(), appointment.getNotes(), appointment.getClientId() , appointment.getPetId() , id});
		}
		
		return get(id);
	}
	
	public void delete(int id) {
		
		jdbcTemplate.update("DELETE FROM appointments WHERE id = ?",
				new Object[] {id});
		
	}

}
