package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Sugar on 7/3/2018
 */
@Entity(tableName = "PAIEMENT", foreignKeys = {
        @ForeignKey(entity = Propriete.class, parentColumns = "CODE_PROPRIETE", childColumns = "CODE_PROPRIETE")
})
public class Paiement {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "CODE_PAIEMENT")
    @SerializedName("codePaiement")
    public String codePaiement;

    @ColumnInfo(name = "CODE_PROPRIETE")
    @SerializedName("codePropriete")
    public int codePropriete;

    @ColumnInfo(name = "DATE_PAIEMENT")
    @SerializedName("datePaiement")
    public Date datePaiement;

    @ColumnInfo(name = "MONTANT")
    @SerializedName("montant")
    public double montant;

    @ColumnInfo(name = "TYPE_PAIEMENT")
    @SerializedName("typePaiement")
    public String typePaiement;

    @ColumnInfo(name = "BANQUE")
    @SerializedName("banque")
    public String banque;

    @ColumnInfo(name = "REFERENCE")
    @SerializedName("referencePaiement")
    public String referencePaiement;

    @ColumnInfo(name = "OBSERVATION")
    @SerializedName("observation")
    public String observation;

    public Paiement(String codePaiement, int codePropriete, Date datePaiement, double montant, String typePaiement, String banque, String referencePaiement, String observation) {
        this.codePaiement = codePaiement;
        this.codePropriete = codePropriete;
        this.datePaiement = datePaiement;
        this.montant = montant;
        this.typePaiement = typePaiement;
        this.banque = banque;
        this.referencePaiement = referencePaiement;
        this.observation = observation;
    }
}
