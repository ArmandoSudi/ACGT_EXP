package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "RIVERAIN")
public class Riverain {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CODE_RIVERAIN")
    public int codeRiverrain;
    @NonNull
    @ColumnInfo(name = "NOM_COMPLET")
    public String nomComplet;
    @NonNull
    @ColumnInfo(name = "ADRESSE")
    public String adresse;
    @NonNull
    @ColumnInfo(name = "TELEPHONE")
    public String telephone;
    @NonNull
    @ColumnInfo(name = "EMAIL")
    public String email;
    @NonNull
    @ColumnInfo(name = "AUTRE_INFORMATION")
    public String autreInformation;
    @NonNull
    @ColumnInfo(name = "TYPE")
    public String type; // PM or PP
    @NonNull
    @ColumnInfo(name = "REPRESENTANT")
    public String representant; // Nullable
    @NonNull
    @ColumnInfo(name = "PIECE_IDENTITE")
    public String pieceIdentite;
    @NonNull
    @ColumnInfo(name = "NUMERO_PIECE_IDENTITE")
    public String numeroPieceIdentite;
    @NonNull
    @ColumnInfo(name = "URL_PIECE_IDENTITE")
    public String urlPieceIdentite;
    @NonNull
    @ColumnInfo(name = "RCCM")
    public String rccm;
    @NonNull
    @ColumnInfo(name = "NUMERO_IMPOT")
    public String numeroImpot;

    public Riverain(@NonNull String nomComplet, @NonNull String adresse, @NonNull String telephone, @NonNull String email, @NonNull String autreInformation, @NonNull String type, @NonNull String representant, @NonNull String pieceIdentite, @NonNull String numeroPieceIdentite, @NonNull String urlPieceIdentite, @NonNull String rccm, @NonNull String numeroImpot) {
        this.nomComplet = nomComplet;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.autreInformation = autreInformation;
        this.type = type;
        this.representant = representant;
        this.pieceIdentite = pieceIdentite;
        this.numeroPieceIdentite = numeroPieceIdentite;
        this.urlPieceIdentite = urlPieceIdentite;
        this.rccm = rccm;
        this.numeroImpot = numeroImpot;
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
