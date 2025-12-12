package com.pot.weather.app.repository;

import com.pot.weather.app.entity.UserLocationData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class LocationRepository implements RowMapper<UserLocationData> {

    private static final String SAVE_USER_LOCATION_SQL = "insert into locations(name, user_id, latitude, longitude) values (:name, :userId, :longitude, :latitude)";
    private static final String FIND_LOCATION_BY_USER_ID_SQL = "select * from locations where user_id = :id";
    private static final String DELETE_LOCATION_BY_ID_SQL = "delete from locations where id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LocationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(UserLocationData location) {
        jdbcTemplate.update(SAVE_USER_LOCATION_SQL, Map.of("name", location.getName(),
                                                       "userId", location.getUserId(),
                                                       "longitude", location.getLongitude(),
                                                       "latitude", location.getLatitude()));
    }

    public List<UserLocationData> findByUserId(int id) {
        return jdbcTemplate.query(FIND_LOCATION_BY_USER_ID_SQL, Map.of("id", id), this);
    }

    public void deleteUserLocation(int userLocId) {
        jdbcTemplate.update(DELETE_LOCATION_BY_ID_SQL, Map.of("id", userLocId));
    }

    public UserLocationData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserLocationData(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("user_id"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")
        );
    }
}
