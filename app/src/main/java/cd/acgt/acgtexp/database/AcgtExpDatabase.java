package cd.acgt.acgtexp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import cd.acgt.acgtexp.dao.ILitigeDao;
import cd.acgt.acgtexp.dao.ILotExpropriationDao;
import cd.acgt.acgtexp.dao.IPaiementDao;
import cd.acgt.acgtexp.dao.IProprieteItemDao;
import cd.acgt.acgtexp.dao.ITypeProprieteDao;
import cd.acgt.acgtexp.entites.Litige;
import cd.acgt.acgtexp.entites.LotExpropriation;
import cd.acgt.acgtexp.entites.Paiement;
import cd.acgt.acgtexp.entites.TypePropriete;
import cd.acgt.acgtexp.utils.DateConverts;
import cd.acgt.acgtexp.dao.IProjetDao;
import cd.acgt.acgtexp.dao.IProprieteDAo;
import cd.acgt.acgtexp.dao.IRiverainDao;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.entites.Riverain;

/**
 * Created by Sugar on 5/22/2018
 */
@Database(entities = {
        Projet.class,
        Propriete.class,
        Riverain.class,
        TypePropriete.class,
        Paiement.class,
        LotExpropriation.class,
        Litige.class},
        version = 10, exportSchema = false
)
@TypeConverters(DateConverts.class)
public abstract class AcgtExpDatabase extends RoomDatabase {

    private static final String DB_NAME = "acgtexp.db";

    public abstract IProjetDao getIProjetDao();
    public abstract IProprieteDAo getIProprieteDao();
    public abstract IRiverainDao getIRiverainDao();
    public abstract IProprieteItemDao getIProprieteItemDao();
    public abstract ITypeProprieteDao getITypeProprieteDao();
    public abstract IPaiementDao getIPaiementDao();
    public abstract ILotExpropriationDao getILotExpropriationDao();
    public abstract ILitigeDao getILitigeDao();

    private static AcgtExpDatabase INSTANCE;

    public static AcgtExpDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AcgtExpDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AcgtExpDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static public AcgtExpDatabase getInstance() { return INSTANCE;}

}
