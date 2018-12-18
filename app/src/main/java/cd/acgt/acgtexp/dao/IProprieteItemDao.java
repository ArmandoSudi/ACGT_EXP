package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Sugar on 5/30/2018
 */
@Dao
public interface IProprieteItemDao {

    @Query("SELECT RIVERAIN.NOM_COMPLET AS proprietaire,  " +
            "RIVERAIN.CODE_RIVERAIN AS codeRiverain, " +
            "PROPRIETE.ADRESSE AS adresse, " +
            "PROPRIETE.TYPE AS type, " +
            "PROPRIETE.CODE_PROPRIETE AS codePropriete " +
            "FROM RIVERAIN INNER JOIN PROPRIETE ON PROPRIETE.CODE_RIVERAIN = RIVERAIN.CODE_RIVERAIN " +
            "WHERE RIVERAIN.CODE_PROJET =:codeProjet")
    public List<ProprieteItem> getAll(String codeProjet);

    @Query("DELETE FROM PROPRIETE WHERE PROPRIETE.CODE_PROPRIETE=:codePropriete")
    int delete(int codePropriete);

    public static class ProprieteItem {
        public int codeRiverain;
        public int codePropriete;
        public String adresse;
        public String proprietaire;
        public String type;
    }
}
