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
@Entity(tableName = "TYPE_PROPRIETE", foreignKeys = {
        @ForeignKey(entity = Propriete.class, parentColumns = "CODE_PROPRIETE", childColumns = "CODE_PROPRIETE")
})
public class TypePropriete {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CODE_TYPE_PROPRIETE")
    public int codeTypePropriete;
    @ColumnInfo(name = "CODE_PROPRIETE")
    public long codePropriete;
    @ColumnInfo(name = "DATE_ENREGISTREMENT")
    public Date dateEnregistrement;
    @ColumnInfo(name = "TYPE")
    public String type;
    @ColumnInfo(name = "LONGUEUR")
    public double longueur;
    @ColumnInfo(name = "LARGEUR")
    public double largeur;
    @ColumnInfo(name = "VOLUME")
    public double volume;
    @ColumnInfo(name = "SURFACE")
    public double surface;
    @ColumnInfo(name = "TYPE_MATERIAU_ESPECE")
    public String typeMateriauEspece;
    @ColumnInfo(name = "NOMBRE")
    public int nombre;
    @ColumnInfo(name = "MONTANT")
    public double montant;
    @ColumnInfo(name = "SIGNATURE_PROTOCOL_ACCORD")
    public Date signatureProtocolAccord;
    @ColumnInfo(name = "AUTRE_INFORMATION")
    public String autreInformation;
    @ColumnInfo(name = "OBSERVATION")
    public String observation;
    @ColumnInfo(name = "DATE_ANNULATION")
    public Date dateAnnulation;
    @ColumnInfo(name = "MOTIF")
    public String motif;

    public TypePropriete(long codePropriete, String type, double longueur, double largeur, double volume, double surface, String typeMateriauEspece, int nombre, double montant, String autreInformation, String observation, String motif) {
        this.codePropriete = codePropriete;
        this.type = type;
        this.longueur = longueur;
        this.largeur = largeur;
        this.volume = volume;
        this.surface = surface;
        this.typeMateriauEspece = typeMateriauEspece;
        this.nombre = nombre;
        this.montant = montant;
        this.autreInformation = autreInformation;
        this.observation = observation;
        this.motif = motif;
    }
}
