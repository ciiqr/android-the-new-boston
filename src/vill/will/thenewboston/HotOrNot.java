package vill.will.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "_persons_name";
	public static final String KEY_HOTNESS = "_persons_hottness";

	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "tblPeople";
	private static final int DATABASE_VERSION = 1;

	private static final String[] COLUMNS = new String[]
	{ KEY_ROWID, KEY_NAME, KEY_HOTNESS };

	private DBHelper databaseHelper;
	private final Context ourContext;
	private SQLiteDatabase database;

	private static class DBHelper extends SQLiteOpenHelper
	{

		public DBHelper(Context context)
		{
			// Super
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// @formatter:off
			db.execSQL("CREATE TABLE " +   DATABASE_TABLE + " (" +
						   KEY_ROWID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						   KEY_NAME    + " TEXT NOT NULL, " +
						   KEY_HOTNESS + " TEXT NOT NULL);" );
			// @formatter:on
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// Remove Old Table
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			// Recreate it
			onCreate(db);
		}

	}

	public HotOrNot(Context c)
	{
		ourContext = c;
	}

	public HotOrNot open() throws SQLException
	{
		databaseHelper = new DBHelper(ourContext);

		database = databaseHelper.getWritableDatabase();

		return this;
	}

	public void close()
	{
		databaseHelper.close();
	}

	public long createEntry(String name, String hotness)
	{
		// Content Values
		ContentValues contentValues = new ContentValues();

		// Pass in Values

		contentValues.put(KEY_NAME, name);
		contentValues.put(KEY_HOTNESS, hotness);

		// Insert Data Into Database
		return database.insert(DATABASE_TABLE, null, contentValues);
	}

	public String getData()
	{
		Cursor cursor = database.query(DATABASE_TABLE, COLUMNS, null, null,
				null, null, null);

		String result = "";

		int iRow = cursor.getColumnIndex(KEY_ROWID);
		int iName = cursor.getColumnIndex(KEY_NAME);
		int iHotness = cursor.getColumnIndex(KEY_HOTNESS);

		// Each Row is a line in the string
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
		{
			result = result + cursor.getString(iRow) + " "
					+ cursor.getString(iName) + " "
					+ cursor.getString(iHotness) + '\n';
		}

		//
		return result;
	}

	// Used for
	// -- getName
	// -- getHotness
	private String getStringForRowIdAndColumn(long rowID, String column) throws SQLException
	{
		//
		Cursor c = database.query(DATABASE_TABLE, COLUMNS, KEY_ROWID + "="
				+ rowID, null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
			String name = c.getString(c.getColumnIndex(column));
			return name;
		}
		return null;
	}

	public String getName(long l) throws SQLException
	{
		// Get Name Where rowID is l
		return getStringForRowIdAndColumn(l, KEY_NAME);
	}

	public String getHotness(long l) throws SQLException
	{
		// Get Hotness Where rowID is l
		return getStringForRowIdAndColumn(l, KEY_HOTNESS);
	}

	public void updateEntry(long rowId, String newName, String newHotness) throws SQLException
	{
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, newName);
		cvUpdate.put(KEY_HOTNESS, newHotness);
		
		// Update Entry
		database.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + rowId, null);
	}

	public void deleteEntry(long rowId) throws SQLException
	{
		// TODO Auto-generated method stub
		database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null);
	}
}
