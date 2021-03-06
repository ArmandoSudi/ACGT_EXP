package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.Riverain;

/**
 * Created by Sugar on 5/22/2018
 */
@Dao
public interface IRiverainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Riverain...riverain);

    @Update
    int update(Riverain...riverain);

    @Delete
    int delete(Riverain...riverain);

    @Query("SELECT * FROM RIVERAIN WHERE CODE_RIVERAIN=:codeRiverain")
    Riverain get(long codeRiverain);

    @Query("SELECT * FROM RIVERAIN WHERE CODE_PROJET=:codeProjet")
    List<Riverain> getRiverainByProjet(String codeProjet);

    @Query("DELETE FROM RIVERAIN")
    void deleteAll();
}
