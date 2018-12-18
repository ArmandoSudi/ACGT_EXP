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
@Entity(tableName = "LITIGE", foreignKeys = {
        @ForeignKey(entity = Propriete.class, parentColumns = "CODE_PROPRIETE", childColumns = "CODE_PROPRIETE")
})
public class Litige {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "CODE_LITIGE")
    public String codeLitige;
    @ColumnInfo(name = "CODE_PROPRIETE")
    public int codePropriete;
    @ColumnInfo(name = "DESCRIPTION")
    public String description;
    @ColumnInfo(name = "DATE_ENREGISTREMENT")
    public Date dateEnregistrement;
    @ColumnInfo(name = "DATE_TRAITEMENT")
    public Date dateTraitement;
    @ColumnInfo(name = "OBSERVATION_TRAITMENT")
    public String observationTraitement;
    @ColumnInfo(name = "DECISION")
    public String decision;

    public Litige(@NonNull String codeLitige, int codePropriete, String description, Date dateEnregistrement, Date dateTraitement, String observationTraitement, String decision) {
        this.codeLitige = codeLitige;
        this.codePropriete = codePropriete;
        this.description = description;
        this.dateEnregistrement = dateEnregistrement;
        this.dateTraitement = dateTraitement;
        this.observationTraitement = observationTraitement;
        this.decision = decision;
    }
}
