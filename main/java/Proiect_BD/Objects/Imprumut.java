package Proiect_BD.Objects;

public class Imprumut {
    private int imprumut_id;
    private int suma_imprumutata;
    private int dobanda;
    private int numar_cont;
    private java.sql.Date data_imprumut;

    public int getImprumut_id() {return imprumut_id;}
    public void setImprumut_id(int imprumut_id) {this.imprumut_id = imprumut_id;}
    public int getSuma_imprumutata() {return suma_imprumutata;}
    public void setSuma_imprumutata(int suma_imprumutata) {this.suma_imprumutata = suma_imprumutata;}
    public int getDobanda() {return dobanda;}
    public void setDobanda(int dobanda) {this.dobanda = dobanda;}
    public int getNumar_cont() {return numar_cont;}
    public void setNumar_cont(int numar_cont) {this.numar_cont = numar_cont;}
    public java.sql.Date getData_imprumut() {
        return data_imprumut;
    }

    public void setData_imprumut(java.sql.Date data_imprumut) {
        this.data_imprumut = data_imprumut;
    }

    @Override
    public String toString() {
    return "Imprumut{" +
            "imprumut_id=" + imprumut_id +
            ", suma_imprumutata=" + suma_imprumutata +
            ", dobanda=" + dobanda +
            ", numar_cont=" + numar_cont +
            ", data_imprumut=" + data_imprumut +
            '}';
    }
}
