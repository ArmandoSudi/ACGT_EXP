package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import cd.acgt.acgtexp.entites.Riverain;

/**
 * Created by Sugar on 5/22/2018
 */
@Dao
public interface IRiverainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Riverain...riverain);

    @Update
    void update(Riverain...riverain);

    @Delete
    void delete(Riverain...riverain);
}
