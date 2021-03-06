package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 5/18/2018
 */

@Entity(tableName = "PROJET")
public class Projet {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "CODE_PROJET")
    @SerializedName("codeProjet")
    public String codeProjet;

    @NonNull
    @ColumnInfo(name = "DESIGNATION")
    @SerializedName("designation")
    public String designation;

    @ColumnInfo(name = "COUT_PROJET")
    @SerializedName("coutProjet")
    public double coutProjet;

    @ColumnInfo(name = "CODE_FINANCEMENT")
    @SerializedName("codeFinancement")
    public String codeFinancement;

    @SerializedName("dateSignatureContrat")
    @ColumnInfo(name = "DATE_SIGNATURE_CONTRAT")
    public Date dateSignatureContrat;

    @SerializedName("dateDemarrage")
    @ColumnInfo(name = "DATE_DEMARRAGE")
    public Date dateDemarrage;

    @ColumnInfo(name = "DATE_FIN_PREVUE")
    @SerializedName("dateFinPrevue")
    public Date dateFinPrevue;

    @ColumnInfo(name = "CODE_ENTREPRISE")
    @SerializedName("codeEntreprise")
    public String codeEntreprise;

    @ColumnInfo(name = "MAITRE_OUVRAGE")
    @SerializedName("maitreOuvrage")
    public String maitreOuvrage;

    @ColumnInfo(name = "MAITRE_OUVRAGE_DELEGUE")
    @SerializedName("maitreOuvrageDelegue")
    public String maitreOuvrageDelegue;

    @ColumnInfo(name = "SHORT_DESCRIPTION")
    @SerializedName("shortDesignation")
    public String shortDesignation;

    @ColumnInfo(name = "MISSION_CONTROLE")
    @SerializedName("missionControle")
    public String missionControle;

    @ColumnInfo(name = "OBJECTIFS")
    @SerializedName("objectifs")
    public String objectifs;

    @ColumnInfo(name = "CARACTERISTIQUES")
    @SerializedName("caracteristiques")
    public String caracterisitiques;

    @ColumnInfo(name = "RECEPTION_PROVISOIRE")
    @SerializedName("receptiionProvisoire")
    public Date receptionProvisoire;

    @ColumnInfo(name = "RECEPTION_DEFINITIVE")
    @SerializedName("receptionDefinitive")
    public Date receptionDefinitive;

    @ColumnInfo(name = "CONTEXTE")
    @SerializedName("contexte")
    public String contexte;

    public Projet(@NonNull String codeProjet, @NonNull String designation, Date dateSignatureContrat, Date dateDemarrage, Date dateFinPrevue, Date receptionProvisoire, Date receptionDefinitive) {
        this.codeProjet = codeProjet;
        this.designation = designation;
        this.dateSignatureContrat = dateSignatureContrat;
        this.dateDemarrage = dateDemarrage;
        this.dateFinPrevue = dateFinPrevue;
        this.receptionProvisoire = receptionProvisoire;
        this.receptionDefinitive = receptionDefinitive;
    }

    @Override
    public String toString() {
        return this.codeProjet + " " + this.shortDesignation + " " ;
//                "Date signature: " + this.dateSignatureContrat + " " +
//                "Date demarrage: " + this.dateDemarrage + " " +
//                "Date fin: " + this.dateFinPrevue + " " +
//                "Date reception provisoire: " + this.receptionProvisoire + " " +
//                "Date reception definitive: " + this.receptionDefinitive;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCodeProjet() {
        return codeProjet;
    }

    public void setCodeProjet(String codeProjet) {
        this.codeProjet = codeProjet;
    }

    public double getCoutProjet() {
        return coutProjet;
    }

    public void setCoutProjet(double coutProjet) {
        this.coutProjet = coutProjet;
    }

    public String getCodeFinancement() {
        return codeFinancement;
    }

    public void setCodeFinancement(String codeFinancement) {
        this.codeFinancement = codeFinancement;
    }

    public Date getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public void setDateSignatureContrat(Date dateSignatureContrat) {
        this.dateSignatureContrat = dateSignatureContrat;
    }

    public Date getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(Date dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    public Date getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(Date dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public String getCodeEntreprise() {
        return codeEntreprise;
    }

    public void setCodeEntreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    public String getMaitreOuvrage() {
        return maitreOuvrage;
    }

    public void setMaitreOuvrage(String maitreOuvrage) {
        this.maitreOuvrage = maitreOuvrage;
    }

    public String getMaitreOuvrageDelegue() {
        return maitreOuvrageDelegue;
    }

    public void setMaitreOuvrageDelegue(String maitreOuvrageDelegue) {
        this.maitreOuvrageDelegue = maitreOuvrageDelegue;
    }

    public String getShortDesignation() {
        return shortDesignation;
    }

    public void setShortDesignation(String shortDesignation) {
        this.shortDesignation = shortDesignation;
    }

    public String getMissionControle() {
        return missionControle;
    }

    public void setMissionControle(String missionControle) {
        this.missionControle = missionControle;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public String getCaracterisitiques() {
        return caracterisitiques;
    }

    public void setCaracterisitiques(String caracterisitiques) {
        this.caracterisitiques = caracterisitiques;
    }

    public Date getReceptionProvisoire() {
        return receptionProvisoire;
    }

    public void setReceptionProvisoire(Date receptionProvisoire) {
        this.receptionProvisoire = receptionProvisoire;
    }

    public Date getReceptionDefinitive() {
        return receptionDefinitive;
    }

    public void setReceptionDefinitive(Date receptionDefinitive) {
        this.receptionDefinitive = receptionDefinitive;
    }

    public String getContexte() {
        return contexte;
    }

    public void setContexte(String contexte) {
        this.contexte = contexte;
    }
}
