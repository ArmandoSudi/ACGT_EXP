package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.Litige;

/**
 * Created by Sugar on 7/3/2018
 */
@Dao
public interface ILitigeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Litige...litige);

    @Update
    int update(Litige...litige);

    @Delete
    int delete(Litige...litige);

    @Query("SELECT * FROM LITIGE WHERE CODE_LITIGE=:codeLitige")
    Litige getByCode(String codeLitige);

    @Query("SELECT * FROM LITIGE")
    List<Litige> get();

    @Query("DELETE FROM LITIGE")
    void deleteAll();
}
