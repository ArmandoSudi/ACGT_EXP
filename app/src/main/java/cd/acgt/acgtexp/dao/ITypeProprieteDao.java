package cd.acgt.acgtexp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cd.acgt.acgtexp.entites.TypePropriete;

/**
 * Created by Sugar on 7/3/2018
 */
@Dao
public interface ITypeProprieteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(TypePropriete...typePropriete);

    @Update
    int update(TypePropriete...typePropriete);

    @Delete
    int delete(TypePropriete...typePropriete);

    @Query("SELECT * FROM TYPE_PROPRIETE WHERE CODE_PROPRIETE=:codePropriete")
    List<TypePropriete> get(long codePropriete);


    @Query("DELETE FROM TYPE_PROPRIETE")
    void deleteAll();
}
