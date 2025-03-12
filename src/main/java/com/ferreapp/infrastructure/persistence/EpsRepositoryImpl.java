package com.ferreapp.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ferreapp.domain.entities.Eps;
import com.ferreapp.domain.repositories.EpsRepository;
import com.ferreapp.infrastructure.database.ConnectionDb;

public class EpsRepositoryImpl implements EpsRepository {
    private final ConnectionDb connectionDb;

    public EpsRepositoryImpl(ConnectionDb connectionDb) {
        this.connectionDb = connectionDb;
    }
    @Override
    public void save(Eps eps) {
        String sqlInsert = "INSERT INTO eps (name) VALUES (?)";
        try(Connection conn = connectionDb.getConexion();
            PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
            ps.setString(1, eps.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Eps findById(int id) {
        String sqlSelect = "SELECT id,name FROM eps WHERE id = ?";
        try(Connection conn = connectionDb.getConexion();
            PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Eps(id, rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Eps> findAll() {
        List<Eps> epsList = new ArrayList<>();
        String sqlSelect = "SELECT id, name FROM eps";
        try(Connection conn = connectionDb.getConexion();
            PreparedStatement ps = conn.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                epsList.add(new Eps(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return epsList;
    }

    @Override
    public void update(Eps eps) {
        String sqlUpdate = "UPDATE eps SET name = ? WHERE id = ?";
        try(Connection conn = connectionDb.getConexion();
            PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
            ps.setString(1, eps.getName());
            ps.setInt(2, eps.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM eps WHERE id = ?";
        try(Connection conn = connectionDb.getConexion();
            PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
