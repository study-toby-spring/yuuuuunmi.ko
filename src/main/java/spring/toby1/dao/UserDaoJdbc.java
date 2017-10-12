package spring.toby1.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.toby1.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


/**
 * Created by yuuuunmi on 2017. 7. 5..
 */
public class UserDaoJdbc implements UserDao{

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper =
            new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            };

    public void add(User user){

        this.jdbcTemplate.update("insert into users(id, name, password) values (?,?,?)", user.getId(), user.getName(), user.getPassword());


    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ? ",
                new Object[]{id}, userRowMapper);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }


    public int getCount(){
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id", userRowMapper);
    }

}
