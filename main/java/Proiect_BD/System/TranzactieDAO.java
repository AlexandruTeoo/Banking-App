package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.Tranzactie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TranzactieDAO {
    public static Tranzactie[] findAll(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TRANZACTIE_ID, TIP_TRANZACTIE, SUMA, DATA_TRANZACTIE FROM TRANZACTIE "))
        {
            List<Tranzactie> result = new ArrayList<Tranzactie>();
            while (rs.next()) {
                Tranzactie x;
                x = new Tranzactie();
                x.setTranzactie_id(rs.getInt(1));
                x.setTip_tranzactie(rs.getString(2));
                x.setSuma(rs.getInt(3));
                x.setData_tranzactie(rs.getDate(4));
                result.add(x);
            }
            Tranzactie[] array = new Tranzactie[result.size()];
            int index=-1;
            for(Tranzactie a : result)
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

    public static Tranzactie[] findAllByCont(int nc){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT t.tranzactie_id, t.tip_tranzactie, t.suma, t.data_tranzactie FROM cont c join relatieonetomany r on c.numar_cont = r.numar_cont join tranzactie t on r.tranzactie_id = t.tranzactie_id where c.numar_cont = '"+nc+"'"))
        {
            List<Tranzactie> result = new ArrayList<Tranzactie>();
            while (rs.next()) {
                Tranzactie x;
                x = new Tranzactie();
                x.setTranzactie_id(rs.getInt(1));
                x.setTip_tranzactie(rs.getString(2));
                x.setSuma(rs.getInt(3));
                x.setData_tranzactie(rs.getDate(4));
                result.add(x);
            }
            Tranzactie[] array = new Tranzactie[result.size()];
            int index=-1;
            for(Tranzactie a : result)
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

    public static Tranzactie findByTranzactieID(int idTranzactie){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TIP_TRANZACTIE, SUMA, DATA_TRANZACTIE FROM TRANZACTIE WHERE TRANZACTIE_ID= '"+idTranzactie+"'"))
        {
            Tranzactie x;
            if (rs.next()) {
                x = new Tranzactie();
                x.setTip_tranzactie(rs.getString(1));
                x.setSuma(rs.getInt(2));
                x.setData_tranzactie(rs.getDate(3));
                x.setTranzactie_id(idTranzactie);
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
    public static void create(Tranzactie x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into Tranzactie (tranzactie_id, tip_tranzactie, suma, numar_cont, data_tranzactie)" +
                        "VALUES(?,?,?,?,?)")) {
            pstmt.setInt(1, x.getTranzactie_id());
            pstmt.setString(2, x.getTip_tranzactie());
            pstmt.setInt(3, x.getSuma());
            pstmt.setDate(4, x.getData_tranzactie());
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
                "DELETE FROM tranzactie WHERE tranzactie_id in (SELECT t.tranzactie_id " +
                        "FROM cont c " +
                        "join relatieonetomany r on c.numar_cont = r.numar_cont " +
                        "join tranzactie t on r.tranzactie_id = t.tranzactie_id " +
                        "where c.numar_cont = '" + numar_cont + "')"))
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
    public static void update(String idTranzactie, Tranzactie x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE TRANZACTIE SET TRANZACTIE_ID = ?, TIP_TRANZACTIE=?, " +
                        "SUMA=?, DATA_TRANZACTIE WHERE TRANZACTIE_ID='" + idTranzactie + "'")) {
            pstmt.setInt(1, x.getTranzactie_id());
            pstmt.setString(2, x.getTip_tranzactie());
            pstmt.setInt(3, x.getSuma());
            pstmt.setDate(4, x.getData_tranzactie());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
}
