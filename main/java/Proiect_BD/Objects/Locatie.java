package Proiect_BD.Objects;

public class Locatie {
    private int locatie_id;
    private String strada_locatie;
    private String telefon_locatie;
    private String email_locatie;

    public int getLocatie_id() {
        return locatie_id;
    }
    public void setLocatie_id(int locatie_id) {
        this.locatie_id = locatie_id;
    }
    public String getStrada_locatie() {
        return strada_locatie;
    }
    public void setStrada_locatie(String strada_locatie) {this.strada_locatie = strada_locatie;}
    public String getTelefon_locatie() {
        return telefon_locatie;
    }
    public void setEmail_locatie(String telefon_locatie) {
        this.telefon_locatie = telefon_locatie;
    }
    public String getEmail_locatie() {
        return email_locatie;
    }
    public void setTelefon_locatie(String email_locatie) {
        this.email_locatie = email_locatie;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "locatie_id=" + locatie_id +
                ", strada_locatie='" + strada_locatie + '\'' +
                ", telefon_locatie='" + telefon_locatie + '\'' +
                ", email_locatie='" + email_locatie + '\'' +
                '}';
    }
}
