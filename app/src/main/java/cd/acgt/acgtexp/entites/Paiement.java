package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
    public String codePaiement;
    @ColumnInfo(name = "CODE_PROPRIETE")
    public int codePropriete;
    @ColumnInfo(name = "DATE_PAIEMENT")
    public Date datePaiement;
    @ColumnInfo(name = "MONTANT")
    public double montant;
    @ColumnInfo(name = "TYPE_PAIEMENT")
    public String typePaiement;
    @ColumnInfo(name = "BANQUE")
    public String banque;
    @ColumnInfo(name = "REFERENCE")
    public String referencePaiement;
    @ColumnInfo(name = "OBSERVATION")
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
