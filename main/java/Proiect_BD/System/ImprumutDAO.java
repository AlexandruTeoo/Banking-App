package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.Imprumut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImprumutDAO {
    public static Imprumut[] findAll(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT IMPRUMUT_ID, SUMA_IMPRUMUT, DOBANDA, NUMAR_CONT, DATA_IMPRUMUT FROM IMPRUMUT "))
        {
            List<Imprumut> result = new ArrayList<Imprumut>();
            while (rs.next()) {
                Imprumut x;
                x = new Imprumut();
                x.setImprumut_id(rs.getInt(1));
                x.setSuma_imprumutata(rs.getInt(2));
                x.setDobanda(rs.getInt(3));
                x.setNumar_cont(rs.getInt(4));
                x.setData_imprumut(rs.getDate(5));
                result.add(x);
            }
            Imprumut[] array = new Imprumut[result.size()];
            int index=-1;
            for(Imprumut a : result)
            {
                index++;
                array[index]=a;
            }
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return null;
    }

    public static Imprumut[] findByNumarCont(int numar_cont){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  SUMA_IMPRUMUT, DOBANDA, IMPRUMUT_ID, DATA_IMPRUMUT FROM IMPRUMUT WHERE NUMAR_CONT= '"+numar_cont+"'"))
        {
            List<Imprumut> loans = new ArrayList<Imprumut>();
            while (rs.next()) {
                Imprumut x;
                x = new Imprumut();
                x.setSuma_imprumutata(rs.getInt(1));
                x.setDobanda(rs.getInt(2));
                x.setNumar_cont(numar_cont);
                x.setImprumut_id(rs.getInt(3));
                x.setData_imprumut(rs.getDate(4));
                loans.add(x);
            }
            Imprumut[] array = new Imprumut[loans.size()];
            int index = -1;
            for (Imprumut x: loans)
            {
                index++;
                array[index] = x;
            }
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return null;
    }

    public static int lastId(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(IMPRUMUT_ID) FROM IMPRUMUT"))
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

    //Setter~Create
    public static void create(Imprumut x) {
        int ID = ImprumutDAO.lastId() + 1;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into Imprumut (imprumut_id, suma_imprumut, dobanda, numar_cont, data_imprumut)" +
                        "VALUES(?,?,?,?,?)")) {
            pstmt.setInt(1, ID);
            pstmt.setInt(2, x.getSuma_imprumutata());
            pstmt.setInt(3, x.getDobanda());
            pstmt.setInt(4, x.getNumar_cont());
            pstmt.setDate(5, x.getData_imprumut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
    //Delete
    public static void delete(String idImprumut)
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM TRANZACTIE WHERE TRANZACTIE_ID='" + idImprumut + "'"))
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
    public static void update(String idImprumut, Imprumut x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE TRANZACTIE SET IMPRUMUT_ID = ?, SUMA_IMPRUMUT=?, " +
                        "DOBANDA=?, NUMAR_CONT=?, DATA_IMPRUMUT WHERE IMPRUMUT_ID='" + idImprumut + "'")) {
            pstmt.setInt(1, x.getImprumut_id());
            pstmt.setInt(2, x.getSuma_imprumutata());
            pstmt.setInt(3, x.getDobanda());
            pstmt.setInt(4, x.getNumar_cont());
            pstmt.setDate(5, x.getData_imprumut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
}
