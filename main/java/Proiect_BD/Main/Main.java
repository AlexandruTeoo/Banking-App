package Proiect_BD.Main;
import Proiect_BD.Front_End.LogIn;

public class Main {
    public static void afisare(Object x){
        if(x==null)
        {
            System.out.println("Nu exista");
        }
        else
        {
            System.out.println(x);
        }
    }

    public static void main(String[] args)
    {
        LogIn window=new LogIn();
        //new MainMenuT(1, 1);
    }
}