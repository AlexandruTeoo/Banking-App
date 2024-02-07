package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.RelatieOneToOne;

import java.sql.*;

public class RelatieOneToOneDAO {
    public static RelatieOneToOne findByNumarCont(int numar_cont){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CLIENT_ID FROM RELATIEONETOONE WHERE NUMAR_CONT='"+ numar_cont +"'"))
        {
            RelatieOneToOne x;
            if (rs.next()) {
                x = new RelatieOneToOne();
                x.setClient_id(rs.getInt(1));
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
    public static void create(RelatieOneToOne x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into RELATIEONETOONE (client_id, numar_cont)" +
                        "VALUES(?,?)")) {
            pstmt.setInt(1, x.getClient_id());
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
                "DELETE FROM RELATIEONETOONE WHERE NUMAR_CONT='" + numar_cont + "'"))
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
