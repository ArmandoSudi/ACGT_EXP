package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cd.acgt.acgtexp.entites.Projet;

/**
 * Created by Sugar on 5/22/2018
 */
@Dao
public interface IProjetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Projet...projet);

    @Update
    void update(Projet...projet);

    @Delete
    void delete(Projet...projet);

    @Query("DELETE FROM PROJET")
    void deleteAll();
}
