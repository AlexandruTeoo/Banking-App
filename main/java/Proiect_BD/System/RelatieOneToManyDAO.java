package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.RelatieOneToMany;

import java.sql.*;

public class RelatieOneToManyDAO {
    public static RelatieOneToMany findByTranzactieID(int numar_cont){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TRANZACTIE_ID FROM RELATIEONETOMANY WHERE NUMAR_CONT='"+ numar_cont +"'"))
        {
            RelatieOneToMany x;
            if (rs.next()) {
                x = new RelatieOneToMany();
                x.setTranzactie_id(rs.getInt(1));
                x.setNumar_cont(numar_cont);
            }
            else
            {
                x=null;
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return null;
    }
    //Setter~Create
    public static void create(RelatieOneToMany x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into RELATIEONETOMANY (tranzactie_id, numar_cont)" +
                        "VALUES(?,?)")) {
            pstmt.setInt(1, x.getTranzactie_id());
            pstmt.setInt(2, x.getNumar_cont());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
    //Delete
    public static void delete(int numar_cont)
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM RELATIEONETOMANY WHERE NUMAR_CONT='" + numar_cont + "'"))
        {
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
    }
}
