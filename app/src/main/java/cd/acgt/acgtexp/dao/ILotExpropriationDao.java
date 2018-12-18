package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.LotExpropriation;

/**
 * Created by Sugar on 7/3/2018
 */
@Dao
public interface ILotExpropriationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(LotExpropriation...lotExpropriation);

    @Update
    int update(LotExpropriation...lotExpropriation);

    @Delete
    int delete(LotExpropriation...lotExpropriation);

    @Query("SELECT * FROM LOT_EXPROPRIATION WHERE CODE_LOT_EXPROPRIATION=:codeLotExpropriation")
    LotExpropriation get(String codeLotExpropriation);

    @Query("DELETE FROM LOT_EXPROPRIATION")
    void deleteAll();
}
