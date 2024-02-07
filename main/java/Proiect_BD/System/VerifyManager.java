package Proiect_BD.System;

import java.time.LocalDate;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyManager {
    public static boolean isValidPhoneNumber(String numar)
    {
        // 1) Begins with 0
        // 2) Then contains 0,1.....9
        // 3) Then contains 10 digits
        Pattern p = Pattern.compile("(0)(2|7)[0-9]{8}");
        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(numar);
        return (m.find() && m.group().equals(numar));
    }

    public static boolean isValidNumber(String numar)
    {
        char[] chars = numar.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidName(String nume)
    {
        char[] chars = nume.toCharArray();
        for(char c : chars){
            if(!Character.isLetter(c) && c!=' '){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDate(String date)
    {
        String[] data = date.split("-");
        if(data.length!=3)
        {
            return false;
        }
        if(data[0].length()!=4)
        {
            return false;
        }
        if(data[1].length()!=2)
        {
            return false;
        }
        if(data[2].length()!=2)
        {
            return false;
        }
        if(!isValidNumber(data[0]))
        {
            return false;
        }
        if(!isValidNumber(data[1]))
        {
            return false;
        }
        if(!isValidNumber(data[2]))
        {
            return false;
        }
        int year;
        int month;
        int day;
        year = Integer.parseInt(data[0]);
        month = Integer.parseInt(data[1]);
        day = Integer.parseInt(data[2]);
        if(year> Year.now().getValue())
        {
            return false;
        }
        if(month>12)
        {
            return false;
        }
        return day <= 31;
    }

    public static String getDate()
    {
        int year;
        int month;
        int day;
        LocalDate curentTime = LocalDate.now();
        year = Year.now().getValue();
        month = curentTime.getMonth().getValue();
        day = curentTime.getDayOfMonth();
        year = year + 5;
        return year + "-" + month + "-" + day;
    }
    public static int getVarsta(String date)
    {
        String[] data = date.split("-");
        int year;
        int month;
        int day;
        year = Integer.parseInt(data[0]);
        month = Integer.parseInt(data[1]);
        day = Integer.parseInt(data[2]);
        int varsta = Year.now().getValue()-year-1;
        LocalDate curentTime = LocalDate.now();
        if(month < curentTime.getMonth().getValue())
        {
            varsta+=1;
        }
        else
        {
            if(month == curentTime.getMonth().getValue() && day<=curentTime.getDayOfMonth())
            {
                varsta += 1;
            }
        }
        return varsta;
    }

    public static boolean isValidEmail(String email)
    {
        return email.contains(".") && email.contains("@");
    }
}
