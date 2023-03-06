package com.sugako.repository;

import com.sugako.configuration.DatabaseProperties;
import com.sugako.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sugako.repository.columns.ProductColumns.*;

@Repository
//@RequiredArgsConstructor

public class ProductRepositoryImpl implements ProductRepository {

    private final DatabaseProperties properties;

    public ProductRepositoryImpl(DatabaseProperties properties) {
        this.properties = properties;
    }

    @Override
    public List findAll() {

        final String findAllQuery = "select * from product order by id";

        List<Product> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object findOne(Object id) {

        Long searchId = (Long) id;

        String findOneQuery = "select * from product where id= ?";

        registerDriver();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery);
            preparedStatement.setLong(1, searchId);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    parseResultSet(rs);

                }
            }
            return parseResultSet(rs);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
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
            product.setChanged(rs.getTimestamp(CHANGED));
            product.setIsDeleted(rs.getBoolean(DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Object create(Object object) {

        Product product = (Product) object;

        registerDriver();

        final String createQuery = "insert into product (sku, description, created, changed) values (?,?,?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(createQuery, new String[]{"id", "created", "changed"});
            preparedStatement.setString(1, product.getSku());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getLong("id"));
                product.setCreated(rs.getTimestamp("created"));
                product.setChanged(rs.getTimestamp("changed"));
            }
            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object update(Object object) {

        Product product = (Product) object;

        final String updateQuery = "update product set sku=?, description=?, changed=? where id=?";

        registerDriver();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery, new String[] {"changed"});
            preparedStatement.setString(1, product.getSku());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(4, product.getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                product.setChanged(rs.getTimestamp("changed"));
            }

            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Object id) {
        Long searchId = (Long) id;

        final String updateQuery = "update product set is_deleted=true where id=?";

        registerDriver();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setLong(1, searchId);
            ResultSet rs = preparedStatement.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Object> searchForModifiedProducts() {
        return null;
    }

    @Override
    public void secondNewMethod() {

    }
}
