package Proiect_BD.Objects;

public class Tranzactie {
    private int tranzactie_id;
    private String tip_tranzactie;
    private int suma;
    private java.sql.Date data_tranzactie;

    public int getTranzactie_id() {
        return tranzactie_id;
    }

    public void setTranzactie_id(int tranzactie_id) {
        this.tranzactie_id = tranzactie_id;
    }

    public String getTip_tranzactie() {
        return tip_tranzactie;
    }

    public void setTip_tranzactie(String tip_tranzactie) {
        this.tip_tranzactie = tip_tranzactie;
    }

    public int getSuma() {return suma;}

    public void setSuma(int suma) {this.suma = suma;}

    public java.sql.Date getData_tranzactie() {
        return data_tranzactie;
    }

    public void setData_tranzactie(java.sql.Date data_nasterii) {
        this.data_tranzactie = data_nasterii;
    }
    public void setData_tranzactie(String data_nasterii) {
        this.data_tranzactie = java.sql.Date.valueOf(data_tranzactie.toLocalDate());
    }


    @Override
    public String toString() {
        return "Tranzactie{" +
                "tranzactie_id=" + tranzactie_id +
                ", tip_tranzactie=" + tip_tranzactie + '\'' +
                ", suma=" + suma +
                ", tip_tranzactie=" + data_tranzactie + '\'' +
                '}';
    }
}
