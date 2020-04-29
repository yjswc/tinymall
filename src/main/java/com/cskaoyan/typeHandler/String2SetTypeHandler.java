package com.cskaoyan.typeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Li Qing
 * @Create: 2020/4/20 12:56
 * @Version: 1.0
 */
@MappedTypes(Set.class)
public class String2SetTypeHandler<T> implements TypeHandler<Set<T>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Set<T> strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index, s);
    }

    @Override
    public Set<T> getResult(ResultSet resultSet, String column) throws SQLException {
        String result = resultSet.getString(column);
        return transfer(result);
    }

    @Override
    public Set<T> getResult(ResultSet resultSet, int index) throws SQLException {
        String result = resultSet.getString(index);
        return transfer(result);
    }

    @Override
    public Set<T> getResult(CallableStatement callableStatement, int index) throws SQLException {
        String result = callableStatement.getString(index);
        return transfer(result);
    }

    private Set<T> transfer(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<T> strings = new HashSet<>();
        //增加判空，防止报错
        if (!StringUtils.isEmpty(result)) {
            try {
                strings = objectMapper.readValue(result, new TypeReference<Set<T>>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }
}
