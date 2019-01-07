package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "RIVERAIN", foreignKeys = {
        @ForeignKey(entity = Projet.class, parentColumns = "CODE_PROJET", childColumns = "CODE_PROJET")
})
public class Riverain {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "CODE_RIVERAIN")
    @SerializedName("codeRiverrain")
    public String codeRiverrain;

    @NonNull
    @ColumnInfo(name = "NOM_COMPLET")
    @SerializedName("nomComplet")
    public String nomComplet;

    @ColumnInfo(name = "ADRESSE")
    @SerializedName("adresse")
    public String adresse;

    @ColumnInfo(name = "TELEPHONE")
    @SerializedName("telephone")
    public String telephone;

    @ColumnInfo(name = "EMAIL")
    @SerializedName("email")
    public String email;

    @ColumnInfo(name = "AUTRE_INFORMATION")
    @SerializedName("informations")
    public String autreInformation;

    @ColumnInfo(name = "TYPE")
    @SerializedName("type")
    public String type; // PM or PP

    @ColumnInfo(name = "REPRESENTANT")
    @SerializedName("representant")
    public String representant; // Nullable

    @ColumnInfo(name = "PIECE_IDENTITE")
    @SerializedName("pieceIdentite")
    public String pieceIdentite;

    @ColumnInfo(name = "NUMERO_PIECE_IDENTITE")
    @SerializedName("numeroPieceIdentite")
    public String numeroPieceIdentite;

    @ColumnInfo(name = "URL_PIECE_IDENTITE")
    @SerializedName("urlPieceidentite")
    public String urlPieceIdentite;

    @ColumnInfo(name = "RCCM")
    @SerializedName("rccm")
    public String rccm;

    @ColumnInfo(name = "NUMERO_IMPOT")
    @SerializedName("numeroImpot")
    public String numeroImpot;

    @ColumnInfo(name = "CODE_PROJET")
    @SerializedName("codeProjet")
    public String codeProjet;

    public Riverain(@NonNull String nomComplet, @NonNull String adresse, @NonNull String telephone, @NonNull String email, @NonNull String autreInformation, @NonNull String type, @NonNull String representant, @NonNull String pieceIdentite, @NonNull String numeroPieceIdentite, @NonNull String urlPieceIdentite, @NonNull String rccm, @NonNull String numeroImpot, @NonNull String codeProjet) {
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
        this.codeProjet = codeProjet;
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
