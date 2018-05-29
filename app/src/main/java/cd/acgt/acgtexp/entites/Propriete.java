package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "PROPRIETE", foreignKeys = {
        @ForeignKey(entity = Projet.class, parentColumns = "CODE_PROJET", childColumns = "CODE_PROJET")
})
public class Propriete {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CODE_PROPRIETE")
    int codePropriete;
    @NonNull
    @ColumnInfo(name = "TYPE")
    public String type;
    @NonNull
    @ColumnInfo(name = "ADRESSE")
    public String adresse;
    @ColumnInfo(name = "URL_PHOTO_1")
    public String urlPhoto1;
    @ColumnInfo(name = "URL_PHOTO_2")
    public String urlPhoto2;
    @ColumnInfo(name = "URL_PHOTO_3")
    public String urlPhoto3;
    @ColumnInfo(name = "CODE_RIVERAIN")
    public int codeRiverain;
    @ColumnInfo(name = "CODE_PROJET")
    public String codeProjet;

    public Propriete(@NonNull String type, @NonNull String adresse, String urlPhoto1, String urlPhoto2, String urlPhoto3, int codeRiverain, String codeProjet) {
        this.type = type;
        this.adresse = adresse;
        this.urlPhoto1 = urlPhoto1;
        this.urlPhoto2 = urlPhoto2;
        this.urlPhoto3 = urlPhoto3;
        this.codeRiverain = codeRiverain;
        this.codeProjet = codeProjet;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePropriete() {
        return codePropriete;
    }

    public void setCodePropriete(int codePropriete) {
        this.codePropriete = codePropriete;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public int getCodeRiverain() {
        return codeRiverain;
    }

    public String getCodeProjet() {
        return codeProjet;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrlPhoto1() {
        return urlPhoto1;
    }

    public void setUrlPhoto1(String urlPhoto1) {
        this.urlPhoto1 = urlPhoto1;
    }

    public String getUrlPhoto2() {
        return urlPhoto2;
    }

    public void setUrlPhoto2(String urlPhoto2) {
        this.urlPhoto2 = urlPhoto2;
    }

    public String getUrlPhoto3() {
        return urlPhoto3;
    }

    public void setUrlPhoto3(String urlPhoto3) {
        this.urlPhoto3 = urlPhoto3;
    }
}
