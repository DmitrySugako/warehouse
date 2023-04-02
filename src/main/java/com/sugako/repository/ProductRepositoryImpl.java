package com.sugako.repository;

import com.sugako.configuration.DatabaseProperties;
import com.sugako.domain.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sugako.repository.columns.ProductColumns.*;

@Repository

@RequiredArgsConstructor

public class ProductRepositoryImpl implements ProductRepository {

    private final DatabaseProperties properties;


    @Override
    public List<Product> findAll() {

        final String findAllQuery = "select * from product where is_deleted=false order by id";

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
    public Product findOne(Long id) {

        final String findOneQuery = "select * from product where id= ?";

        Product product = null;

        registerDriver();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                product = parseResultSet(rs);

            }
            return product;

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
            product.setSrp1(rs.getString(SRP1));
            product.setSrp2(rs.getString(SRP2));
            product.setSrp3(rs.getString(SRP3));
            product.setBarcode(rs.getLong(BARCODE));
            product.setWeight(rs.getDouble(WEIGHT));
            product.setCreated(rs.getTimestamp(CREATED));
            product.setChanged(rs.getTimestamp(CHANGED));
            product.setIsDeleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product create(Product object) {

        final String createQuery = "insert into product (sku, description, srp1, srp2, srp3, barcode, weight," +
                "created, changed) values (?,?,?,?,?,?,?,?,?)";

        registerDriver();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(createQuery, new String[]{"id", "created", "changed"})) {
            preparedStatement.setString(1, object.getSku());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setString(3, object.getSrp1());
            preparedStatement.setString(4, object.getSrp2());
            preparedStatement.setString(5, object.getSrp3());
            preparedStatement.setLong(6, object.getBarcode());
            preparedStatement.setDouble(7, object.getWeight());
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                object.setId(rs.getLong("id"));
                object.setCreated(rs.getTimestamp("created"));
                object.setChanged(rs.getTimestamp("changed"));
            }
            return object;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product object) {

        final String updateQuery = "update product set sku=?, description=?, srp1=?, srp2=?, srp3=?, barcode=?," +
                " weight=?, changed=? where id=?";

        registerDriver();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery, new String[]{"changed"})) {
            preparedStatement.setString(1, object.getSku());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setString(3, object.getSrp1());
            preparedStatement.setString(4, object.getSrp2());
            preparedStatement.setString(5, object.getSrp3());
            preparedStatement.setLong(6, object.getBarcode());
            preparedStatement.setDouble(7, object.getWeight());
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(9, object.getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                object.setChanged(rs.getTimestamp("changed"));
            }

            return object;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product delete(Long id) {

        final String updateQuery = "update product set is_deleted=true where id=?";

        registerDriver();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            return findOne(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void checkingAndHardDelete() {

        final String checkingQuery = "select * from product where is_deleted=true order by id";
        final int numberOfDaysOfStorage = 10;

        List<Product> result = new ArrayList<>();
        LocalDate date = LocalDate.now();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(checkingQuery)) {

            while (rs.next()) {
                if (ChronoUnit.DAYS.between(parseResultSet(rs).getChanged().toLocalDateTime().toLocalDate(), date) > numberOfDaysOfStorage) {
                    result.add(parseResultSet(rs));
                }
            }
            if (result.isEmpty()) {
                System.out.println("records older than " + numberOfDaysOfStorage + " days not found");
            } else {
                hardDelete(result);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private void hardDelete(List<Product> result) {

        final String deleteQuery = "delete from product where id in(?)";

        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            for (Product pr : result) {
                preparedStatement.setLong(1, pr.getId());
                System.out.println("entry with id=" + pr.getId() + " deleted");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

    }

    @Override
    public void secondNewMethod() {

    }
}
