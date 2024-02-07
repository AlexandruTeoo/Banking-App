package Proiect_BD.System;

import Proiect_BD.Database;
import Proiect_BD.Objects.Locatie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocatieDAO {
    public static Locatie[] findAll(){
        Connection con = Database.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select strada_locatie from locatie"))
        {
            List<Locatie> locatie = new ArrayList<Locatie>();
            while (rs.next()) {
                Locatie x = new Locatie();
                x.setStrada_locatie(rs.getString(1));
                locatie.add(x);
            }
            Locatie[] array = new Locatie[locatie.size()];

            int index = -1;
            for (Locatie x: locatie)
            {
                index++;
                array[index] = x;
            }

            return array;
        }
        catch (SQLException e) { e.printStackTrace(); }
        finally { Database.closeConnection(); }
        return null;
    }
}
