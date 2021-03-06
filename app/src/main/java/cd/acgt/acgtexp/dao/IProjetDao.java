package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.Projet;

/**
 * Created by Sugar on 5/22/2018
 */
@Dao
public interface IProjetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Projet...projet);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Projet> projets);

    @Update
    void update(Projet...projet);

    @Delete
    void delete(Projet...projet);

    @Query("SELECT * FROM PROJET WHERE CODE_PROJET=:codeProjet")
    Projet get(String codeProjet);

    @Query("SELECT * FROM PROJET")
    List<Projet> getAll();

    @Query("SELECT PROJET.DESIGNATION FROM PROJET " +
            "INNER JOIN LOT_EXPROPRIATION ON LOT_EXPROPRIATION.CODE_PROJET = PROJET.CODE_PROJET " +
            "WHERE LOT_EXPROPRIATION.CODE_LOT_EXPROPRIATION=:codeLotExpropriation")
    String getProjetDesignation(String codeLotExpropriation);

    @Query("DELETE FROM PROJET")
    void deleteAll();
}
