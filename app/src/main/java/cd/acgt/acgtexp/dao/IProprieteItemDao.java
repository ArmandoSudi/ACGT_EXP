package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sugar on 5/30/2018
 */
@Dao
public interface IProprieteItemDao {

    @Query("SELECT RIVERAIN.NOM_COMPLET AS proprietaire,  " +
            "PROPRIETE.ADRESSE AS adresse, " +
            "PROPRIETE.TYPE AS type, " +
            "PROPRIETE.CODE_PROPRIETE AS codePropriete " +
            "FROM RIVERAIN INNER JOIN PROPRIETE ON PROPRIETE.CODE_RIVERAIN = RIVERAIN.CODE_RIVERAIN " +
            "WHERE RIVERAIN.CODE_PROJET =:codeProjet")
    public List<ProprieteItem> getAll(String codeProjet);

    public static class ProprieteItem {
        public int codePropriete;
        public String adresse;
        public String proprietaire;
        public String type;
    }
}
