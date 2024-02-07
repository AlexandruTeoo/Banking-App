package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.Client;

import java.sql.*;

public class ClientDAO {
    public static Client findByID(int id){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select NUME, PRENUME, EMAIL, NUMAR_TELEFON, ADRESA, DATA_NASTERII from CLIENT where CLIENT_ID ='"+ id +"'"))
        {
            Client x;
            if (rs.next()) {
                x = new Client();
                x.setNume(rs.getString(1));
                x.setPrenume(rs.getString(2));
                x.setEmail(rs.getString(3));
                x.setNumar_telefon(rs.getString(4));
                x.setAdresa(rs.getString(5));
                x.setData_nasterii(rs.getDate(6));
                x.setClient_id(id);
            }
            else { x = null; }
            return x;
        }
        catch (SQLException e) { e.printStackTrace(); }
        finally { Database.closeConnection(); }
        return null;
    }

    public static int lastId(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(CLIENT_ID) FROM CLIENT"))
        {
            int x;
            if (rs.next()) {
                x=rs.getInt(1);
            }
            else
            {
                x=0;
            }
            return x;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            Database.closeConnection();
        }
        return 0;
    }
    public static boolean VerifyNumarCont(String username){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NUMAR_CONT FROM CONT WHERE NUMAR_CONT= '"+ username +"'"))
        {
            if (rs.next()) {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            Database.closeConnection();
        }
        return true;
    }

    //Setter~Create
    public static int create(Client x)
    {
        int id=0;
        id=lastId()+1;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into CLIENT (client_id, nume, prenume, email, numar_telefon, adresa, data_nasterii)" +
                        "VALUES(?,?,?,?,?,?,?)")) {
            pstmt.setInt(1, id);
            pstmt.setString(2, x.getNume());
            pstmt.setString(3, x.getPrenume());
            pstmt.setString(4, x.getEmail());
            pstmt.setString(5, x.getNumar_telefon());
            pstmt.setString(6, x.getAdresa());
            pstmt.setDate(7, x.getData_nasterii());
            pstmt.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
        return id;
    }
    //Delete
    public static void delete(int client_id)
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM CLIENT WHERE CLIENT_ID='" + client_id + "'"))
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
    //Update
    public static void update(int client_id, Client x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE CLIENT SET NUME=?, PRENUME=?, EMAIL=?, " +
                        "NUMAR_TELEFON=?, ADRESA=?, DATA_NASTERII=? WHERE CLIENT_ID='" + client_id + "'")) {
            pstmt.setString(1, x.getNume());
            pstmt.setString(2, x.getPrenume());
            pstmt.setString(3, x.getEmail());
            pstmt.setString(4, x.getNumar_telefon());
            pstmt.setString(5,x.getAdresa());
            pstmt.setDate(6, x.getData_nasterii());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
}
