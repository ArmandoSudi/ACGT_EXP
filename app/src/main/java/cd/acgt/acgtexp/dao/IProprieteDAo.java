package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import cd.acgt.acgtexp.entites.Propriete;

/**
 * Created by Sugar on 5/22/2018
 */
@Dao
public interface IProprieteDAo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Propriete...propriete);

    @Update
    void update(Propriete...propriete);

    @Delete
    void delete(Propriete...propriete);

    @Query("DELETE FROM PROPRIETE")
    void deleteAll();
}
