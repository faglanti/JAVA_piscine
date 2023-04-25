package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }


    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM product");

        while (result.next()) {
            productList.add(new Product(result.getLong(1), result.getString(2), result.getInt(3)));
        }
        result.close();
        statement.close();
        connection.close();
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM product WHERE id = " + id);
        if (!result.next())
            throw new RuntimeException(("object with id " + id + " not found"));
        Product product = new Product(result.getLong(1), result.getString(2), result.getInt(3));
        result.close();
        statement.close();
        connection.close();

        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product SET name = ?, price = ? " +
                        "WHERE id = ?"
        );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(id, name, price) VALUES (?, ?, ?)");
        preparedStatement.setLong(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM product WHERE id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }
}
