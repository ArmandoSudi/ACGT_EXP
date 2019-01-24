package cd.acgt.acgtexp.entites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Sugar on 7/3/2018
 */
@Entity(tableName = "LOT_EXPROPRIATION", foreignKeys = {
        @ForeignKey(entity = Projet.class, parentColumns = "CODE_PROJET", childColumns = "CODE_PROJET")
})
public class LotExpropriation {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "CODE_LOT_EXPROPRIATION")
    public String codeLotExpropriation;
    @ColumnInfo(name = "CODE_PROJET")
    public String codeProjet;
    @ColumnInfo(name = "DESIGNATION")
    public String designation;
    @ColumnInfo(name = "PK_INITIAL")
    public String pkInitial;
    @ColumnInfo(name = "PK_FINAL")
    public String pkFinal;

    public LotExpropriation(@NonNull String codeLotExpropriation, String codeProjet, String designation, String pkInitial, String pkFinal) {
        this.codeLotExpropriation = codeLotExpropriation;
        this.codeProjet = codeProjet;
        this.designation = designation;
        this.pkInitial = pkInitial;
        this.pkFinal = pkFinal;
    }

    @Override
    public String toString() {
        return this.designation;
    }

    @Override
    public boolean equals(Object obj) {
        return this.codeLotExpropriation.equals(obj) ? true : false;
    }
}
