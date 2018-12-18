package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.Paiement;

/**
 * Created by Sugar on 7/3/2018
 */
@Dao
public interface IPaiementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Paiement...paiement);

    @Update
    int update(Paiement...paiement);

    @Delete
    int delete(Paiement...paiement);

    @Query("SELECT * FROM PAIEMENT")
    List<Paiement> get();

    @Query("SELECT * FROM PAIEMENT WHERE CODE_PAIEMENT=:codePaiement")
    Paiement getByCode(long codePaiement);

    @Query("DELETE FROM PAIEMENT")
    void deleteAll();
}
