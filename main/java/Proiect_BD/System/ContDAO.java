package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.Cont;

import java.sql.*;

public class ContDAO {
    public static boolean verify(int numar_cont, String pin_cont, int cvv, java.sql.Date data_expirarii){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DATA_EXPIRARII FROM CONT WHERE NUMAR_CONT='"+numar_cont+"' AND PIN_CONT='"+pin_cont+"' AND CVV='"+cvv+"' "))
        {
            if (rs.next())
            {
                java.sql.Date dt = rs.getDate(1);
                return dt.getTime() == data_expirarii.getTime();
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return false;
    }
    //Getter
    public static Cont findByNumarCont(int nc){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NUMAR_CONT, PIN_CONT, DATA_EXPIRARII, CVV, TIP_CONT, SUMA_BANI FROM CONT WHERE NUMAR_CONT= '"+ nc +"'"))
        {
            Cont x;
            if (rs.next()) {
                x = new Cont();
                x.setNumar_cont(rs.getInt(1));
                x.setPin_cont(rs.getString(2));
                x.setData_expirarii(rs.getDate(3));
                x.setCvv(rs.getInt(4));
                x.setTip_cont(rs.getString(5));
                x.setSuma_bani(rs.getInt(6));
                x.setNumar_cont(nc);
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

    public static int lastId(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(NUMAR_CONT) FROM CONT"))
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return 0;

    }
    public static int getCVV(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(CVV) FROM CONT"))
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return 0;

    }
    //Setter~Create
    public static int create(Cont x) {
        int id=-1;
        id=lastId()+1;
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into Cont (numar_cont, pin_cont, data_expirarii, cvv, tip_cont, suma_bani, taxa)" +
                        "VALUES(?,?,?,?,?,?)")) {
            pstmt.setInt(1, id);
            pstmt.setString(2, x.getPin_cont());
            pstmt.setDate(3, x.getData_expirarii());
            pstmt.setInt(4, x.getCvv());
            pstmt.setString(5, x.getTip_cont());
            pstmt.setInt(6, x.getSuma_bani());
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
    public static void delete(int nc)
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM CONT WHERE NUMAR_CONT='" + nc + "'"))
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
    public static void update(int id, Cont x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE CONT SET NUMAR_CONT=?, " +
                        "PIN_CONT=?, " +
                        "DATA_EXPIRARII=?, " +
                        "CVV=?, " +
                        "TIP_CONT=?, "  +
                        "SUMA_BANI=? WHERE NUMAR_CONT='" + id + "'")) {
            pstmt.setInt(1, x.getNumar_cont());
            pstmt.setString(2, x.getPin_cont());
            pstmt.setDate(3, x.getData_expirarii());
            pstmt.setInt(4, x.getCvv());
            pstmt.setString(5, x.getTip_cont());
            pstmt.setInt(6, x.getSuma_bani());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection();
        }
    }
}
