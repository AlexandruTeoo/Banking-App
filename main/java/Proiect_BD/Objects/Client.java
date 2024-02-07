package Proiect_BD.Objects;

public class Client {

    private int client_id;
    private String nume;
    private String prenume;
    private String email;
    private String numar_telefon;
    private String adresa;
    private java.sql.Date data_nasterii;

    public int getClient_id() { return client_id; }
    public void setClient_id(int client_id) {this.client_id = client_id;}
    public String getNume() { return nume; }
    public void setNume(String nume) {this.nume = nume;}
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) {this.prenume = prenume;}
    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email;}
    public String getNumar_telefon() {
        return numar_telefon;
    }

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public java.sql.Date getData_nasterii() {
        return data_nasterii;
    }

    public void setData_nasterii(java.sql.Date data_nasterii) {
        this.data_nasterii = data_nasterii;
    }
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) {this.adresa = adresa;}

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", nume=" + nume + '\'' +
                ", prenume=" + prenume + '\'' +
                ", email=" + email + '\'' +
                ", numar_telefon=" + numar_telefon +
                ", adresa=" + adresa +
                ", data_nasterii=" + data_nasterii + '\'' +
                '}';
    }
}
