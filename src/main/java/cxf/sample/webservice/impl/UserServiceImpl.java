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
        System.out.println(jdbcTemplate);
        List<User> users;
        try {
            users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        } catch (EmptyResultDataAccessException e) {
            return Response.ok(new GenericEntity<List<User>>(new ArrayList<>()) {
            }).build();
        }
        return Response.ok(new GenericEntity<List<User>>(users) {
        }).build();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public Response get(Integer id) {
        final String sql = "SELECT * FROM `user` WHERE id=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return Response.ok(new User()).build();
        }
        return Response.ok(user).build();
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