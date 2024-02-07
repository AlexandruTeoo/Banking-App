package Proiect_BD.Objects;

public class Cont {
    private int numar_cont;
    private String pin_cont;
    private java.sql.Date data_expirarii;
    private int cvv;
    private String tip_cont;
    private int suma_bani;

    public int getNumar_cont() {
        return numar_cont;
    }

    public void setNumar_cont(int numar_cont) {
        this.numar_cont = numar_cont;
    }
    public String getPin_cont() {
        return pin_cont;
    }
    public void setPin_cont(String pin_cont) {
        this.pin_cont = pin_cont;
    }
    public java.sql.Date getData_expirarii() {
        return data_expirarii;
    }
    public void setData_expirarii(java.sql.Date data_expirarii) {
        this.data_expirarii = data_expirarii;
    }
    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    public String getTip_cont() {
        return tip_cont;
    }
    public void setTip_cont(String tip_cont) {
        this.tip_cont = tip_cont;
    }
    public int getSuma_bani() {
        return suma_bani;
    }
    public void setSuma_bani(int suma_bani) {
        this.suma_bani = suma_bani;
    }

    @Override
    public String toString() {
        return "Cont{" +
                "numar_cont=" + numar_cont +
                ", pin_cont=" + pin_cont + '\'' +
                ", data_expirarii=" + data_expirarii + '\'' +
                ", cvv=" + cvv + '\'' +
                ", tip_cont=" + tip_cont + '\'' +
                ", suma_bani=" + suma_bani +
                '}';
    }
}
