package com.sugako.repository;

import com.sugako.configuration.DatabaseProperties;
import com.sugako.domain.Product;
import com.sun.jdi.connect.spi.Connection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.sugako.repository.columns.ProductColumns.*;

@Repository
@RequiredArgsConstructor

public class ProductRepositoryImpl implements ProductRepository {

    private final DatabaseProperties properties;


    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getPassword());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findOne(Object id) {
        return null;
    }

    @Override
    public List findAll() {

        final String findAllQuerry = "select * from product order bt id desc";

        List<Product> result = new ArrayList<>();

        registerDriver();

        try (Connection connection = getConnection();
             Statement statement = connection.createConnection;
             ResultSet rs = statement.executeQuery(findAllQuerry)
        ) {
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }

            return result;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Product parseResultSet(ResultSet rs) {
        Product product;

        product = new Product();
        try {
            product.setId(rs.getLong(ID));
            product.setSku(rs.getString(SKU));
            product.setDescription(rs.getString(DESCRIPTION));
            product.setCreated(rs.getTimestamp(CREATED));
            product.setChanged(rs.getTimestamp(CHANGET));
            product.setIsDeleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public void searchProduct() {

    }
}
