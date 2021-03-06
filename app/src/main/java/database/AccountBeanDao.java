package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zxyoyo.apk.ji.accounting.source.AccountBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACCOUNT_BEAN".
*/
public class AccountBeanDao extends AbstractDao<AccountBean, Long> {

    public static final String TABLENAME = "ACCOUNT_BEAN";

    /**
     * Properties of entity AccountBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Account = new Property(1, String.class, "account", false, "ACCOUNT");
        public final static Property Number = new Property(2, double.class, "number", false, "NUMBER");
        public final static Property Time = new Property(3, long.class, "time", false, "TIME");
        public final static Property Icon = new Property(4, int.class, "icon", false, "ICON");
        public final static Property Type = new Property(5, int.class, "type", false, "TYPE");
        public final static Property DetailType = new Property(6, String.class, "detailType", false, "DETAIL_TYPE");
        public final static Property Description = new Property(7, String.class, "description", false, "DESCRIPTION");
        public final static Property Photo = new Property(8, String.class, "photo", false, "PHOTO");
    }


    public AccountBeanDao(DaoConfig config) {
        super(config);
    }
    
    public AccountBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACCOUNT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"ACCOUNT\" TEXT," + // 1: account
                "\"NUMBER\" REAL NOT NULL ," + // 2: number
                "\"TIME\" INTEGER NOT NULL ," + // 3: time
                "\"ICON\" INTEGER NOT NULL ," + // 4: icon
                "\"TYPE\" INTEGER NOT NULL ," + // 5: type
                "\"DETAIL_TYPE\" TEXT," + // 6: detailType
                "\"DESCRIPTION\" TEXT," + // 7: description
                "\"PHOTO\" TEXT);"); // 8: photo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACCOUNT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AccountBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
        stmt.bindDouble(3, entity.getNumber());
        stmt.bindLong(4, entity.getTime());
        stmt.bindLong(5, entity.getIcon());
        stmt.bindLong(6, entity.getType());
 
        String detailType = entity.getDetailType();
        if (detailType != null) {
            stmt.bindString(7, detailType);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(8, description);
        }
 
        String photo = entity.getPhoto();
        if (photo != null) {
            stmt.bindString(9, photo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AccountBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
        stmt.bindDouble(3, entity.getNumber());
        stmt.bindLong(4, entity.getTime());
        stmt.bindLong(5, entity.getIcon());
        stmt.bindLong(6, entity.getType());
 
        String detailType = entity.getDetailType();
        if (detailType != null) {
            stmt.bindString(7, detailType);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(8, description);
        }
 
        String photo = entity.getPhoto();
        if (photo != null) {
            stmt.bindString(9, photo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public AccountBean readEntity(Cursor cursor, int offset) {
        AccountBean entity = new AccountBean( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // account
            cursor.getDouble(offset + 2), // number
            cursor.getLong(offset + 3), // time
            cursor.getInt(offset + 4), // icon
            cursor.getInt(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // detailType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // description
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // photo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AccountBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setAccount(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNumber(cursor.getDouble(offset + 2));
        entity.setTime(cursor.getLong(offset + 3));
        entity.setIcon(cursor.getInt(offset + 4));
        entity.setType(cursor.getInt(offset + 5));
        entity.setDetailType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDescription(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPhoto(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AccountBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AccountBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AccountBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
