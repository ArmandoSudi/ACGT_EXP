package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.Entity;

//@Entity(tableName = "RIVERAIN")
public class Riverain {
    public String nomComplet;
    public String adresse;
    public String telephone;
    public String email;
    public String autreInformation;
    public String type; // PM or PP
    public String representant; // Nullable
    public String pieceIdentite;
    public String numeroPieceIdentite;
    public String urlPieceIdentite;
    public String rccm;
    public String numeroImpot;

    public Riverain(String nomComplet, String adresse, String telephone) {
        this.nomComplet = nomComplet;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAutreInformation() {
        return autreInformation;
    }

    public void setAutreInformation(String autreInformation) {
        this.autreInformation = autreInformation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRepresentant() {
        return representant;
    }

    public void setRepresentant(String representant) {
        this.representant = representant;
    }

    public String getPieceIdentite() {
        return pieceIdentite;
    }

    public void setPieceIdentite(String pieceIdentite) {
        this.pieceIdentite = pieceIdentite;
    }

    public String getNumeroPieceIdentite() {
        return numeroPieceIdentite;
    }

    public void setNumeroPieceIdentite(String numeroPieceIdentite) {
        this.numeroPieceIdentite = numeroPieceIdentite;
    }

    public String getUrlPieceIdentite() {
        return urlPieceIdentite;
    }

    public void setUrlPieceIdentite(String urlPieceIdentite) {
        this.urlPieceIdentite = urlPieceIdentite;
    }

    public String getRccm() {
        return rccm;
    }

    public void setRccm(String rccm) {
        this.rccm = rccm;
    }

    public String getNumeroImpot() {
        return numeroImpot;
    }

    public void setNumeroImpot(String numeroImpot) {
        this.numeroImpot = numeroImpot;
    }
}
