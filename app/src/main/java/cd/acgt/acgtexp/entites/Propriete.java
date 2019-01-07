package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "PROPRIETE", foreignKeys = {
        @ForeignKey(entity = LotExpropriation.class, parentColumns = "CODE_LOT_EXPROPRIATION", childColumns = "CODE_LOT_EXPROPRIATION")
})
public class Propriete {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CODE_PROPRIETE")
    @SerializedName("codePropriete")
    int codePropriete;

    @ColumnInfo(name = "CODE_LOT_EXPROPRIATION")
    @SerializedName("codeLotExpropriation")
    String codeLotExpropriation;

    @ColumnInfo(name = "CODE_RIVERAIN")
    @SerializedName("codeRiverain")
    public String codeRiverain;

    @NonNull
    @ColumnInfo(name = "ADRESSE")
    @SerializedName("adresse")
    public String adresse;

    @ColumnInfo(name = "PK")
    @SerializedName("PK")
    public String PK;

    @ColumnInfo(name = "LATITUDE")
    @SerializedName("latitude")
    public double latitude;

    @ColumnInfo(name = "LONGITUDE")
    @SerializedName("longitude")
    public double longitude;

    @ColumnInfo(name = "URL_IMAGES")
    @SerializedName("urlImages")
    public String urlImages;

    @ColumnInfo(name = "SIGNATURE_PROTOCOLE_ACCORD")
    @SerializedName("signatureProtocoleAccord")
    public Date signatureProtocoleAccord;

    public Propriete(@NonNull int codePropriete, String codeLotExpropriation, String codeRiverain, @NonNull String adresse, String PK, double latitude, double longitude, String urlImages, Date signatureProtocoleAccord) {
        this.codePropriete = codePropriete;
        this.codeLotExpropriation = codeLotExpropriation;
        this.codeRiverain = codeRiverain;
        this.adresse = adresse;
        this.PK = PK;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlImages = urlImages;
        this.signatureProtocoleAccord = signatureProtocoleAccord;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getCodeRiverain() {
        return codeRiverain;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(String urlImages) {
        this.urlImages = urlImages;
    }

    public Date getSignatureProtocoleAccord() {
        return signatureProtocoleAccord;
    }

    public void setSignatureProtocoleAccord(Date signatureProtocoleAccord) {
        this.signatureProtocoleAccord = signatureProtocoleAccord;
    }

    public String getCodeLotExpropriation() {
        return codeLotExpropriation;
    }

    public void setCodeLotExpropriation(String codeLotExpropriation) {
        this.codeLotExpropriation = codeLotExpropriation;
    }

    public void setCodeRiverain(String codeRiverain) {
        this.codeRiverain = codeRiverain;
    }

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }
}
