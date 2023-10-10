package com.pm05.product_web_app.models.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pm05.product_web_app.models.Product;

public class DBCrud {
    public static List<Product> getAllProduct(Connection conn) {
        List<Product> productList = null;
        String sql = "SELECT * FROM product";
        PreparedStatement ps = null;
        ResultSet rs = null;
        productList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Product product = new Product(code, name, price);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return productList;
    }

    public static void insertProduct(Connection conn, Product product) {
        String sqlString = "insert into product (CODE, NAME, PRICE)  values (?, ?, ?)";

        try {
            PreparedStatement pstm = conn.prepareStatement(sqlString);
            pstm.setString(1, product.getCode());
            pstm.setString(2, product.getName());
            pstm.setDouble(3, product.getPrice());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Product findProductByCode(Connection conn, String code) {
        Product product = null;
        String sql = "SELECT code, name, price FROM product where code = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                product = new Product(code, name, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public static void updateProduct(Connection conn, Product product) {
        String sql = "update product set name = ?, price = ?      where code = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Product removeProductByCode(Connection conn, String code) {
        Product product = null;
        String sql = "Delete FROM product where code = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
