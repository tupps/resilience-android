package au.com.dius.resilience.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import au.com.dius.resilience.Constants;

public abstract class AbstractSqlLiteRepository<T> extends SQLiteOpenHelper implements Repository<T> {

  protected static final int DATABASE_VERSION = 1;
  public static final String DB_NAME = "resilience.db";
  
  public static final String COL_ID = "_id";
  
  private static final String CREATE_DB_SQL = "CREATE TABLE " + Constants.TABLE_INCIDENT
                                            + " (" + COL_ID + " integer primary key autoincrement, "
                                            + Constants.COL_INCIDENT_NAME + " text, "
                                            + Constants.COL_INCIDENT_CATEGORY + " text not null, "
                                            + Constants.COL_INCIDENT_SUBCATEGORY + " text not null, "
                                            + Constants.COL_INCIDENT_IMPACT + " text not null, "
                                            + Constants.COL_INCIDENT_CREATION_DATE + " date not null, "
                                            + Constants.COL_INCIDENT_NOTE + " text);";
  
  public AbstractSqlLiteRepository(Context context) {
    super(context, DB_NAME, null, DATABASE_VERSION);
  }
  
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SqlLiteIncidentRepository.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", old data will be pulverised.");
    db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_INCIDENT);
    onCreate(db);
  }
  
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_DB_SQL);
  }
}
