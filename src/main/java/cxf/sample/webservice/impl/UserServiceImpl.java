package cxf.sample.webservice.impl;

import cxf.sample.bean.User;
import cxf.sample.webservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public Response all() {
        final String sql = "SELECT * FROM `user` ORDER BY createdAt DESC";
        try {
            List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
            return Response.ok(new GenericEntity<List<User>>(users) {
            }).build();
        } catch (EmptyResultDataAccessException e) {
            return Response.ok(new GenericEntity<List<User>>(new ArrayList<>()) {
            }).build();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public Response get(Integer id) {
        final String sql = "SELECT * FROM `user` WHERE id=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), id);
            return Response.ok(user).build();
        } catch (EmptyResultDataAccessException e) {
            return Response.ok(new User()).build();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response post(User user) {
        final String sql = "INSERT INTO `user` VALUES(NULL,?,?,?,NOW())";
        int row = jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getAddress());
        if (row > 0) {
            return Response.ok("Success").build();
        } else {
            return Response.ok("Fail").build();
        }
    }

    @Override
    public Response hello() {
        return Response.ok("hello").build();
    }
}